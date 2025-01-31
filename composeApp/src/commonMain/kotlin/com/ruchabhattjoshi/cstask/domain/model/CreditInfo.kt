package com.ruchabhattjoshi.cstask.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditInfo(
    @SerialName("accountIDVStatus")
    val accountIDVStatus: String,
    @SerialName("augmentedCreditScore")
    val augmentedCreditScore: Int?,
    @SerialName("coachingSummary")
    val coachingSummary: CoachingSummary,
    @Contextual @SerialName("creditReportInfo")
    val creditReportInfo: CreditReportInfo,
    @SerialName("dashboardStatus")
    val dashboardStatus: String,
    @SerialName("personaType")
    val personaType: String
)