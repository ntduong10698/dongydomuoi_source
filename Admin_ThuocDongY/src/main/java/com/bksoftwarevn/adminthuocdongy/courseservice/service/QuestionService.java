package com.bksoftwarevn.adminthuocdongy.courseservice.service;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Question;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.QuestionOption;

import java.util.List;

public interface QuestionService extends BaseService<Question>{

    List<Question> findByLesson(int id);

    List<QuestionOption> saveAll(List<QuestionOption> options);
}
