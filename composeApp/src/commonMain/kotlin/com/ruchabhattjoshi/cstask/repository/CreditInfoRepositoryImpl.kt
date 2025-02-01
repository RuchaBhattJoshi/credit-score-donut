package com.ruchabhattjoshi.cstask.repository

import com.ruchabhattjoshi.cstask.data.remote.api.ClearScoreApiService
import com.ruchabhattjoshi.cstask.domain.RequestState
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreditInfoRepositoryImpl(
    private val api: ClearScoreApiService
) : CreditInfoRepository {
    override fun getCreditInfo(): Flow<RequestState<CreditInfo>> =
        flow {
        emit(api.getLatestCreditInfo())
    }
}