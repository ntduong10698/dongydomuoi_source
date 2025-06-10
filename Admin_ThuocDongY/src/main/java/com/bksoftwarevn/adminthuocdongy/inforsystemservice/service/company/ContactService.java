package com.bksoftwarevn.adminthuocdongy.inforsystemservice.service.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContactService {

    Optional<Contact> findById(int id);

    Page<Contact> findByCom(int comId, Boolean checked, Pageable pageable);

    Contact save(Contact contact);

    int countUnchecked(int comId);

    boolean delete(int id);
}
