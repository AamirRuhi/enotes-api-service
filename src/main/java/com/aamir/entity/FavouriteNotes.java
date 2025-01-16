package com.aamir.entity;


import java.util.Date;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FavouriteNotes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
	//1 notes favourite to many users
@ManyToOne
private Notes note;

//abhi user module nhi bnaye h isliye userid liye hai qki mapping krna hota hai, FavouriteNotes table bnalo
private Integer userId;
}
