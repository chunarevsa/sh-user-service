package com.smarthome.shuserservice.service

import com.smarthome.shuserservice.dto.CreateUserRequest
import com.smarthome.shuserservice.dto.UpdateUserRequest
import com.smarthome.shuserservice.entity.Account
import com.smarthome.shuserservice.entity.ERoleName
import com.smarthome.shuserservice.entity.Profile
import com.smarthome.shuserservice.entity.User
import com.smarthome.shuserservice.exception.NotFoundUserException
import com.smarthome.shuserservice.repo.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleService: RoleService
) {

    fun getUser(userId: Long): Optional<User> = userRepository.findById(userId)

    fun createUser(req: CreateUserRequest): User {
        val user = User().apply {
            this.isActive = true
            this.login = req.login
            this.password = req.password
            this.isEmailVerified = false
            this.profile = Profile(req.firstName, req.lastName, this)
            this.account = Account(this)
        }
        user.addRole(roleService.getRoleByRoleName(ERoleName.ROLE_USER))
        return userRepository.save(user)
    }

    fun updateUser(userId: Long, req: UpdateUserRequest): User {
        val user = userRepository.findById(userId)
            .map { it }
            .orElseThrow { NotFoundUserException(userId) }
        req.firstName.let { user.profile.firstName = it }
        req.lastName.let { user.profile.lastName = it }
        req.password.let { user.password = it }

        return userRepository.save(user)
    }

    fun deactivateUser(id: Long) {
        userRepository.findById(id).map { it }.orElseThrow { NotFoundUserException(id) }
    }

    fun updateRole(userId: Long, role: String) {
        userRepository.findById(userId).map { it.addRole(roleService.getRoleByRoleName(ERoleName.valueOf(role))) }
            .orElseThrow { NotFoundUserException(userId) } // TODO check: Which exception does it throw
    }

}

