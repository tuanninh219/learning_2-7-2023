package com.example.authenticationdacn_723.repository;

import com.example.authenticationdacn_723.model.Role;
import com.example.authenticationdacn_723.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Create By : ANHTUAN
*/
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
