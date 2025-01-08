package com.aamir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aamir.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Boolean existsByEmail(String email);

}
