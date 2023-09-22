package com.socialhobbies.shuserservice.repo

import com.socialhobbies.shuserservice.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
