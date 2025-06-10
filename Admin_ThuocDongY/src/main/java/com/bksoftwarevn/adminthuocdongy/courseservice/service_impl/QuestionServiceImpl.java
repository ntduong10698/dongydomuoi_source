package com.bksoftwarevn.adminthuocdongy.courseservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Question;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.QuestionOption;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.OptionRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.QuestionRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl extends BaseServiceImpl<Question, Integer, QuestionRepo> implements QuestionService {

    public QuestionServiceImpl(QuestionRepo repo) {
        super(repo, "Question");
    }

    @Autowired
    private OptionRepo optionRepo;

    @Override
    public List<Question> findByLesson(int id) {
        try {
            return repo.findByLesson(id);
        }catch (Exception ex){
            log.error("find quest by lesson err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<QuestionOption> saveAll(List<QuestionOption> options) {
        try {
            return optionRepo.saveAll(options);
        }catch (Exception ex){
            log.error("save options err {0}", ex);
            throw ex;
        }
    }
}
