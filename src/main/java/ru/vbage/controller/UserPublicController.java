package ru.vbage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vbage.dto.UserPublicDto;
import ru.vbage.service.ClassToDtoService;
import ru.vbage.service.UserService;

/**
 * The type User public controller.
 */
@RestController
@RequestMapping(value = "/v1/api/user/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserPublicController {

    @Autowired
    ClassToDtoService classToDtoService;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * Gets public user info by id.
     *
     * @param id the id
     * @return the public user info by id
     */
    @GetMapping("/user_by_id")
    public ResponseEntity<UserPublicDto> getPublicUserInfoById(@RequestParam long id) {
        return ResponseEntity.ok(
                classToDtoService.convertUserToUserPublicDto(
                    userService.findUserById(id)
                )
        );
    }

    /**
     * Gets public user info by username.
     *
     * @param username the username
     * @return the public user info by username
     */
    @GetMapping("/user_by_username")
    public ResponseEntity<UserPublicDto> getPublicUserInfoByUsername(@RequestParam String username) {
        return ResponseEntity.ok(
                classToDtoService.convertUserToUserPublicDto(
                        userService.findUserByUsername(username)
                )
        );
    }

}
