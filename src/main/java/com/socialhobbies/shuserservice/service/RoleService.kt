package com.socialhobbies.shuserservice.service

import com.socialhobbies.shuserservice.entity.ERoleName
import com.socialhobbies.shuserservice.entity.Role
import com.socialhobbies.shuserservice.exception.NotFoundException
import com.socialhobbies.shuserservice.repo.RoleRepository
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class RoleService(
    private val roleRepo: RoleRepository
) {

    @PostConstruct // TODO clear it after adding migration
    fun addRoles() {
        val roleFromDb = roleRepo.findAll().map { it.role.name }
        ERoleName.values().forEach { if (!roleFromDb.contains(it.name)) roleRepo.save(
            Role(it)
        ) }
    }

    fun getRoleByRoleName(roleName: ERoleName) : Role {
        return roleRepo.findAll().find { it.role.name == roleName.name } ?: throw NotFoundException("role", roleName.name)
    }

    fun getRoleByRoleName(roleName: String) : Role {
        return roleRepo.findAll().find { it.role.name == roleName } ?: throw NotFoundException("role", roleName)
    }

}