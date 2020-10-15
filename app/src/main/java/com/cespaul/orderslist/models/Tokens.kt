package com.cespaul.orderslist.models

import com.google.gson.annotations.SerializedName

data class Tokens(
    @SerializedName("RequestToken")
    val RequestToken: String = "",

    @SerializedName("AccessToken")
    val AccessToken: String = ""
)