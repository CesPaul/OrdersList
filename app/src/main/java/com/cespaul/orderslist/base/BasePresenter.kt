package com.cespaul.orderslist.base

/**
 * Обеспечивает основу для конкретных презентеров.
 *
 * @param V Конкретная реализация базовой View.
 * @property view Конкретная реализация базовой View.
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    open fun onViewCreated() {}

    open fun onViewDestroyed() {}
}