package com.codersfactory.user;

import com.codersfactory.flashcards.FlashcardCollection;
import com.codersfactory.user.dto.UserRegisterDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Email
    String email;
    String username;
    String password;
    Roles role;
    @OneToMany(mappedBy = "user")
    List<FlashcardCollection> collections;

    public User(UserRegisterDto dto) {
        this.email = dto.email();
        this.username = dto.username();
        this.role = Roles.USER;
    }
}
