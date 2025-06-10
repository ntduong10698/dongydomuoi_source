package com.bksoftwarevn.adminthuocdongy.userservice.security;

import com.bksoftwarevn.adminthuocdongy.userservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String usernameAndComId) throws UsernameNotFoundException {
        String username = usernameAndComId.split("-")[0];
        int comId = Integer.parseInt(usernameAndComId.split("-")[1]);
        return userRepo.findByUsername(username,comId)
                .map(appUser -> new User(usernameAndComId, "", new HashSet<>()))
                .orElseGet(() -> {
                    throw new UsernameNotFoundException(usernameAndComId);
                });
    }
}
