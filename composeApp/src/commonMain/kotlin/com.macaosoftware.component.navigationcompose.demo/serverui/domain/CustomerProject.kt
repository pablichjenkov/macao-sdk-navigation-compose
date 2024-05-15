package com.macaosoftware.component.navigationcompose.demo.serverui.domain

import kotlinx.serialization.Serializable

@Serializable
class CustomerProject(
    val ownerId: String,
    val jsonMetadata: String
) {
    companion object {
        const val EntityKind = "CustomerProjectEntity"

        object FIELDS {
            const val ownerId = "ownerId"
            const val jsonMetadata = "jsonMetadata"
        }
    }
}
