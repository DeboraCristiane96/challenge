package com.compass.challenge.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class CommentDto {

    @JsonProperty("postId")
    private Long podtId;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("id")
    private String commentId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("body")
    private String body;
}
