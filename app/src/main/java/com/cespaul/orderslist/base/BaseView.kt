package com.cespaul.orderslist.base

import android.content.Context

/**
 * Основа для базовых View.
 *
 */
interface BaseView {

    /**
     * Получение контекста.
     *
     * @return Возвращает контекст.
     */
    fun getContext(): Context
}