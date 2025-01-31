package com.ruchabhattjoshi.cstask.data.remote.api

import com.ruchabhattjoshi.cstask.domain.ClearScoreApiService
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import com.ruchabhattjoshi.cstask.domain.RequestState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class ClearScoreApiServiceImpl(private val httpClient: HttpClient) : ClearScoreApiService {

    override suspend fun getLatestCreditInfo(): RequestState<CreditInfo> {
        return try {
            val response = httpClient.get(ApiConfig.BASE_URL + ApiConfig.ENDPOINT)
            if (response.status == HttpStatusCode.OK) {
                val creditInfo = response.body<CreditInfo>()
                RequestState.Success(data = creditInfo)
            } else {
                throw ApiException.ServerException("HTTP Error Code: ${response.status}")
            }
        } catch (_: HttpRequestTimeoutException) {
            RequestState.Error(message = "Request timed out")
        } catch (e: ClientRequestException) {
            RequestState.Error(message = "Client error: ${e.response.status}")
        } catch (e: ServerResponseException) {
            RequestState.Error(message = "Server error: ${e.response.status}")
        } catch (e: ApiException) {
            RequestState.Error(message = e.message ?: "An API error occurred")
        } catch (e: Exception) {
            RequestState.Error(message = "An unexpected error occurred: ${e.message}")
        }
    }
}