package com.project.collathon;

import com.project.collathon.repository.history.History;
import com.project.collathon.repository.history.HistoryRepository;
import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.pet.PetRepository;
import com.project.collathon.repository.users.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppDataTest {

    @Autowired
    PetRepository petRepository;

    @Autowired
    HistoryRepository historyRepository;

    Users testUser;

    @Before
    public void setUp(){
        testUser = new Users();
        testUser.setName("박경철");
    }

    @Test
    public void addPet(){
        Pet pet = new Pet("멍뭉이", "개", "치와와",
                Timestamp.valueOf(LocalDateTime.now()), testUser.getName());
        Pet testPet = petRepository.save(pet);
        assertEquals(pet, testPet);
    }

    @Test
    public void addHistory(){
        History history = new History();
        Optional<Pet> opPet = petRepository.findById(1L);
        Pet pet = opPet.orElse(new Pet());
        history.setDate(Timestamp.valueOf(LocalDateTime.now()));
        history.setContent("이력 테스트 1");
        history.setAddress("대전광역시 유성구 궁동");
        history.setPet(pet);
        History testHistory = historyRepository.save(history);
        assertEquals(history, testHistory);
    }
}
