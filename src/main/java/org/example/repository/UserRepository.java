package org.example.repository;

import org.example.model.User;
import org.example.service.outputDto.UserDtoOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.example.service.outputDto.UserDtoOutput;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByUsername(String username);

        //@Query(" select new org.example.service.outputDto.UserDtoOutput(u.id,u.username, u.password, u.email) from User u WHERE u.username = :username AND u.password = :password")
        User findByUsernameAndPassword(String username, String password);

//    @Query(""")
//        select new org.example.internetapplab.application.Dto.outputDto.UserDto(u.id, u.email, u.username, u.firstname, u.lastname, u.creation, u.birthday) from User u WHERE u.username = :username
//    """)
//    List<UserDto> findUsersDtoByUsername(String username);

}
