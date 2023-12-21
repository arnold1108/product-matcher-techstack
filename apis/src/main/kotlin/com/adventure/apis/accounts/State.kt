package com.adventure.apis.accounts

class State {
    enum class Sex(val value: String) {
        MALE("MALE"),
        FEMALE("FEMALE"),
        OTHER("OTHER")
    }
}