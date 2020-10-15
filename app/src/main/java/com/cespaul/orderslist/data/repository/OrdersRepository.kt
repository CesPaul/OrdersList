package com.cespaul.orderslist.data.repository

import com.cespaul.orderslist.models.Order
import com.cespaul.orderslist.models.Orders
import com.cespaul.orderslist.models.Tokens
import io.reactivex.Observable

interface OrdersRepository {

    fun getRequest(): Observable<Tokens>

    fun getAccess(oauth: String, login: String, password: String): Observable<Tokens>

    fun loadOrders(oAuthAccess: String): Observable<Orders>

    fun getOrdersList(): List<Order>

    fun getOrders(): Orders

}