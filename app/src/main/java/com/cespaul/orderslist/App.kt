package com.cespaul.orderslist

import android.app.Application
import com.cespaul.orderslist.data.network.OrdersApi
import com.cespaul.orderslist.utils.BASE_URL
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        ordersApi = retrofit.create(OrdersApi::class.java)

        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var ordersApi: OrdersApi
    }
}