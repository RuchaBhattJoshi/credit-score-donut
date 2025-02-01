package com.ruchabhattjoshi.cstask.repository

import com.ruchabhattjoshi.cstask.domain.RequestState
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import kotlinx.coroutines.flow.Flow

interface CreditInfoRepository {
    fun getCreditInfo(): Flow<RequestState<CreditInfo>>
}