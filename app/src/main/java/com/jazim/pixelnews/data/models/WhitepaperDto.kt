package com.jazim.pixelnews.data.models

import com.google.gson.annotations.SerializedName

data class WhitepaperDto(
    @SerializedName("link")
    val link: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)
