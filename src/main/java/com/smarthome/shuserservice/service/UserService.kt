package com.smarthome.userservice.service

import com.smarthome.shuserservice.exception.NotFoundUserException
import com.smarthome.userservice.dto.CreateUserRequest
import com.smarthome.userservice.dto.UpdateUserRequest
import com.smarthome.userservice.entity.ERoleName
import com.smarthome.userservice.entity.Profile
import com.smarthome.userservice.entity.User
import com.smarthome.userservice.repo.ProfileRepository
import com.smarthome.userservice.repo.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository,
    private val roleService: RoleService
) {

    fun getUser(userId: Long): Optional<User> = userRepository.findById(userId)

    fun updateRole(userId: Long, role: String) {
        userRepository.findById(userId).map { it.addRole(roleService.getRoleByRoleName(ERoleName.valueOf(role))) }
            .orElseThrow { NotFoundUserException(userId) } // TODO check: Which exception does it throw
    }

    fun createUser(req: CreateUserRequest): User {
        val user = User(
            password = req.password,
            login = req.login,
            profile = Profile(
                firstName = req.firstName,
                lastName = req.lastName
            )
        )
        save(user)
        return user
    }

    fun updateUser(req: UpdateUserRequest): User {
        val user = userRepository.findById(req.id)
            .map { it }
            .orElseThrow { NotFoundUserException(req.id) }
        req.firstName.let { user.profile.firstName = it }
        req.lastName.let { user.profile.lastName = it }
        req.password.let { user.password = it }

//        save(user)
        return user
    }

    // TODO: Maybe is not necessary
    private fun save(user: User) {
        profileRepository.save(user.profile)
        userRepository.save(user)
    }

    fun deactivateUser(id: Long) {
        userRepository.findById(id).map { it.isActive = false }.orElseThrow { NotFoundUserException(id) }
    }

}

