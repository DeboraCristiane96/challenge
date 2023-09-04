package com.compass.challenge.domain.business.impls;

import com.compass.challenge.api.mappers.HistoryConverter;
import com.compass.challenge.domain.business.services.HistotyService;
import com.compass.challenge.domain.entitys.History;
import com.compass.challenge.domain.repositorys.HistoryRepository;
import com.compass.challenge.enums.PostStatus;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.HistoryDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistotyServiceImpl implements HistotyService {

    private HistoryRepository historyRepository;
    private final HistoryConverter converter;

    @Override
    public HistoryDto createHistory(HistoryDto history) {
        return historyRepository.save(history);
    }

    @Override
    public HistoryDto createHistoryDto(HistoryDto historyDto) {
        return historyRepository.save(historyDto);
    }

    @Override
    public HistoryDto getAllHistorys() {
        return (HistoryDto)
                historyRepository.findAll();
    }

    @Override
    public Optional<History> getIdHistorys(Long id) {
        return historyRepository.findById(id);
    }

    @Override
    public HistoryDto updateHistory( Long id,HistoryDto historyDto) throws ResourceNotFoundException {
        History history = historyRepository.getReferenceById(id);
        //postId must be a number between 1 and 100.
        if (id < 1 || id > 100) {
            throw new ResourceNotFoundException();
        }
        history.setDate(historyDto.getDate());
        history.setStatus(historyDto.getStatus());
        history.setStatus(history.getStatus());
        HistoryDto history1 = converter.toDTO(history);
        return historyRepository.save(history1);
    }

    @Override
    public void updateStatusHistory(History history) {
        if (history.getStatus().equals(PostStatus.ENABLED.toString())){
            history.setStatus(PostStatus.DISABLED);
        }else {
            history.setStatus(PostStatus.ENABLED);
        }
    }

    @Override
    public PostStatus returnStatusHistory(Long id) {
        Optional<History> history = historyRepository.findById(id);
        return history.get().getStatus();
    }

    @Override
    public void deletHistory(Long id) {
        historyRepository.deleteById(id);

    }
}
