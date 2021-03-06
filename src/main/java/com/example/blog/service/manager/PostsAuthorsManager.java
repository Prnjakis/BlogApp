package com.example.blog.service.manager;

import com.example.blog.model.Posts;
import com.example.blog.model.PostsAuthors;

import java.util.List;

public interface PostsAuthorsManager {

    void save(int id_post, int id_author);

    void removeByPost(int id_post);

    List<PostsAuthors> getAllPostsAuthors();

    List<Posts> getAllPostsByAuthors(int id_author);

}
