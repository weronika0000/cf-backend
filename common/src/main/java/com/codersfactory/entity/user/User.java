package com.codersfactory.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long userId;

    private String username;

    private String emailAdress;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="total_points")
    private int totalPoints;

}
