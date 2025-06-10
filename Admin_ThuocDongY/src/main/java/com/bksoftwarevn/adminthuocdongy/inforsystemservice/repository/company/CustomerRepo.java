package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    
    @Query(value = "from Customer b where b.id = ?1")
    Optional<Customer> findById(int id);

    @Query(value = "from Customer b where b.company.id = ?1 and b.deleted = false order by b.id desc")
    List<Customer> findByCompany(int id);

    @Transactional
    @Modifying
    @Query("update Customer b set b.deleted = true where b.id = ?1")
    int deleted(int id);
}
