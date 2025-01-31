package com.ruchabhattjoshi.cstask.presentation.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ruchabhattjoshi.cstask.domain.ClearScoreApiService
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import com.ruchabhattjoshi.cstask.domain.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val api: ClearScoreApiService,
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
        try {
            _clearScoreInfo.value = RequestState.Loading
            _clearScoreInfo.value = api.getLatestCreditInfo()
        } catch (e: Exception) {
            println(e.message)
        }
    }

}