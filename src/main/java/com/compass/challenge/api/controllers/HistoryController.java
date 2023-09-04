package com.compass.challenge.api.controllers;

import com.compass.challenge.domain.business.services.HistotyService;
import com.compass.challenge.domain.entitys.History;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.HistoryDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historys")
@RequiredArgsConstructor
public class HistoryController {

    private HistotyService histotyService;

    @PostMapping
    public ResponseEntity<HistoryDto> create(@RequestBody HistoryDto data) {
        HistoryDto HistoryCreated = histotyService.createHistory(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(HistoryCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> findById(@PathVariable (value = "id") Long id) throws ResourceNotFoundException {
        return histotyService.getIdHistorys(id).map(instructor ->ResponseEntity.ok(instructor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
        histotyService.deletHistory(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Object> updateInstructor(@PathVariable Long id, @RequestBody HistoryDto data ) {
        try {
            HistoryDto historyUpdate = histotyService.updateHistory(id, data);
            return ResponseEntity.ok(historyUpdate);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
