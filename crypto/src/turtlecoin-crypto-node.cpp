// Copyright (c) 2018-2020, The TurtleCoin Developers
//
// Please see the included LICENSE file for more information.

#include <nan.h>
#include <turtlecoin-crypto.h>
#include <v8.h>

/*
 *
 * Helper methods
 *
 */

inline v8::Local<v8::Array> prepareResult(const bool success, const v8::Local<v8::Value> val)
{
    v8::Local<v8::Array> result = Nan::New<v8::Array>(2);

    /* We do the inverse of success because we want the results in [err, value] format */
    Nan::Set(result, 0, Nan::New(!success));

    Nan::Set(result, 1, val);

    return result;
}

inline std::string getString(const Nan::FunctionCallbackInfo<v8::Value> &info, const uint8_t index)
{
    std::string result = std::string();

    if (info.Length() >= index)
    {
        if (info[index]->IsString())
        {
            result = std::string(
                *Nan::Utf8String(info[index]->ToString(Nan::GetCurrentContext()).FromMaybe(v8::Local<v8::String>())));
        }
    }

    return result;
}

inline std::vector<std::string> toStringVector(const Nan::FunctionCallbackInfo<v8::Value> &info, const uint8_t index)
{
    std::vector<std::string> results;

    if (info.Length() >= index)
    {
        if (info[index]->IsArray())
        {
            v8::Local<v8::Array> array = v8::Local<v8::Array>::Cast(info[index]);

            for (size_t i = 0; i < array->Length(); i++)
            {
                std::string value = std::string(*Nan::Utf8String(Nan::Get(array, i).ToLocalChecked()));

                results.push_back(value);
            }
        }
    }

    return results;
}

inline uint32_t getUInt32(const Nan::FunctionCallbackInfo<v8::Value> &info, const uint8_t index)
{
    uint32_t value = 0;

    if (info.Length() >= index)
    {
        if (info[index]->IsNumber())
        {
            value = Nan::To<uint32_t>(info[index]).FromJust();
        }
    }

    return value;
}

inline uint64_t getUInt64(const Nan::FunctionCallbackInfo<v8::Value> &info, const uint8_t index)
{
    uint64_t value = 0;

    if (info.Length() >= index)
    {
        if (info[index]->IsNumber())
        {
            value = (uint64_t)Nan::To<uint32_t>(info[index]).FromJust();
        }
    }

    return value;
}

inline v8::Local<v8::Array> toV8Array(const std::vector<std::string> &vector)
{
    v8::Local<v8::Array> arr = Nan::New<v8::Array>(vector.size());

    for (size_t i = 0; i < vector.size(); i++)
    {
        v8::Local<v8::String> result = Nan::New(vector[i]).ToLocalChecked();

        Nan::Set(arr, i, result);
    }

    return arr;
}

/*
 *
 * Core Cryptographic Operations
 *
 */
void calculateMultisigPrivateKeys(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string privateSpendKey = getString(info, 0);

    std::vector<std::string> keys = toStringVector(info, 1);

    if (!privateSpendKey.empty() && keys.size() != 0)
    {
        try
        {
            std::vector<std::string> multiSigKeys =
                Core::Cryptography::calculateMultisigPrivateKeys(privateSpendKey, keys);

            functionReturnValue = toV8Array(multiSigKeys);

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void calculateSharedPrivateKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::vector<std::string> keys = toStringVector(info, 0);

    if (keys.size() != 0)
    {
        try
        {
            std::string result = Core::Cryptography::calculateSharedPrivateKey(keys);

            functionReturnValue = Nan::New(result).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void calculateSharedPublicKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::vector<std::string> keys = toStringVector(info, 0);

    if (keys.size() != 0)
    {
        try
        {
            std::string result = Core::Cryptography::calculateSharedPublicKey(keys);

            functionReturnValue = Nan::New(result).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void prepareRingSignatures(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Object> jsonObject = Nan::New<v8::Object>();

    bool functionSuccess = false;

    std::string prefixHash = getString(info, 0);

    std::string keyImage = getString(info, 1);

    std::vector<std::string> publicKeys = toStringVector(info, 2);

    uint64_t realOutput = getUInt64(info, 3);

    std::string k = getString(info, 4);

    if (!prefixHash.empty() && !keyImage.empty() && publicKeys.size() != 0)
    {
        try
        {
            std::vector<std::string> signatures;

            bool success = false;

            if (k.empty())
            {
                success = Core::Cryptography::prepareRingSignatures(
                    prefixHash, keyImage, publicKeys, realOutput, signatures, k);
            }
            else
            {
                success = Core::Cryptography::prepareRingSignatures(
                    prefixHash, keyImage, publicKeys, realOutput, k, signatures);
            }

            if (success)
            {
                v8::Local<v8::String> signaturesProp = Nan::New("signatures").ToLocalChecked();

                v8::Local<v8::String> randomScalarProp = Nan::New("key").ToLocalChecked();

                v8::Local<v8::Value> signaturesValue = toV8Array(signatures);

                v8::Local<v8::Value> randomScalarValue = Nan::New(k).ToLocalChecked();

                Nan::Set(jsonObject, signaturesProp, signaturesValue);

                Nan::Set(jsonObject, randomScalarProp, randomScalarValue);

                functionSuccess = true;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, jsonObject));
}

void completeRingSignatures(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string transactionSecretKey = getString(info, 0);

    uint64_t realOutput = getUInt64(info, 1);

    std::string k = getString(info, 2);

    std::vector<std::string> signatures = toStringVector(info, 3);

    if (!transactionSecretKey.empty() && !k.empty() && signatures.size() != 0)
    {
        try
        {
            bool success = Core::Cryptography::completeRingSignatures(transactionSecretKey, realOutput, k, signatures);

            if (success)
            {
                functionReturnValue = toV8Array(signatures);

                functionSuccess = true;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void restoreRingSignatures(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string derivation = getString(info, 0);

    size_t output_index = (size_t)getUInt32(info, 1);

    std::vector<std::string> partialSigningKeys = toStringVector(info, 2);

    uint64_t realOutput = getUInt64(info, 3);

    std::string k = getString(info, 4);

    std::vector<std::string> signatures = toStringVector(info, 5);

    if (!derivation.empty() && partialSigningKeys.size() != 0 && !k.empty() && signatures.size() != 0)
    {
        try
        {
            bool success = Core::Cryptography::restoreRingSignatures(
                derivation, output_index, partialSigningKeys, realOutput, k, signatures);

            if (success)
            {
                functionReturnValue = toV8Array(signatures);

                functionSuccess = true;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void generatePartialSigningKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New(false);

    bool functionSuccess = false;

    std::string signature = getString(info, 0);

    std::string privateSpendKey = getString(info, 1);

    if (!signature.empty() && !privateSpendKey.empty())
    {
        try
        {
            std::string result = Core::Cryptography::generatePartialSigningKey(signature, privateSpendKey);

            functionReturnValue = Nan::New(result).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void restoreKeyImage(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New(false);

    bool functionSuccess = false;

    std::string publicEphemeral = getString(info, 0);

    std::string derivation = getString(info, 1);

    size_t outputIndex = (size_t)getUInt32(info, 2);

    std::vector<std::string> partialKeyImages = toStringVector(info, 3);

    if (!publicEphemeral.empty() && !derivation.empty() && partialKeyImages.size() != 0)
    {
        try
        {
            std::string result =
                Core::Cryptography::restoreKeyImage(publicEphemeral, derivation, outputIndex, partialKeyImages);

            functionReturnValue = Nan::New(result).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void checkKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New(false);

    std::string publicKey = getString(info, 0);

    if (!publicKey.empty())
    {
        try
        {
            bool success = Core::Cryptography::checkKey(publicKey);

            functionReturnValue = Nan::New(success);
        }
        catch (const std::exception &)
        {
            functionReturnValue = Nan::New(false);
        }
    }

    info.GetReturnValue().Set(functionReturnValue);
}

void checkRingSignature(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New(false);

    std::string prefixHash = getString(info, 0);

    std::string keyImage = getString(info, 1);

    std::vector<std::string> publicKeys = toStringVector(info, 2);

    std::vector<std::string> signatures = toStringVector(info, 3);

    if (!prefixHash.empty() && !keyImage.empty() && publicKeys.size() != 0 && signatures.size() != 0)
    {
        try
        {
            bool success = Core::Cryptography::checkRingSignature(prefixHash, keyImage, publicKeys, signatures);

            functionReturnValue = Nan::New(success);
        }
        catch (const std::exception &)
        {
            functionReturnValue = Nan::New(false);
        }
    }

    info.GetReturnValue().Set(functionReturnValue);
}

void checkSignature(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New(false);
    std::string prefixHash = getString(info, 0);

    std::string publicKey = getString(info, 1);

    std::string signature = getString(info, 2);

    if (!prefixHash.empty() && !publicKey.empty() && !signature.empty())
    {
        try
        {
            bool success = Core::Cryptography::checkSignature(prefixHash, publicKey, signature);

            functionReturnValue = Nan::New(success);
        }
        catch (const std::exception &)
        {
            functionReturnValue = Nan::New(false);
        }
    }

    info.GetReturnValue().Set(functionReturnValue);
}

void derivePublicKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string derivation = getString(info, 0);

    size_t outputIndex = (size_t)getUInt32(info, 1);

    std::string publicKey = getString(info, 2);

    if (!derivation.empty() && !publicKey.empty())
    {
        try
        {
            std::string outPublicKey = std::string();

            bool success = Core::Cryptography::derivePublicKey(derivation, outputIndex, publicKey, outPublicKey);

            if (success)
            {
                functionReturnValue = Nan::New(outPublicKey).ToLocalChecked();

                functionSuccess = success;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void deriveSecretKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string derivation = getString(info, 0);

    size_t outputIndex = (size_t)getUInt32(info, 1);

    std::string secretKey = getString(info, 2);

    if (!derivation.empty() && !secretKey.empty())
    {
        try
        {
            std::string _secretKey = Core::Cryptography::deriveSecretKey(derivation, outputIndex, secretKey);

            if (!_secretKey.empty())
            {
                functionReturnValue = Nan::New(_secretKey).ToLocalChecked();

                functionSuccess = true;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void generateKeys(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    v8::Local<v8::Object> jsonObject = Nan::New<v8::Object>();

    bool functionSuccess = false;

    try
    {
        std::string secretKey;

        std::string publicKey;

        Core::Cryptography::generateKeys(secretKey, publicKey);

        v8::Local<v8::String> publicKeyProp = Nan::New("publicKey").ToLocalChecked();

        v8::Local<v8::String> secretKeyProp = Nan::New("secretKey").ToLocalChecked();

        v8::Local<v8::Value> publicKeyValue = Nan::New(publicKey).ToLocalChecked();

        v8::Local<v8::Value> secretKeyValue = Nan::New(secretKey).ToLocalChecked();

        Nan::Set(jsonObject, publicKeyProp, publicKeyValue);

        Nan::Set(jsonObject, secretKeyProp, secretKeyValue);

        functionSuccess = true;
    }
    catch (const std::exception &)
    {
        functionSuccess = false;
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, jsonObject));
}

void generateKeyDerivation(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string publicKey = getString(info, 0);

    std::string secretKey = getString(info, 1);

    if (!secretKey.empty() && !publicKey.empty())
    {
        try
        {
            std::string derivation;

            bool success = Core::Cryptography::generateKeyDerivation(publicKey, secretKey, derivation);

            if (success)
            {
                functionReturnValue = Nan::New(derivation).ToLocalChecked();

                functionSuccess = true;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void generateKeyDerivationScalar(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string publicKey = getString(info, 0);

    std::string secretKey = getString(info, 1);

    uint64_t outputIndex = getUInt64(info, 2);

    if (!secretKey.empty() && !publicKey.empty())
    {
        try
        {
            std::string derivationScalar =
                Core::Cryptography::generateKeyDerivationScalar(publicKey, secretKey, outputIndex);

            functionReturnValue = Nan::New(derivationScalar).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void derivationToScalar(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string derivation = getString(info, 0);

    uint64_t outputIndex = getUInt64(info, 1);

    if (!derivation.empty())
    {
        try
        {
            std::string derivationScalar = Core::Cryptography::derivationToScalar(derivation, outputIndex);

            functionReturnValue = Nan::New(derivationScalar).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void generateKeyImage(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string publicKey = getString(info, 0);

    std::string secretKey = getString(info, 1);

    if (!publicKey.empty() && !secretKey.empty())
    {
        std::string keyImage;

        try
        {
            keyImage = Core::Cryptography::generateKeyImage(publicKey, secretKey);

            functionReturnValue = Nan::New(keyImage).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void generatePrivateViewKeyFromPrivateSpendKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string secretKey = getString(info, 0);

    if (!secretKey.empty())
    {
        try
        {
            std::string privateViewKey = Core::Cryptography::generatePrivateViewKeyFromPrivateSpendKey(secretKey);

            functionReturnValue = Nan::New(privateViewKey).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void generateDeterministicSubwalletKeys(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Object> jsonObject = Nan::New<v8::Object>();

    bool functionSuccess = false;

    if (info.Length() == 2)
    {
        std::string secretKey = getString(info, 0);

        size_t walletIndex = (size_t)getUInt32(info, 1);

        if (!secretKey.empty())
        {
            std::string newSecretKey = std::string();

            std::string newPublicKey = std::string();

            try
            {
                functionSuccess = Core::Cryptography::generateDeterministicSubwalletKeys(
                    secretKey, walletIndex, newSecretKey, newPublicKey);

                v8::Local<v8::String> publicKeyProp = Nan::New("publicKey").ToLocalChecked();

                v8::Local<v8::String> secretKeyProp = Nan::New("secretKey").ToLocalChecked();

                v8::Local<v8::Value> publicKeyValue = Nan::New(newPublicKey).ToLocalChecked();

                v8::Local<v8::Value> secretKeyValue = Nan::New(newSecretKey).ToLocalChecked();

                Nan::Set(jsonObject, publicKeyProp, publicKeyValue);

                Nan::Set(jsonObject, secretKeyProp, secretKeyValue);
            }
            catch (const std::exception &)
            {
                functionSuccess = false;
            }
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, jsonObject));
}

void generateViewKeysFromPrivateSpendKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Object> jsonObject = Nan::New<v8::Object>();

    v8::Local<v8::String> publicKeyProp = Nan::New("publicKey").ToLocalChecked();

    v8::Local<v8::String> secretKeyProp = Nan::New("secretKey").ToLocalChecked();

    bool functionSuccess = false;

    std::string secretKey = getString(info, 0);

    if (!secretKey.empty())
    {
        try
        {
            std::string privateViewKey;

            std::string publicViewKey;

            Core::Cryptography::generateViewKeysFromPrivateSpendKey(secretKey, privateViewKey, publicViewKey);

            v8::Local<v8::Value> publicKeyValue = Nan::New(publicViewKey).ToLocalChecked();

            v8::Local<v8::Value> secretKeyValue = Nan::New(privateViewKey).ToLocalChecked();

            Nan::Set(jsonObject, publicKeyProp, publicKeyValue);

            Nan::Set(jsonObject, secretKeyProp, secretKeyValue);

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, jsonObject));
}

void generateRingSignatures(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string prefixHash = getString(info, 0);

    std::string keyImage = getString(info, 1);

    std::vector<std::string> publicKeys = toStringVector(info, 2);

    std::string transactionSecretKey = getString(info, 3);

    uint64_t realOutput = getUInt64(info, 4);

    if (!prefixHash.empty() && !keyImage.empty() && !transactionSecretKey.empty() && publicKeys.size() != 0)
    {
        try
        {
            std::vector<std::string> signatures;

            bool success = Core::Cryptography::generateRingSignatures(
                prefixHash, keyImage, publicKeys, transactionSecretKey, realOutput, signatures);

            if (success)
            {
                functionReturnValue = toV8Array(signatures);

                functionSuccess = true;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void generateSignature(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string prefixHash = getString(info, 0);

    std::string publicKey = getString(info, 1);

    std::string secretKey = getString(info, 2);

    if (!prefixHash.empty() && !publicKey.empty() && !secretKey.empty())
    {
        std::string signature;

        try
        {
            signature = Core::Cryptography::generateSignature(prefixHash, publicKey, secretKey);

            functionReturnValue = Nan::New(signature).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void hashToEllipticCurve(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string hash = getString(info, 0);

    if (!hash.empty())
    {
        try
        {
            std::string _ec = Core::Cryptography::hashToEllipticCurve(hash);

            functionReturnValue = Nan::New(_ec).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void hashToScalar(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string _scalar = Core::Cryptography::hashToScalar(data);

            functionReturnValue = Nan::New(_scalar).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void scalarDerivePublicKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string derivation = getString(info, 0);

    std::string publicKey = getString(info, 1);

    if (!derivation.empty() && !publicKey.empty())
    {
        try
        {
            std::string outPublicKey = std::string();

            bool success = Core::Cryptography::derivePublicKey(derivation, publicKey, outPublicKey);

            if (success)
            {
                functionReturnValue = Nan::New(outPublicKey).ToLocalChecked();

                functionSuccess = success;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void scalarDeriveSecretKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string derivation = getString(info, 0);

    std::string secretKey = getString(info, 1);

    if (!derivation.empty() && !secretKey.empty())
    {
        try
        {
            std::string _secretKey = Core::Cryptography::deriveSecretKey(derivation, secretKey);

            if (!_secretKey.empty())
            {
                functionReturnValue = Nan::New(_secretKey).ToLocalChecked();

                functionSuccess = true;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void scalarmultKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string keyImageA = getString(info, 0);

    std::string keyImageB = getString(info, 1);

    if (!keyImageA.empty() && !keyImageB.empty())
    {
        try
        {
            std::string keyImageC = Core::Cryptography::scalarmultKey(keyImageA, keyImageB);

            functionReturnValue = Nan::New(keyImageC).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void scReduce32(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string scalar = Core::Cryptography::scReduce32(data);

            functionReturnValue = Nan::New(scalar).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void secretKeyToPublicKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string secretKey = getString(info, 0);

    if (!secretKey.empty())
    {
        try
        {
            std::string publicKey;

            bool success = Core::Cryptography::secretKeyToPublicKey(secretKey, publicKey);

            if (success)
            {
                functionReturnValue = Nan::New(publicKey).ToLocalChecked();

                functionSuccess = true;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void tree_depth(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    uint32_t count = 0;

    if (info[0]->IsNumber())
    {
        try
        {
            count = getUInt32(info, 0);

            uint32_t depth = Core::Cryptography::tree_depth(count);

            functionReturnValue = Nan::New(depth);

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void tree_hash(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::vector<std::string> hashes = toStringVector(info, 0);

    if (hashes.size() != 0)
    {
        try
        {
            std::string hash = Core::Cryptography::tree_hash(hashes);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void tree_branch(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::vector<std::string> hashes = toStringVector(info, 0);

    if (hashes.size() != 0)
    {
        try
        {
            std::vector<std::string> _branches = Core::Cryptography::tree_branch(hashes);

            v8::Local<v8::Array> branches = Nan::New<v8::Array>(_branches.size());

            for (size_t i = 0; i < _branches.size(); i++)
            {
                v8::Local<v8::String> result = Nan::New(_branches[i]).ToLocalChecked();

                Nan::Set(branches, i, result);
            }

            functionReturnValue = branches;

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void tree_hash_from_branch(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::vector<std::string> branches = toStringVector(info, 0);

    std::string leaf = getString(info, 1);

    std::string path = getString(info, 2);

    if (info[2]->IsNumber())
    {
        path = std::to_string((size_t)getUInt32(info, 2));
    }

    if (!leaf.empty() && !path.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::tree_hash_from_branch(branches, leaf, path);

            functionReturnValue = Nan::New(hash).ToLocalChecked();
            ;

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void underivePublicKey(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string derivation = getString(info, 0);

    size_t outputIndex = (size_t)getUInt32(info, 1);

    std::string derivedKey = getString(info, 2);

    if (!derivation.empty() && !derivedKey.empty())
    {
        try
        {
            std::string publicKey;

            bool success = Core::Cryptography::underivePublicKey(derivation, outputIndex, derivedKey, publicKey);

            if (success)
            {
                functionReturnValue = Nan::New(publicKey).ToLocalChecked();

                functionSuccess = true;
            }
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

/*
 *
 * Hashing Operations
 *
 */

void cn_fast_hash(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_fast_hash(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

/* Cryptonight Variants */

void cn_slow_hash_v0(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_slow_hash_v0(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_slow_hash_v1(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_slow_hash_v1(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_slow_hash_v2(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_slow_hash_v2(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

/* Cryptonight Lite Variants */

void cn_lite_slow_hash_v0(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_lite_slow_hash_v0(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_lite_slow_hash_v1(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_lite_slow_hash_v1(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_lite_slow_hash_v2(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_lite_slow_hash_v2(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

/* Cryptonight Dark Variants */

void cn_dark_slow_hash_v0(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_dark_slow_hash_v0(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_dark_slow_hash_v1(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_dark_slow_hash_v1(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_dark_slow_hash_v2(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_dark_slow_hash_v2(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

/* Cryptonight Dark Lite Variants */

void cn_dark_lite_slow_hash_v0(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_dark_lite_slow_hash_v0(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_dark_lite_slow_hash_v1(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_dark_lite_slow_hash_v1(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_dark_lite_slow_hash_v2(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_dark_lite_slow_hash_v2(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

/* Cryptonight Turtle Variants */

void cn_turtle_slow_hash_v0(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_turtle_slow_hash_v0(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_turtle_slow_hash_v1(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_turtle_slow_hash_v1(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_turtle_slow_hash_v2(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_turtle_slow_hash_v2(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

/* Cryptonight Turtle Lite Variants */

void cn_turtle_lite_slow_hash_v0(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_turtle_lite_slow_hash_v0(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_turtle_lite_slow_hash_v1(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_turtle_lite_slow_hash_v1(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void cn_turtle_lite_slow_hash_v2(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::cn_turtle_lite_slow_hash_v2(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

/* Chukwa */

void chukwa_slow_hash_base(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    uint32_t iterations = getUInt32(info, 1);

    uint32_t memory = getUInt32(info, 2);

    uint32_t threads = getUInt32(info, 3);

    if (!data.empty() && iterations != 0 && memory != 0 && threads != 0)
    {
        try
        {
            std::string hash = Core::Cryptography::chukwa_slow_hash_base(
                data,
                iterations,
                memory,
                threads);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void chukwa_slow_hash_v1(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::chukwa_slow_hash_v1(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

void chukwa_slow_hash_v2(const Nan::FunctionCallbackInfo<v8::Value> &info)
{
    /* Setup our return object */
    v8::Local<v8::Value> functionReturnValue = Nan::New("").ToLocalChecked();

    bool functionSuccess = false;

    std::string data = getString(info, 0);

    if (!data.empty())
    {
        try
        {
            std::string hash = Core::Cryptography::chukwa_slow_hash_v2(data);

            functionReturnValue = Nan::New(hash).ToLocalChecked();

            functionSuccess = true;
        }
        catch (const std::exception &)
        {
            functionSuccess = false;
        }
    }

    info.GetReturnValue().Set(prepareResult(functionSuccess, functionReturnValue));
}

NAN_MODULE_INIT(InitModule)
{
    /* Core Cryptographic Operations */
    Nan::Set(
        target,
        Nan::New("calculateMultisigPrivateKeys").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(calculateMultisigPrivateKeys)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("calculateSharedPrivateKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(calculateSharedPrivateKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("calculateSharedPublicKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(calculateSharedPublicKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generatePartialSigningKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generatePartialSigningKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("prepareRingSignatures").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(prepareRingSignatures)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("completeRingSignatures").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(completeRingSignatures)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("restoreRingSignatures").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(restoreRingSignatures)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("restoreKeyImage").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(restoreKeyImage)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("checkKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(checkKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("checkRingSignature").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(checkRingSignature)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("checkSignature").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(checkSignature)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("checkSignature").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(checkSignature)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("derivationToScalar").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(derivationToScalar)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("derivePublicKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(derivePublicKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("deriveSecretKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(deriveSecretKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generateKeys").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generateKeys)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generateKeyDerivation").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generateKeyDerivation)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generateKeyDerivationScalar").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generateKeyDerivationScalar)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generateKeyImage").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generateKeyImage)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generatePrivateViewKeyFromPrivateSpendKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generatePrivateViewKeyFromPrivateSpendKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generateDeterministicSubwalletKeys").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generateDeterministicSubwalletKeys)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generateViewKeysFromPrivateSpendKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generateViewKeysFromPrivateSpendKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generateRingSignatures").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generateRingSignatures)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("generateSignature").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(generateSignature)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("hashToEllipticCurve").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(hashToEllipticCurve)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("hashToScalar").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(hashToScalar)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("scalarDerivePublicKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(scalarDerivePublicKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("scalarDeriveSecretKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(scalarDeriveSecretKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("scalarmultKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(scalarmultKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("scReduce32").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(scReduce32)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("secretKeyToPublicKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(secretKeyToPublicKey)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("tree_depth").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(tree_depth)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("tree_hash").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(tree_hash)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("tree_branch").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(tree_branch)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("tree_hash_from_branch").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(tree_hash_from_branch)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("underivePublicKey").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(underivePublicKey)).ToLocalChecked());

    /* Hashing Operations */
    Nan::Set(
        target,
        Nan::New("cnFastHash").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_fast_hash)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_fast_hash").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_fast_hash)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_slow_hash_v0").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_slow_hash_v0)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_slow_hash_v1").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_slow_hash_v1)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_slow_hash_v2").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_slow_hash_v2)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_lite_slow_hash_v0").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_lite_slow_hash_v0)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_lite_slow_hash_v1").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_lite_slow_hash_v1)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_lite_slow_hash_v2").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_lite_slow_hash_v2)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_dark_slow_hash_v0").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_dark_slow_hash_v0)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_dark_slow_hash_v1").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_dark_slow_hash_v1)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_dark_slow_hash_v2").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_dark_slow_hash_v2)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_dark_lite_slow_hash_v0").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_dark_lite_slow_hash_v0)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_dark_lite_slow_hash_v1").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_dark_lite_slow_hash_v1)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_dark_lite_slow_hash_v2").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_dark_lite_slow_hash_v2)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_turtle_slow_hash_v0").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_turtle_slow_hash_v0)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_turtle_slow_hash_v1").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_turtle_slow_hash_v1)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_turtle_slow_hash_v2").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_turtle_slow_hash_v2)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_turtle_lite_slow_hash_v0").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_turtle_lite_slow_hash_v0)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_turtle_lite_slow_hash_v1").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_turtle_lite_slow_hash_v1)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("cn_turtle_lite_slow_hash_v2").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(cn_turtle_lite_slow_hash_v2)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("chukwa_slow_hash_base").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(chukwa_slow_hash_base)).ToLocalChecked());

    Nan::Set(
        target,
        Nan::New("chukwa_slow_hash_v1").ToLocalChecked(),
        Nan::GetFunction(Nan::New<v8::FunctionTemplate>(chukwa_slow_hash_v1)).ToLocalChecked());

     Nan::Set(
         target,
         Nan::New("chukwa_slow_hash_v2").ToLocalChecked(),
         Nan::GetFunction(Nan::New<v8::FunctionTemplate>(chukwa_slow_hash_v2)).ToLocalChecked());
}

NODE_MODULE(turtlecoincrypto, InitModule);
