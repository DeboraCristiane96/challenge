package com.compass.challenge.domain.business;

import com.compass.challenge.api.mappers.CommentsConverter;
import com.compass.challenge.api.mappers.PostConverter;
import com.compass.challenge.client.CommentsConsumerClient;
import com.compass.challenge.client.PostConsumerClient;
import com.compass.challenge.domain.business.services.CommentsService;
import com.compass.challenge.domain.business.services.PostService;
import com.compass.challenge.payload.CommentDto;
import com.compass.challenge.payload.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChallengeApiService {

    private final PostConsumerClient postCliente;
    private final CommentsConsumerClient consumerClient;
    private final PostConverter converter;
    private final CommentsConverter commentsConverter;
    private final PostService postService;
    private final CommentsService commentsService;
    @Autowired
    public ChallengeApiService(PostConsumerClient postCliente, CommentsConsumerClient consumerClient, PostConverter converter, CommentsConverter commentsConverter, PostService postService, CommentsService commentsService) {
        this.postCliente = postCliente;
        this.consumerClient = consumerClient;
        this.converter = converter;
        this.commentsConverter = commentsConverter;
        this.postService = postService;
        this.commentsService = commentsService;
    }

    public List<PostDto> findPost() {
        try {
            List<PostDto> dto = postCliente.getListPosts();
            dto.forEach(postDto -> {
                Boolean retorno = postService.findByTitle(postDto.getTitle());
                if (postDto.getId() < 1 || postDto.getId() > 100) {
                    throw new RuntimeException(String.valueOf(postDto.getId()));
                }
                if (retorno.equals(false)) {
                    postService.savePostDto(postDto);
                }
            }
            );
            return postService.getAllPosts();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching and writing post to database");
        }
    }


    public List<CommentDto> findCooments(){
        try {
            List<CommentDto> dto = consumerClient.getListComments();
            dto.forEach(commentDto -> {
                        Boolean retorno = postService.findByTitle(commentDto.getCommentId());
                        if (retorno.equals(false)) {
                            commentsService.createComment(commentDto);
                        }
                    }
            );
            return commentsService.getAllComments();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching and writing comments to database");
        }
    }
}
