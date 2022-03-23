package com.example.blog.repositories;

import com.example.blog.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Integer> {
    Authors findByUsername(String username);
}
