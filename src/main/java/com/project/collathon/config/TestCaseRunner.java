package com.project.collathon.config;

import com.project.collathon.repository.history.HistoryRepository;
import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.pet.PetRepository;
import com.project.collathon.repository.users.UserRepository;
import com.project.collathon.repository.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class TestCaseRunner implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /* Test용 User 2명 */
        Users testuser1 = new Users();
        testuser1.setId(1000000L);
        testuser1.setName("홍길동");
        testuser1.setAge("30-39");
        testuser1.setEmail("hongildong@naver.com");
        userRepository.save(testuser1);
        Users testuser2 = new Users();
        testuser2.setId(1000001L);
        testuser2.setName("김두한");
        testuser2.setAge("40-49");
        testuser2.setEmail("4dallor@naver.com");
        userRepository.save(testuser2);
        /* Test용 Pet 추가 */
        Pet testpet1 = new Pet("헐크", "개", "불독", Timestamp.valueOf(LocalDateTime.now()), testuser1);
        testpet1.setProfile("/uploads/dog.PNG");
        testpet1.setBreeding(true);
        testpet1.setIntro("헐크처럼 쌘 강아지!");
        petRepository.save(testpet1);
        Pet testpet2 = new Pet("토종산개", "개", "진돗개", Timestamp.valueOf(LocalDateTime.now()), testuser2);
        testpet2.setProfile("/uploads/dog.PNG");
        testpet2.setIntro("역시 국산이 최고지!");
        petRepository.save(testpet2);
        Pet testpet3 = new Pet("초코", "개", "푸들", Timestamp.valueOf(LocalDateTime.now()), testuser1);
        testpet3.setProfile("/uploads/dog.PNG");
        testpet3.setIntro("I got you babe, I call, I call it chocolate love\n" +
                "\n" +
                "너를 원해, 가질래 아찔 아찔 오 chocolate love\n" +
                "\n" +
                "I got you babe, I call, I call it chocolate love\n" +
                "\n" +
                "너를 원해, 가질래 달콤 달콤 오 chocolate love");
        petRepository.save(testpet3);
    }
}
