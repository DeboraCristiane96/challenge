package com.compass.challenge.client;

import com.compass.challenge.domain.entitys.Post;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "post-consumer",
        url="https://jsonplaceholder.typicode.com")
public interface PostConsumerClient {
    @GetMapping(value = "/posts")
    Page<PostDto> getPosts(Pageable pageable);
    @GetMapping(value = "/posts")
    List<PostDto> getListPosts();

    @GetMapping("/posts/{id}")
    PostDto findPostById(@PathVariable("id") Long id) throws ResourceNotFoundException;
}
