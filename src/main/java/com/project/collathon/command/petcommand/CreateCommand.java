package com.project.collathon.command.petcommand;

import com.project.collathon.command.Command;
import com.project.collathon.repository.pet.Pet;
import com.project.collathon.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Map;

@Component
public class CreateCommand implements Command {

    @Autowired
    PetService petService;

    @Override
    public void excute(Map map) throws IOException {
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
        Model model = (Model) map.get("model");
        boolean isBreed = request.getParameter("isBreed") == "T" ? true : false;
        Pet pet = petService.registerPet(request.getParameter("name"), request.getParameter("category"),
                request.getParameter("breed"), request.getParameter("username"),
                request.getParameter("intro"), isBreed, request.getFile("profile"));
        model.addAttribute(pet);
    }
}
