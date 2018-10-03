package com.project.collathon.controller;

import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.users.Users;
import com.project.collathon.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping("/pet")
    public ModelAndView petMain(){
        List<Pet> petList = petService.getPetList();
        return new ModelAndView("petlist", "petList", petList);
    }

    @GetMapping("/pet/{id}")
    public ModelAndView petDetails(@PathVariable Long id){
        Pet pet = petService.getDetail(id);
        return new ModelAndView("pet", "pet", pet);
    }

    @PostMapping("/pet")
    public ModelAndView registerPet(HttpServletRequest request){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        boolean isBreed = request.getParameter("isBreed") == "T" ? true : false;
        Pet pet = petService.registerPet(request.getParameter("name"), request.getParameter("category"), request.getParameter("breed"), user.getName(),
                request.getParameter("intro"), isBreed,request.getParameter("profile"));
        return new ModelAndView("pet", "pet", pet);
    }

    @PatchMapping("/pet/{id}")
    public ModelAndView updatePet(@PathVariable Long id){
        // 수정 페이지 고민
        return new ModelAndView();
    }

    @DeleteMapping("/pet/{id}")
    public String deletePet(@PathVariable Long id){
        petService.deletePet(id);
        return "redirect:/pet";
    }
}
