package com.springboot.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Subject;
import com.springboot.repository.SubjectRepository;

/***
 * 
 * @author Jeet Khatri
 * @date 16-Oct-2018
 * @link https://github.com/JeetKhatri/SpringBoot-many-to-one-with-mysql.git
 *
 */

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<Subject> getListOfSubjects() {
		return subjectRepository.findAll();
	}

	public Subject addSubject(@Valid Subject subject) {
		return subjectRepository.save(subject);
	}

	public Subject updateSubject(@Valid Subject subject, int id) {
		Subject subject1 = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));

		subject1.setTitle(subject.getTitle());
		subject1.setDescription(subject.getDescription());

        return subjectRepository.save(subject1);
	}

	public Subject getSubject(int id) {
		// lambda expression
		return subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
	}

	public ResponseEntity<?> deleteSubject(int id) {
		// lambda expression
		Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
		subjectRepository.deleteById(subject.getId());
		return ResponseEntity.ok().build();
	}

}
