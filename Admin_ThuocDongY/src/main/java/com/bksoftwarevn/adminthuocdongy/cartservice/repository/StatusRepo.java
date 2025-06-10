package com.bksoftwarevn.adminthuocdongy.cartservice.repository;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepo extends BaseRepo<Status,Integer> {

    @Query(value = "from Status s where s.deleted = false and s.id = ?1")
    Optional<Status> findDataById(int id);

    @Query(value = "from Status s where s.deleted = false")
    List<Status> findAll();

    @Modifying
    @Transactional
    @Query("update Status s set s.deleted = true where s.id = ?1")
    int deleteDataById(int id);

}
