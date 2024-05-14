package com.bcs.modeltest.controller;

import com.bcs.modeltest.model.QuestionWrapper;
import com.bcs.modeltest.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {
    private  final QuizService quizService;
   @PostMapping("create")
public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
    return  quizService.createQuiz(category, numQ, title);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable int id){
       return  quizService.getQuizQuestion(id);

    }
}
