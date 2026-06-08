package org.example.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "user_employee")
public class UserEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // normalerweise verschlüsseln
    private String email;
    private Boolean isLoggedIn;



    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventRequest> eventRequests;

    public UserEmployee() {
    }

    public UserEmployee(String username, String password, String email) {
                this.username = username;
        this.password = password;
        this.email = email;
        this.isLoggedIn = false; // Standardmäßig auf false setzen
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public void setIsLoggedIn() { this.isLoggedIn = true; }
    public void setIsLoggedOut() { this.isLoggedIn = false; }





}
