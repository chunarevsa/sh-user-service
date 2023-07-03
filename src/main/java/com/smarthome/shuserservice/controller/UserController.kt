package com.smarthome.shuserservice.controller

import com.smarthome.shuserservice.dto.AddItemsRequest
import com.smarthome.shuserservice.dto.CreateUserRequest
import com.smarthome.shuserservice.dto.AddRoleRequest
import com.smarthome.shuserservice.dto.UpdateUserRequest
import com.smarthome.shuserservice.entity.ItemUnit
import com.smarthome.shuserservice.entity.User
import com.smarthome.shuserservice.service.UserService
import com.smarthome.shuserservice.util.HeaderUtil
import com.smarthome.shuserservice.util.ResponseUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService,
    @Value("\${spring.application.name}")
    private val applicationName: String
) {
    private val log: Logger = LoggerFactory.getLogger(UserController::class.java)

    private val ENTITY_NAME = "user"

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<User> {
        log.debug("REST request to get $ENTITY_NAME : {}", id)
        return ResponseUtil.wrapOrNotFound(userService.getUser(id))
    }

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

    @PostMapping("/{id}/update")
    fun updateUser(@PathVariable id: Long, @RequestBody req: UpdateUserRequest): ResponseEntity<User> {
        log.debug("REST request to update $ENTITY_NAME : {}", req)
        val user = userService.updateUser(id, req)
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName, false, ENTITY_NAME, user.id.toString()
                )
            )
            .body(user)
    }

    @PostMapping("/{id}/deactivate")
    fun deactivate(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("REST request to deactivate user : {}", id)
        userService.deactivateUser(id)
        return ResponseEntity.noContent()
            .headers(
                HeaderUtil.createEntityDeactivationAlert(
                    applicationName, false, ENTITY_NAME, id.toString()
                )
            ).build()
    }

    @PostMapping("/isExists")
    fun userExists(@RequestBody userId: Long) : ResponseEntity<Boolean> {
        log.debug("REST request to exists user : {}", userId)
        return ResponseEntity(userService.getUser(userId).isPresent, HttpStatus.OK)
    }

    // TODO add @Valid
    @PostMapping("/{id}/addRole")
    fun addUserRole(@PathVariable id: Long, @RequestBody req: AddRoleRequest): ResponseEntity<Void> {
        log.debug("REST request to update role : {}, {}", id, req)
        userService.addRole(id, req.role!!) // TODO: clear !!
        return ResponseEntity.noContent().headers(
            HeaderUtil.createEntityUpdateRoleAlert(
                applicationName, false, ENTITY_NAME, id.toString()
            )
        ).build()
    }

    @PostMapping("/{id}/addItems")
    fun addItems(@PathVariable id: Long, @RequestBody req: AddItemsRequest): ResponseEntity<Void> {
        log.debug("REST request to add items : {}, {}", id, req)
        userService.addItems(id, req)
        return ResponseEntity.noContent().headers(
            HeaderUtil.createEntityAddItemsAlert(
                applicationName, false, ENTITY_NAME, id.toString()
            )
        ).build()
    }

    @GetMapping("/{id}/getItems")
    fun getItems(@PathVariable id: Long): MutableSet<ItemUnit> {
        log.debug("REST request to getting user items : {}", id)
        return userService.getItems(id)
    }

}