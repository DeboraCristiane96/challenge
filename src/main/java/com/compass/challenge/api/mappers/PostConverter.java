package com.compass.challenge.api.mappers;

import com.compass.challenge.domain.entitys.Post;
import com.compass.challenge.payload.PostDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
public class PostConverter {


    public Post toEntity(PostDto dto) {
        return Post.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .userId(dto.getUseId())
                .title(dto.getTitle())
                .body(dto.getBody())
                .comments(dto.getComments())
                .history(dto.getHistory())
                .build();
    }

    public Post toEntityUpdate(Post entity, PostDto dto, Long id) {
        return Post.builder()
                .id(String.valueOf(id.longValue()))
                .userId(dto.getUseId() != null ? dto.getUseId() : entity.getUserId())
                .title(dto.getTitle() != null ? dto.getTitle() : entity.getTitle() )
                .body(dto.getBody() != null ? dto.getBody() : entity.getBody())
                .comments(dto.getComments() != null ? dto.getComments() :entity.getComments())
                .history(dto.getHistory() !=null ? dto.getHistory(): entity.getHistory())
                .build();
    }

    public PostDto toDTO(Post entity){
        return PostDto.builder()
                .useId(entity.getUserId())
                .id(Long.valueOf(entity.getId()))
                .title(entity.getTitle())
                .body(entity.getBody())
                .comments(entity.getComments())
                .history(entity.getHistory())
                .build();
    }

    public List<PostDto> toListDto(List<Post> postsList){
        return postsList.stream().map(this:: toDTO).toList();

    }

}
