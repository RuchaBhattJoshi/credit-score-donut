package com.ruchabhattjoshi.cstask.presentation.screen

import com.ruchabhattjoshi.cstask.domain.RequestState
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import com.ruchabhattjoshi.cstask.repository.CreditInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCreditInfoRepository(
    private val requestState: RequestState<CreditInfo>
) : CreditInfoRepository {
    override fun getCreditInfo(): Flow<RequestState<CreditInfo>> =
        flow {
        emit(requestState)
    }
}