package com.project.collathon.service;

import com.project.collathon.repository.history.History;
import com.project.collathon.repository.history.HistoryRepository;
import com.project.collathon.repository.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    HistoryRepository historyRepository;

    public History getHistory(Long historyId){
        Optional<History> history = historyRepository.findById(historyId);
        return history.orElseThrow(RuntimeException::new);
    }
    public List<History> getHistories(Long petId){
        List<History> histories = historyRepository.findHistoriesByPet_Id(petId);
        return histories;
    } // 이력 조회

    public History createHistory(){
        History history = new History();
        return historyRepository.save(history);
    } // 이력 생성

    public void updateHistory(){

    } // 이력 수정

    public void deleteHistory(Long id){
        historyRepository.deleteById(id);
    } // 이력 삭제
}
