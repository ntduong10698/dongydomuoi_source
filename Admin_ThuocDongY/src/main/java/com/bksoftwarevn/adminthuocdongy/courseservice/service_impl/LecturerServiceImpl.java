package com.bksoftwarevn.adminthuocdongy.courseservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lecturer;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.LecturerRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.LecturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LecturerServiceImpl extends BaseServiceImpl<Lecturer, Integer, LecturerRepo> implements LecturerService {

    public LecturerServiceImpl(LecturerRepo repo) {
        super(repo, "Lecturer");
    }

    @Override
    public List<Lecturer> findByCompany(int comId) throws Exception {
        try {
            return repo.findByCompanyId(comId);
        }catch (Exception ex){
            log.error("find Lecturer by company err {0}", ex);
            throw ex;
        }
    }
}
