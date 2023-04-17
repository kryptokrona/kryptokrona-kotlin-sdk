// Copyright (c) 2022-2023, The Kryptokrona Developers
//
// Written by Marcus Cvjeticanin
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

#include <stdio.h>
#include "jni_crypto.h"
#include "crypto.h"

JNIEXPORT jint JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_generateKeyDerivation(JNIEnv* env, jobject thiz,
    jbyteArray tx_pub_key, jbyteArray priv_view_key, jbyteArray key_derivation)
{
    jbyte* public_key_ptr = (*env)->GetByteArrayElements(env, tx_pub_key, NULL);
    jbyte* secret_key_ptr = (*env)->GetByteArrayElements(env, priv_view_key, NULL);
    jbyte* key_derivation_ptr = (*env)->GetByteArrayElements(env, key_derivation, NULL);

    // call the C function
    int result = generate_key_derivation((const uint8_t*)public_key_ptr, (const uint8_t*)secret_key_ptr, (uint8_t*)key_derivation_ptr);

    (*env)->ReleaseByteArrayElements(env, tx_pub_key, public_key_ptr, JNI_ABORT);
    (*env)->ReleaseByteArrayElements(env, priv_view_key, secret_key_ptr, JNI_ABORT);
    (*env)->ReleaseByteArrayElements(env, key_derivation, key_derivation_ptr, 0);

    return result;
}

JNIEXPORT jint JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_underivePublicKey(JNIEnv *env, jclass clazz,
    jbyteArray derivation, jint output_index, jbyteArray derived_key, jbyteArray base)
{
    // convert the input byte arrays to C arrays
    jbyte *derivation_c = (*env)->GetByteArrayElements(env, derivation, NULL);
    jsize derivation_len = (*env)->GetArrayLength(env, derivation);

    jbyte *derived_key_c = (*env)->GetByteArrayElements(env, derived_key, NULL);
    jsize derived_key_len = (*env)->GetArrayLength(env, derived_key);

    jbyte *base_c = (*env)->GetByteArrayElements(env, base, NULL);
    jsize base_len = (*env)->GetArrayLength(env, base);

    // call the C function
    int result = underive_public_key((const uint8_t *) derivation_c, (size_t) output_index,
                                     (const uint8_t *) derived_key_c, (uint8_t *) base_c);

    // Release the C arrays and return the result
    (*env)->ReleaseByteArrayElements(env, derivation, derivation_c, 0);
    (*env)->ReleaseByteArrayElements(env, derived_key, derived_key_c, 0);
    (*env)->ReleaseByteArrayElements(env, base, base_c, 0);

    return (jint) result;
}

JNIEXPORT void JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_generateKeyImage(JNIEnv *env, jclass clazz,
    jbyteArray pub, jbyteArray sec, jbyteArray image)
{
    uint8_t *pub_bytes = (uint8_t *)(*env)->GetByteArrayElements(env, pub, NULL);
    uint8_t *sec_bytes = (uint8_t *)(*env)->GetByteArrayElements(env, sec, NULL);
    uint8_t *image_bytes = (uint8_t *)(*env)->GetByteArrayElements(env, image, NULL);

    // call the C function
    generate_key_image(pub_bytes, sec_bytes, image_bytes);

    (*env)->ReleaseByteArrayElements(env, pub, (jbyte *)pub_bytes, JNI_ABORT);
    (*env)->ReleaseByteArrayElements(env, sec, (jbyte *)sec_bytes, JNI_ABORT);
    (*env)->ReleaseByteArrayElements(env, image, (jbyte *)image_bytes, 0);
}

JNIEXPORT jint JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_derivePublicKey(JNIEnv *env, jclass clazz,
    jbyteArray derivation, jint output_index, jbyteArray base, jbyteArray derived_key)
{
    // convert the input byte arrays to C arrays
    jbyte *derivation_c = (*env)->GetByteArrayElements(env, derivation, NULL);
    jsize derivation_len = (*env)->GetArrayLength(env, derivation);

    jbyte *base_c = (*env)->GetByteArrayElements(env, base, NULL);
    jsize base_len = (*env)->GetArrayLength(env, base);

    jbyte *derived_key_c = (*env)->GetByteArrayElements(env, derived_key, NULL);
    jsize derived_key_len = (*env)->GetArrayLength(env, derived_key);

    // call the C function
    int result = derive_public_key((const uint8_t *) derivation_c, (size_t) output_index,
                                   (const uint8_t *) base_c, (uint8_t *) derived_key_c);

    // Release the C arrays and return the result
    (*env)->ReleaseByteArrayElements(env, derivation, derivation_c, 0);
    (*env)->ReleaseByteArrayElements(env, base, base_c, 0);
    (*env)->ReleaseByteArrayElements(env, derived_key, derived_key_c, 0);

    return (jint) result;
}

JNIEXPORT jint JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_deriveSecretKey(JNIEnv *env, jclass clazz,
    jbyteArray derivation, jint output_index, jbyteArray base, jbyteArray derived_key)
{
    // convert the input byte arrays to C arrays
    jbyte *derivation_c = (*env)->GetByteArrayElements(env, derivation, NULL);
    jsize derivation_len = (*env)->GetArrayLength(env, derivation);

    jbyte *base_c = (*env)->GetByteArrayElements(env, base, NULL);
    jsize base_len = (*env)->GetArrayLength(env, base);

    jbyte *derived_key_c = (*env)->GetByteArrayElements(env, derived_key, NULL);
    jsize derived_key_len = (*env)->GetArrayLength(env, derived_key);

    // call the C function
    int result = derive_secret_key((const uint8_t *) derivation_c, (size_t) output_index,
                                   (const uint8_t *) base_c, (uint8_t *) derived_key_c);

    // Release the C arrays and return the result
    (*env)->ReleaseByteArrayElements(env, derivation, derivation_c, 0);
    (*env)->ReleaseByteArrayElements(env, base, base_c, 0);
    (*env)->ReleaseByteArrayElements(env, derived_key, derived_key_c, 0);

    return (jint) result;
}

