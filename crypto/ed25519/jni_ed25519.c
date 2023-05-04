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
#include "jni_ed25519.h"
#include "ed25519.h"

JNIEXPORT void JNICALL Java_org_kryptokrona_sdk_crypto_Ed25519_createKeyPair(JNIEnv *env, jobject obj,
    jbyteArray publicKey, jbyteArray privateKey, jbyteArray seed) {

    // Get pointers to the Java/Kotlin byte arrays
    jbyte *pk = (*env)->GetByteArrayElements(env, publicKey, NULL);
    jbyte *sk = (*env)->GetByteArrayElements(env, privateKey, NULL);
    jbyte *sd = (*env)->GetByteArrayElements(env, seed, NULL);

    // Call the ed25519_create_keypair function
    ed25519_create_keypair((unsigned char *) pk, (unsigned char *) sk, (const unsigned char *) sd);

    // Update the lengths of the key arrays
    int pk_len = 32;
    int sk_len = 64;

    // Copy the generated keys back to the Java/Kotlin byte arrays
    (*env)->SetByteArrayRegion(env, publicKey, 0, pk_len, pk);
    (*env)->SetByteArrayRegion(env, privateKey, 0, sk_len, sk);

    // Release the Java/Kotlin byte arrays
    (*env)->ReleaseByteArrayElements(env, publicKey, pk, 0);
    (*env)->ReleaseByteArrayElements(env, privateKey, sk, 0);
    (*env)->ReleaseByteArrayElements(env, seed, sd, JNI_ABORT);
}


