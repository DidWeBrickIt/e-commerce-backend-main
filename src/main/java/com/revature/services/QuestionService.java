package com.revature.services;

import com.revature.models.Payment;
import com.revature.models.Question;
import com.revature.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public Optional<Question> findByUserid(int id) { return questionRepository.findByUserid(id);}

    public Question save(Question question) {
        return questionRepository.save(question);
    }
}
