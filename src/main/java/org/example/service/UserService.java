package org.example.service;

import org.example.model.UserEmployee;
import org.example.repository.UserRepository;
import org.example.service.inputDto.LoginRequestDto;
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

    public UserEmployee login(LoginRequestDto loginRequestDto) {
        System.out.println("im UserService angekommen" + loginRequestDto.getUsername());
        // todo wenn daten stimmen dann isLoggedIn auf true setzen und wenn nicht dann false

        UserEmployee user = userRepository.findByUsernameAndPassword(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );
        user.setIsLoggedIn();
        userRepository.save(user);
        return user;
    }
    public UserEmployee getLoggedInUserId() {
        return userRepository.findByIsLoggedIn(true);

    }

    //todo logout methode hinzufügen, die isLoggedIn auf false setzt
    public void logout() {
        UserEmployee user = userRepository.findByIsLoggedIn(true);
        if (user != null) {
            user.setIsLoggedOut();
            userRepository.save(user);
        }
    }


}
