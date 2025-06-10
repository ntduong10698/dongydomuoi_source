package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer> {

    @Query(value = "from Branch b where b.id = ?1 and b.deleted = false ")
    Optional<Branch> findById(int id);

    @Query(value = "from Branch b where b.company.id = ?1 and b.deleted = false")
    List<Branch> findByCompany(int id);

    @Transactional
    @Modifying
    @Query("update Branch b set b.deleted = true where b.id = ?1")
    int deleted(int id);


}
