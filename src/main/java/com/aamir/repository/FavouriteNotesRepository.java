package com.aamir.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aamir.entity.FavouriteNotes;

public interface FavouriteNotesRepository extends JpaRepository<FavouriteNotes, Integer> {

	List<FavouriteNotes> findByUserId(int userId);

}
