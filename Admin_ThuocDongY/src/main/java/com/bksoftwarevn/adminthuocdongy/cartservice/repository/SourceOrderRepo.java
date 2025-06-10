package com.bksoftwarevn.adminthuocdongy.cartservice.repository;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.SourceOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface SourceOrderRepo extends  BaseRepo<SourceOrder,Integer> {

    @Query(value = "from SourceOrder s where s.deleted = false and s.id = ?1")
    Optional<SourceOrder> findDataById(int id);

    @Query("from SourceOrder x where x.companyId = ?1 and x.defaultValue = true")
    Optional<SourceOrder> findDefault(int comId);

    @Query(value = "from SourceOrder s where s.deleted = false order by s.nameSourceOrder")
    Page<SourceOrder> findAllAsc(Pageable pageable);

    @Query(value = "from SourceOrder s where s.deleted = false order by s.nameSourceOrder desc")
    Page<SourceOrder> findAllDesc(Pageable pageable);

    @Query(value = "from SourceOrder s where s.deleted = false and " +
            "s.nameSourceOrder like concat('%', ?1, '%') " +
            "order by s.nameSourceOrder asc ")
    Page<SourceOrder> filterAsc(String text, Pageable pageable);

    @Query(value = "from SourceOrder s where s.deleted = false and " +
            "s.nameSourceOrder like concat('%', ?1, '%') " +
            "order by s.nameSourceOrder desc ")
    Page<SourceOrder> filterDesc(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update SourceOrder s set s.deleted = true where s.id = ?1")
    int deleteDataById(int id);
}
