package com.compass.challenge.api.mappers;

import com.compass.challenge.domain.entitys.History;
import com.compass.challenge.domain.entitys.Post;
import com.compass.challenge.payload.HistoryDto;
import com.compass.challenge.payload.PostDto;

import java.util.List;
import java.util.UUID;

public class HistoryConverter {


    public History toEntity(HistoryDto dto) {
        return History.builder()
                .date(dto.getDate())
                .status(dto.getStatus())
                .build();
    }

    public History toEntityUpdate(History entity, HistoryDto dto, Long id) {
        return History.builder()
                .id(dto.getId())
                .date(dto.getDate() != null ? dto.getDate() : entity.getDate())
                .status(dto.getStatus() != null ? dto.getStatus() : entity.getStatus())
                .build();
    }

    public HistoryDto toDTO(History entity){
        return HistoryDto.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .status(entity.getStatus())
                .build();
    }

    public List<HistoryDto> toListDto(List<History> historoysList){
        return historoysList.stream().map(this:: toDTO).toList();

    }

}
