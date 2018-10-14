package com.project.collathon.repository.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.collathon.repository.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Validated
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class History {
    @Id @GeneratedValue
    @Column(name="HISTORY_ID")
    private Long id;
    private Timestamp date;
    @Length(max=100)
    private String title;
    @Length(max=80)
    private String address;
    @Lob
    private String content;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PET_ID", foreignKey = @ForeignKey(name="HISTORY_PET_FK"))
    private Pet pet;
}
