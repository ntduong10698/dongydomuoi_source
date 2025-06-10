package com.bksoftwarevn.adminthuocdongy.courseservice.service;


import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Chapter;

import java.util.List;

public interface ChapterService extends BaseService<Chapter>{
    List<Chapter> findByCourse(int id);
}
