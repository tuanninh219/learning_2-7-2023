package com.example.authenticationdacn_723.config;

import com.example.authenticationdacn_723.model.User;
import com.example.authenticationdacn_723.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
Create By : ANHTUAN
*/
public class UserDetailService_Security implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailService_Security(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUserName(username);
//        return user.map(UserDetail_Impl::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).get();
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetail_Impl(user);
    }
}
