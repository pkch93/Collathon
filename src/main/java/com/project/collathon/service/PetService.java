package com.project.collathon.service;

import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.pet.PetRepository;
import com.project.collathon.repository.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return petList;
    }

    public List<Pet> getMyPet(String name){
        return petRepository.findByUserName(name);
    }

    public Pet getDetail(Long id){
        Optional<Pet> pet = petRepository.findById(id);
        return pet.orElse(null);
    } // 상세 정보 조회

    public Pet registerPet(String name, String category, String breed, Users user,
                            @Nullable String intro, @Nullable boolean isBreed, @Nullable MultipartFile profile) throws IOException {
        Pet pet = new Pet(name, category, breed, new Timestamp(System.currentTimeMillis()), user);
        String saveUrl = restore(profile);
        extraDataUpdate(pet, intro, isBreed, saveUrl);
        return petRepository.save(pet);
    } // Pet 등록

    public void extraDataUpdate(Pet pet, @Nullable String intro, @Nullable boolean isBreeding, @Nullable String profile){
        pet.setIntro(intro);
        pet.setBreeding(isBreeding);
        pet.setProfile(profile);
    } // 추가 정보 등록시

    public Pet updatePet(HttpServletRequest request, Long petId, String mode) throws IOException {
        Optional<Pet> opPet = petRepository.findById(petId);
        Pet pet = opPet.orElseThrow(RuntimeException::new);
        if(mode.equals("required")){
            String petName = request.getParameter("name");
            String category = request.getParameter("category");
            String breed = request.getParameter("breed");
            pet.updateRequire(petName, category, breed);
        }
        else if(mode.equals("intro")){
            String intro = request.getParameter("intro");
            pet.setIntro(intro);
        }
        else if(mode.equals("profile")){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile profile = multipartRequest.getFile("profile");
            String saveUrl = restore(profile);
            pet.setProfile(saveUrl);
        }
        else if(mode.equals("breeding")){
            String isbreed = request.getParameter("isbreed");
            boolean breeding = isbreed.equals("T") ? true : false;
            pet.setBreeding(breeding);
        }
        return petRepository.save(pet);
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
        FileOutputStream fos = new FileOutputStream("C:\\Users\\hp\\Desktop\\collathon-project\\src\\main\\resources\\static\\"+SAVE_PATH + "/" + saveFileName);
        fos.write(data);
        fos.close();
    }
}
