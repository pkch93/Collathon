package com.project.collathon.controller;

import com.project.collathon.repository.history.History;
import com.project.collathon.repository.pet.Pet;
import com.project.collathon.service.HistoryService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pet/{petId}")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/history")
    public List<History> getHistory(@PathVariable Long petId){
        return historyService.getHistories(petId);
    } // history 정보 가져오기

    @PostMapping("/history")
    public List<History> createHistory(@PathVariable Long petId, @RequestParam String title,
                                @RequestParam String address, @RequestParam String content){
        historyService.createHistory(petId, title, address, content);
        return historyService.getHistories(petId);
    } // history 생성

    @DeleteMapping("/history/{historyId}")
    public List<History> deleteHistory(@PathVariable Long petId, @PathVariable Long historyId) {
        historyService.deleteHistory(historyId);
        return historyService.getHistories(petId);
    } // history 삭제 시
}
