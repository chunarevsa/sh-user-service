package com.smarthome.shuserservice.exception.service

import com.smarthome.shuserservice.entity.ERoleName
import com.smarthome.shuserservice.entity.Role
import com.smarthome.shuserservice.repo.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val roleRepo: RoleRepository
) {
    fun getRoleByRoleName(roleName: ERoleName) : Role {
        return roleRepo.findByRoleName(roleName.name) ?: throw Exception("Not found role $roleName")
    }

}