package com.example.demoForJpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoForJpa.entity.User;
import com.example.demoForJpa.repo.UserRepo;
    @CrossOrigin(origins ="http://localhost:3000")
	@RestController
	@RequestMapping("/api")
	public class UserController {
		@Autowired
		UserRepo userRepo;
		
		
		@PostMapping("/register")
		public ResponseEntity<User>saveUser(@RequestBody User user) {
			return new ResponseEntity<>(userRepo.save(user),HttpStatus.CREATED);
		}
		

		   @GetMapping("/register")
		      public ResponseEntity<List<User>> getStudent(){
			    return new ResponseEntity<>(userRepo.findAll(),HttpStatus.OK);
		   }
		    
		   @PostMapping("/login")
			public ResponseEntity<String> validateUser(@RequestBody User user) {
			    Optional<User> userOptional = userRepo.findByUsername(user.getUsername());

			    if (userOptional.isPresent()) {
			        User users = userOptional.get();
			        if (users.getPassword().equals(user.getPassword())) {
			            return ResponseEntity.ok("Login successful");
			        }
			    }

			    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password: try again and you limited retries");
			}
}