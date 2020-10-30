package com.cespaul.orderslist.models

import com.google.gson.annotations.SerializedName

data class Tokens(
    @SerializedName("RequestToken")
    val requestToken: String = "",

    @SerializedName("AccessToken")
    val accessToken: String = ""
)