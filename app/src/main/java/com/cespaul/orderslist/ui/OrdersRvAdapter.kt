package com.cespaul.orderslist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cespaul.orderslist.R
import com.cespaul.orderslist.data.repository.OrdersRepository
import com.cespaul.orderslist.models.Orders
import com.google.common.base.CharMatcher
import kotlinx.android.synthetic.main.order_row.view.*
import java.text.SimpleDateFormat

class OrdersRvAdapter(
    private val context: Context,
    ordersRepository: OrdersRepository
) : RecyclerView.Adapter<OrdersRvAdapter.OrdersVH>() {

    private var ordersList = ordersRepository.getOrdersList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersVH {
        return OrdersVH(
            LayoutInflater.from(context).inflate(
                R.layout.order_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    fun updateList(orders: Orders) {
        this.ordersList = orders.Result
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(vh: OrdersVH, position: Int) {
        val orderItem = ordersList[position]
        vh.customId.text =
            context.resources.getString(R.string.customId, orderItem.customId.toString())
        vh.status.text = orderItem.status
        vh.paymentStatus.text =
            context.resources.getString(R.string.paymentStatus, orderItem.paymentStatus.toString())
        vh.title.text = orderItem.title
        vh.zipCode.text = orderItem.deliveryAddress?.zipCode.toString()
        vh.addressLine.text = orderItem.deliveryAddress?.addressLine1
        vh.country.text = orderItem.deliveryAddress?.country
        vh.state.text = orderItem.deliveryAddress?.state
        vh.city.text = orderItem.deliveryAddress?.city
        vh.fullName.text = context.resources.getString(
            R.string.fullName,
            orderItem.deliveryAddress?.fullName.toString()
        )
        vh.phone.text =
            context.resources.getString(R.string.phone, orderItem.deliveryAddress?.phone.toString())
        vh.titleShipping.text =
            context.resources.getString(R.string.titleShipping, orderItem.shipping?.title)
        vh.phoneShipping.text =
            context.resources.getString(R.string.phoneShipping, orderItem.shipping?.phone)
        vh.emailShipping.text =
            context.resources.getString(R.string.emailShipping, orderItem.shipping?.email)
        vh.dateCreated.text = context.resources.getString(
            R.string.dateCreated,
            orderItem.dateCreated?.let { convertDate(it) })
        vh.dateModified.text = context.resources.getString(
            R.string.dateModified,
            orderItem.dateModified?.let { convertDate(it) })
        vh.datePaid.text = context.resources.getString(
            R.string.datePaid,
            orderItem.datePaid?.let { convertDate(it) })
        vh.price.text = context.resources.getString(R.string.price, orderItem.price.toString())
        vh.discountPrice.text =
            context.resources.getString(R.string.discountPrice, orderItem.discountPrice.toString())
        vh.deliveryPrice.text =
            context.resources.getString(R.string.deliveryPrice, orderItem.deliveryPrice.toString())
        vh.totalPrice.text =
            context.resources.getString(R.string.totalPrice, orderItem.totalPrice.toString())
    }

    @SuppressLint("SimpleDateFormat")
    @Suppress("DEPRECATION")
    private fun convertDate(inputDate: String): String {
        val numberDate = CharMatcher.javaDigit().retainFrom(inputDate)
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        return simpleDateFormat.format(numberDate.toLong())
    }

    class OrdersVH(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var customId: TextView = itemLayoutView.custom_id
        var status: TextView = itemLayoutView.status
        var paymentStatus: TextView = itemLayoutView.payment_status
        var title: TextView = itemLayoutView.title
        var zipCode: TextView = itemLayoutView.zip_code
        var addressLine: TextView = itemLayoutView.address_line
        var country: TextView = itemLayoutView.country
        var state: TextView = itemLayoutView.state
        var city: TextView = itemLayoutView.city
        var fullName: TextView = itemLayoutView.full_name
        var phone: TextView = itemLayoutView.phone
        var titleShipping: TextView = itemLayoutView.title_shipping
        var phoneShipping: TextView = itemLayoutView.phone_shipping
        var emailShipping: TextView = itemLayoutView.email_shipping
        var dateCreated: TextView = itemLayoutView.date_created
        var dateModified: TextView = itemLayoutView.date_modified
        var datePaid: TextView = itemLayoutView.date_paid
        var price: TextView = itemLayoutView.price
        var discountPrice: TextView = itemLayoutView.discount_price
        var deliveryPrice: TextView = itemLayoutView.delivery_price
        var totalPrice: TextView = itemLayoutView.total_price
    }
}