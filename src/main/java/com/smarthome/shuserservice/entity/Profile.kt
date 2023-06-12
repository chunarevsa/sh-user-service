package com.smarthome.userservice.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Profile(
    @Id
    @Column(name = "PROFILE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @JsonIgnore
    @OneToOne(optional = false, mappedBy = "profile")
    val user: User? = null,

    @Column(name = "FIRST_NAME", nullable = false)
    var firstName: String? = null,

    @Column(name = "LAST_NAME", nullable = false)
    var lastName: String? = null,
)