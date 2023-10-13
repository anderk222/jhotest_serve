package com.anderk222.jhotest.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anderk222.jhotest.feature.user.UserRepository;
import com.anderk222.jhotest.feature.user.domain.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository
                .findByAlias(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));


        return new UserDetailsImpl(user);

    }

}