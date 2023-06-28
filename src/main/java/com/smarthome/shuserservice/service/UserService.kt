package com.smarthome.shuserservice.service

import com.smarthome.shuserservice.dto.AddItemsRequest
import com.smarthome.shuserservice.dto.CreateUserRequest
import com.smarthome.shuserservice.dto.UpdateUserRequest
import com.smarthome.shuserservice.entity.*
import com.smarthome.shuserservice.exception.NotFoundException
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
            this.profile = Profile(
                req.firstName,
                req.lastName,
                this
            )
            this.account = Account(this)
        }
        user.addRole(roleService.getRoleByRoleName(ERoleName.ROLE_USER))
        return userRepository.save(user)
    }

    fun updateUser(userId: Long, req: UpdateUserRequest): User {
        val user = findUser(userId)
        req.firstName.let { user.profile.firstName = it }
        req.lastName.let { user.profile.lastName = it }
        req.password.let { user.password = it }

        return userRepository.save(user)
    }

    fun deactivateUser(userId: Long) {
        val user = findUser(userId)
        user.isActive = false
        userRepository.save(user)
    }

    fun addRole(userId: Long, role: String) {
        val user = findUser(userId)
        user.addRole(roleService.getRoleByRoleName(role))
        userRepository.save(user)
    }

    fun addItems(userId: Long, req: AddItemsRequest) {
        val user = findUser(userId)
        val item = user.items.find { it.sku == req.sku }
        if (item != null) {
            item.amount =+ req.amount
        } else user.items.add(Item().apply {
            this.sku = req.sku
            this.amount = req.amount
            this.user = user
        })

        userRepository.save(user)
    }

    fun getItems(userId: Long): MutableSet<Item> = findUser(userId).items

    private fun findUser(userId: Long): User {
        return userRepository.findById(userId).map { it }.orElseThrow {
            NotFoundException("user", userId.toString())
        }
    }
}

