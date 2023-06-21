package com.smarthome.shuserservice.repo

import com.smarthome.shuserservice.entity.UserOld
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserOld, Long>
