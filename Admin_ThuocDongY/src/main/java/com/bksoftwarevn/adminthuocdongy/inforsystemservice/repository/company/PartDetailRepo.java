package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.PartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PartDetailRepo extends JpaRepository<PartDetail, Integer> {

    @Query(value = "from PartDetail p where p.id = ?1")
    Optional<PartDetail> findById(int id);

    @Transactional
    @Modifying
    @Query(value = "update PartDetail p set p.deleted=true where p.id = ?1")
    int deleted(int id);

    @Query(value = "from PartDetail p where p.part.id = ?1 and p.deleted = false order by p.stt")
    List<PartDetail> findByPartId(int id);
}
