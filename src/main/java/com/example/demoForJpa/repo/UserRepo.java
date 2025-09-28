package com.example.demoForJpa.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoForJpa.entity.User;

public interface UserRepo  extends JpaRepository<User ,Long> {


	Optional<User> findByUsername(String username);

	Optional<User> findById(Long id);

	void deleteById(Long id);

	boolean existsById(Long id);
}



