package com.example.blog.repositories;

import com.example.blog.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {

    List<Posts> findPostsByTags_OrPost_content(String filter);
}
