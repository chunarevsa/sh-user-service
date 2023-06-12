package com.smarthome.userservice.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
class Role(
    @Id @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "ROLE_NAME")
    @Enumerated(EnumType.STRING)
    @NaturalId
    var role: ERoleName? = null,

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    var users: MutableSet<User> = HashSet()

) {
    fun isAdminRole(): Boolean = this.role == ERoleName.ROLE_ADMIN

}
