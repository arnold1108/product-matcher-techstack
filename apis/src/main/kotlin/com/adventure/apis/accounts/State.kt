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

    enum class AccountStatus(val value: String) {
        ACTIVE("ACTIVE"),
        INACTIVE("INACTIVE"),
        SUSPENDED("SUSPENDED");

        fun canTransitionTo(status: AccountStatus): Boolean =
            when(this) {
                ACTIVE -> setOf(INACTIVE, SUSPENDED).contains(status)
                INACTIVE -> setOf(ACTIVE).contains(status)
                SUSPENDED -> setOf(ACTIVE).contains(status)
            }
    }
}