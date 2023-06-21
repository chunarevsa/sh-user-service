package com.smarthome.shuserservice.exception.service

import com.smarthome.shuserservice.exception.NotFoundUserException
import com.smarthome.shuserservice.dto.CreateUserRequest
import com.smarthome.shuserservice.dto.UpdateUserRequest
import com.smarthome.shuserservice.entity.*
import com.smarthome.shuserservice.repo.ProfileRepository
import com.smarthome.shuserservice.repo.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository,
    private val roleService: RoleService
) {

    fun getUser(userId: Long): Optional<UserOld> = userRepository.findById(userId)

    fun createUser(req: CreateUserRequest): UserOld {
        val userOld = UserOld(
            password = req.password,
            login = req.login,
            profile = Profile(
                firstName = req.firstName,
                lastName = req.lastName
            )
        )
        save(userOld)
        return userOld
    }

    fun updateUser(userId: Long, req: UpdateUserRequest): UserOld {
        val user = userRepository.findById(userId)
            .map { it }
            .orElseThrow { NotFoundUserException(userId) }
        req.firstName.let { user.profile.firstName = it }
        req.lastName.let { user.profile.lastName = it }
        req.password.let { user.password = it }

        save(user)
        return user
    }
    fun deactivateUser(id: Long) {
        userRepository.findById(id).map { it.isActive = false }.orElseThrow { NotFoundUserException(id) }
    }

    fun updateRole(userId: Long, role: String) {
        userRepository.findById(userId).map { it.addRole(roleService.getRoleByRoleName(ERoleName.valueOf(role))) }
            .orElseThrow { NotFoundUserException(userId) } // TODO check: Which exception does it throw
    }

    private fun save(userOld: UserOld) {
        profileRepository.save(userOld.profile)
        userRepository.save(userOld)
    }

}

