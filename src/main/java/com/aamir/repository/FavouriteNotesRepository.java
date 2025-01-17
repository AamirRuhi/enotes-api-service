package com.aamir.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aamir.entity.FavouriteNotes;
@Repository
public interface FavouriteNotesRepository extends JpaRepository<FavouriteNotes, Integer> {

	List<FavouriteNotes> findByUserId(int userId);

}
