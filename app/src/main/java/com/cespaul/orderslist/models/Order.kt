package com.cespaul.orderslist.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Order(
    @SerializedName("CustomId") val customId: String?,
    @SerializedName("Title") val title: String?,
    @SerializedName("Status") val status: String?,
    @SerializedName("PaymentStatus") val paymentStatus: String?,
    @SerializedName("DeliveryAddress") val deliveryAddress: DeliveryAddress?,
    @SerializedName("Shipping") val shipping: Shipping?,
    @SerializedName("Price") val price: BigDecimal?,
    @SerializedName("DiscountPrice") val discountPrice: BigDecimal?,
    @SerializedName("DeliveryPrice") val deliveryPrice: BigDecimal?,
    @SerializedName("TotalPrice") val totalPrice: BigDecimal?,
    @SerializedName("PaidPrice") val paidPrice: BigDecimal?,
    @SerializedName("UserId") val userId: Int?,
    @SerializedName("UserCompanyAccountId") val userCompanyAccountId: Int?,
    @SerializedName("DateCreated") val dateCreated: String?,
    @SerializedName("DateModified") val dateModified: String?,
    @SerializedName("DatePaid") val datePaid: String?,
)