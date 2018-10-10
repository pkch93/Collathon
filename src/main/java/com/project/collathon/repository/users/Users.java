package com.project.collathon.repository.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.collathon.repository.pet.Pet;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Validated
public class Users {
    @Id @Column(name="USER_ID")
    private Long id;
    private String name;
    private String email;
    private String age;
    private String gender;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Pet> pets;
}
