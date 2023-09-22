package com.socialhobbies.shuserservice.repo

import com.socialhobbies.shuserservice.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>
