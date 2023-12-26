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
    lateinit var firstName: String
    @Column(name = "last_name")
    lateinit var lastName: String
    @Column(name = "dob")
    lateinit var dob: LocalDateTime
    @Column(name = "email_address")
    lateinit var email: String
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    lateinit var gender: Sex
    @Enumerated(EnumType.STRING)
    @Column(name = "country_code")
    lateinit var countryCode: IsoCountryCode

}