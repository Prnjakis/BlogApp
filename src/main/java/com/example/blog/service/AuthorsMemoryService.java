package com.example.blog.service;

import com.example.blog.model.Authors;
import com.example.blog.model.Roles;
import com.example.blog.repositories.AuthorsRepository;
import com.example.blog.service.manager.AuthorsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorsMemoryService implements AuthorsManager {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void save(Authors a) {
        Authors user = new Authors(a.getFirst_name(), a.getLast_name(),
                a.getUsername(), a.getEmail(), passwordEncoder.encode(a.getPassword()),
                Collections.singletonList(new Roles("ROLE_USER")));
        authorsRepository.save(user);
    }

    public void save(Authors a, String role) {
        Authors user = new Authors(a.getFirst_name(), a.getLast_name(),
                a.getUsername(), a.getEmail(), passwordEncoder.encode(a.getPassword()),
                Collections.singletonList(new Roles(role)));
        authorsRepository.save(user);
    }


    @Override
    public Authors findById(int id) { return authorsRepository.findById(id).get(); }

    @Override
    public Authors findByUsername(String username) { return authorsRepository.findByUsername(username); }

    @Override
    public List<Authors> getAllAuthors() { return authorsRepository.findAll(); }

    /*
    @PostConstruct
    public void init() {
        //saveAll();
        save(new Authors("admin", "admin", "admin", "admin@admin.hr", "admin"), "ROLE_ADMIN");
    }
     */

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Authors author = authorsRepository.findByUsername(s);
        if(author == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(author.getUsername(), author.getPassword(), mapRolesToAuthorities(author.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
