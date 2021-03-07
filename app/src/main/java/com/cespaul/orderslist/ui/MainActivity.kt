package com.cespaul.orderslist.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.orderslist.R
import com.cespaul.orderslist.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        val recycler = recycler_orders
        recycler.layoutManager = layoutManager
        recycler.adapter = presenter.adapter
        val dividerItemDecoration = DividerItemDecoration(
            this,
            layoutManager.orientation
        )
        recycler.addItemDecoration(dividerItemDecoration)

        snackbar = initSnackbar("", false)

        presenter.onViewCreated()

        swipeUpdateOrders.setOnRefreshListener {
            swipeUpdateOrders.isRefreshing = true
            presenter.onReload()
            snackbar.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun initSnackbar(message: String, isIndefiniteLength: Boolean): Snackbar {
        return when (isIndefiniteLength) {
            true -> Snackbar.make(
                main_constraint,
                message,
                Snackbar.LENGTH_INDEFINITE
            )
            false -> Snackbar.make(
                main_constraint,
                message,
                Snackbar.LENGTH_LONG
            )
        }
    }

    override fun showErrorMessage() {
        snackbar = initSnackbar(getString(R.string.error_load_orders), true)
        snackbar.setAction(
            getString(R.string.retry_load_button)
        ) {
            presenter.onReload()
        }
        snackbar.show()
    }

    override fun visibilityProgressBar(isVisible: Boolean) {
        when (isVisible) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.INVISIBLE
        }
    }

    override fun refreshingSwipe(isRefreshing: Boolean) {
        swipeUpdateOrders.isRefreshing = isRefreshing
    }

    override fun isRefreshingSwipeProgressBar(): Boolean {
        return swipeUpdateOrders.isRefreshing
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.swipeUpdateOrders -> {
                swipeUpdateOrders.isRefreshing = true

                presenter.onReload()
                snackbar.dismiss()
                swipeUpdateOrders.isRefreshing = false
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }
}