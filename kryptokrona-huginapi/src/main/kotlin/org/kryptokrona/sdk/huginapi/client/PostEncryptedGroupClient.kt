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

package org.kryptokrona.sdk.huginapi.client

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.*
import org.kryptokrona.sdk.huginapi.model.HuginAPI
import org.kryptokrona.sdk.huginapi.model.response.PostEncryptedGroupAllResponse
import org.kryptokrona.sdk.huginapi.model.response.PostEncryptedGroupResponse
import org.kryptokrona.sdk.node.common.HttpClient
import org.slf4j.LoggerFactory
import java.nio.channels.UnresolvedAddressException

/**
 * Post Encrypted Group client
 *
 * @author Marcus Cvjeticanin
 * @since 0.4.0
 * @param huginapi The Hugin API that we will connect to.
 */
class PostEncryptedGroupClient(private val huginApi: HuginAPI) {

    private val logger = LoggerFactory.getLogger("PostEncryptedGroupClient")

    /**
     * Get all encrypted group posts from Hugin API.
     *
     * @author Marcus Cvjeticanin
     * @since 0.4.0
     * @return PostEncryptedGroupAllResponse
     */
    suspend fun getAll(): PostEncryptedGroupAllResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            huginApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${huginApi.hostName}:${huginApi.port}/api/v1/posts-encrypted")
                } else {
                    url.takeFrom("http://${huginApi.hostName}:${huginApi.port}/api/v1/posts-encrypted")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<PostEncryptedGroupAllResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting all encrypted group posts from Hugin API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting all encrypted group posts from Hugin API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting all encrypted group posts from Hugin API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get encrypted group post by tx hash from Hugin API.
     *
     * @author Marcus Cvjeticanin
     * @since 0.4.0
     * @return PostEncryptedGroupTxHashResponse
     */
    suspend fun getByTxHash(txHash: String): PostEncryptedGroupResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            huginApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${huginApi.hostName}:${huginApi.port}/api/v1/posts-encrypted/$txHash")
                } else {
                    url.takeFrom("http://${huginApi.hostName}:${huginApi.port}/api/v1/posts-encrypted/$txHash")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<PostEncryptedGroupResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting encrypted group post by tx hash from Hugin API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting encrypted group post by tx hash from Hugin API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting encrypted group post by tx hash from Hugin API. Could not parse the response.", e)
        }

        return null
    }
}