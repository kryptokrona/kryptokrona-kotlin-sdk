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

#include <jni.h>
#ifndef _Included_JNI_Crypto
#define _Included_JNI_Crypto
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_generateKeyDerivation(JNIEnv* env, jobject thiz,
    jbyteArray tx_pub_key, jbyteArray priv_view_key, jbyteArray key_derivation);

JNIEXPORT jint JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_underivePublicKey(
    JNIEnv *env, jclass clazz, jbyteArray derivation, jlong output_index, jbyteArray derived_key, jbyteArray base);

JNIEXPORT void JNICALL Java_org_kryptokrona_sdk_crypto_Crypto_generateKeyImage(JNIEnv *env, jclass clazz,
    jbyteArray pub, jbyteArray sec, jbyteArray image);

#ifdef __cplusplus
}
#endif
#endif
