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
    printf("createKeyPair() called\n");
    unsigned char pk[32];
    unsigned char sk[64];

    // Convert the Java byte arrays to C arrays
    jbyte *pk_bytes = (*env)->GetByteArrayElements(env, publicKey, NULL);
    jbyte *sk_bytes = (*env)->GetByteArrayElements(env, privateKey, NULL);
    jbyte *seed_bytes = (*env)->GetByteArrayElements(env, seed, NULL);

    // Call the ed25519_create_keypair function
    ed25519_create_keypair(pk, sk, (const unsigned char *) seed_bytes);

    // Copy the results back to the Java byte arrays
    (*env)->SetByteArrayRegion(env, publicKey, 0, 32, (jbyte *) pk);
    (*env)->SetByteArrayRegion(env, privateKey, 0, 64, (jbyte *) sk);

    // Release the C arrays
    (*env)->ReleaseByteArrayElements(env, publicKey, pk_bytes, 0);
    (*env)->ReleaseByteArrayElements(env, privateKey, sk_bytes, 0);
    (*env)->ReleaseByteArrayElements(env, seed, seed_bytes, JNI_ABORT);
}
