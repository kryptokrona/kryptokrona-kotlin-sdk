// Copyright (c) 2012-2017, The CryptoNote developers, The Bytecoin developers
// Copyright (c) 2014-2018, The Monero Project
// Copyright (c) 2018-2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include <memory>
#include <set>

#if defined(_MSC_VER)
#include <malloc.h>
#endif

#include "multisig.h"

namespace Crypto
{
    namespace Multisig
    {
        extern "C"
        {
#include "ed25519.h"
        }

        /* Checks if an arbitrary pod is a scalar */
        template<typename T> bool is_scalar(const T &key)
        {
            return !sc_check(reinterpret_cast<const unsigned char *>(key.data));
        }

        /* Base template for adding keys together byref */
        template<typename T> void addKeys(const T &a, const T &b, T &c)
        {
            /* Adding public keys (non-scalars) together requires
               using ge point based methods */
            if (!is_scalar(a))
            {
                ge_p3 b2;
                ge_p3 a2;
                ge_frombytes_vartime(&b2, reinterpret_cast<const unsigned char *>(b.data));
                ge_frombytes_vartime(&a2, reinterpret_cast<const unsigned char *>(a.data));
                ge_cached tmp2;
                ge_p3_to_cached(&tmp2, &b2);
                ge_p1p1 tmp3;
                ge_add(&tmp3, &a2, &tmp2);
                ge_p1p1_to_p3(&a2, &tmp3);
                ge_p3_tobytes(reinterpret_cast<unsigned char *>(c.data), &a2);
            }
            else
            {
                /* Adding private keys (scalars) is handled simply by
                   adding the scalars together */
                sc_add(
                    reinterpret_cast<unsigned char *>(&c),
                    reinterpret_cast<const unsigned char *>(&a),
                    reinterpret_cast<const unsigned char *>(&b));
            }
        };

        /* Helper method for adding an array of keys together and
           returning a singular key */
        template<typename T> T addKeys(const std::vector<T> &keys)
        {
            if (keys.size() == 0)
            {
                return T();
            }
            else if (keys.size() == 1)
            {
                return keys.front();
            }

            T result = keys.front();

            for (size_t i = 1; i < keys.size(); i++)
            {
                addKeys(result, keys.at(i), result);
            }

            return result;
        };

        /* Helper template for adding keys together starting
           with one particular key and adding the rest to that
           first key */
        template<typename T> T addKeys(const T &key, const std::vector<T> &keys)
        {
            /* Adds the first key to the start of the temporary vector */
            std::vector<T> result(keys.size() + 1, key);

            /* Copies the rest of the keys into the temporary vector */
            std::copy(keys.begin(), keys.end(), result.begin() + 1);

            /* Adds all of the keys together and returns the result */
            return addKeys(result);
        };

        /* Helper template used to deduplicate and sort the keys in
           a vector as we can cannot include duplicate keys in any
           multisig based math operations */
        template<typename T> std::vector<T> dedupeAndSortKeys(const std::vector<T> &keys)
        {
            auto cmp = [](const T a, const T b) { return memcmp(&a, &b, sizeof(a)) > 0; };

            std::set<T, decltype(cmp)> seen(keys.begin(), keys.end(), cmp);

            std::vector<T> result(seen.begin(), seen.end());

            return result;
        }

        /* Public Methods */

        /* This method calculates a private key that is based
           on multiplying our private key with another party's
           public key, hashing the result, and then converting it
           back to a scalar thus resulting in a new private key */
        bool calculate_multisig_private_key(
            const Crypto::PublicKey &pub,
            const Crypto::SecretKey &sec,
            Crypto::SecretKey &multisig)
        {
            /* If we didn't supply a scalar for the secret key,
               then none of this is going to work. */
            if (!is_scalar(sec))
            {
                return false;
            }
            Crypto::EllipticCurveScalar scalar;
            ge_p3 point;
            ge_p2 point2;
            ge_p1p1 point3;
            /* Load the public key as a point */
            if (ge_frombytes_vartime(&point, reinterpret_cast<const unsigned char *>(&pub)) != 0)
            {
                return false;
            }
            /* Multiply the private key by the public key point */
            ge_scalarmult(&point2, reinterpret_cast<const unsigned char *>(&sec), &point);
            /* Make sure the resultant point is a group of 8 */
            ge_mul8(&point3, &point2);
            /* convert the point back over */
            ge_p1p1_to_p2(&point2, &point3);
            /* dump the new point into bytes */
            ge_tobytes(reinterpret_cast<unsigned char *>(&scalar), &point2);

            /* hash the point bytes and sc_reduce32 it to get a
               new scalar */
            Crypto::hashToScalar(scalar.data, sizeof(scalar), scalar);

            /* convert the resultant scalar over to a private key type */
            multisig = Crypto::SecretKey(scalar.data);

            return true;
        }

        /* Helper method for the above that returns the
           resultant private key */
        Crypto::SecretKey calculate_multisig_private_key(const Crypto::PublicKey &pub, const Crypto::SecretKey &sec)
        {
            Crypto::SecretKey multisig;

            calculate_multisig_private_key(pub, sec, multisig);

            return multisig;
        }

        /* Calculates the multisig private keys using
           our private key multiple by each other
           parties public key */
        std::vector<Crypto::SecretKey> calculate_multisig_private_keys(
            const Crypto::SecretKey &ourPrivateSpendKey,
            const std::vector<Crypto::PublicKey> &publicKeys)
        {
            std::vector<Crypto::SecretKey> secretKeys;

            for (const auto key : publicKeys)
            {
                secretKeys.push_back(calculate_multisig_private_key(key, ourPrivateSpendKey));
            }

            return secretKeys;
        }

        /* Calculates a shared private key by adding the vector
           of private keys together */
        void calculate_shared_private_key(
            const std::vector<Crypto::SecretKey> &secretKeys,
            Crypto::SecretKey &sharedSecretKey)
        {
            /* Remove any duplicate keys from the keys that are being
               added together as we only use unique values */
            std::vector<Crypto::SecretKey> keys = dedupeAndSortKeys(secretKeys);

            sharedSecretKey = addKeys(keys);
        }

        /* Helper method that calculates a shared private key by adding the vector
           of private keys together and returing the result directly */
        Crypto::SecretKey calculate_shared_private_key(const std::vector<Crypto::SecretKey> &secretKeys)
        {
            Crypto::SecretKey secretKey;

            calculate_shared_private_key(secretKeys, secretKey);

            return secretKey;
        }

        /* Calculates a shared public key by adding the vector
           of public keys together and returning the result directly */
        void calculate_shared_public_key(
            const std::vector<Crypto::PublicKey> &publicKeys,
            Crypto::PublicKey &sharedPublicKey)
        {
            /* Remove any duplicate keys from the keys that are being
               added together as we only use unique values */
            std::vector<Crypto::PublicKey> keys = dedupeAndSortKeys(publicKeys);

            sharedPublicKey = addKeys(keys);
        }

        /* Helper method that calculates a shared public key by adding the vector
           of public keys together and returning the result directly */
        Crypto::PublicKey calculate_shared_public_key(const std::vector<Crypto::PublicKey> &publicKeys)
        {
            Crypto::PublicKey publicKey;

            calculate_shared_public_key(publicKeys, publicKey);

            return publicKey;
        }

        /* Generates a partial signing key using the pre-prepared ring signature
           and our private spend key and returns the resultant signing key that is
           okay to distribute between the other multisig participants for
           completing a ring signature */
        Crypto::SecretKey
            generate_partial_signing_key(const Crypto::Signature &signature, const Crypto::SecretKey &privateSpendKey)
        {
            /* This is kind of a hack because we we just need the
               first 32-bytes of the existing signature that we will
               "sign" via the sc_mul call below */
            const Crypto::SecretKey signatureAsKey(signature.data);

            Crypto::SecretKey result;

            /* Multiplies the private key against the first 32-bytes of
               the signature to come up with the scalar that is part
               of the full signature for the given signature */
            sc_mul(
                reinterpret_cast<unsigned char *>(&result),
                reinterpret_cast<const unsigned char *>(&signatureAsKey),
                reinterpret_cast<const unsigned char *>(&privateSpendKey));

            return result;
        }

        /* Restores a key image using the public emphemeral of a spent output (input)
           the derivation scalar, and the full list of partial key images and
           returns the resultant */
        Crypto::KeyImage restore_key_image(
            const Crypto::PublicKey &publicEphemeral,
            const Crypto::EllipticCurveScalar &derivationScalar,
            const std::vector<Crypto::KeyImage> &partialKeyImages)
        {
            Crypto::SecretKey derivation(derivationScalar.data);

            Crypto::KeyImage baseKeyImage;

            /* This uses the standard generate_key_image method provided
               and used elsewhere */
            Crypto::generate_key_image(publicEphemeral, derivation, baseKeyImage);

            /* Remove any duplicate keys from the keys that are being
               added together as we only use unique values */
            std::vector<Crypto::KeyImage> keys = dedupeAndSortKeys(partialKeyImages);

            return addKeys(baseKeyImage, keys);
        }

        /* Helper method for restoring a key image using the Key Derivation
           and the output index instead of the derivation scalar */
        Crypto::KeyImage restore_key_image(
            const Crypto::PublicKey &publicEphemeral,
            const Crypto::KeyDerivation &derivation,
            const size_t output_index,
            const std::vector<Crypto::KeyImage> &partialKeyImages)
        {
            Crypto::EllipticCurveScalar derivationScalar;

            /* Converts our derivation to the scalar we need using then
               existing crypto primitives */
            Crypto::derivation_to_scalar(derivation, output_index, derivationScalar);

            return restore_key_image(publicEphemeral, derivationScalar, partialKeyImages);
        }

        /* Restores a full set of ring signatures using the derivation scalar,
           all of the partial signing keys, the realOutput index (the incomplete
           signature in the set of ring signatures, and the previously generated k
           (random scalar) used in the creation of the realOutput signature
           and returns the full signature set byref */
        bool restore_ring_signatures(
            const Crypto::EllipticCurveScalar &derivationScalar,
            const std::vector<Crypto::SecretKey> &partialSigningKeys,
            const uint64_t realOutput,
            const Crypto::EllipticCurveScalar &k,
            std::vector<Crypto::Signature> &signatures)
        {
            /* Verify that k and the derivation scalar is indeed
               a scalar or fail out of the method early */
            if (!is_scalar(k) || !is_scalar(derivationScalar))
            {
                return false;
            }

            Crypto::SecretKey derivation(derivationScalar.data);

            Crypto::SecretKey partialKey = generate_partial_signing_key(signatures[realOutput], derivation);

            /* If the returned partial key that is the base for the
               signature is not a scalar, the rest of the math
               will fail as it is reliant on scalars */
            if (!is_scalar(partialKey))
            {
                return false;
            }

            /* Remove any duplicate keys from the keys that are being
               added together as we only use unique values */
            std::vector<Crypto::SecretKey> keys = dedupeAndSortKeys(partialSigningKeys);

            /* Verify that all of the deduped signing keys are scalars
               as we expect otherwise the math below will fail */
            for (const auto key : partialSigningKeys)
            {
                if (!is_scalar(key))
                {
                    return false;
                }
            }

            Crypto::SecretKey derivedSecretKey = addKeys(partialKey, keys);

            /* Subtract the result of the cumulation of the partial signing
               keys created by the number of parties from the k value from
               the prepared ring signature to arrive at the final value to
               complete the ring signature */
            sc_sub(
                reinterpret_cast<unsigned char *>(&signatures[realOutput]) + 32,
                reinterpret_cast<const unsigned char *>(&k),
                reinterpret_cast<const unsigned char *>(&derivedSecretKey));

            return true;
        }

        /* Helper method for restoring ring signatures by using the
           Key Derivation and the output index instead of the
           derivation scalar */
        bool restore_ring_signatures(
            const Crypto::KeyDerivation &derivation,
            const size_t output_index,
            const std::vector<Crypto::SecretKey> &partialSigningKeys,
            const uint64_t realOutput,
            const Crypto::EllipticCurveScalar &k,
            std::vector<Crypto::Signature> &signatures)
        {
            Crypto::EllipticCurveScalar derivationScalar;

            Crypto::derivation_to_scalar(derivation, output_index, derivationScalar);

            return restore_ring_signatures(derivationScalar, partialSigningKeys, realOutput, k, signatures);
        }

        /* Simple helper for helping to calculate the number of
           multisig public key exchange rounds that must be completed
           in a M:N (or N-1:N) signing wallet scheme */
        uint32_t rounds_required(const uint32_t participants, const uint32_t threshold)
        {
            return participants - threshold + 1;
        }
    } // namespace Multisig
} // namespace Crypto
