package com.springboot.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Topic;
import com.springboot.repository.SubjectRepository;
import com.springboot.repository.TopicRepository;

/***
 * 
 * @author Jeet Khatri
 * @date 16-Oct-2018
 * @link https://github.com/JeetKhatri/SpringBoot-many-to-one-with-mysql.git
 *
 */

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<Topic> getListOfTopicsBySubjectId(int subjectId) {
		return topicRepository.findBySubjectId(subjectId);
	}

	public Topic addTopic(@Valid Topic topic,int subjectId) {
		
		// lambda expression
		return subjectRepository.findById(subjectId).map(subject ->{
			topic.setSubject(subject);
			return topicRepository.save(topic);
		}).orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectId));
	}

	public Topic updateTopic(@Valid Topic topicFromRequest, int id, int subjectId) {
		
		if(subjectRepository.existsById(subjectId)) {
			// lambda expression
			return topicRepository.findById(id).map(topic -> {
				topic.setTopicName(topicFromRequest.getTopicName());
				topic.setTopicDesc(topicFromRequest.getTopicDesc());
				return topicRepository.save(topic);
			}).orElseThrow(() -> new ResourceNotFoundException("Topic", "topicId", id));
		}else {
			throw new ResourceNotFoundException("Subject", "id", subjectId);
		}
	}

	public Topic getTopic(int id, int subjectId) {
		if(!subjectRepository.existsById(subjectId)) {
			throw new ResourceNotFoundException("Subject", "id", subjectId);
		}else if(!topicRepository.existsById(id)){
			throw new ResourceNotFoundException("Topic", "topicId", id);
		}else {
			// Stream and lambda are feature of Java 8
			return topicRepository.findBySubjectId(subjectId)
					.stream()
					.filter(t -> t.getTopicId()==id)
					.findFirst()
					.orElseThrow(() -> new ResourceNotFoundException("Topic", "topicId", id));
		}
	}

	public ResponseEntity<?> deleteTopic(int id,int subjectId) {
		
		if(subjectRepository.existsById(subjectId)) {
			// lambda expression
			return topicRepository.findById(id).map(topic -> {
				topicRepository.delete(topic);
				return ResponseEntity.ok().build();
			}).orElseThrow(() -> new ResourceNotFoundException("Topic", "topicId", id));
		}else {
			throw new ResourceNotFoundException("Subject", "id", subjectId);
		}
	}

}
