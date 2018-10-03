package com.project.collathon.repository.history;

import com.project.collathon.repository.pet.Pet;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Validated
public class History {
    @Id @GeneratedValue
    @Column(name="HISTORY_ID")
    private Long id;
    private Timestamp date;
    @Length(max=80)
    private String address;
    @Lob
    private String content;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PET_ID")
    private Pet pet;
}
