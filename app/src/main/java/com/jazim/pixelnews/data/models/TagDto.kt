package com.jazim.pixelnews.data.models

import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("coin_counter")
    val coinCounter: Int,
    @SerializedName("ico_counter")
    val icoCounter: Int,
)
