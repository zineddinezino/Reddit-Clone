package com.example.RedditClone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "This field can not be empty")
    private String userName;

    @NotBlank(message = "This field can not be empty")
    private String password;

    @Email
    @NotEmpty(message = "The email is required")
    private String userEmail;

    private Instant userCreatedDate;

    private boolean accountEnabled;

}
