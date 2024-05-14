package com.bcs.modeltest.controller;

import com.bcs.modeltest.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {
    private  final QuizService quizService;
   @PostMapping("create")
public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
    return  quizService.createQuiz(category, numQ, title);
    }
}
