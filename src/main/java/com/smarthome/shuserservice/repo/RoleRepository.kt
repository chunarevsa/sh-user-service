package com.smarthome.userservice.repo

import com.smarthome.userservice.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {

    fun findByRoleName(roleName: String): Role?

}
