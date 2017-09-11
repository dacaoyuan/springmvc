package com.shkj.spring.service;

import com.shkj.spring.model.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
	Course getCoursebyId(Integer courseId);
}
