package com.cespaul.orderslist.models

import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("Id") val id: Int,
    @SerializedName("Title") val title: String?,
    @SerializedName("Phone") val phone: String?,
    @SerializedName("Email") val email: String?
)