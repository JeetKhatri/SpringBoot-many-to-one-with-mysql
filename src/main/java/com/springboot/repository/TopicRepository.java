package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Subject;
import com.springboot.model.Topic;

/***
 * 
 * @author Jeet Khatri
 * @date 16-Oct-2018
 * @link https://github.com/JeetKhatri/SpringBoot-many-to-one-with-mysql.git
 *
 */

public interface TopicRepository extends JpaRepository<Topic, Integer>{

	public List<Topic> findBySubjectId(int topicId);
}

