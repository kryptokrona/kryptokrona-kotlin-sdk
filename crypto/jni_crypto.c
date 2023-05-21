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
#include "hash.h"

JNIEXPORT jint JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_generateKeyDerivation(JNIEnv* env, jclass clazz,
    jbyteArray key, jbyteArray derivation, jbyteArray output)
{
    uint8_t *key_bytes = (uint8_t *)(*env)->GetByteArrayElements(env, key, NULL);
    uint8_t *derivation_bytes = (uint8_t *)(*env)->GetByteArrayElements(env, derivation, NULL);
    uint8_t *output_bytes = (uint8_t *)(*env)->GetByteArrayElements(env, output, NULL);

    // call the C function
    int result = generate_key_derivation(key_bytes, derivation_bytes, output_bytes);

    (*env)->ReleaseByteArrayElements(env, key, (jbyte *)key_bytes, JNI_ABORT);
    (*env)->ReleaseByteArrayElements(env, derivation, (jbyte *)derivation_bytes, JNI_ABORT);
    (*env)->ReleaseByteArrayElements(env, output, (jbyte *)output_bytes, 0);

    return result;
}

JNIEXPORT jint JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_underivePublicKey(JNIEnv *env, jclass clazz,
    jbyteArray derivation, jint output_index, jbyteArray pub, jbyteArray derived_key)
{
    uint8_t *derivation_bytes = (uint8_t *)(*env)->GetByteArrayElements(env, derivation, NULL);
    uint8_t *pub_bytes = (uint8_t *)(*env)->GetByteArrayElements(env, pub, NULL);
    uint8_t *derived_key_bytes = (uint8_t *)(*env)->GetByteArrayElements(env, derived_key, NULL);

    // call the C function
    int result = underive_public_key(derivation_bytes, output_index, pub_bytes, derived_key_bytes);

    (*env)->ReleaseByteArrayElements(env, derivation, (jbyte *)derivation_bytes, JNI_ABORT);
    (*env)->ReleaseByteArrayElements(env, pub, (jbyte *)pub_bytes, JNI_ABORT);
    (*env)->ReleaseByteArrayElements(env, derived_key, (jbyte *)derived_key_bytes, 0);

    return result;
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
  uint8_t *c_derivation = (uint8_t*) (*env)->GetByteArrayElements(env, derivation, NULL);
  uint8_t *c_base = (uint8_t*) (*env)->GetByteArrayElements(env, base, NULL);
  uint8_t *c_derived_key = (uint8_t*) (*env)->GetByteArrayElements(env, derived_key, NULL);

  int result = derive_public_key(c_derivation, output_index, c_base, c_derived_key);

  (*env)->ReleaseByteArrayElements(env, derivation, (jbyte*)c_derivation, JNI_ABORT);
  (*env)->ReleaseByteArrayElements(env, base, (jbyte*)c_base, JNI_ABORT);
  (*env)->ReleaseByteArrayElements(env, derived_key, (jbyte*)c_derived_key, 0);

  return result;
}

JNIEXPORT void JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_deriveSecretKey(JNIEnv *env, jclass clazz,
    jbyteArray derivation, jint output_index, jbyteArray base, jbyteArray derived_key)
{
  uint8_t *c_derivation = (uint8_t*) (*env)->GetByteArrayElements(env, derivation, NULL);
  uint8_t *c_base = (uint8_t*) (*env)->GetByteArrayElements(env, base, NULL);
  uint8_t *c_derived_key = (uint8_t*) (*env)->GetByteArrayElements(env, derived_key, NULL);

  derive_secret_key(c_derivation, output_index, c_base, c_derived_key);

  (*env)->ReleaseByteArrayElements(env, derivation, (jbyte*)c_derivation, JNI_ABORT);
  (*env)->ReleaseByteArrayElements(env, base, (jbyte*)c_base, JNI_ABORT);
  (*env)->ReleaseByteArrayElements(env, derived_key, (jbyte*)c_derived_key, 0);
}

JNIEXPORT void JNICALL Java_org_kryptokrona_sdk_crypto_Hash_cnFastHash(JNIEnv* env, jclass clazz,
    jbyteArray data, jlong length, jbyteArray hash) {
  // get the byte arrays as native C pointers
  jbyte* c_data = (*env)->GetByteArrayElements(env, data, NULL);
  jbyte* c_hash = (*env)->GetByteArrayElements(env, hash, NULL);

  // call cn_fast_hash with the provided data and length
  cn_fast_hash((const void*)c_data, (size_t)length, (char*)c_hash);

  // release the acquired native C pointers
  (*env)->ReleaseByteArrayElements(env, data, c_data, JNI_ABORT);
  (*env)->ReleaseByteArrayElements(env, hash, c_hash, 0);
}
