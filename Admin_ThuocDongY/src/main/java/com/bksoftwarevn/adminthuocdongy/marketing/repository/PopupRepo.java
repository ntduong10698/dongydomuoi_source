package com.bksoftwarevn.adminthuocdongy.marketing.repository;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Popup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PopupRepo extends BaseRepo<Popup, Integer>{

    @Query("from Popup x where x.id = ?1 and x.deleted = false")
    Optional<Popup> findDataById(int id);

    @Query("from Popup x where x.companyId = ?1 and x.deleted = false")
    List<Popup> findByCom(int comId);

    @Query("from Popup x where x.companyId = ?1 and x.active = true and x.deleted = false")
    Optional<Popup> findShowed(int comId);

    @Modifying
    @Transactional
    @Query("update Popup x set x.active = false where x.companyId = ?1")
    int setAllHide(int comId);

    @Modifying
    @Transactional
    @Query("update Popup x set x.active= true where x.id = ?1")
    int setShow(int id);

    @Modifying
    @Transactional
    @Query("update Popup x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
