package com.bcs.modeltest.dao;

import com.bcs.modeltest.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
