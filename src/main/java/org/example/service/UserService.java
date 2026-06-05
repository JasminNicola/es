package org.example.service;

import org.example.model.UserEmployee;
import org.example.repository.UserRepository;
import org.example.service.inputDto.LoginRequest;
import org.springframework.stereotype.Service;

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

    public UserEmployee login(LoginRequest loginRequest) {
        System.out.println("im UserService angekommen"+loginRequest.getUsername());
        return userRepository.findByUsernameAndPassword(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
    }

}
