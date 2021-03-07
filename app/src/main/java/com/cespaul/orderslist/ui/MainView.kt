package com.cespaul.orderslist.ui

import com.cespaul.orderslist.base.BaseView
import com.google.android.material.snackbar.Snackbar

interface MainView : BaseView {
    fun initSnackbar(message: String, isIndefiniteLength: Boolean): Snackbar

    fun showErrorMessage()

    fun visibilityProgressBar(isVisible: Boolean)

    fun refreshingSwipe(isRefreshing: Boolean)

    fun isRefreshingSwipeProgressBar(): Boolean
}