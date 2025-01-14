package com.aamir.entity;

import java.time.LocalDateTime;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notes extends BaseModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String title;
private String description;
//bahut sara notes 1 category me ho skta hai
@ManyToOne
private Category category;
//jab file ka kaam krenge to file bhi de denge
@ManyToOne
private FileDetails fileDetails; 
private boolean isDeleted;
//private Date deletedOn; use krenge LocalDate days ko minus krne ke liye
private LocalDateTime deletedOn;

}
