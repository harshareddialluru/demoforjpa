package com.example.demoForJpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoForJpa.entity.Student;
import com.example.demoForJpa.repo.StudentRepo;
@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StudentController {
	
	 @Autowired
	 StudentRepo studentRepo;	
	 
    @PostMapping("/students")
	public ResponseEntity<Student>saveStudent(@RequestBody Student student) {
    	return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
    }
   
   @GetMapping("/students")
      public ResponseEntity<List<Student>> getStudent(){
	    return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
   }
    
   @GetMapping("/Getting/{id}")
   	public ResponseEntity<Student> getStudent(@PathVariable Long id) {
    Optional<Student> student = studentRepo.findById(id);
    	if(student.isPresent()) {
    		return new ResponseEntity<>(student.get(),HttpStatus.OK);
    	}{
     	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
   @DeleteMapping("/Deleting/{id}")
  	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
   	Optional<Student> student = studentRepo.findById(id);
   	if(student.isPresent()) {
   		studentRepo.deleteById(id);
   		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   	}{
      	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
      @PutMapping("/Puting/{id}")
      public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student stud ){
      Optional<Student> student = studentRepo.findById(id);

    if(student.isPresent()) {
     student.get().setStudentName(stud.getStudentName());
     student.get().setStudentEmail(stud.getStudentEmail());
     student.get().setStudentAddress(stud.getStudentAddress());
     
     return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
    }
   else {
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
    
   


    }
     


 
}