package com.project.collathon.controller;

import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.users.UserRepository;
import com.project.collathon.repository.users.Users;
import com.project.collathon.service.HistoryService;
import com.project.collathon.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class PetController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PetService petService;

    @Autowired
    HistoryService historyService;

    @GetMapping("/pet")
    public ModelAndView petMain(){
        List<Pet> petList = petService.getPetList();
        ModelAndView mav = new ModelAndView("petlist", "petList", petList);
        mav.addObject("mode", "petlist");
        return mav;
    }

    @GetMapping("/mypet")
    public ModelAndView myPet(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        List<Pet> petList = petService.getMyPet(user.getName());
        ModelAndView mav = new ModelAndView("petlist", "petList", petList);
        mav.addObject("mode", "mypet");
        return mav;
    }

    @GetMapping("/pet/{id}")
    public ModelAndView petDetails(@PathVariable Long id){
        Pet pet = petService.getDetail(id);
        ModelAndView modelAndView = new ModelAndView("pet", "pet", pet);
        return modelAndView;
    }

    @GetMapping(value={"/register", "/register/pet/{petId}"})
    public String register(@Nullable @PathVariable Long petId) {
        return "register";
    }

    @PostMapping("/pet")
    public String registerPet(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Users user = userRepository.findById(Long.parseLong(request.getParameter("userId"))).get();
        boolean isBreed = request.getParameter("isbreed").equals("T") ? true : false;
        Pet pet = petService.registerPet(request.getParameter("petname"), request.getParameter("category"),
                request.getParameter("breed"), user, request.getParameter("intro"), isBreed, multipartRequest.getFile("profile"));
        return "redirect:/pet/" + pet.getId();
    }

    @GetMapping("/pet/{id}/modify")
    public ModelAndView modify(@PathVariable Long id){
        Pet pet = petService.getDetail(id);
        return new ModelAndView("modify", "pet", pet);
    }

    @PatchMapping("/pet/{id}")
    public ModelAndView updatePet(HttpServletRequest request,
                                  @RequestParam(name="modify-mode") String mode, @PathVariable Long id) throws IOException {
        ModelAndView mav = new ModelAndView("pet");
        Pet pet = petService.updatePet(request, id, mode);
        mav.addObject("pet", pet);
        return mav;
    }

    @DeleteMapping("/pet/{id}")
    public String deletePet(@PathVariable Long id){
        petService.deletePet(id);
        return "redirect:/pet";
    }

    @PostMapping("/search")
    public String search(HttpServletRequest request, Model model) {
        String method = request.getParameter("method");
        String query = request.getParameter("query");
        List<Pet> petList = petService.searchPets(method, query);
        model.addAttribute(petList);
        model.addAttribute("mode", "search");
        return "petlist";
    }
}
