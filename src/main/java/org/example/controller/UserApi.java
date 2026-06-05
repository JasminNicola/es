package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.example.service.inputDto.LoginRequest;
import org.example.service.outputDto.UserDtoOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class UserApi {
    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam LoginRequest loginrequest) {
//        System.out.println("im Backand angekommen"+loginrequest.getUsername());
//        Optional<User> userOptional = userService.login(loginrequest);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//             String name = user.getUsername();
//            return ResponseEntity.ok("Hi {}Login successful");
//            }
//        return ResponseEntity.status(404).body("Username or Password not found");
//    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());



        User userOptional = userService.login(loginRequest);
            if (userOptional != null) {
                System.out.println("Login successful for userbe: " + userOptional.getUsername());

                return ResponseEntity.ok(Map.of("message", "Hi " + userOptional.getUsername() + " Login successful"))
                        ;

            }
//        if (userOptional.isPresent()) {
//            return ResponseEntity.ok(
//                    "Hi " + userOptional.get() + " Login successful");
//        }
        System.out.println("nicht gefunden");

        return ResponseEntity.status(404)
                .body("Username or Password not found");
    }


}
