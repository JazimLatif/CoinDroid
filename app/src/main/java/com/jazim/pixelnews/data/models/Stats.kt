package com.jazim.pixelnews.data.models

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("contributors")
    val contributors: Int,
    @SerializedName("stars")
    val stars: Int,
    @SerializedName("followers")
    val followers: Int,
)
