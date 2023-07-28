package com.example.authenticationdacn_723.dto;

import com.example.authenticationdacn_723.model.Book;
import com.example.authenticationdacn_723.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
Create By : ANHTUAN
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private boolean isEmailVerified;
    private int gender;

    private Set<Book> books;
    private List<Role> roles = new ArrayList<>();
}
