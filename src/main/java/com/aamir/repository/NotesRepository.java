package com.aamir.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aamir.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer>{

	Page<Notes> findByCreatedBy(Integer userId, Pageable pageable);

	List<Notes> findByCreatedByAndIsDeletedTrue(Integer userId);

	Page<Notes> findByCreatedByAndIsDeletedFalse(Integer userId, Pageable pageable);

	List<Notes> findAllByIsDeletedAndDeletedOnBefore(boolean b, LocalDateTime cutOffDate);
//Searches for keyword in the title,description ,category column, ignoring case. based on certain condition
	//The CONCAT('%', :keyword, '%') wraps the keyword with %, meaning it matches if the keyword appears anywhere in the text (prefix, infix, or suffix).
	//notes search ke liye ,hql(jpql),Lower means caseinsensitive ,space bhi dena h
	@Query("SELECT n FROM Notes n " +
		       "WHERE (LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "OR LOWER(n.description) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "OR LOWER(n.category.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
		       "AND n.isDeleted = false " +
		       "AND n.createdBy = :userId")
	Page<Notes> searchNotes(@Param("keyword") String keyword, @Param("userId") Integer userId, Pageable pageable);
	//notesService me ayenge
}
