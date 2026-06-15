package org.example.controller;

import org.example.model.UserEmployee;
import org.example.service.outputDto.UserDtoOutput;
import org.example.service.UserService;
import org.example.service.inputDto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class UserApi {
    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        UserDtoOutput userEmployeeOptional = userService.login(loginRequestDto);
            if (userEmployeeOptional != null) {
                return ResponseEntity.ok(Map.of("message", "Hi " + userEmployeeOptional.getUsername() + " Login successful"));
            }
        return ResponseEntity.status(404)
                .body("Username or Password not found");
    }

    @PostMapping("/logout")
    public Boolean logout() {
        return userService.logout();
    }



}
