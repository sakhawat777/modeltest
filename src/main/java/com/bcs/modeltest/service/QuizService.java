package com.bcs.modeltest.service;

import com.bcs.modeltest.dao.QuestionDao;
import com.bcs.modeltest.dao.QuizDao;
import com.bcs.modeltest.model.Question;
import com.bcs.modeltest.model.QuestionWrapper;
import com.bcs.modeltest.model.Quiz;
import com.bcs.modeltest.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

   public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {
      Optional<Quiz> quiz = quizDao.findById(id);
      List<Question> questionsFromDB = quiz.get().getQuestions();
      List<QuestionWrapper> questionsForUsers = new ArrayList<>();
      for (Question q: questionsFromDB){
         QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
         questionsForUsers.add(qw);
      }
      return  new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
   }

   public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
      Quiz quiz = quizDao.findById(id).get();
      List<Question> questions = quiz.getQuestions();
      int right = 0;
      int i = 0;
      for (Response response : responses){
         if(response.getResponse().equals(questions.get(i).getRightAnswer()))
            right ++;
         i++;
      }
      return  new ResponseEntity<>(right, HttpStatus.OK);

   }
}
