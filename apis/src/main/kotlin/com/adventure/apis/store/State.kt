package com.adventure.apis.store

class State {
    enum class StoreCategory(val displayName: String) {
        SPORTS("Sports"),
        FASHION("fashion"),
        ELECTRONICS("electronics"),
        APPLIANCES("appliances"),
        HOME_AND_OFFICE("Home & Office"),
        COMPUTING("Computing"),
        HEALTH_AND_BEAUTY("Health & Beauty"),
        GROCERIES("Groceries")
    }

    enum class StoreStatus(val value: String) {
        CREATED("CREATED"),
        SUSPENDED("SUSPENDED"),
        OUT_OF_STOCK("OUT OF STOCK"),
        CLOSED("CLOSED"),
        OPEN("OPEN");

        fun canTransitionTo(status: StoreStatus): Boolean =
            when (this) {
                CREATED -> setOf(SUSPENDED, OUT_OF_STOCK, CLOSED, OPEN).contains(status)
                SUSPENDED -> setOf(CLOSED).contains(status)
                OUT_OF_STOCK -> setOf(OPEN, CLOSED).contains(status)
                CLOSED -> setOf(OPEN, SUSPENDED).contains(status)
                OPEN -> setOf(CLOSED, SUSPENDED, OUT_OF_STOCK).contains(status)
            }
    }


}