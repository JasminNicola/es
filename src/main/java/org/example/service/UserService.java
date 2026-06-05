package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.inputDto.LoginRequest;
import org.example.service.outputDto.UserDtoOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


//    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
//        System.clearProperty("im Backend angekommen"+loginRequest.getUsername());
//        Optional<User> user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
//        if (user.isPresent()) {
//            return ResponseEntity.ok(null); //"Hi " +user.get()+ " Login successful");
//        }
//        return ResponseEntity.status(404).body(null);
//           //     .body("Username or Password not found");
//}

    public User login(LoginRequest loginRequest) {
        System.out.println("im UserService angekommen"+loginRequest.getUsername());
        return userRepository.findByUsernameAndPassword(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
    }

}
