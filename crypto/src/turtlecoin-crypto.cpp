// Copyright (c) 2018-2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include <StringTools.h>
#include <string.h>
#include <turtlecoin-crypto.h>

#ifndef NO_CRYPTO_EXPORTS
#ifdef _WIN32
#include <windows.h>
#ifdef _MANAGED
#pragma managed(push, off)
#endif

EXPORTDLL bool DllMain(
    HMODULE /*hModule*/,
    DWORD ul_reason_for_call,
    LPVOID /*lpReserved*/
)
{
    switch (ul_reason_for_call)
    {
        case DLL_PROCESS_ATTACH:
        case DLL_THREAD_ATTACH:
        case DLL_THREAD_DETACH:
        case DLL_PROCESS_DETACH:
            break;
    }
    return true;
}

#ifdef _MANAGED
#pragma managed(pop)
#endif
#endif
#endif

namespace Core
{
    template<typename T> void toTypedVector(const std::vector<std::string> &stringVector, std::vector<T> &result)
    {
        result.clear();

        for (const auto element : stringVector)
        {
            T value = T();

            Common::podFromHex(element, value);

            result.push_back(value);
        }
    }

    template<typename T> void toStringVector(const std::vector<T> &typedVector, std::vector<std::string> &result)
    {
        result.clear();

        for (const auto element : typedVector)
        {
            if (sizeof(element) == sizeof(Crypto::Signature))
            {
                result.push_back(Common::toHex(&element, sizeof(element)));
            }
            else
            {
                result.push_back(Common::podToHex(element));
            }
        }
    }

    inline Crypto::BinaryArray toBinaryArray(const std::string input)
    {
        return Common::fromHex(input);
    }

    /* Hashing Methods */
    std::string Cryptography::cn_fast_hash(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_fast_hash(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_slow_hash_v0(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_slow_hash_v0(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_slow_hash_v1(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_slow_hash_v1(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_slow_hash_v2(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_slow_hash_v2(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_lite_slow_hash_v0(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_lite_slow_hash_v0(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_lite_slow_hash_v1(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_lite_slow_hash_v1(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_lite_slow_hash_v2(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_lite_slow_hash_v2(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_dark_slow_hash_v0(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_dark_slow_hash_v0(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_dark_slow_hash_v1(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_dark_slow_hash_v1(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_dark_slow_hash_v2(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_dark_slow_hash_v2(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_dark_lite_slow_hash_v0(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_dark_lite_slow_hash_v0(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_dark_lite_slow_hash_v1(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_dark_lite_slow_hash_v1(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_dark_lite_slow_hash_v2(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_dark_lite_slow_hash_v2(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_turtle_slow_hash_v0(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_turtle_slow_hash_v0(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_turtle_slow_hash_v1(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_turtle_slow_hash_v1(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_turtle_slow_hash_v2(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_turtle_slow_hash_v2(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_turtle_lite_slow_hash_v0(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_turtle_lite_slow_hash_v0(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_turtle_lite_slow_hash_v1(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_turtle_lite_slow_hash_v1(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_turtle_lite_slow_hash_v2(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_turtle_lite_slow_hash_v2(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_soft_shell_slow_hash_v0(const std::string input, const uint64_t height)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_soft_shell_slow_hash_v0(data.data(), data.size(), hash, height);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_soft_shell_slow_hash_v1(const std::string input, const uint64_t height)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_soft_shell_slow_hash_v1(data.data(), data.size(), hash, height);

        return Common::podToHex(hash);
    }

    std::string Cryptography::cn_soft_shell_slow_hash_v2(const std::string input, const uint64_t height)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::cn_soft_shell_slow_hash_v2(data.data(), data.size(), hash, height);

        return Common::podToHex(hash);
    }

    std::string Cryptography::chukwa_slow_hash_base(
        const std::string input,
        const uint32_t iterations,
        const uint32_t memory,
        const uint32_t threads)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::chukwa_slow_hash_base(data.data(), data.size(), hash, iterations, memory, threads);

        return Common::podToHex(hash);
    }

    std::string Cryptography::chukwa_slow_hash_v1(const std::string input)
    {
        Crypto::Hash hash = Crypto::Hash();

        Crypto::BinaryArray data = toBinaryArray(input);

        Crypto::chukwa_slow_hash_v1(data.data(), data.size(), hash);

        return Common::podToHex(hash);
    }

    std::string Cryptography::chukwa_slow_hash_v2(const std::string input)
        {
            Crypto::Hash hash = Crypto::Hash();

            Crypto::BinaryArray data = toBinaryArray(input);

            Crypto::chukwa_slow_hash_v2(data.data(), data.size(), hash);

            return Common::podToHex(hash);
        }

    uint32_t Cryptography::tree_depth(const uint32_t count)
    {
        return Crypto::tree_depth(count);
    }

    std::string Cryptography::tree_hash(const std::vector<std::string> hashes)
    {
        std::vector<Crypto::Hash> treeHashes;

        toTypedVector(hashes, treeHashes);

        Crypto::Hash treeHash = Crypto::Hash();

        Crypto::tree_hash(treeHashes.data(), treeHashes.size(), treeHash);

        return Common::podToHex(treeHash);
    }

    std::vector<std::string> Cryptography::tree_branch(const std::vector<std::string> hashes)
    {
        std::vector<Crypto::Hash> l_hashes;

        toTypedVector(hashes, l_hashes);

        std::vector<Crypto::Hash> l_branches(tree_depth(l_hashes.size()));

        Crypto::tree_branch(l_hashes.data(), l_hashes.size(), l_branches.data());

        std::vector<std::string> branches;

        toStringVector(l_branches, branches);

        return branches;
    }

    std::string Cryptography::tree_hash_from_branch(
        const std::vector<std::string> branches,
        const std::string leaf,
        const std::string path)
    {
        std::vector<Crypto::Hash> l_branches;

        toTypedVector(branches, l_branches);

        Crypto::Hash l_leaf = Crypto::Hash();

        Common::podFromHex(leaf, l_leaf);

        Crypto::Hash l_hash = Crypto::Hash();

        if (path != "0")
        {
            Crypto::Hash l_path = Crypto::Hash();

            Common::podFromHex(path, l_path);

            Crypto::tree_hash_from_branch(l_branches.data(), branches.size(), l_leaf, l_path.data, l_hash);
        }
        else
        {
            Crypto::tree_hash_from_branch(l_branches.data(), branches.size(), l_leaf, 0, l_hash);
        }

        return Common::podToHex(l_hash);
    }

    /* Crypto Methods */
    bool Cryptography::generateRingSignatures(
        const std::string prefixHash,
        const std::string keyImage,
        const std::vector<std::string> publicKeys,
        const std::string transactionSecretKey,
        const uint64_t realOutput,
        std::vector<std::string> &signatures)
    {
        Crypto::Hash l_prefixHash = Crypto::Hash();

        Common::podFromHex(prefixHash, l_prefixHash);

        Crypto::KeyImage l_keyImage = Crypto::KeyImage();

        Common::podFromHex(keyImage, l_keyImage);

        std::vector<Crypto::PublicKey> l_publicKeys;

        toTypedVector(publicKeys, l_publicKeys);

        Crypto::SecretKey l_transactionSecretKey;

        Common::podFromHex(transactionSecretKey, l_transactionSecretKey);

        std::vector<Crypto::Signature> l_signatures;

        bool success = Crypto::crypto_ops::generateRingSignatures(
            l_prefixHash, l_keyImage, l_publicKeys, l_transactionSecretKey, realOutput, l_signatures);

        if (success)
        {
            toStringVector(l_signatures, signatures);
        }

        return success;
    }

    bool Cryptography::checkRingSignature(
        const std::string prefixHash,
        const std::string keyImage,
        const std::vector<std::string> publicKeys,
        const std::vector<std::string> signatures)
    {
        Crypto::Hash l_prefixHash = Crypto::Hash();

        Common::podFromHex(prefixHash, l_prefixHash);

        Crypto::KeyImage l_keyImage = Crypto::KeyImage();

        Common::podFromHex(keyImage, l_keyImage);

        std::vector<Crypto::PublicKey> l_publicKeys;

        toTypedVector(publicKeys, l_publicKeys);

        std::vector<Crypto::Signature> l_signatures;

        toTypedVector(signatures, l_signatures);

        return Crypto::crypto_ops::checkRingSignature(l_prefixHash, l_keyImage, l_publicKeys, l_signatures);
    }

    std::string Cryptography::generatePrivateViewKeyFromPrivateSpendKey(const std::string privateSpendKey)
    {
        Crypto::SecretKey l_privateSpendKey = Crypto::SecretKey();

        Common::podFromHex(privateSpendKey, l_privateSpendKey);

        Crypto::SecretKey privateViewKey = Crypto::SecretKey();

        Crypto::crypto_ops::generateViewFromSpend(l_privateSpendKey, privateViewKey);

        return Common::podToHex(privateViewKey);
    }

    void Cryptography::generateViewKeysFromPrivateSpendKey(
        const std::string privateSpendKey,
        std::string &privateViewKey,
        std::string &publicViewKey)
    {
        Crypto::SecretKey l_privateSpendKey = Crypto::SecretKey();

        Common::podFromHex(privateSpendKey, l_privateSpendKey);

        Crypto::SecretKey l_privateViewKey = Crypto::SecretKey();

        Crypto::PublicKey l_publicViewKey = Crypto::PublicKey();

        Crypto::crypto_ops::generateViewFromSpend(l_privateSpendKey, l_privateViewKey, l_publicViewKey);

        privateViewKey = Common::podToHex(l_privateViewKey);

        publicViewKey = Common::podToHex(l_publicViewKey);
    }

    void Cryptography::generateKeys(std::string &privateKey, std::string &publicKey)
    {
        Crypto::SecretKey l_privateKey = Crypto::SecretKey();

        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        Crypto::generate_keys(l_publicKey, l_privateKey);

        privateKey = Common::podToHex(l_privateKey);

        publicKey = Common::podToHex(l_publicKey);
    }

    bool Cryptography::checkKey(const std::string publicKey)
    {
        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        Common::podFromHex(publicKey, l_publicKey);

        return Crypto::check_key(l_publicKey);
    }

    bool Cryptography::secretKeyToPublicKey(const std::string privateKey, std::string &publicKey)
    {
        Crypto::SecretKey l_privateKey = Crypto::SecretKey();

        Common::podFromHex(privateKey, l_privateKey);

        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        bool success = Crypto::secret_key_to_public_key(l_privateKey, l_publicKey);

        if (success)
        {
            publicKey = Common::podToHex(l_publicKey);
        }

        return success;
    }

    bool Cryptography::generateKeyDerivation(
        const std::string publicKey,
        const std::string privateKey,
        std::string &derivation)
    {
        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        Common::podFromHex(publicKey, l_publicKey);

        Crypto::SecretKey l_privateKey = Crypto::SecretKey();

        Common::podFromHex(privateKey, l_privateKey);

        Crypto::KeyDerivation l_derivation = Crypto::KeyDerivation();

        bool success = Crypto::generate_key_derivation(l_publicKey, l_privateKey, l_derivation);

        if (success)
        {
            derivation = Common::podToHex(l_derivation);
        }

        return success;
    }

    std::string Cryptography::generateKeyDerivationScalar(
        const std::string publicKey,
        const std::string secretKey,
        const uint64_t outputIndex)
    {
        std::string scalar = std::string();

        if (generateKeyDerivation(publicKey, secretKey, scalar))
        {
            scalar = derivationToScalar(scalar, outputIndex);
        }

        return scalar;
    }

    std::string Cryptography::derivationToScalar(const std::string derivation, const uint64_t outputIndex)
    {
        Crypto::KeyDerivation l_derivation = Crypto::KeyDerivation();

        Common::podFromHex(derivation, l_derivation);

        Crypto::EllipticCurveScalar derivationScalar;

        Crypto::derivation_to_scalar(l_derivation, outputIndex, derivationScalar);

        return Common::podToHex(derivationScalar);
    }

    bool Cryptography::derivePublicKey(
        const std::string &derivation,
        const uint64_t outputIndex,
        const std::string &publicKey,
        std::string &derivedKey)
    {
        Crypto::KeyDerivation l_derivation = Crypto::KeyDerivation();

        Common::podFromHex(derivation, l_derivation);

        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        Common::podFromHex(publicKey, l_publicKey);

        Crypto::PublicKey l_derivedKey = Crypto::PublicKey();

        bool success = Crypto::derive_public_key(l_derivation, outputIndex, l_publicKey, l_derivedKey);

        if (success)
        {
            derivedKey = Common::podToHex(l_derivedKey);
        }

        return success;
    }

    bool Cryptography::derivePublicKey(
        const std::string &derivationScalar,
        const std::string &publicKey,
        std::string &derivedKey)
    {
        Crypto::EllipticCurveScalar l_derivationScalar = Crypto::EllipticCurveScalar();

        Common::podFromHex(derivationScalar, l_derivationScalar);

        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        Common::podFromHex(publicKey, l_publicKey);

        Crypto::PublicKey l_derivedKey = Crypto::PublicKey();

        bool success = Crypto::derive_public_key(l_derivationScalar, l_publicKey, l_derivedKey);

        if (success)
        {
            derivedKey = Common::podToHex(l_derivedKey);
        }

        return success;
    }

    std::string Cryptography::deriveSecretKey(
        const std::string &derivation,
        const uint64_t outputIndex,
        const std::string &privateKey)
    {
        Crypto::KeyDerivation l_derivation = Crypto::KeyDerivation();

        Common::podFromHex(derivation, l_derivation);

        Crypto::SecretKey l_privateKey = Crypto::SecretKey();

        Common::podFromHex(privateKey, l_privateKey);

        Crypto::SecretKey l_derivedKey = Crypto::SecretKey();

        Crypto::derive_secret_key(l_derivation, outputIndex, l_privateKey, l_derivedKey);

        return Common::podToHex(l_derivedKey);
    }

    std::string Cryptography::deriveSecretKey(const std::string &derivationScalar, const std::string &privateKey)
    {
        Crypto::EllipticCurveScalar l_derivationScalar = Crypto::EllipticCurveScalar();

        Common::podFromHex(derivationScalar, l_derivationScalar);

        Crypto::SecretKey l_privateKey = Crypto::SecretKey();

        Common::podFromHex(privateKey, l_privateKey);

        Crypto::SecretKey l_derivedKey = Crypto::SecretKey();

        Crypto::derive_secret_key(l_derivationScalar, l_privateKey, l_derivedKey);

        return Common::podToHex(l_derivedKey);
    }

    bool Cryptography::underivePublicKey(
        const std::string derivation,
        const uint64_t outputIndex,
        const std::string derivedKey,
        std::string &publicKey)
    {
        Crypto::KeyDerivation l_derivation = Crypto::KeyDerivation();

        Common::podFromHex(derivation, l_derivation);

        Crypto::PublicKey l_derivedKey = Crypto::PublicKey();

        Common::podFromHex(derivedKey, l_derivedKey);

        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        bool success = Crypto::underive_public_key(l_derivation, outputIndex, l_derivedKey, l_publicKey);

        if (success)
        {
            publicKey = Common::podToHex(l_publicKey);
        }

        return success;
    }

    std::string Cryptography::generateSignature(
        const std::string prefixHash,
        const std::string publicKey,
        const std::string privateKey)
    {
        Crypto::Hash l_prefixHash = Crypto::Hash();

        Common::podFromHex(prefixHash, l_prefixHash);

        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        Common::podFromHex(publicKey, l_publicKey);

        Crypto::SecretKey l_privateKey = Crypto::SecretKey();

        Common::podFromHex(privateKey, l_privateKey);

        Crypto::Signature l_signature = Crypto::Signature();

        Crypto::generate_signature(l_prefixHash, l_publicKey, l_privateKey, l_signature);

        return Common::podToHex(l_signature);
    }

    bool Cryptography::checkSignature(
        const std::string prefixHash,
        const std::string publicKey,
        const std::string signature)
    {
        Crypto::Hash l_prefixHash = Crypto::Hash();

        Common::podFromHex(prefixHash, l_prefixHash);

        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        Common::podFromHex(publicKey, l_publicKey);

        Crypto::Signature l_signature = Crypto::Signature();

        Common::podFromHex(signature, l_signature);

        return Crypto::check_signature(l_prefixHash, l_publicKey, l_signature);
    }

    std::string Cryptography::generateKeyImage(const std::string publicKey, const std::string privateKey)
    {
        Crypto::PublicKey l_publicKey = Crypto::PublicKey();

        Common::podFromHex(publicKey, l_publicKey);

        Crypto::SecretKey l_privateKey = Crypto::SecretKey();

        Common::podFromHex(privateKey, l_privateKey);

        Crypto::KeyImage l_keyImage = Crypto::KeyImage();

        Crypto::generate_key_image(l_publicKey, l_privateKey, l_keyImage);

        return Common::podToHex(l_keyImage);
    }

    std::string Cryptography::scalarmultKey(const std::string keyImageA, const std::string keyImageB)
    {
        Crypto::KeyImage l_keyImageA = Crypto::KeyImage();

        Common::podFromHex(keyImageA, l_keyImageA);

        Crypto::KeyImage l_keyImageB = Crypto::KeyImage();

        Common::podFromHex(keyImageB, l_keyImageB);

        Crypto::KeyImage l_keyImage = Crypto::scalarmultKey(l_keyImageA, l_keyImageB);

        return Common::podToHex(l_keyImage);
    }

    std::string Cryptography::hashToEllipticCurve(const std::string hash)
    {
        Crypto::Hash l_hash = Crypto::Hash();

        Common::podFromHex(hash, l_hash);

        Crypto::PublicKey l_ellipticCurve = Crypto::PublicKey();

        Crypto::hash_data_to_ec(l_hash.data, sizeof(l_hash.data), l_ellipticCurve);

        return Common::podToHex(l_ellipticCurve);
    }

    std::string Cryptography::scReduce32(const std::string data)
    {
        Crypto::EllipticCurveScalar l_scalar;

        Common::podFromHex(data, l_scalar);

        Crypto::scReduce32(l_scalar);

        return Common::podToHex(l_scalar);
    }

    std::string Cryptography::hashToScalar(const std::string hash)
    {
        Crypto::BinaryArray l_hash = toBinaryArray(hash);

        Crypto::EllipticCurveScalar l_scalar;

        Crypto::hashToScalar(l_hash.data(), l_hash.size(), l_scalar);

        return Common::podToHex(l_scalar);
    }

    bool Cryptography::generateDeterministicSubwalletKeys(
        const std::string basePrivateKey,
        const uint64_t walletIndex,
        std::string &privateKey,
        std::string &publicKey)
    {
        Crypto::SecretKey l_basePrivateKey;

        Common::podFromHex(basePrivateKey, l_basePrivateKey);

        Crypto::SecretKey l_privateKey;

        Crypto::PublicKey l_publicKey;

        if (Crypto::generate_deterministic_subwallet_keys(l_basePrivateKey, walletIndex, l_privateKey, l_publicKey))
        {
            privateKey = Common::podToHex(l_privateKey);

            publicKey = Common::podToHex(l_publicKey);

            return true;
        }

        return false;
    }

    std::string Cryptography::restoreKeyImage(
        const std::string &publicEphemeral,
        const std::string &derivation,
        const size_t output_index,
        const std::vector<std::string> &partialKeyImages)
    {
        Crypto::PublicKey l_publicEphemeral;

        Common::podFromHex(publicEphemeral, l_publicEphemeral);

        Crypto::KeyDerivation l_derivation;

        Common::podFromHex(derivation, l_derivation);

        std::vector<Crypto::KeyImage> l_partialKeyImages;

        toTypedVector(partialKeyImages, l_partialKeyImages);

        Crypto::KeyImage l_keyImage =
            Crypto::Multisig::restore_key_image(l_publicEphemeral, l_derivation, output_index, l_partialKeyImages);

        return Common::podToHex(l_keyImage);
    }

    bool Cryptography::restoreRingSignatures(
        const std::string &derivation,
        const size_t output_index,
        const std::vector<std::string> &partialSigningKeys,
        const uint64_t realOutput,
        const std::string &k,
        std::vector<std::string> &signatures)
    {
        Crypto::KeyDerivation l_derivation;

        Common::podFromHex(derivation, l_derivation);

        std::vector<Crypto::SecretKey> l_partialSigningKeys;

        toTypedVector(partialSigningKeys, l_partialSigningKeys);

        Crypto::EllipticCurveScalar l_k;

        Common::podFromHex(k, l_k);

        std::vector<Crypto::Signature> l_signatures;

        toTypedVector(signatures, l_signatures);

        const auto success = Crypto::Multisig::restore_ring_signatures(
            l_derivation, output_index, l_partialSigningKeys, realOutput, l_k, l_signatures);

        if (success)
        {
            toStringVector(l_signatures, signatures);
        }

        return success;
    }

    std::string
        Cryptography::generatePartialSigningKey(const std::string &signature, const std::string &privateSpendKey)
    {
        Crypto::Signature l_signature;

        Common::podFromHex(signature, l_signature);

        Crypto::SecretKey l_privateSpendKey;

        Common::podFromHex(privateSpendKey, l_privateSpendKey);

        Crypto::SecretKey l_key = Crypto::Multisig::generate_partial_signing_key(l_signature, l_privateSpendKey);

        return Common::podToHex(l_key);
    }

    bool Cryptography::prepareRingSignatures(
        const std::string prefixHash,
        const std::string keyImage,
        const std::vector<std::string> publicKeys,
        const uint64_t realOutput,
        std::vector<std::string> &signatures,
        std::string &k)
    {
        Crypto::Hash l_prefixHash;

        Common::podFromHex(prefixHash, l_prefixHash);

        Crypto::KeyImage l_keyImage;

        Common::podFromHex(keyImage, l_keyImage);

        std::vector<Crypto::PublicKey> l_publicKeys;

        toTypedVector(publicKeys, l_publicKeys);

        std::vector<Crypto::Signature> l_signatures;

        Crypto::EllipticCurveScalar l_k;

        const auto success = Crypto::crypto_ops::prepareRingSignatures(
            l_prefixHash, l_keyImage, l_publicKeys, realOutput, l_signatures, l_k);

        if (success)
        {
            toStringVector(l_signatures, signatures);

            k = Common::podToHex(l_k);
        }

        return success;
    }

    bool Cryptography::prepareRingSignatures(
        const std::string prefixHash,
        const std::string keyImage,
        const std::vector<std::string> publicKeys,
        const uint64_t realOutput,
        const std::string k,
        std::vector<std::string> &signatures)
    {
        Crypto::Hash l_prefixHash;

        Common::podFromHex(prefixHash, l_prefixHash);

        Crypto::KeyImage l_keyImage;

        Common::podFromHex(keyImage, l_keyImage);

        std::vector<Crypto::PublicKey> l_publicKeys;

        toTypedVector(publicKeys, l_publicKeys);

        std::vector<Crypto::Signature> l_signatures;

        Crypto::EllipticCurveScalar l_k;

        Common::podFromHex(k, l_k);

        const auto success = Crypto::crypto_ops::prepareRingSignatures(
            l_prefixHash, l_keyImage, l_publicKeys, realOutput, l_k, l_signatures);

        if (success)
        {
            toStringVector(l_signatures, signatures);
        }

        return success;
    }

    bool Cryptography::completeRingSignatures(
        const std::string transactionSecretKey,
        const uint64_t realOutput,
        const std::string &k,
        std::vector<std::string> &signatures)
    {
        Crypto::SecretKey l_transactionSecretKey;

        Common::podFromHex(transactionSecretKey, l_transactionSecretKey);

        Crypto::EllipticCurveScalar l_k;

        Common::podFromHex(k, l_k);

        std::vector<Crypto::Signature> l_signatures;

        toTypedVector(signatures, l_signatures);

        const auto success =
            Crypto::crypto_ops::completeRingSignatures(l_transactionSecretKey, realOutput, l_k, l_signatures);

        if (success)
        {
            toStringVector(l_signatures, signatures);
        }

        return success;
    }

    std::vector<std::string> Cryptography::calculateMultisigPrivateKeys(
        const std::string &ourPrivateSpendKey,
        const std::vector<std::string> &publicKeys)
    {
        Crypto::SecretKey l_ourPrivateSpendKey;

        Common::podFromHex(ourPrivateSpendKey, l_ourPrivateSpendKey);

        std::vector<Crypto::PublicKey> l_publicKeys;

        toTypedVector(publicKeys, l_publicKeys);

        std::vector<Crypto::SecretKey> l_multisigKeys =
            Crypto::Multisig::calculate_multisig_private_keys(l_ourPrivateSpendKey, l_publicKeys);

        std::vector<std::string> multisigKeys;

        toStringVector(l_multisigKeys, multisigKeys);

        return multisigKeys;
    }

    std::string Cryptography::calculateSharedPrivateKey(const std::vector<std::string> &secretKeys)
    {
        std::vector<Crypto::SecretKey> l_secretKeys;

        toTypedVector(secretKeys, l_secretKeys);

        Crypto::SecretKey sharedPrivateKey = Crypto::Multisig::calculate_shared_private_key(l_secretKeys);

        return Common::podToHex(sharedPrivateKey);
    }

    std::string Cryptography::calculateSharedPublicKey(const std::vector<std::string> &publicKeys)
    {
        std::vector<Crypto::PublicKey> l_publicKeys;

        toTypedVector(publicKeys, l_publicKeys);

        Crypto::PublicKey sharedPublicKey = Crypto::Multisig::calculate_shared_public_key(l_publicKeys);

        return Common::podToHex(sharedPublicKey);
    }
} // namespace Core

inline void tree_hash(const char *hashes, const uint64_t hashesLength, char *&hash)
{
    const std::string *hashesBuffer = reinterpret_cast<const std::string *>(hashes);

    std::vector<std::string> l_hashes(hashesBuffer, hashesBuffer + hashesLength);

    std::string result = Core::Cryptography::tree_hash(l_hashes);

    hash = strdup(result.c_str());
}

inline void tree_branch(const char *hashes, const uint64_t hashesLength, char *&branch)
{
    const std::string *hashesBuffer = reinterpret_cast<const std::string *>(hashes);

    std::vector<std::string> l_hashes(hashesBuffer, hashesBuffer + hashesLength);

    std::vector<std::string> l_branch = Core::Cryptography::tree_branch(l_hashes);

    branch = reinterpret_cast<char *>(l_branch.data());
}

inline void tree_hash_from_branch(
    const char *branches,
    const uint64_t branchesLength,
    const char *leaf,
    const char *path,
    char *&hash)
{
    const std::string *branchesBuffer = reinterpret_cast<const std::string *>(branches);

    std::vector<std::string> l_branches(branchesBuffer, branchesBuffer + branchesLength);

    std::string l_hash = Core::Cryptography::tree_hash_from_branch(l_branches, leaf, path);

    hash = strdup(l_hash.c_str());
}

inline int generateRingSignatures(
    const char *prefixHash,
    const char *keyImage,
    const char *publicKeys,
    uint64_t publicKeysLength,
    const char *transactionSecretKey,
    const uint64_t realOutput,
    char *&signatures)
{
    const std::string *publicKeysBuffer = reinterpret_cast<const std::string *>(publicKeys);

    std::vector<std::string> l_publicKeys(publicKeysBuffer, publicKeysBuffer + publicKeysLength);

    std::vector<std::string> l_signatures;

    bool success = Core::Cryptography::generateRingSignatures(
        prefixHash, keyImage, l_publicKeys, transactionSecretKey, realOutput, l_signatures);

    if (success)
    {
        signatures = reinterpret_cast<char *>(l_signatures.data());
    }

    return success;
}

inline bool checkRingSignature(
    const char *prefixHash,
    const char *keyImage,
    const char *publicKeys,
    const uint64_t publicKeysLength,
    const char *signatures,
    const uint64_t signaturesLength)
{
    const std::string *publicKeysBuffer = reinterpret_cast<const std::string *>(publicKeys);

    std::vector<std::string> l_publicKeys(publicKeysBuffer, publicKeysBuffer + publicKeysLength);

    const std::string *signaturesBuffer = reinterpret_cast<const std::string *>(signatures);

    std::vector<std::string> l_signatures(signaturesBuffer, signaturesBuffer + signaturesLength);

    return Core::Cryptography::checkRingSignature(prefixHash, keyImage, l_publicKeys, l_signatures);
}

inline void generateViewKeysFromPrivateSpendKey(const char *privateSpendKey, char *&privateKey, char *&publicKey)
{
    std::string l_privateKey;

    std::string l_publicKey;

    Core::Cryptography::generateViewKeysFromPrivateSpendKey(privateSpendKey, l_privateKey, l_publicKey);

    privateKey = strdup(l_privateKey.c_str());

    publicKey = strdup(l_publicKey.c_str());
}

inline void generateKeys(char *&privateKey, char *&publicKey)
{
    std::string l_privateKey;

    std::string l_publicKey;

    Core::Cryptography::generateKeys(l_privateKey, l_publicKey);

    privateKey = strdup(l_privateKey.c_str());

    publicKey = strdup(l_publicKey.c_str());
}

inline int secretKeyToPublicKey(const char *privateKey, char *&publicKey)
{
    std::string l_publicKey;

    bool success = Core::Cryptography::secretKeyToPublicKey(privateKey, l_publicKey);

    publicKey = strdup(l_publicKey.c_str());

    return success;
}

inline int generateKeyDerivation(const char *publicKey, const char *privateKey, char *&derivation)
{
    std::string l_derivation;

    bool success = Core::Cryptography::generateKeyDerivation(publicKey, privateKey, l_derivation);

    derivation = strdup(l_derivation.c_str());

    return success;
}

inline int
    derivePublicKey(const char *derivation, const uint64_t outputIndex, const char *publicKey, char *&outPublicKey)
{
    std::string l_outPublicKey;

    bool success = Core::Cryptography::derivePublicKey(derivation, outputIndex, publicKey, l_outPublicKey);

    outPublicKey = strdup(l_outPublicKey.c_str());

    return success;
}

inline int
    underivePublicKey(const char *derivation, const uint64_t outputIndex, const char *derivedKey, char *&publicKey)
{
    std::string l_publicKey;

    bool success = Core::Cryptography::underivePublicKey(derivation, outputIndex, derivedKey, l_publicKey);

    publicKey = strdup(l_publicKey.c_str());

    return success;
}

inline bool generateDeterministicSubwalletKeys(
    const char *basePrivateKey,
    const uint64_t walletIndex,
    char *&privateKey,
    char *&publicKey)
{
    std::string l_privateKey;

    std::string l_publicKey;

    if (Core::Cryptography::generateDeterministicSubwalletKeys(basePrivateKey, walletIndex, l_privateKey, l_publicKey))
    {
        privateKey = strdup(l_privateKey.c_str());

        publicKey = strdup(l_publicKey.c_str());

        return true;
    }

    return false;
}

inline int completeRingSignatures(
    const char *transactionSecretKey,
    const uint64_t realOutput,
    const char *k,
    char *&signatures,
    const uint64_t signaturesLength)
{
    const std::string *sigsBuffer = reinterpret_cast<const std::string *>(signatures);

    std::vector<std::string> sigs(sigsBuffer, sigsBuffer + signaturesLength);

    bool success = Core::Cryptography::completeRingSignatures(transactionSecretKey, realOutput, k, sigs);

    if (success)
    {
        signatures = reinterpret_cast<char *>(sigs.data());
    }

    return success;
}

inline int prepareRingSignatures(
    const char *prefixHash,
    const char *keyImage,
    const char *publicKeys,
    const uint64_t publicKeysLength,
    const uint64_t realOutput,
    char *&signatures,
    char *&k)
{
    const std::string *keysBuffer = reinterpret_cast<const std::string *>(publicKeys);

    std::vector<std::string> keys(keysBuffer, keysBuffer + publicKeysLength);

    std::vector<std::string> sigs;

    std::string kTemp;

    bool success = Core::Cryptography::prepareRingSignatures(prefixHash, keyImage, keys, realOutput, sigs, kTemp);

    if (success)
    {
        k = strdup(kTemp.c_str());

        signatures = reinterpret_cast<char *>(sigs.data());
    }

    return success;
}

inline void restoreKeyImage(
    const char *publicEphemeral,
    const char *derivation,
    const uint64_t output_index,
    const char *partialKeyImages,
    const uint64_t partialKeyImagesLength,
    char *&keyImage)
{
    const std::string *keysBuffer = reinterpret_cast<const std::string *>(partialKeyImages);

    std::vector<std::string> keys(keysBuffer, keysBuffer + partialKeyImagesLength);

    const std::string result = Core::Cryptography::restoreKeyImage(publicEphemeral, derivation, output_index, keys);

    keyImage = strdup(result.c_str());
}

inline int restoreRingSignatures(
    const char *derivation,
    const uint64_t output_index,
    const char *partialSigningKeys,
    const uint64_t partialSigningKeysLength,
    const uint64_t realOutput,
    const char *k,
    char *&signatures,
    const uint64_t signaturesLength)
{
    const std::string *keysBuffer = reinterpret_cast<const std::string *>(partialSigningKeys);

    std::vector<std::string> keys(keysBuffer, keysBuffer + partialSigningKeysLength);

    const std::string *sigsBuffer = reinterpret_cast<const std::string *>(signatures);

    std::vector<std::string> sigs(sigsBuffer, sigsBuffer + signaturesLength);

    bool success = Core::Cryptography::restoreRingSignatures(derivation, output_index, keys, realOutput, k, sigs);

    if (success)
    {
        signatures = reinterpret_cast<char *>(sigs.data());
    }

    return success;
}

inline void calculateMultisigPrivateKeys(
    const char *ourPrivateSpendKey,
    const char *publicKeys,
    const uint64_t publicKeysLength,
    char *&multisigKeys)
{
    const std::string *keysBuffer = reinterpret_cast<const std::string *>(publicKeys);

    std::vector<std::string> keys(keysBuffer, keysBuffer + publicKeysLength);

    std::vector<std::string> multisigKeysTemp =
        Core::Cryptography::calculateMultisigPrivateKeys(ourPrivateSpendKey, keys);

    multisigKeys = reinterpret_cast<char *>(multisigKeysTemp.data());
}

inline void calculateSharedPrivateKey(const char *secretKeys, const uint64_t secretKeysLength, char *&secretKey)
{
    const std::string *keysBuffer = reinterpret_cast<const std::string *>(secretKeys);

    std::vector<std::string> keys(keysBuffer, keysBuffer + secretKeysLength);

    const std::string result = Core::Cryptography::calculateSharedPrivateKey(keys);

    secretKey = strdup(result.c_str());
}

inline void calculateSharedPublicKey(const char *publicKeys, const uint64_t publicKeysLength, char *&publicKey)
{
    const std::string *keysBuffer = reinterpret_cast<const std::string *>(publicKeys);

    std::vector<std::string> keys(keysBuffer, keysBuffer + publicKeysLength);

    const std::string result = Core::Cryptography::calculateSharedPublicKey(keys);

    publicKey = strdup(result.c_str());
}

extern "C"
{
    /* Hashing Methods */

    EXPORTDLL void _cn_fast_hash(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_fast_hash(input).c_str());
    }

    EXPORTDLL void _cn_slow_hash_v0(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_slow_hash_v0(input).c_str());
    }

    EXPORTDLL void _cn_slow_hash_v1(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_slow_hash_v1(input).c_str());
    }

    EXPORTDLL void _cn_slow_hash_v2(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_slow_hash_v2(input).c_str());
    }

    EXPORTDLL void _cn_lite_slow_hash_v0(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_lite_slow_hash_v0(input).c_str());
    }

    EXPORTDLL void _cn_lite_slow_hash_v1(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_lite_slow_hash_v1(input).c_str());
    }

    EXPORTDLL void _cn_lite_slow_hash_v2(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_lite_slow_hash_v2(input).c_str());
    }

    EXPORTDLL void _cn_dark_slow_hash_v0(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_dark_slow_hash_v0(input).c_str());
    }

    EXPORTDLL void _cn_dark_slow_hash_v1(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_dark_slow_hash_v1(input).c_str());
    }

    EXPORTDLL void _cn_dark_slow_hash_v2(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_dark_slow_hash_v2(input).c_str());
    }

    EXPORTDLL void _cn_dark_lite_slow_hash_v0(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_dark_lite_slow_hash_v0(input).c_str());
    }

    EXPORTDLL void _cn_dark_lite_slow_hash_v1(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_dark_lite_slow_hash_v1(input).c_str());
    }

    EXPORTDLL void _cn_dark_lite_slow_hash_v2(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_dark_lite_slow_hash_v2(input).c_str());
    }

    EXPORTDLL void _cn_turtle_slow_hash_v0(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_turtle_slow_hash_v0(input).c_str());
    }

    EXPORTDLL void _cn_turtle_slow_hash_v1(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_turtle_slow_hash_v1(input).c_str());
    }

    EXPORTDLL void _cn_turtle_slow_hash_v2(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_turtle_slow_hash_v2(input).c_str());
    }

    EXPORTDLL void _cn_turtle_lite_slow_hash_v0(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_turtle_lite_slow_hash_v0(input).c_str());
    }

    EXPORTDLL void _cn_turtle_lite_slow_hash_v1(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_turtle_lite_slow_hash_v1(input).c_str());
    }

    EXPORTDLL void _cn_turtle_lite_slow_hash_v2(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::cn_turtle_lite_slow_hash_v2(input).c_str());
    }

    EXPORTDLL void _cn_soft_shell_slow_hash_v0(const char *input, const uint32_t height, char *&output)
    {
        output = strdup(Core::Cryptography::cn_soft_shell_slow_hash_v0(input, height).c_str());
    }

    EXPORTDLL void _cn_soft_shell_slow_hash_v1(const char *input, const uint32_t height, char *&output)
    {
        output = strdup(Core::Cryptography::cn_soft_shell_slow_hash_v1(input, height).c_str());
    }

    EXPORTDLL void _cn_soft_shell_slow_hash_v2(const char *input, const uint32_t height, char *&output)
    {
        output = strdup(Core::Cryptography::cn_soft_shell_slow_hash_v2(input, height).c_str());
    }

    EXPORTDLL void _chukwa_slow_hash_base(const char *input, char *&output, const uint32_t iterations, const uint32_t memory, const uint32_t threads)
    {
        output = strdup(Core::Cryptography::chukwa_slow_hash_base(
            input,
            iterations,
            memory,
            threads).c_str());
    }

    EXPORTDLL void _chukwa_slow_hash_v1(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::chukwa_slow_hash_v1(input).c_str());
    }

    EXPORTDLL void _chukwa_slow_hash_v2(const char *input, char *&output)
    {
        output = strdup(Core::Cryptography::chukwa_slow_hash_v2(input).c_str());
    }

    EXPORTDLL uint32_t _tree_depth(const uint32_t count)
    {
        return Core::Cryptography::tree_depth(count);
    }

    EXPORTDLL void _tree_hash(const char *hashes, const uint64_t hashesLength, char *&hash)
    {
        tree_hash(hashes, hashesLength, hash);
    }

    EXPORTDLL void _tree_branch(const char *hashes, const uint64_t hashesLength, char *&branch)
    {
        tree_branch(hashes, hashesLength, branch);
    }

    EXPORTDLL void _tree_hash_from_branch(
        const char *branches,
        const uint64_t branchesLength,
        const uint64_t depth,
        const char *leaf,
        const char *path,
        char *&hash)
    {
        tree_hash_from_branch(branches, branchesLength, leaf, path, hash);
    }

    /* Crypto Methods */

    EXPORTDLL int _generateRingSignatures(
        const char *prefixHash,
        const char *keyImage,
        const char *publicKeys,
        const uint64_t publicKeysLength,
        const char *transactionSecretKey,
        const uint64_t realOutput,
        char *&signatures)
    {
        return generateRingSignatures(
            prefixHash, keyImage, publicKeys, publicKeysLength, transactionSecretKey, realOutput, signatures);
    }

    EXPORTDLL bool _checkRingSignature(
        const char *prefixHash,
        const char *keyImage,
        const char *publicKeys,
        const uint64_t publicKeysLength,
        const char *signatures,
        const uint64_t signaturesLength)
    {
        return checkRingSignature(prefixHash, keyImage, publicKeys, publicKeysLength, signatures, signaturesLength);
    }

    EXPORTDLL void _generatePrivateViewKeyFromPrivateSpendKey(const char *spendPrivateKey, char *&output)
    {
        output = strdup(Core::Cryptography::generatePrivateViewKeyFromPrivateSpendKey(spendPrivateKey).c_str());
    }

    EXPORTDLL void
        _generateViewKeysFromPrivateSpendKey(const char *spendPrivateKey, char *&privateKey, char *&publicKey)
    {
        generateViewKeysFromPrivateSpendKey(spendPrivateKey, privateKey, publicKey);
    }

    EXPORTDLL void _generateKeys(char *&privateKey, char *&publicKey)
    {
        generateKeys(privateKey, publicKey);
    }

    EXPORTDLL int _checkKey(const char *publicKey)
    {
        return Core::Cryptography::checkKey(publicKey);
    }

    EXPORTDLL int _secretKeyToPublicKey(const char *privateKey, char *&publicKey)
    {
        return secretKeyToPublicKey(privateKey, publicKey);
    }

    EXPORTDLL int _generateKeyDerivation(const char *publicKey, const char *privateKey, char *&derivation)
    {
        return generateKeyDerivation(publicKey, privateKey, derivation);
    }

    EXPORTDLL int
        _derivePublicKey(const char *derivation, uint32_t outputIndex, const char *publicKey, char *&outPublicKey)
    {
        return derivePublicKey(derivation, outputIndex, publicKey, outPublicKey);
    }

    EXPORTDLL void
        _deriveSecretKey(const char *derivation, uint32_t outputIndex, const char *privateKey, char *&outPrivateKey)
    {
        outPrivateKey = strdup(Core::Cryptography::deriveSecretKey(derivation, outputIndex, privateKey).c_str());
    }

    EXPORTDLL int
        _underivePublicKey(const char *derivation, const uint64_t outputIndex, const char *derivedKey, char *&publicKey)
    {
        return underivePublicKey(derivation, outputIndex, derivedKey, publicKey);
    }

    EXPORTDLL void
        _generateSignature(const char *prefixHash, const char *publicKey, const char *privateKey, char *&signature)
    {
        signature = strdup(Core::Cryptography::generateSignature(prefixHash, publicKey, privateKey).c_str());
    }

    EXPORTDLL int _checkSignature(const char *prefixHash, const char *publicKey, const char *signature)
    {
        return Core::Cryptography::checkSignature(prefixHash, publicKey, signature);
    }

    EXPORTDLL void _generateKeyImage(const char *publicKey, const char *privateKey, char *&keyImage)
    {
        keyImage = strdup(Core::Cryptography::generateKeyImage(publicKey, privateKey).c_str());
    }

    EXPORTDLL void _scalarmultKey(const char *keyImageA, const char *keyImageB, char *&keyImageC)
    {
        keyImageC = strdup(Core::Cryptography::scalarmultKey(keyImageA, keyImageB).c_str());
    }

    EXPORTDLL void _hashToEllipticCurve(const char *hash, char *&ec)
    {
        ec = strdup(Core::Cryptography::hashToEllipticCurve(hash).c_str());
    }

    EXPORTDLL void _scReduce32(const char *data, char *&output)
    {
        output = strdup(Core::Cryptography::scReduce32(data).c_str());
    }

    EXPORTDLL void _hashToScalar(const char *hash, char *&output)
    {
        output = strdup(Core::Cryptography::hashToScalar(hash).c_str());
    }

    EXPORTDLL int _generateDeterministicSubwalletKeys(
        const char *basePrivateKey,
        const uint64_t walletIndex,
        char *&privateKey,
        char *&publicKey)
    {
        return generateDeterministicSubwalletKeys(basePrivateKey, walletIndex, privateKey, publicKey);
    }

    EXPORTDLL void _restoreKeyImage(
        const char *publicEphemeral,
        const char *derivation,
        const uint64_t output_index,
        const char *partialKeyImages,
        const uint64_t partialKeyImagesLength,
        char *&keyImage)
    {
        restoreKeyImage(publicEphemeral, derivation, output_index, partialKeyImages, partialKeyImagesLength, keyImage);
    }

    EXPORTDLL int _restoreRingSignatures(
        const char *derivation,
        const uint64_t output_index,
        const char *partialSigningKeys,
        const uint64_t partialSigningKeysLength,
        const uint64_t realOutput,
        const char *k,
        char *&signatures,
        const uint64_t signaturesLength)
    {
        return restoreRingSignatures(
            derivation,
            output_index,
            partialSigningKeys,
            partialSigningKeysLength,
            realOutput,
            k,
            signatures,
            signaturesLength);
    }

    EXPORTDLL void
        _generatePartialSigningKey(const char *signature, const char *privateSpendKey, char *&partialSigningKey)
    {
        partialSigningKey = strdup(Core::Cryptography::generatePartialSigningKey(signature, privateSpendKey).c_str());
    }

    EXPORTDLL int _prepareRingSignatures(
        const char *prefixHash,
        const char *keyImage,
        const char *publicKeys,
        const uint64_t publicKeysLength,
        const uint64_t realOutput,
        char *&signatures,
        char *&k)
    {
        return prepareRingSignatures(prefixHash, keyImage, publicKeys, publicKeysLength, realOutput, signatures, k);
    }

    EXPORTDLL int _completeRingSignatures(
        const char *transactionSecretKey,
        const uint64_t realOutput,
        const char *k,
        char *&signatures,
        const uint64_t signaturesLength)
    {
        return completeRingSignatures(transactionSecretKey, realOutput, k, signatures, signaturesLength);
    }

    EXPORTDLL void _calculateMultisigPrivateKeys(
        const char *ourPrivateSpendKey,
        const char *publicKeys,
        const uint64_t publicKeysLength,
        char *&multisigKeys)
    {
        calculateMultisigPrivateKeys(ourPrivateSpendKey, publicKeys, publicKeysLength, multisigKeys);
    }

    EXPORTDLL void _calculateSharedPrivateKey(const char *secretKeys, const uint64_t secretKeysLength, char *&secretKey)
    {
        calculateSharedPrivateKey(secretKeys, secretKeysLength, secretKey);
    }

    EXPORTDLL void _calculateSharedPublicKey(const char *publicKeys, const uint64_t publicKeysLength, char *&publicKey)
    {
        calculateSharedPublicKey(publicKeys, publicKeysLength, publicKey);
    }
}
