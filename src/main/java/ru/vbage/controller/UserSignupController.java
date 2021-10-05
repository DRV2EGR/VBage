package ru.vbage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vbage.dto.UserDto;
import ru.vbage.payload.UserDtoPayload;
import ru.vbage.service.UserService;

/**
 * The type User signup controller.
 */
@Controller
@RequestMapping("/v1/api/signup")
public class UserSignupController {
    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * Sign up new customer response entity.
     *
     * @param userDtoPayload the user dto payload
     * @return the response entity
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUpNewCustomer(@RequestBody UserDtoPayload userDtoPayload) {
        return ResponseEntity.ok(userService.registerNewUser(userDtoPayload));
    }

}
