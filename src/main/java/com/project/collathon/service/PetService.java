package com.project.collathon.service;

import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public List<Pet> getPetList(){
        List<Pet> petList = petRepository.findAll();
        petList.sort(Comparator.comparing(Pet::getRegister));
        return petList;
    }

    public Pet getDetail(Long id){
        Optional<Pet> pet = petRepository.findById(id);
        return pet.orElse(null);
    } // 상세 정보 조회

    public Pet registerPet(String name, String category, String breed, String userName,
                            @Nullable String intro, @Nullable boolean isBreed, @Nullable String profile){
        Pet pet = new Pet(name, category, breed, new Timestamp(System.currentTimeMillis()), userName);
        extraDataUpdate(pet, intro, isBreed, profile);
        return petRepository.save(pet);
    } // Pet 등록

    public void extraDataUpdate(Pet pet, @Nullable String intro, @Nullable boolean isBreeding, @Nullable String profile){
        pet.setIntro(intro);
        pet.setBreeding(isBreeding);
        pet.setProfile(profile);
    } // 추가 정보 등록시

    public void updatePet(){

    } // 애완동물 정보 수정

    public void deletePet(Long id){
        petRepository.deleteById(id);
    } // 애완동물 삭제

    public List<Pet> searchPets(String method, String query){
        List<Pet> searchResult = new ArrayList<>();
        if (method.equals("name")){
            searchResult = petRepository.findByName(query);
        }
        else if (method.equals("category")){
            searchResult = petRepository.findByCategory(query);
        }
        else if (method.equals("breed")){
            searchResult = petRepository.findByBreed(query);
        }
        return searchResult;
    }
    // 검색 Logic
}
