package com.project.collathon.repository.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByName(String name); // 이름 검색
    List<Pet> findByCategory(String category); // 분류 검색
    List<Pet> findByBreed(String breed); //품종 검색
}
