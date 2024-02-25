package com.adventure.apis

class AccountNotFoundException(emailAddress: String) :
        NoSuchElementException("Account with Email Address $emailAddress NOT found!!")