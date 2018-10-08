package com.project.collathon.service;

import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService{

    private static final String SAVE_PATH = "/uploads";
    private static final String PREFIX_URL = "/uploads/";

    @Autowired
    PetRepository petRepository;

    public List<Pet> getPetList(){
        List<Pet> petList = petRepository.findAll(Sort.by(Sort.Order.desc("register")));
        // petList.sort(Comparator.comparing(Pet::getRegister));
        return petList;
    }

    public Pet getDetail(Long id){
        Optional<Pet> pet = petRepository.findById(id);
        return pet.orElse(null);
    } // 상세 정보 조회

    public Pet registerPet(String name, String category, String breed, String userName,
                            @Nullable String intro, @Nullable boolean isBreed, @Nullable MultipartFile profile) throws IOException {
        Pet pet = new Pet(name, category, breed, new Timestamp(System.currentTimeMillis()), userName);
        String saveUrl = restore(profile);
        extraDataUpdate(pet, intro, isBreed, saveUrl);
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
        List<Pet> searchResult = null;
        if (method.equals("name")) searchResult = petRepository.findByNameLike(query);
        else if (method.equals("category")) searchResult = petRepository.findByCategory(query);
        else if (method.equals("breed")) searchResult = petRepository.findByBreed(query);
        return searchResult;
    } // 검색 Logic

    public String restore(MultipartFile file) throws IOException {
        String url;

        String originFilename = file.getOriginalFilename();
        String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());

        String saveFileName = generateFileName(extName);
        writeFile(file, saveFileName);
        url = PREFIX_URL + saveFileName;
        return url;
    }

    private String generateFileName(String extName){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMddhhMMss");
        String fileName = now.format(formatter) + extName;
        return fileName;
    }

    private void writeFile(MultipartFile file, String saveFileName) throws IOException {
        byte [] data = file.getBytes();
        FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
        fos.write(data);
        fos.close();
    }
}
