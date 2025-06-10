package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> findAll();

    Optional<Company> findById(int id);

    Optional<Company> save(Company company);

    boolean deleted(int id);

}
