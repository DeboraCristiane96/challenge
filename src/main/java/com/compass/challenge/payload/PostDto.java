package com.compass.challenge.payload;

import com.compass.challenge.domain.entitys.Comments;
import com.compass.challenge.domain.entitys.History;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.util.Set;
@Data
@Getter
@Setter
@Builder

public class PostDto {

    @JsonProperty("userId")
    private Long useId;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;
    @JsonProperty("comments")
    private Set<Comments> comments;
    @JsonProperty("history")
    private Set<History> history;
}
