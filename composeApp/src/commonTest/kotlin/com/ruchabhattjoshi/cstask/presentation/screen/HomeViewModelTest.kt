package com.ruchabhattjoshi.cstask.presentation.screen

import com.ruchabhattjoshi.cstask.domain.RequestState
import com.ruchabhattjoshi.cstask.domain.model.CoachingSummary
import com.ruchabhattjoshi.cstask.domain.model.CreditInfo
import com.ruchabhattjoshi.cstask.domain.model.CreditReportInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class HomeViewModelTest {

    private lateinit var fakeApi: FakeClearScoreApiService
    private lateinit var viewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchNewCreditInfo should update state with success`() = runTest {
        val creditInfo = CreditInfo(
            accountIDVStatus = "MATCH",
            augmentedCreditScore = null,
            coachingSummary = CoachingSummary(
                activeChat = false,
                activeTodo = false,
                numberOfCompletedTodoItems = 0,
                numberOfTodoItems = 1,
                selected = false
            ),
        creditReportInfo = CreditReportInfo(
            score = 514,
            maxScoreValue = 700,
            changeInLongTermDebt = 0,
            changeInShortTermDebt = 0,
            changedScore = 0,
            clientRef = "CS-SED-655426-708782",
            currentLongTermCreditLimit = null,
            currentLongTermCreditUtilisation = null,
            currentLongTermDebt = 0,
            currentLongTermNonPromotionalDebt = 0,
            currentShortTermCreditLimit = 0,
            currentShortTermCreditUtilisation = 0,
            currentShortTermDebt = 0,
            currentShortTermNonPromotionalDebt = 0,
            daysUntilNextReport = 0,
            equifaxScoreBand = 0,
            equifaxScoreBandDescription = "",
            hasEverBeenDelinquent = false,
            hasEverDefaulted = false,
            minScoreValue = 0,
            monthsSinceLastDefaulted = 0,
            monthsSinceLastDelinquent = 0,
            numNegativeScoreFactors = 0,
            numPositiveScoreFactors = 0,
            percentageCreditUsed = 0,
            percentageCreditUsedDirectionFlag = 0,
            scoreBand = 0,
            status = "ACTIVE"
        ),
        dashboardStatus = "ACTIVE",
        personaType = "CLASSIC"
        )
        fakeApi = FakeClearScoreApiService(RequestState.Success(creditInfo))
        viewModel = HomeViewModel(fakeApi)

        viewModel.fetchNewCreditInfo()

        assertEquals(RequestState.Success(creditInfo), viewModel.clearScoreInfo.value)
    }

    @Test
    fun `fetchNewCreditInfo should update state with error on failure`() = runTest {

        fakeApi = FakeClearScoreApiService(RequestState.Error("Network Error"))
        viewModel = HomeViewModel(fakeApi)

        viewModel.fetchNewCreditInfo()

        assertTrue(viewModel.clearScoreInfo.value is RequestState.Error)
    }
}