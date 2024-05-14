package com.bcs.modeltest.service;

import com.bcs.modeltest.dao.QuestionDao;
import com.bcs.modeltest.dao.QuizDao;
import com.bcs.modeltest.model.Question;
import com.bcs.modeltest.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
   private final QuizDao quizDao;
   private final QuestionDao questionDao;

   public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
      List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
      Quiz quiz = new Quiz();
      quiz.setTitle(title);
      quiz.setQuestions(questions);
      quizDao.save(quiz);
      return new ResponseEntity<>("Success", HttpStatus.CREATED);
   }
}
