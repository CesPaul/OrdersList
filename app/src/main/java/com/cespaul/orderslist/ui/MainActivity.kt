package com.cespaul.orderslist.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.orderslist.R
import com.cespaul.orderslist.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity<MainPresenter>(), MainView {

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

        presenter.onViewCreated()
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
            ).setAnchorView(main_constraint)
            false -> Snackbar.make(
                main_constraint,
                message,
                Snackbar.LENGTH_LONG
            ).setAnchorView(main_constraint)
        }
    }

    override fun visibilityProgressBar(isVisible: Boolean) {
        when (isVisible) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.INVISIBLE
        }
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }
}