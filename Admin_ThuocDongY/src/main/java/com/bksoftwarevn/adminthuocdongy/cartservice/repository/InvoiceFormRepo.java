package com.bksoftwarevn.adminthuocdongy.cartservice.repository;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.InvoiceTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface InvoiceFormRepo extends BaseRepo<InvoiceTemplate,Integer> {

    @Query(value = "from InvoiceTemplate i  where i.deleted = false and i.id = ?1")
    Optional<InvoiceTemplate> findDataById(int id);

    @Query(value = "from InvoiceTemplate i where i.deleted = false order by i.form asc ")
    Page<InvoiceTemplate> findAllAsc(Pageable pageable);

    @Query(value = "from InvoiceTemplate i where i.deleted = false order by i.form desc ")
    Page<InvoiceTemplate> findAllDsc(Pageable pageable);

    @Query(value = "from InvoiceTemplate i where i.deleted = false and " +
            "i.form like concat('%',?1,'%') " +
            "order by i.form asc ")
    Page<InvoiceTemplate> filterAsc(String text, Pageable pageable);

    @Query(value = "from InvoiceTemplate i where i.deleted = false and " +
            "i.form like concat('%',?1,'%') " +
            "order by i.form desc ")
    Page<InvoiceTemplate> filterDesc(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update InvoiceTemplate i set i.deleted = true where i.id = ?1")
    int deleteDataById(int id);
}
