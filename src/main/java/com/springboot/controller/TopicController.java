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

import com.springboot.model.Topic;
import com.springboot.service.TopicService;

/***
 * 
 * @author Jeet Khatri
 * @date 16-Oct-2018
 * @link https://github.com/JeetKhatri/SpringBoot-many-to-one-with-mysql.git
 *
 */

@RestController
@RequestMapping("/subjects")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@GetMapping("/{subjectId}/topics")
	public List<Topic> listOfTopics(@PathVariable int subjectId) {
		return topicService.getListOfTopicsBySubjectId(subjectId);
	}
	
	@PostMapping("/{subjectId}/topics")
	public Topic addTopic(@PathVariable int subjectId, @Valid @RequestBody Topic topic) {
		return topicService.addTopic(topic,subjectId);
	}
	
	@PutMapping("/{subjectId}/topics/{id}")
	public Topic updateTopic(@Valid @RequestBody Topic topic, @PathVariable int id, @PathVariable int subjectId) {
		return topicService.updateTopic(topic,id,subjectId);
	}
	
	/*
	 * Two way to get variable from url
	 * 1.	@PathVariable int id  (same name required in getMapping)
	 * 2.	@PathVariable(name="id") int subjectId (can use different name)
	 * 
	 */
	@GetMapping("/{subjectId}/topics/{id}")
	public Topic getTopic(@PathVariable(name="id") int topictId, @PathVariable int subjectId) {
		return topicService.getTopic(topictId, subjectId);
	}
	
	@DeleteMapping("/{subjectId}/topics/{id}")
	public ResponseEntity<?> deleteTopic(@PathVariable int id, @PathVariable int subjectId) {
		return topicService.deleteTopic(id,subjectId);
	}
	
}
