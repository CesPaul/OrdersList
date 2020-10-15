package com.cespaul.orderslist.models

import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("Result")
    val Result: List<Order>
)