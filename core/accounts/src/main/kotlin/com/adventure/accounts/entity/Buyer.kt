package com.adventure.accounts.entity

import com.adventure.apis.accounts.State.Sex
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.Locale.IsoCountryCode

@Entity
@Table(name = "buyers")
class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "first_name")
    val firstName: String? = null
    @Column(name = "last_name")
    val lastName: String? = null
    @Column(name = "dob")
    val dob: LocalDateTime? = null
    @Column(name = "email_address")
    val email: String? = null
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    val gender: Sex? = null
    @Enumerated(EnumType.STRING)
    @Column(name = "country_code")
    val countryCode: IsoCountryCode? = null

}