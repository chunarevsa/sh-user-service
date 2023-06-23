package com.smarthome.shuserservice.repo

import com.smarthome.shuserservice.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>
