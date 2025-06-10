package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

    @Query(value = "from Company c where c.deleted = false")
    List<Company> findAll();

    @Query(value = "from Company c where c.id = ?1 and c.deleted = false")
    Optional<Company> findById(int id);

    @Modifying
    @Transactional
    @Query("update Company c set c.deleted = true where c.id = ?1")
    int deleted(int id);


}
