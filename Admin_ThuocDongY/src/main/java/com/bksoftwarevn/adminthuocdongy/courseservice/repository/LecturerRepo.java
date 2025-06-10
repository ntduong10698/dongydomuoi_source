package com.bksoftwarevn.adminthuocdongy.courseservice.repository;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lecturer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepo extends BaseRepo<Lecturer, Integer>{

    @Query("from Lecturer x where x.id = ?1 and x.deleted = false")
    Optional<Lecturer> findDataById(int id);

    @Query("from Lecturer x where x.companyId = ?1 and x.deleted = false order by x.id desc ")
    List<Lecturer> findByCompanyId(int comId);

    @Modifying
    @Transactional
    @Query("update Lecturer x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
