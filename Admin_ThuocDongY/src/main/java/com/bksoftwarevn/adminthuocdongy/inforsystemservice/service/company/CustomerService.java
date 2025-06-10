package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findById(int id) throws Exception;

    Optional<Customer> save(Customer customer) throws Exception;

    Boolean deleted(int id) throws Exception;

    List<Customer> findByCompany(int id) throws Exception;
}
