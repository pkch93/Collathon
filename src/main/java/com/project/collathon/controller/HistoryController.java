package com.project.collathon.controller;

import com.project.collathon.repository.history.History;
import com.project.collathon.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet/{petId}")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping("/history/{historyId}")
    public History getHistory(@PathVariable Long historyId){
        return historyService.getHistory(historyId);
    } // history 정보 가져오기

    @PostMapping("/history")
    public String createHistory(@PathVariable Long petId){
        historyService.createHistory();
        return "redirect:/pet/" + petId;
    } // history 생성

    @PatchMapping("/history/{historyId}")
    public String updateHistory(@PathVariable Long petId, @PathVariable Long historyId){
        historyService.updateHistory();
        return "redirect:/pet/" + petId;
    } // history 수정

    @DeleteMapping("/history/{historyId}")
    public String deleteHistory(@PathVariable Long petId, @PathVariable Long historyId) {
        historyService.deleteHistory(historyId);
        return "redirect:/pet/" + petId;
    } // history 삭제 시
}
