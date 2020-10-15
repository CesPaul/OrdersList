package com.cespaul.orderslist.ui

import android.util.Log
import com.cespaul.orderslist.App
import com.cespaul.orderslist.base.BasePresenter
import com.cespaul.orderslist.data.repository.OrdersRepository
import com.cespaul.orderslist.data.repository.OrdersRepositoryImpl
import com.cespaul.orderslist.utils.PRIVATE_KEY
import com.cespaul.orderslist.utils.PUBLIC_KEY
import com.google.common.hash.Hashing
import io.reactivex.disposables.Disposable
import java.nio.charset.Charset

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {

    private val ordersApi = App.ordersApi

    private var oAuthRequest: String = ""
    private var oAuthAccess: String = ""

    private var request: Disposable? = null
    private var access: Disposable? = null
    private var orders: Disposable? = null

    private val repository: OrdersRepository = OrdersRepositoryImpl(ordersApi)

    var adapter: OrdersRvAdapter = OrdersRvAdapter(
        view.getContext(),
        repository
    )

    private fun getRequest(): Disposable? {
        view.visibilityProgressBar(true)
        return repository
            .getRequest()
            .subscribe(
                {
                    oAuthRequest = it.RequestToken
                    Log.d("get_orders", "success request")
                    access = getAccess()
                },
                {
                    view.visibilityProgressBar(false)
                    Log.d("get_orders", "error request")
                }
            )
    }

    private fun getAccess(): Disposable? {
        val pass = Hashing.sha1().hashString(oAuthRequest + PRIVATE_KEY, Charset.defaultCharset())
            .toString()
        return repository
            .getAccess(oAuthRequest, PUBLIC_KEY, pass)
            .subscribe(
                {
                    oAuthAccess = it.AccessToken
                    Log.d("get_orders", "success access")
                    orders = getOrders()
                },
                {
                    Log.d("get_orders", "error access")
                }
            )
    }

    private fun getOrders(): Disposable? {
        return repository
            .loadOrders(oAuthAccess)
            .subscribe(
                {
                    view.visibilityProgressBar(false)
                    adapter.updateList(it)
                    Log.d("get_orders", "success")
                },
                {
                    Log.d("get_orders", "error get")
                }
            )
    }

    override fun onViewCreated() {
        request = getRequest()
    }

    override fun onViewDestroyed() {
        request?.dispose()
        access?.dispose()
        orders?.dispose()
    }
}