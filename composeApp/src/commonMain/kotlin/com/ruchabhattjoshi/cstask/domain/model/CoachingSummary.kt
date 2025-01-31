package com.ruchabhattjoshi.cstask.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoachingSummary(
    @SerialName("activeChat")
    val activeChat: Boolean,
    @SerialName("activeTodo")
    val activeTodo: Boolean,
    @SerialName("numberOfCompletedTodoItems")
    val numberOfCompletedTodoItems: Int,
    @SerialName("numberOfTodoItems")
    val numberOfTodoItems: Int,
    @SerialName("selected")
    val selected: Boolean
)