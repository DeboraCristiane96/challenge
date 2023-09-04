package com.compass.challenge.domain.business.services;

import com.compass.challenge.domain.entitys.History;
import com.compass.challenge.enums.PostStatus;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.HistoryDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HistotyService {

    HistoryDto createHistory(HistoryDto history);
    HistoryDto createHistoryDto(HistoryDto historyDto);
    HistoryDto getAllHistorys();
    Optional<History> getIdHistorys(Long id) throws ResourceNotFoundException;
    HistoryDto updateHistory(Long id,HistoryDto historyDto) throws ResourceNotFoundException;
    void updateStatusHistory(History history);
    PostStatus returnStatusHistory(Long id);
    void deletHistory(Long id);

}
