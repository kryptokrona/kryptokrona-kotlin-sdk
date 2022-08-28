// Copyright (c) 2018-2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include <emscripten/bind.h>
#include <stdio.h>
#include <stdlib.h>
#include <turtlecoin-crypto.h>

using namespace emscripten;

struct Keys
{
    std::string publicKey;
    std::string secretKey;
};

struct PreparedSignatures
{
    std::vector<std::string> signatures;
    std::string key;
};

/* Most of the redefintions below are the result of the methods returning a bool instead
   of the value we need or issues with method signatures having a uint64_t */

std::string cn_soft_shell_slow_hash_v0(const std::string data, const int height)
{
    return Core::Cryptography::cn_soft_shell_slow_hash_v0(data, height);
}

std::string cn_soft_shell_slow_hash_v1(const std::string data, const int height)
{
    return Core::Cryptography::cn_soft_shell_slow_hash_v1(data, height);
}

std::string cn_soft_shell_slow_hash_v2(const std::string data, const int height)
{
    return Core::Cryptography::cn_soft_shell_slow_hash_v2(data, height);
}

std::vector<std::string> generateRingSignatures(
    const std::string prefixHash,
    const std::string keyImage,
    const std::vector<std::string> publicKeys,
    const std::string transactionSecretKey,
    const int realOutputIndex)
{
    std::vector<std::string> signatures;

    bool success = Core::Cryptography::generateRingSignatures(
        prefixHash, keyImage, publicKeys, transactionSecretKey, realOutputIndex, signatures);

    return signatures;
}

PreparedSignatures prepareRingSignatures(
    const std::string prefixHash,
    const std::string keyImage,
    const std::vector<std::string> publicKeys,
    const int realOutputIndex)
{
    std::vector<std::string> signatures;

    std::string k;

    bool success =
        Core::Cryptography::prepareRingSignatures(prefixHash, keyImage, publicKeys, realOutputIndex, signatures, k);

    PreparedSignatures result;

    result.signatures = signatures;

    result.key = k;

    return result;
}

PreparedSignatures prepareRingSignaturesK(
    const std::string prefixHash,
    const std::string keyImage,
    const std::vector<std::string> publicKeys,
    const int realOutputIndex,
    const std::string k)
{
    std::vector<std::string> signatures;

    bool success =
        Core::Cryptography::prepareRingSignatures(prefixHash, keyImage, publicKeys, realOutputIndex, k, signatures);

    PreparedSignatures result;

    result.signatures = signatures;

    result.key = k;

    return result;
}

Keys generateViewKeysFromPrivateSpendKey(const std::string secretKey)
{
    std::string viewSecretKey;

    std::string viewPublicKey;

    Core::Cryptography::generateViewKeysFromPrivateSpendKey(secretKey, viewSecretKey, viewPublicKey);

    Keys keys;

    keys.publicKey = viewPublicKey;

    keys.secretKey = viewSecretKey;

    return keys;
}

Keys generateKeys()
{
    std::string secretKey;

    std::string publicKey;

    Core::Cryptography::generateKeys(secretKey, publicKey);

    Keys keys;

    keys.publicKey = publicKey;

    keys.secretKey = secretKey;

    return keys;
}

Keys generateDeterministicSubwalletKeys(const std::string basePrivateKey, const size_t walletIndex)
{
    std::string newPrivateKey;

    std::string newPublicKey;

    Keys keys;

    Core::Cryptography::generateDeterministicSubwalletKeys(basePrivateKey, walletIndex, keys.secretKey, keys.publicKey);

    return keys;
}

std::string secretKeyToPublicKey(const std::string secretKey)
{
    std::string publicKey;

    bool success = Core::Cryptography::secretKeyToPublicKey(secretKey, publicKey);

    return publicKey;
}

std::string generateKeyDerivation(const std::string publicKey, const std::string secretKey)
{
    std::string derivation;

    bool success = Core::Cryptography::generateKeyDerivation(publicKey, secretKey, derivation);

    return derivation;
}

std::string generateKeyDerivationScalar(const std::string publicKey, const std::string secretKey, size_t outputIndex)
{
    return Core::Cryptography::generateKeyDerivationScalar(publicKey, secretKey, outputIndex);
}

std::string derivationToScalar(const std::string derivation, size_t outputIndex)
{
    return Core::Cryptography::derivationToScalar(derivation, outputIndex);
}

std::string derivePublicKey(const std::string derivation, const size_t outputIndex, const std::string publicKey)
{
    std::string derivedKey;

    bool success = Core::Cryptography::derivePublicKey(derivation, outputIndex, publicKey, derivedKey);

    return derivedKey;
}

std::string scalarDerivePublicKey(const std::string derivationScalar, const std::string publicKey)
{
    std::string derivedKey;

    bool success = Core::Cryptography::derivePublicKey(derivationScalar, publicKey, derivedKey);

    return derivedKey;
}

std::string deriveSecretKey(const std::string derivation, const size_t outputIndex, const std::string secretKey)
{
    return Core::Cryptography::deriveSecretKey(derivation, outputIndex, secretKey);
}

std::string scalarDeriveSecretKey(const std::string derivationScalar, const std::string secretKey)
{
    return Core::Cryptography::deriveSecretKey(derivationScalar, secretKey);
}

std::string underivePublicKey(const std::string derivation, const size_t outputIndex, const std::string derivedKey)
{
    std::string publicKey;

    bool success = Core::Cryptography::underivePublicKey(derivation, outputIndex, derivedKey, publicKey);

    return publicKey;
}

std::vector<std::string> completeRingSignatures(
    const std::string transactionSecretKey,
    const int realOutputIndex,
    const std::string k,
    const std::vector<std::string> signatures)
{
    std::vector<std::string> completeSignatures;

    for (auto sig : signatures)
    {
        completeSignatures.push_back(sig);
    }

    bool success =
        Core::Cryptography::completeRingSignatures(transactionSecretKey, realOutputIndex, k, completeSignatures);

    return completeSignatures;
}

std::vector<std::string> restoreRingSignatures(
    const std::string derivation,
    const size_t output_index,
    const std::vector<std::string> partialSigningKeys,
    const int realOutput,
    const std::string k,
    const std::vector<std::string> signatures)
{
    std::vector<std::string> completeSignatures;

    for (auto sig : signatures)
    {
        completeSignatures.push_back(sig);
    }

    bool success = Core::Cryptography::restoreRingSignatures(
        derivation, output_index, partialSigningKeys, realOutput, k, completeSignatures);

    return completeSignatures;
}

EMSCRIPTEN_BINDINGS(signatures)
{
    function("cn_fast_hash", &Core::Cryptography::cn_fast_hash);

    function("cn_slow_hash_v0", &Core::Cryptography::cn_slow_hash_v0);
    function("cn_slow_hash_v1", &Core::Cryptography::cn_slow_hash_v1);
    function("cn_slow_hash_v2", &Core::Cryptography::cn_slow_hash_v2);

    function("cn_lite_slow_hash_v0", &Core::Cryptography::cn_lite_slow_hash_v0);
    function("cn_lite_slow_hash_v1", &Core::Cryptography::cn_lite_slow_hash_v1);
    function("cn_lite_slow_hash_v2", &Core::Cryptography::cn_lite_slow_hash_v2);

    function("cn_dark_slow_hash_v0", &Core::Cryptography::cn_dark_slow_hash_v0);
    function("cn_dark_slow_hash_v1", &Core::Cryptography::cn_dark_slow_hash_v1);
    function("cn_dark_slow_hash_v2", &Core::Cryptography::cn_dark_slow_hash_v2);

    function("cn_dark_lite_slow_hash_v0", &Core::Cryptography::cn_dark_lite_slow_hash_v0);
    function("cn_dark_lite_slow_hash_v1", &Core::Cryptography::cn_dark_lite_slow_hash_v1);
    function("cn_dark_lite_slow_hash_v2", &Core::Cryptography::cn_dark_lite_slow_hash_v2);

    function("cn_turtle_slow_hash_v0", &Core::Cryptography::cn_turtle_slow_hash_v0);
    function("cn_turtle_slow_hash_v1", &Core::Cryptography::cn_turtle_slow_hash_v1);
    function("cn_turtle_slow_hash_v2", &Core::Cryptography::cn_turtle_slow_hash_v2);

    function("cn_turtle_lite_slow_hash_v0", &Core::Cryptography::cn_turtle_lite_slow_hash_v0);
    function("cn_turtle_lite_slow_hash_v1", &Core::Cryptography::cn_turtle_lite_slow_hash_v1);
    function("cn_turtle_lite_slow_hash_v2", &Core::Cryptography::cn_turtle_lite_slow_hash_v2);

    function("cn_soft_shell_slow_hash_v0", &cn_soft_shell_slow_hash_v0);
    function("cn_soft_shell_slow_hash_v1", &cn_soft_shell_slow_hash_v1);
    function("cn_soft_shell_slow_hash_v2", &cn_soft_shell_slow_hash_v2);

    function("chukwa_slow_hash_base", &Core::Cryptography::chukwa_slow_hash_base);
    function("chukwa_slow_hash_v1", &Core::Cryptography::chukwa_slow_hash_v1);
    function("chukwa_slow_hash_v2", &Core::Cryptography::chukwa_slow_hash_v2);

    function("tree_depth", &Core::Cryptography::tree_depth);
    function("tree_hash", &Core::Cryptography::tree_hash);
    function("tree_branch", &Core::Cryptography::tree_branch);
    function("tree_hash_from_branch", &Core::Cryptography::tree_hash_from_branch);

    function("generateRingSignatures", &generateRingSignatures);
    function("prepareRingSignatures", &prepareRingSignatures);
    function("prepareRingSignaturesK", &prepareRingSignaturesK);
    function("completeRingSignatures", &completeRingSignatures);
    function("checkRingSignature", &Core::Cryptography::checkRingSignature);
    function(
        "generatePrivateViewKeyFromPrivateSpendKey", &Core::Cryptography::generatePrivateViewKeyFromPrivateSpendKey);
    function("generateViewKeysFromPrivateSpendKey", &generateViewKeysFromPrivateSpendKey);
    function("generateKeys", &generateKeys);
    function("generateDeterministicSubwalletKeys", &generateDeterministicSubwalletKeys);
    function("checkKey", &Core::Cryptography::checkKey);
    function("secretKeyToPublicKey", &secretKeyToPublicKey);
    function("generateKeyDerivation", &generateKeyDerivation);
    function("generateKeyDerivationScalar", &generateKeyDerivationScalar);
    function("derivationToScalar", &derivationToScalar);
    function("derivePublicKey", &derivePublicKey);
    function("deriveSecretKey", &deriveSecretKey);
    function("scalarDerivePublicKey", &scalarDerivePublicKey);
    function("scalarDeriveSecretKey", &scalarDeriveSecretKey);
    function("underivePublicKey", &underivePublicKey);
    function("generateSignature", &Core::Cryptography::generateSignature);
    function("checkSignature", &Core::Cryptography::checkSignature);
    function("generateKeyImage", &Core::Cryptography::generateKeyImage);
    function("scalarmultKey", &Core::Cryptography::scalarmultKey);
    function("hashToEllipticCurve", &Core::Cryptography::hashToEllipticCurve);
    function("scReduce32", &Core::Cryptography::scReduce32);
    function("hashToScalar", &Core::Cryptography::hashToScalar);

    /* Multisig Methods */
    function("calculateMultisigPrivateKeys", &Core::Cryptography::calculateMultisigPrivateKeys);
    function("calculateSharedPrivateKey", &Core::Cryptography::calculateSharedPrivateKey);
    function("calculateSharedPublicKey", &Core::Cryptography::calculateSharedPublicKey);
    function("generatePartialSigningKey", &Core::Cryptography::generatePartialSigningKey);
    function("restoreKeyImage", &Core::Cryptography::restoreKeyImage);
    function("restoreRingSignatures", &restoreRingSignatures);

    register_vector<std::string>("VectorString");

    value_object<Keys>("Keys").field("secretKey", &Keys::secretKey).field("publicKey", &Keys::publicKey);

    value_object<PreparedSignatures>("Keys")
        .field("signatures", &PreparedSignatures::signatures)
        .field("key", &PreparedSignatures::key);
}
