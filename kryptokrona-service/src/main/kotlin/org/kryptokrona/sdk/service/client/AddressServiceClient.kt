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

package org.kryptokrona.sdk.service.client

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.kryptokrona.sdk.service.common.HttpClient
import org.kryptokrona.sdk.service.model.Service
import org.kryptokrona.sdk.service.model.request.address.CreateAddressRequest
import org.kryptokrona.sdk.service.model.request.address.CreateIntegratedAddressRequest
import org.kryptokrona.sdk.service.model.request.address.DeleteAddressRequest
import org.kryptokrona.sdk.service.model.request.address.GetAddressesRequest
import org.kryptokrona.sdk.service.model.request.balance.BalanceRequest
import org.kryptokrona.sdk.service.model.response.address.CreateAddressResponse
import org.kryptokrona.sdk.service.model.response.address.CreateIntegratedAddressResponse
import org.kryptokrona.sdk.service.model.response.address.DeleteAddressResponse
import org.kryptokrona.sdk.service.model.response.address.GetAddressesResponse
import org.kryptokrona.sdk.service.model.response.balance.BalanceResponse
import org.slf4j.LoggerFactory
import java.nio.channels.UnresolvedAddressException

/**
 * Address Service Client
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 * @param service The service to connect to.
 */
class AddressServiceClient(private val service: Service) {

    private val logger = LoggerFactory.getLogger("AddressServiceClient")

    /**
     * Create a new address.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param createAddressRequest The request to create a new address.
     * @return The response from the server.
     */
    suspend fun createAddress(createAddressRequest: CreateAddressRequest): CreateAddressResponse? {
        val jsonBody = Json.encodeToString(createAddressRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<CreateAddressResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error creating address. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error creating address. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error creating address. Could not parse the response.", e)
        }

        return null
    }

    // createAddressList

    /**
     * Delete an address.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param deleteAddressRequest The request to delete an address.
     * @return The response from the server.
     */
    suspend fun deleteAddress(deleteAddressRequest: DeleteAddressRequest): DeleteAddressResponse? {
        val jsonBody = Json.encodeToString(deleteAddressRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<DeleteAddressResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error deleting address. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error deleting address. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error deleting address. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get a list of addresses for the wallet.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param getAddressesRequest The request object.
     * @return The response object.
     */
    suspend fun getAddresses(getAddressesRequest: GetAddressesRequest): GetAddressesResponse? {
        val jsonBody = Json.encodeToString(getAddressesRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<GetAddressesResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting addresses. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting addresses. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting addresses. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Create an integrated address.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param createIntegratedAddressRequest The request.
     * @return The response.
     */
    suspend fun createIntegratedAddress(createIntegratedAddressRequest: CreateIntegratedAddressRequest):
            CreateIntegratedAddressResponse? {
        val jsonBody = Json.encodeToString(createIntegratedAddressRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<CreateIntegratedAddressResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error creating integrated address. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error creating integrated address. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error creating integrated address. Could not parse the response.", e)
        }

        return null
    }
}
