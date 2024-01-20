package com.adventure.apis.accounts

class State {
    enum class Sex(val value: String) {
        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other")
    }

    enum class Role(val value: String) {
        BUYER("Buyer"),
        SELLER("Seller"),
    }
}