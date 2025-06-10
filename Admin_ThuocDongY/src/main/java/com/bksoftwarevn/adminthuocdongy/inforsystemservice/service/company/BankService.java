package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    
    Optional<Bank> findById(int id) throws Exception;

    Optional<Bank> save(Bank bank) throws Exception;

    List<Bank> saveAll(Iterable<Bank> banks);

    Boolean deleted(int id) throws Exception;

    List<Bank> findByCompany(int id) throws Exception;
}
