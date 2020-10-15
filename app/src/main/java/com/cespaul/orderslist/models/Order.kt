package com.cespaul.orderslist.models

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("CustomId") val customId: String?,
    @SerializedName("Title") val title: String?,
    @SerializedName("Status") val status: String?,
    @SerializedName("PaymentStatus") val paymentStatus: String?,
    @SerializedName("DeliveryAddress") val deliveryAddress: DeliveryAddress?,
    @SerializedName("Shipping") val shipping: Shipping?,
    @SerializedName("Price") val price: Int?,
    @SerializedName("DiscountPrice") val discountPrice: String?,
    @SerializedName("DeliveryPrice") val deliveryPrice: String?,
    @SerializedName("TotalPrice") val totalPrice: String?,
    @SerializedName("PaidPrice") val paidPrice: String?,
    @SerializedName("UserId") val userId: Int?,
    @SerializedName("UserCompanyAccountId") val userCompanyAccountId: Int?,
    @SerializedName("DateCreated") val dateCreated: String?,
    @SerializedName("DateModified") val dateModified: String?,
    @SerializedName("DatePaid") val datePaid: String?,
)