package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepo extends JpaRepository<Bank,Integer> {

    @Query(value = "from Bank b where b.id = ?1")
    Optional<Bank> findById(int id);

    @Query(value = "from Bank b where b.company.id = ?1 and b.deleted = false")
    List<Bank> findByCompany(int id);

    @Transactional
    @Modifying
    @Query("update Bank b set b.deleted = true where b.id = ?1")
    int deleted(int id);
}
