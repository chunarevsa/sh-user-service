package com.smarthome.shuserservice.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.*
import javax.persistence.*


//@Entity
//@Table(name = "users")
//open class UserOld(
//    @Id
//    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    open var id: Long? = null, //TODO: check it
//
//    @CreatedDate
//    @JoinColumn(nullable = false, updatable = false)
//    open val created: Instant? = null, //TODO: check it
//
//    @LastModifiedDate
//    @JoinColumn(nullable = false)
//    open var updated: Instant? = null, //TODO: check it
//
//    @OneToOne(optional = false, cascade = [CascadeType.ALL])
//    @JoinColumn(name = "profile_id", unique = true)
//    open var profile: Profile = Profile(),
//
//    @JoinColumn(nullable = false, unique = true, updatable = false)
//    open var login: String? = null,
//
//    @JoinColumn(nullable = false)
//    open var password: String? = null,
//
//    @OneToOne(optional = false, cascade = [CascadeType.ALL])
//    @JoinColumn(name = "ACCOUNT_ID", unique = true)
//    open var account: Account? = Account(),
//
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "USER_AUTHORITY",
        joinColumns = [JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")],
        inverseJoinColumns = [JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")]
    )
//    open var roles: MutableSet<Role> = HashSet(),
//
//    @JoinColumn(nullable = false)
//    open var isActive: Boolean = true
//) {
//    open fun addRole(role: Role) {
//        roles.add(role)
//        role.userOlds.add(this)
//    }
//
//    open fun addRoles(roles: Set<Role>) {
//        roles.forEach { role -> addRole(role)}
//    }
//
//    open fun isAdmin(): Boolean = roles.find { it.isAdminRole() } != null
//
//}
