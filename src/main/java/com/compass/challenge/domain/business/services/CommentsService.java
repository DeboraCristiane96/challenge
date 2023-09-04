package com.compass.challenge.domain.business.services;

import com.compass.challenge.domain.entitys.Comments;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.CommentDto;
import com.compass.challenge.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentsService {
    CommentDto createComment(CommentDto commentDto);

    List<CommentDto> getCommentsByCommentId(Long id);
    List<CommentDto> getAllComments();
    CommentDto updateComment(Long commentId, CommentDto commentRequest) throws ResourceNotFoundException;

    void deleteComment(Long commentId);
}
