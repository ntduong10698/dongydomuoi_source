package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ContactRepo extends JpaRepository<Contact, Integer> {

    @Query("from Contact x where x.companyId = ?1 and ( x.checked = ?2 or ?2 is null ) and x.deleted = false order by x.id desc")
    Page<Contact> findByCompanyId(int companyId, Boolean checked, Pageable pageable);

    @Query("select count (c) from Contact c where c.companyId = ?1 and c.checked = false and c.deleted = false")
    int countUnchecked(int comId);

    @Modifying
    @Transactional
    @Query("update Contact c set c.deleted = true where c.id = ?1")
    int deleted(int id);
}
