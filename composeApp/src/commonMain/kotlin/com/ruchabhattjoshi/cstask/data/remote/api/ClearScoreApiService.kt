package com.ruchabhattjoshi.cstask.data.remote.api

import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import com.ruchabhattjoshi.cstask.domain.RequestState

interface ClearScoreApiService {
    suspend fun getLatestCreditInfo():
            RequestState<CreditInfo>
}