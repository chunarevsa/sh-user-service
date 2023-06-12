package com.smarthome.userservice.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.function.Consumer
import javax.persistence.*

@Entity
open class User(
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, //TODO: check it

    @CreatedDate
    @JoinColumn(nullable = false, updatable = false)
    val created: Instant? = null, //TODO: check it

    @LastModifiedDate
    @JoinColumn(nullable = false)
    var updated: Instant? = null, //TODO: check it

    @OneToOne(optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name = "PROFILE_ID", unique = true)
    var profile: Profile = Profile(),

    @JoinColumn(nullable = false, unique = true, updatable = false)
    val login: String? = null,

    @JoinColumn(nullable = false)
    var password: String? = null,

    @OneToOne(optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name = "ACCOUNT_ID", unique = true)
    val account: Account? = Account(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "USER_AUTHORITY",
        joinColumns = [JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")],
        inverseJoinColumns = [JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")]
    )
    var roles: MutableSet<Role> = HashSet(),

    @JoinColumn(nullable = false)
    var isActive: Boolean = true
) {
    fun addRole(role: Role) {
        roles.add(role)
        role.users.add(this)
    }

    fun addRoles(roles: Set<Role>) {
        roles.forEach(Consumer { role: Role -> addRole(role) })
    }

    fun isAdmin(): Boolean = roles.find { it.isAdminRole() } != null

}
