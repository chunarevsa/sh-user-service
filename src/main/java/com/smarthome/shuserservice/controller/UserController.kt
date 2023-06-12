package com.smarthome.userservice.controller

import com.smarthome.userservice.dto.CreateUserRequest
import com.smarthome.userservice.dto.EditRoleRequest
import com.smarthome.userservice.dto.UpdateUserRequest
import com.smarthome.userservice.entity.User
import com.smarthome.userservice.service.UserService
import com.smarthome.userservice.util.HeaderUtil
import com.smarthome.userservice.util.ResponseUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
    @Value("\${spring.application.name}")
    private val applicationName: String
) {
    private val log: Logger = LoggerFactory.getLogger(UserController::class.java)

    private val ENTITY_NAME = "user"

    // TODO add @Valid
    @PostMapping("/create")
    fun createUser(@RequestBody req: CreateUserRequest): ResponseEntity<User> {
        log.debug("REST request to create $ENTITY_NAME : {}", req)
        val user = userService.createUser(req)
        return ResponseEntity.created(URI("/api/user/${user.id}"))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName, false, ENTITY_NAME, user.id.toString()
                )
            )
            .body(user)
    }

    @PostMapping("/")
    fun updateUser(@RequestBody req: UpdateUserRequest): ResponseEntity<User> {
        log.debug("REST request to update $ENTITY_NAME : {}", req)
        val user = userService.updateUser(req)
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName, false, ENTITY_NAME, user.id.toString()
                )
            )
            .body(user)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<User> {
        log.debug("REST request to get $ENTITY_NAME : {}", id)
        return ResponseUtil.wrapOrNotFound(userService.getUser(id))
    }

    // TODO add @Valid
    @PostMapping("/{id}/updateRole")
    fun updateUserRole(@PathVariable id: Long, @RequestBody req: EditRoleRequest): ResponseEntity<Void> {
        log.debug("REST request to update role : {}, {}", id, req)
        userService.updateRole(req.userId, req.role)
        return ResponseEntity.noContent().headers(
            HeaderUtil.createEntityUpdateRoleAlert(
                applicationName, false, ENTITY_NAME, id.toString()
            )
        ).build()
    }

    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("REST request to deactivate user : {}", id)
        userService.deactivateUser(id)
        return ResponseEntity.noContent()
            .headers(
                HeaderUtil.createEntityDeactivationAlert(
                    applicationName, false, ENTITY_NAME, id.toString()
                )
            ).build()
    }

}