package com.bksoftwarevn.adminthuocdongy.courseservice.service;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.StudentProduct;

import java.util.List;

public interface StudentProductService extends BaseService<StudentProduct> {

    List<StudentProduct> findByCourse(int courseId, int comId, Boolean enableI, Boolean enableOn, Boolean enableOff);

}
