package com.project.collathon.repository.pet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.collathon.repository.history.History;
import com.project.collathon.repository.users.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;

@Entity
@Data
@Validated
@NoArgsConstructor // args가 없는 생성자
@RequiredArgsConstructor // @AllArgsConstructor args가 모든 필드에 필요한 생성자
// @RequiredArgsConstructor는 final이나 @Nonnull 필드만 생성자의 args로 필요한 생성자를 만듬
public class Pet {
    @Id @GeneratedValue
    @Column(name="PET_ID")
    private Long id;
    @Length(max = 20) @NonNull
    private String name;
    @NonNull
    private String category;
    @NonNull
    private String breed;
    @NonNull
    private Timestamp register;
    @Lob
    private String intro;
    private boolean isBreeding = false;
    private String profile;
    @NonNull
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private Users user;
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "pet")
    @JsonIgnore
    private List<History> histories;

    public String registerDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        return register.toLocalDateTime().format(formatter);
    }
}
