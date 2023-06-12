package com.smarthome.userservice.repo

import com.smarthome.userservice.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Long>
