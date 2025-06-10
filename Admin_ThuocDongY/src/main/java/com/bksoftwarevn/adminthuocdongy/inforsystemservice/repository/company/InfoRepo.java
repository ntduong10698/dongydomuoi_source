package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface InfoRepo extends JpaRepository<Information,Integer> {

    @Query(value = "from Information c where c.id = ?1 and c.deleted = false ")
    Optional<Information> findById(int id);

    @Query(value = "from Information c where c.company.id = ?1 and c.deleted = false ")
    List<Information> findByCompany(int id);

    @Modifying
    @Transactional
    @Query("update Information c set c.deleted = true where c.id = ?1")
    int deleted(int id);

}
