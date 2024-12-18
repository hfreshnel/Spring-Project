package com.isn.quizplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isn.quizplatform.service.ClassementService;

import java.util.List;
import java.util.Map;

@RestController
public class ClassementController {

    @Autowired
    private ClassementService classementService;

    @GetMapping("/classement")
    public List<Map<String, Object>> getClassement(@RequestParam("quizID") Long quizID) {
        return classementService.getClassementByQuizId(quizID);
    }

    @GetMapping("/propositions-percentage")
    public List<Map<String, Object>> getPropositionsPercentage(@RequestParam("questionID") Long questionID) {
        System.out.println("Controller: Received request for /propositions-percentage with questionID: " + questionID);
        return classementService.getPropositionsPercentage(questionID);
    }
}
