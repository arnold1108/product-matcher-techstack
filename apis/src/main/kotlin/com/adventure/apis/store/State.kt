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
}