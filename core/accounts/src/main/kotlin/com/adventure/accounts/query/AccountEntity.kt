package com.adventure.accounts.query

import com.adventure.apis.accounts.State.AccountStatus
import com.adventure.apis.accounts.State.AccountStatus.INACTIVE
import com.adventure.apis.accounts.State.Role
import com.adventure.apis.accounts.State.Role.BUYER
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "sellers")
class AccountEntity {
    @Id
    @Column(name = "account_id") var sellerId: UUID = UUID.randomUUID()
    @Column(name = "first_name") var firstName: String = ""
    @Column(name = "last_name") var lastName: String = ""
    @Column(name = "date_of_birth") var dob: LocalDateTime = LocalDateTime.now()
    @Column(name = "email_address") var email: String = ""
    @Column(name = "gender") var gender: String = ""
    @Enumerated(EnumType.STRING) @Column(name = "account_status") var accountStatus: AccountStatus = INACTIVE
    @Enumerated(EnumType.STRING) @Column(name = "account_role") var accountRole: Role = BUYER
    @Column(name = "country") var country: String = ""

    companion object {
        fun newAccount(
            sellerId: UUID,
            accountStatus: AccountStatus,
            accountRole: Role,
            firstName: String,
            lastName: String,
//            dateOfBirth: LocalDateTime,
            email: String,
            gender: String,
            country: String
        ) =
            AccountEntity().apply {
                this.sellerId = sellerId
                this.accountStatus = accountStatus
                this.accountRole = accountRole
                this.firstName = firstName
                this.lastName = lastName
//                this.dob = dateOfBirth
                this.email = email
                this.gender = gender
                this.country = country
            }
    }

    fun updateAccountStatus(accountStatus: AccountStatus) {
        this.accountStatus = accountStatus
    }
}