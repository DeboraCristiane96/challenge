package com.compass.challenge.domain.business.services;

import com.compass.challenge.client.response.PostResponse;
import com.compass.challenge.domain.entitys.Post;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {

    Post savePost(Post post);
    PostDto savePostDto(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    Boolean findByTitle(String title);
    List<PostDto> getAllPosts();
    PostDto updatePost(String id, PostDto post);
    void deletePost(String id) ;

    Optional<Post> findById(String id);
}
