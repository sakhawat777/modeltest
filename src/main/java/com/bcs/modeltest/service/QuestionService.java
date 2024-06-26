package com.bcs.modeltest.service;

import com.bcs.modeltest.model.Question;
import com.bcs.modeltest.dao.QuestionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

private final QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
       try{
           return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
       }
        catch (Exception e) {
        e.printStackTrace();
    }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Data Successfully Created...", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String deleteQuestion(int id) {
        questionDao.deleteById(id);
        return "Deleted Successfully...";
    }

    public String updateQuestion(int id, Question question) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setQuestionTitle(question.getQuestionTitle());
            existingQuestion.setOption1(question.getOption1());
            existingQuestion.setOption2(question.getOption2());
            existingQuestion.setOption3(question.getOption3());
            existingQuestion.setOption4(question.getOption4());
            existingQuestion.setRightAnswer(question.getRightAnswer());
            existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
            existingQuestion.setCategory(question.getCategory());
            questionDao.save(existingQuestion);
            return "Updated Successfully...";
        } else {
            return "Question not found for id: " + id;
        }
    }
}
