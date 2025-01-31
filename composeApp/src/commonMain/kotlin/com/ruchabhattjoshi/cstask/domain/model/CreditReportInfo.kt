package com.ruchabhattjoshi.cstask.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditReportInfo(
    @SerialName("changeInLongTermDebt")
    val changeInLongTermDebt: Int,
    @SerialName("changeInShortTermDebt")
    val changeInShortTermDebt: Int,
    @SerialName("changedScore")
    val changedScore: Int,
    @SerialName("clientRef")
    val clientRef: String,
    @SerialName("currentLongTermCreditLimit")
    val currentLongTermCreditLimit: Int?,
    @SerialName("currentLongTermCreditUtilisation")
    val currentLongTermCreditUtilisation: Int?,
    @SerialName("currentLongTermDebt")
    val currentLongTermDebt: Int,
    @SerialName("currentLongTermNonPromotionalDebt")
    val currentLongTermNonPromotionalDebt: Int,
    @SerialName("currentShortTermCreditLimit")
    val currentShortTermCreditLimit: Int,
    @SerialName("currentShortTermCreditUtilisation")
    val currentShortTermCreditUtilisation: Int,
    @SerialName("currentShortTermDebt")
    val currentShortTermDebt: Int,
    @SerialName("currentShortTermNonPromotionalDebt")
    val currentShortTermNonPromotionalDebt: Int,
    @SerialName("daysUntilNextReport")
    val daysUntilNextReport: Int,
    @SerialName("equifaxScoreBand")
    val equifaxScoreBand: Int,
    @SerialName("equifaxScoreBandDescription")
    val equifaxScoreBandDescription: String,
    @SerialName("hasEverBeenDelinquent")
    val hasEverBeenDelinquent: Boolean,
    @SerialName("hasEverDefaulted")
    val hasEverDefaulted: Boolean,
    @SerialName("maxScoreValue")
    val maxScoreValue: Int,
    @SerialName("minScoreValue")
    val minScoreValue: Int,
    @SerialName("monthsSinceLastDefaulted")
    val monthsSinceLastDefaulted: Int,
    @SerialName("monthsSinceLastDelinquent")
    val monthsSinceLastDelinquent: Int,
    @SerialName("numNegativeScoreFactors")
    val numNegativeScoreFactors: Int,
    @SerialName("numPositiveScoreFactors")
    val numPositiveScoreFactors: Int,
    @SerialName("percentageCreditUsed")
    val percentageCreditUsed: Int,
    @SerialName("percentageCreditUsedDirectionFlag")
    val percentageCreditUsedDirectionFlag: Int,
    @SerialName("score")
    val score: Int,
    @SerialName("scoreBand")
    val scoreBand: Int,
    @SerialName("status")
    val status: String
)