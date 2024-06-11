package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import kotlinx.serialization.Serializable

@Serializable
data class MacaoApiError(
    val instanceId: Long = -1L,
    val errorCode: Int,
    val errorDescription: String
)
