package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Branch;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company.BranchRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepo branchRepo;

    @Override
    public Optional<Branch> findById(int id) {
        try{
            return branchRepo.findById(id);
        }catch (Exception ex){
            log.error("save-branch-err : " + ex);
            throw ex;
        }
    }

    @Override
    public Optional<Branch> save(Branch branch) {
        try{
            return Optional.of(branchRepo.save(branch));
        }catch (Exception ex){
            log.error("save-branch-err : " + ex);
            throw ex;
        }
    }

    @Override
    public Boolean deleted(int id) {
        try{
            return branchRepo.deleted(id) > 0;
        }catch (Exception ex){
            log.error("delete-branch-err : " + ex);
            throw ex;
        }
    }

    @Override
    public List<Branch> findByCompany(int id) {
        try {
            return branchRepo.findByCompany(id);
        }catch (Exception ex){
            throw ex;
        }
    }


}
