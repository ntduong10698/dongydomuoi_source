package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service_impl.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Contact;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company.ContactRepo;
import com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo repo;

    @Override
    public Optional<Contact> findById(int id) {
        try {
            return repo.findById(id);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public Page<Contact> findByCom(int comId, Boolean checked, Pageable pageable) {
        try {
            return repo.findByCompanyId(comId, checked, pageable);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public Contact save(Contact contact) {
        try {
            return repo.save(contact);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public int countUnchecked(int comId) {
        try {
            return repo.countUnchecked(comId);
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return repo.deleted(id) > 0;
        }catch (Exception ex){
            throw ex;
        }
    }
}
