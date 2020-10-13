package com.cespaul.orderslist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.orderslist.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        val recycler = recycler_orders
        recycler.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            recycler.context,
            layoutManager.orientation
        )
        recycler.addItemDecoration(dividerItemDecoration)
    }
}