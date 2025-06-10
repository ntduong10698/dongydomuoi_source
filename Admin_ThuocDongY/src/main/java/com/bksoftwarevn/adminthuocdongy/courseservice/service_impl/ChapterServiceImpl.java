package com.bksoftwarevn.adminthuocdongy.courseservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Chapter;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.ChapterRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChapterServiceImpl extends BaseServiceImpl<Chapter, Integer, ChapterRepo> implements ChapterService {

    public ChapterServiceImpl(ChapterRepo repo) {
        super(repo, "Chapter");
    }

    @Override
    public List<Chapter> findByCourse(int id) {
        try {
            return repo.findByCourse(id);
        }catch (Exception ex){
            log.error("find Chapter by course err {0}", ex);
            throw ex;
        }
    }
}
