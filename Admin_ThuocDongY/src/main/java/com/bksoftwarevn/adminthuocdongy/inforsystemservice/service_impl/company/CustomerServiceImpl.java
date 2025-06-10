package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Customer;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company.CustomerRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Optional<Customer> findById(int id) throws Exception {
        return customerRepo.findById(id);
    }

    @Override
    public Optional<Customer> save(Customer customer) throws Exception {
        return Optional.of(customerRepo.save(customer));
    }

    @Override
    public Boolean deleted(int id) throws Exception {
        return customerRepo.deleted(id) > 0;
    }

    @Override
    public List<Customer> findByCompany(int id) throws Exception {
        return customerRepo.findByCompany(id);
    }
}
