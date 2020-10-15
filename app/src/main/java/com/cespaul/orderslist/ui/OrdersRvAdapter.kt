package com.cespaul.orderslist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cespaul.orderslist.R
import com.cespaul.orderslist.data.repository.OrdersRepository
import com.cespaul.orderslist.models.Orders
import kotlinx.android.synthetic.main.order_row.view.*

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

        vh.customId.text = orderItem.customId.toString()
        vh.status.text = orderItem.status
        vh.paymentStatus.text = orderItem.paymentStatus
        vh.title.text = orderItem.title
        vh.zipCode.text = orderItem.deliveryAddress?.zipCode.toString()
        vh.addressLine.text = orderItem.deliveryAddress?.addressLine1
        vh.country.text = orderItem.deliveryAddress?.country
        vh.state.text = orderItem.deliveryAddress?.state
        vh.city.text = orderItem.deliveryAddress?.city
        vh.fullName.text = orderItem.deliveryAddress?.fullName
        vh.phone.text = orderItem.deliveryAddress?.phone
        vh.shippingId.text = orderItem.shipping?.id.toString()
        vh.titleShipping.text = orderItem.shipping?.title
        vh.phoneShipping.text = orderItem.shipping?.phone
        vh.emailShipping.text = orderItem.shipping?.email
        vh.userId.text = orderItem.userId.toString()
        vh.userCompanyAccountId.text = orderItem.userCompanyAccountId.toString()
        vh.dateCreated.text = orderItem.dateCreated
        vh.dateModified.text = orderItem.dateModified
        vh.datePaid.text = orderItem.datePaid
        vh.price.text = orderItem.price.toString()
        vh.discountPrice.text = orderItem.discountPrice.toString()
        vh.deliveryPrice.text = orderItem.deliveryPrice.toString()
        vh.totalPrice.text = orderItem.totalPrice.toString()
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
        var shippingId: TextView = itemLayoutView.shipping_id
        var titleShipping: TextView = itemLayoutView.title_shipping
        var phoneShipping: TextView = itemLayoutView.phone_shipping
        var emailShipping: TextView = itemLayoutView.email_shipping
        var userId: TextView = itemLayoutView.user_id
        var userCompanyAccountId: TextView = itemLayoutView.user_company_account_id
        var dateCreated: TextView = itemLayoutView.date_created
        var dateModified: TextView = itemLayoutView.date_modified
        var datePaid: TextView = itemLayoutView.date_paid
        var price: TextView = itemLayoutView.price
        var discountPrice: TextView = itemLayoutView.discount_price
        var deliveryPrice: TextView = itemLayoutView.delivery_price
        var totalPrice: TextView = itemLayoutView.total_price
    }
}