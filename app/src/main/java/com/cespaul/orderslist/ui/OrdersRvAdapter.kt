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
import kotlinx.android.synthetic.main.order_item.view.*
import java.text.SimpleDateFormat

class OrdersRvAdapter(
    private val context: Context,
    ordersRepository: OrdersRepository
) : RecyclerView.Adapter<OrdersRvAdapter.OrdersVH>() {

    private var ordersList = ordersRepository.getOrdersList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersVH {
        return OrdersVH(
            LayoutInflater.from(context).inflate(
                R.layout.order_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    fun updateList(orders: Orders) {
        this.ordersList = orders.result
        notifyDataSetChanged()
    }

    private fun setViewVisibility(view: View, isNotVisible: Boolean) {
        if (isNotVisible)
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE
    }

    override fun onBindViewHolder(vh: OrdersVH, position: Int) {
        val orderItem = ordersList[position]
        vh.customId.text =
            context.resources.getString(R.string.customId, orderItem.customId.toString())
        setViewVisibility(vh.customId, orderItem.customId.isNullOrBlank())

        vh.status.text = orderItem.status
        setViewVisibility(vh.status, orderItem.status.isNullOrBlank())

        vh.paymentStatus.text =
            context.resources.getString(R.string.paymentStatus, orderItem.paymentStatus.toString())
        setViewVisibility(vh.paymentStatus, orderItem.paymentStatus.isNullOrBlank())

        vh.title.text = orderItem.title
        setViewVisibility(vh.title, orderItem.title.isNullOrBlank())

        vh.zipCode.text = orderItem.deliveryAddress?.zipCode.toString()
        setViewVisibility(vh.zipCode, orderItem.deliveryAddress?.zipCode.isNullOrBlank())

        vh.addressLine.text = orderItem.deliveryAddress?.addressLine1
        setViewVisibility(vh.addressLine, orderItem.deliveryAddress?.addressLine1.isNullOrBlank())

        vh.country.text = orderItem.deliveryAddress?.country
        setViewVisibility(vh.country, orderItem.deliveryAddress?.country.isNullOrBlank())

        vh.state.text = orderItem.deliveryAddress?.state
        setViewVisibility(vh.state, orderItem.deliveryAddress?.state.isNullOrBlank())

        vh.city.text = orderItem.deliveryAddress?.city
        setViewVisibility(vh.city, orderItem.deliveryAddress?.city.isNullOrBlank())

        vh.fullName.text = context.resources.getString(
            R.string.fullName,
            orderItem.deliveryAddress?.fullName.toString()
        )
        setViewVisibility(vh.fullName, orderItem.deliveryAddress?.fullName.isNullOrBlank())

        vh.phone.text =
            context.resources.getString(R.string.phone, orderItem.deliveryAddress?.phone.toString())
        setViewVisibility(vh.phone, orderItem.deliveryAddress?.phone.isNullOrBlank())

        vh.titleShipping.text =
            context.resources.getString(R.string.titleShipping, orderItem.shipping?.title)
        setViewVisibility(vh.titleShipping, orderItem.shipping?.title.isNullOrBlank())

        vh.phoneShipping.text =
            context.resources.getString(R.string.phoneShipping, orderItem.shipping?.phone)
        setViewVisibility(vh.phoneShipping, orderItem.shipping?.phone.isNullOrBlank())

        vh.emailShipping.text =
            context.resources.getString(R.string.emailShipping, orderItem.shipping?.email)
        setViewVisibility(vh.emailShipping, orderItem.shipping?.email.isNullOrBlank())

        val dateCreated = orderItem.dateCreated?.let { convertDate(it) }
        vh.dateCreated.text = context.resources.getString(
            R.string.dateCreated, dateCreated)
        setViewVisibility(vh.dateCreated, dateCreated.isNullOrBlank())

        val dateModified = orderItem.dateModified?.let { convertDate(it) }
        vh.dateModified.text = context.resources.getString(
            R.string.dateModified,
            dateModified)
        setViewVisibility(vh.dateModified, dateModified.isNullOrBlank())

        val datePaid = orderItem.datePaid?.let { convertDate(it) }
        vh.datePaid.text = context.resources.getString(
            R.string.datePaid,
            datePaid)
        setViewVisibility(vh.datePaid, datePaid.isNullOrBlank())

        vh.price.text = context.resources.getString(R.string.price, orderItem.price.toString())
        setViewVisibility(vh.price, orderItem.price == null)

        vh.discountPrice.text =
            context.resources.getString(R.string.discountPrice, orderItem.discountPrice.toString())
        setViewVisibility(vh.discountPrice, orderItem.discountPrice == null)

        vh.deliveryPrice.text =
            context.resources.getString(R.string.deliveryPrice, orderItem.deliveryPrice.toString())
        setViewVisibility(vh.deliveryPrice, orderItem.deliveryPrice == null)

        vh.totalPrice.text =
            context.resources.getString(R.string.totalPrice, orderItem.totalPrice.toString())
        setViewVisibility(vh.totalPrice, orderItem.totalPrice == null)
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