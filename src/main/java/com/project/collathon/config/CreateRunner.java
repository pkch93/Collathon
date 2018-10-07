package com.project.collathon.config;

import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class CreateRunner implements ApplicationRunner {

    @Autowired
    PetRepository petRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Pet pet1 = new Pet("멍뭉이", "개", "치와와", Timestamp.valueOf(LocalDateTime.now()), "박경철");
        Pet pet2 = new Pet("강아지", "개", "진돗개", Timestamp.valueOf(LocalDateTime.now()), "박경철");
        pet1.setProfile("/uploads/dog.PNG");
        pet2.setProfile("/uploads/dog.PNG");
        petRepository.save(pet1);
        petRepository.save(pet2);
    }
}
