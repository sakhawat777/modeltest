package com.bcs.modeltest.dao;

import com.bcs.modeltest.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    static List<Question> findByCategory(String category) {
        return null;
    }
}
