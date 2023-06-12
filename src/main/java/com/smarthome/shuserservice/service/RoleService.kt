package com.smarthome.userservice.service

import com.smarthome.userservice.entity.ERoleName
import com.smarthome.userservice.entity.Role
import com.smarthome.userservice.repo.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val roleRepo: RoleRepository
) {
    fun getRoleByRoleName(roleName: ERoleName) : Role {
        return roleRepo.findByRoleName(roleName.name) ?: throw Exception("Not found role $roleName")
    }

}