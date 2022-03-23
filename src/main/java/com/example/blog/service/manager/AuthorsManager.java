package com.example.blog.service.manager;

import com.example.blog.model.Authors;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AuthorsManager extends UserDetailsService {

    void save(Authors authors);

    Authors findById(int id);

    Authors findByUsername(String username);

    List<Authors> getAllAuthors();

}
