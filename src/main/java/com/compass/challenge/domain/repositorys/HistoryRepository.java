package com.compass.challenge.domain.repositorys;

import com.compass.challenge.domain.entitys.History;
import com.compass.challenge.payload.HistoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History ,Long> {
    HistoryDto save(HistoryDto dto);
}
