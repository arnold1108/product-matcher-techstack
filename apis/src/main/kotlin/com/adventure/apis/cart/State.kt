package com.adventure.apis.cart

class State {

    enum class CartStatus(val value: String) {
        CREATED("CREATED"),
        CHECKED_OUT("CHECKED OUT");

        fun canTransitionTo(status: CartStatus): Boolean =
            when(this) {
                CREATED -> setOf(CHECKED_OUT).contains(status)
                CHECKED_OUT -> setOf(CHECKED_OUT).contains(status)
            }
    }
}