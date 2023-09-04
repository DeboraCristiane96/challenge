package com.compass.challenge.client;

import com.compass.challenge.domain.entitys.Comments;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.CommentDto;
import com.compass.challenge.payload.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "comments-consumer",
        url="https://jsonplaceholder.typicode.com")
public interface CommentsConsumerClient {
    @GetMapping(value = "/comments")
    Page<Comments> getComments(Pageable pageable);
    @GetMapping(value = "/posts")
    List<CommentDto> getListComments();
    @GetMapping("/comments/{id}")
    Comments getCommentId(@PathVariable("id") Long id) throws ResourceNotFoundException;
}
