package org.example.service;

import org.example.model.UserEmployee;
import org.example.repository.UserRepository;
import org.example.service.inputDto.LoginRequestDto;
import org.example.service.outputDto.UserDtoOutput;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDtoOutput login(LoginRequestDto loginRequestDto) {
        System.out.println("im UserService angekommen" + loginRequestDto.getUsername());
        // TODO später mit Principal und SecurityContext arbeiten, hier erstmal einfaches Login
        UserDtoOutput user = userRepository.findByUsernameAndPassword(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );
        UserEmployee ue =userRepository.findById(user.getId()).get();
        ue.setIsLoggedIn();
        userRepository.save(ue);

        return user;
    }
    public UserEmployee getLoggedInUserId() {
        return userRepository.findByIsLoggedIn(true);
    }

    //todo logout später mit Principal und SecurityContext arbeiten, hier erstmal einfaches Logout
    public Boolean logout() {
        UserEmployee user = userRepository.findByIsLoggedIn(true);
        if (user != null) {
            user.setIsLoggedOut();
            userRepository.save(user);
            return true;
        }
        return false;
    }


}
