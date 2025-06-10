package com.bksoftwarevn.adminthuocdongy.inforsystemservice.repository.company;

import com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.company.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PartRepo extends JpaRepository<Part, Integer> {

    @Query(value = "from Part p where p.id = ?1")
    Optional<Part> findById(int id);

    @Transactional
    @Modifying
    @Query(value = "update Part p set p.video = ?2 where p.id = ?1")
    int setVideo(int id, boolean video);

    @Transactional
    @Modifying
    @Query(value = "update Part p set p.deleted = true where p.id = ?1")
    int delete(int id);

    @Query(value = "from Part p where p.company.id = ?1 and p.code like concat('%',?2,'%') and p.deleted = false order by p.stt")
    List<Part> findByCompany(int id, String code);
}
