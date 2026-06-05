package org.example.service.outputDto;

public class UserDtoOutput {
    private Long id;
    private String username;
    private String password;
    private String email;

    public UserDtoOutput() {
    }
    public UserDtoOutput(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
