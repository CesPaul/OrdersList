package com.cespaul.orderslist.data.network

import com.cespaul.orderslist.models.Orders
import com.cespaul.orderslist.models.Tokens
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OrdersApi {

    @GET("oauth/requesttoken")
    fun getRequestToken(): Observable<Tokens>

    @GET("oauth/accesstoken")
    fun getAccessToken(
        @Query("oauth_token") oauth_token: String,
        @Query("grant_type") grantType: String,
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<Tokens>

    @GET("orders")
    fun getOrders(
        @Query("oauth_token") oauth_token: String
    ): Observable<Orders>
}