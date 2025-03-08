package com.jazim.pixelnews.data.models

import com.google.gson.annotations.SerializedName

data class TeamDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: Int
)
