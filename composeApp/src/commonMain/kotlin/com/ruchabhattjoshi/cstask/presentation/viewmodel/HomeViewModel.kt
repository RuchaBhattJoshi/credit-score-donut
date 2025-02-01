package com.ruchabhattjoshi.cstask.presentation.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ruchabhattjoshi.cstask.data.remote.api.ClearScoreApiService
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import com.ruchabhattjoshi.cstask.domain.RequestState
import com.ruchabhattjoshi.cstask.repository.CreditInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repository: CreditInfoRepository,
) : ScreenModel {

    private val _clearScoreInfo: MutableStateFlow<RequestState<CreditInfo>?> =
        MutableStateFlow(RequestState.Loading)
    val clearScoreInfo: StateFlow<RequestState<CreditInfo>?> = _clearScoreInfo

    init {
        screenModelScope.launch {
            fetchNewCreditInfo()
        }
    }

    suspend fun fetchNewCreditInfo() {
        repository.getCreditInfo().collectLatest {
            _clearScoreInfo.value = it
        }
    }

}