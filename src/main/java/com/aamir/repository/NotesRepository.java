package com.aamir.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aamir.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer>{

	Page<Notes> findByCreatedBy(Integer userId, Pageable pageable);

}
