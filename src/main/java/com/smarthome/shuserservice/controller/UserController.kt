package com.smarthome.shuserservice.controller

import com.smarthome.shuserservice.dto.CreateUserRequest
import com.smarthome.shuserservice.dto.EditRoleRequest
import com.smarthome.shuserservice.dto.UpdateUserRequest
import com.smarthome.shuserservice.entity.UserOld
import com.smarthome.shuserservice.exception.service.UserService
import com.smarthome.shuserservice.util.HeaderUtil
import com.smarthome.shuserservice.util.ResponseUtil
import org.springframework.beans.factory.annotation.Value
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
//    private val log: Logger = LoggerFactory.getLogger(UserController)

    private val ENTITY_NAME = "user"

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserOld> {
//        log.debug("REST request to get $ENTITY_NAME : {}", id)
        return ResponseUtil.wrapOrNotFound(userService.getUser(id))
    }

    // TODO add @Valid
    @PostMapping("/create")
    fun createUser(@RequestBody req: CreateUserRequest): ResponseEntity<UserOld> {
//        log.debug("REST request to create $ENTITY_NAME : {}", req)
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
    fun updateUser(@PathVariable id: Long, @RequestBody req: UpdateUserRequest): ResponseEntity<UserOld> {
//        log.debug("REST request to update $ENTITY_NAME : {}", req)
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
//        log.debug("REST request to deactivate user : {}", id)
        userService.deactivateUser(id)
        return ResponseEntity.noContent()
            .headers(
                HeaderUtil.createEntityDeactivationAlert(
                    applicationName, false, ENTITY_NAME, id.toString()
                )
            ).build()
    }

    // TODO add @Valid
    @PostMapping("/{id}/updateRole")
    fun updateUserRole(@PathVariable id: Long, @RequestBody req: EditRoleRequest): ResponseEntity<Void> {
//        log.debug("REST request to update role : {}, {}", id, req)
        userService.updateRole(id, req.role)
        return ResponseEntity.noContent().headers(
            HeaderUtil.createEntityUpdateRoleAlert(
                applicationName, false, ENTITY_NAME, id.toString()
            )
        ).build()
    }

}