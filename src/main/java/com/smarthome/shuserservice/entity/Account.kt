package com.smarthome.userservice.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Account(
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @JsonIgnore
    @OneToOne(optional = false, mappedBy = "profile")
    val user: User? = null,

    var amount: Long = 0L,
)
