package com.smarthome.shuserservice.repo

import com.smarthome.shuserservice.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
