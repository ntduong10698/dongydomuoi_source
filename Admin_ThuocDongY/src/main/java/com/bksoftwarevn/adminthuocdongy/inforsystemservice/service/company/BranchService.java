package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {

    Optional<Branch> findById(int id);

    Optional<Branch> save(Branch branch);

    Boolean deleted(int id);

    List<Branch> findByCompany(int id);
}
