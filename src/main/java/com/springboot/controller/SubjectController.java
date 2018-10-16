package com.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Subject;
import com.springboot.service.SubjectService;

/***
 * 
 * @author Jeet Khatri
 * @date 16-Oct-2018
 * @link https://github.com/JeetKhatri/SpringBoot-many-to-one-with-mysql.git
 *
 */

@RestController
@RequestMapping("/subjects")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping
	public List<Subject> listOfSubjects() {
		return subjectService.getListOfSubjects();
	}
	
	@PostMapping
	public Subject addSubject(@Valid @RequestBody Subject subject) {
		return subjectService.addSubject(subject);
	}
	
	@PutMapping("/{id}")
	public Subject updateSubject(@Valid @RequestBody Subject subject, @PathVariable int id) {
		return subjectService.updateSubject(subject,id);
	}
	
	/*
	 * Two way to get variable from url
	 * 1.	@PathVariable int id  (same name required in getMapping)
	 * 2.	@PathVariable(name="id") int subjectId (can use different name)
	 * 
	 */
	@GetMapping("/{id}")
	public Subject getSubject(@PathVariable(name="id") int subjectId) {
		return subjectService.getSubject(subjectId);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSubject(@PathVariable int id) {
		return subjectService.deleteSubject(id);
	}
	
}
