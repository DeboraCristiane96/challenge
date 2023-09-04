package com.compass.challenge.api.mappers;

import com.compass.challenge.domain.entitys.Comments;
import com.compass.challenge.domain.entitys.Post;
import com.compass.challenge.payload.CommentDto;
import com.compass.challenge.payload.PostDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CommentsConverter {

    public Comments toEntity(CommentDto commentDto){
        return Comments.builder()
                .commentId(commentDto.getCommentId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .body(commentDto.getBody())
                .build();

    }


    public Comments  toEntityUpdate(Comments  entity, CommentDto dto, Long id) {
        return Comments .builder()
                .commentId(entity.getCommentId())
                .email(dto.getEmail()!= null ? dto.getEmail() : entity.getEmail())
                .body(dto.getBody() != null ? dto.getBody() : entity.getBody() )
                .body(dto.getBody() != null ? dto.getBody() : entity.getBody())
                .name(dto.getName()!=null ? dto.getName(): entity.getName())
                .build();
    }

    public CommentDto entitytoDTO(Comments comments){
        return CommentDto.builder()
                .commentId(comments.getCommentId())
                .name(comments.getName())
                .email(comments.getEmail())
                .body(comments.getBody())
                .build();
    }

    public List<CommentDto> toListDto(List<Comments> commentsList){
        return commentsList.stream().map(this::entitytoDTO).toList();

    }
}
