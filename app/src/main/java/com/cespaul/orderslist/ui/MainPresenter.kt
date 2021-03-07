package com.cespaul.orderslist.ui

import com.cespaul.orderslist.App
import com.cespaul.orderslist.base.BasePresenter
import com.cespaul.orderslist.data.repository.OrdersRepository
import com.cespaul.orderslist.data.repository.OrdersRepositoryImpl
import com.cespaul.orderslist.utils.PRIVATE_KEY
import com.cespaul.orderslist.utils.PUBLIC_KEY
import com.google.common.hash.Hashing
import io.reactivex.disposables.Disposable
import timber.log.Timber
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
        onLoadingRefreshState()
        return repository
            .getRequest()
            .subscribe(
                {
                    oAuthRequest = it.requestToken
                    Timber.tag("get_orders").d("success request")
                    access = getAccess()
                },
                {
                    view.visibilityProgressBar(false)
                    view.refreshingSwipe(false)
                    view.showErrorMessage()
                    Timber.tag("get_orders").d("error request")
                }
            )
    }

    private fun getAccess(): Disposable? {
        @Suppress("UnstableApiUsage", "DEPRECATION")
        val pass = Hashing
            .sha1()
            .hashString(
                oAuthRequest + PRIVATE_KEY,
                Charset.defaultCharset()
            )
            .toString()
        return repository
            .getAccess(oAuthRequest, PUBLIC_KEY, pass)
            .subscribe(
                {
                    oAuthAccess = it.accessToken
                    Timber.tag("get_orders").d("success access")
                    orders = getOrders()
                },
                {
                    view.visibilityProgressBar(false)
                    view.refreshingSwipe(false)
                    view.showErrorMessage()
                    Timber.tag("get_orders").d("error access")
                }
            )
    }

    private fun onLoadingRefreshState() {
        if (!view.isRefreshingSwipeProgressBar())
            view.visibilityProgressBar(true)
        else
            view.visibilityProgressBar(false)
    }

    private fun getOrders(): Disposable? {
        return repository
                .loadOrders(oAuthAccess)
                .subscribe(
                        {
                            adapter.updateList(it)
                            view.visibilityProgressBar(false)
                            view.refreshingSwipe(false)
                            Timber.tag("get_orders").d("success")
                        },
                        {
                            view.showErrorMessage()
                            view.visibilityProgressBar(false)
                            view.refreshingSwipe(false)
                            Timber.tag("get_orders").d("error get $it")

                        }
                )
    }

    fun onReload() {
        request = getRequest()
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