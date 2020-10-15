package com.cespaul.orderslist.models

import com.google.gson.annotations.SerializedName

data class DeliveryAddress(
    @SerializedName("ZipCode") val zipCode: String?,
    @SerializedName("AddressLine1") val addressLine1: String?,
    @SerializedName("City") val city: String?,
    @SerializedName("Country") val country: String?,
    @SerializedName("State") val state: String?,
    @SerializedName("FullName") val fullName: String?,
    @SerializedName("Phone") val phone: String?
)