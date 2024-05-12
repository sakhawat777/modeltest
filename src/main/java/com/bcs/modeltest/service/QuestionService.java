package com.bcs.modeltest.service;

import com.bcs.modeltest.Question;
import com.bcs.modeltest.dao.QuestionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

private final QuestionDao questionDao;
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }
}
