package com.smarthome.shuserservice.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Account(
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @JsonIgnore
    @OneToOne(optional = false, mappedBy = "profile")
    val userOld: UserOld? = null,

    var amount: Long = 0L,
)
