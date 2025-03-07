package com.jazim.pixelnews.data.models

import com.google.gson.annotations.SerializedName

data class LinkExtended(
    @SerializedName("url")
    val url: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("stats")
    val stats: Stats
)
