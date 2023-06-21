package com.smarthome.shuserservice.repo

import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Long> {

}
