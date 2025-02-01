package com.ruchabhattjoshi.cstask.presentation.screen

import com.ruchabhattjoshi.cstask.domain.ClearScoreApiService
import com.ruchabhattjoshi.cstask.domain.RequestState
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo

class FakeClearScoreApiService(private var response: RequestState<CreditInfo>) :
    ClearScoreApiService {
    override suspend fun getLatestCreditInfo(): RequestState<CreditInfo> {
        return response
    }
}