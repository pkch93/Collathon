package com.project.collathon.service;

import com.project.collathon.repository.history.History;
import com.project.collathon.repository.history.HistoryRepository;
import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        List<History> histories = historyRepository.findByPet_Id(petId);
        return histories;
    } // 이력 조회

    public History createHistory(Long petId, String title, String address, String content){
        Pet pet = petRepository.getOne(petId);
        Timestamp registerDate = Timestamp.valueOf(LocalDateTime.now());
        History history = new History(null, registerDate, title, address, content, pet);
        return historyRepository.save(history);
    } // 이력 생성

    public void deleteHistory(Long id){
        historyRepository.deleteById(id);
    } // 이력 삭제
}
