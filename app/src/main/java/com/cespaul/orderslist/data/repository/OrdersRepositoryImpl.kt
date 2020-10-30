package com.cespaul.orderslist.data.repository

import com.cespaul.orderslist.data.network.OrdersApi
import com.cespaul.orderslist.models.Order
import com.cespaul.orderslist.models.Orders
import com.cespaul.orderslist.models.Tokens
import com.cespaul.orderslist.utils.GRANT_TYPE
import com.cespaul.orderslist.utils.NUMBER_ORDERS
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OrdersRepositoryImpl(private val ordersApi: OrdersApi) : OrdersRepository {

    private var ordersList: List<Order> = listOf()

    override fun getRequest(): Observable<Tokens> {
        return ordersApi.getRequestToken()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getAccess(oauth: String, login: String, password: String): Observable<Tokens> {
        return ordersApi.getAccessToken(oauth, GRANT_TYPE, login, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun loadOrders(oAuthAccess: String): Observable<Orders> {
        return ordersApi.getOrders(oAuthAccess, NUMBER_ORDERS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getOrdersList(): List<Order> = ordersList


    override fun getOrders(): Orders {
        return Orders(ordersList)
    }
}