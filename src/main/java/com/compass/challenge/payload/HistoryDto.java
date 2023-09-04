package com.compass.challenge.payload;

import com.compass.challenge.enums.PostStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@Builder
public class HistoryDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    private LocalDateTime date;

    @JsonProperty("status")
    private PostStatus status;

}
