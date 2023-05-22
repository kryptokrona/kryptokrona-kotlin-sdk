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
#include "crypto-ops.h"

JNIEXPORT void JNICALL Java_org_kryptokrona_sdk_crypto_CryptoOps_scReduce32(JNIEnv *env, jclass clazz,
    jbyteArray bytes, jbyteArray seed) {

    // get the byte arrays and their lengths
    jsize bytes_len = (*env)->GetArrayLength(env, bytes);
    jsize seed_len = (*env)->GetArrayLength(env, seed);
    uint8_t *bytes_ptr = (uint8_t *)(*env)->GetByteArrayElements(env, bytes, NULL);
    uint8_t *seed_ptr = (uint8_t *)(*env)->GetByteArrayElements(env, seed, NULL);

    // perform the reduction operation
    sc_reduce32(bytes_ptr);

    // release the byte arrays
    (*env)->ReleaseByteArrayElements(env, bytes, (jbyte *)bytes_ptr, 0);
    (*env)->ReleaseByteArrayElements(env, seed, (jbyte *)seed_ptr, JNI_ABORT);
}
