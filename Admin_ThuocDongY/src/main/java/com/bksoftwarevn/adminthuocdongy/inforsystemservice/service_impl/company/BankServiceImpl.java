package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Bank;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company.BankRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepo bankRepo;


    @Override
    public Optional<Bank> findById(int id) throws Exception {
        return bankRepo.findById(id);
    }

    @Override
    public Optional<Bank> save(Bank bank) throws Exception {
        return Optional.of(bankRepo.save(bank));
    }

    @Override
    public List<Bank> saveAll(Iterable<Bank> banks) {
        return bankRepo.saveAll(banks);
    }

    @Override
    public Boolean deleted(int id) throws Exception {
        return bankRepo.deleted(id) > 0;
    }

    @Override
    public List<Bank> findByCompany(int id) throws Exception {
        return bankRepo.findByCompany(id);
    }
}
