package com.bksoftwarevn.adminthuocdongy.courseservice.service;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lecturer;

import java.util.List;

public interface LecturerService extends BaseService<Lecturer>{

    List<Lecturer> findByCompany(int comId) throws Exception;
}
