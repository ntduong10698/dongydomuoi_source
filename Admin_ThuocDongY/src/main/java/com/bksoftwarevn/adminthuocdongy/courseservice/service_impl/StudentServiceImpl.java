package com.bksoftwarevn.adminthuocdongy.courseservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.StudentProduct;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.StudentProductRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.StudentProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl extends BaseServiceImpl<StudentProduct,Integer, StudentProductRepo> implements StudentProductService {

    public StudentServiceImpl(StudentProductRepo repo) {
        super(repo, "StudentProduct");
    }


    @Override
    public List<StudentProduct> findByCourse(int courseId, int comId, Boolean enableI, Boolean enableOn, Boolean enableOff) {
        try {
            return repo.findByCourse(courseId, comId, enableI, enableOn, enableOff);
        }catch (Exception e){
            throw e;
        }
    }
}
