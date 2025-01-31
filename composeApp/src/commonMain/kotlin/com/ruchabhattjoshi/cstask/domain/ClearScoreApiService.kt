package com.ruchabhattjoshi.cstask.domain

import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import com.ruchabhattjoshi.cstask.domain.RequestState

interface ClearScoreApiService {
    suspend fun getLatestCreditInfo():
            RequestState<CreditInfo>
}