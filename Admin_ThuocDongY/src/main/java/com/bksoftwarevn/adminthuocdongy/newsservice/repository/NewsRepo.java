package com.bksoftwarevn.adminthuocdongy.newsservice.repository;

import com.bksoftwarevn.adminthuocdongy.newsservice.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepo extends BaseRepo<News, Integer>, JpaSpecificationExecutor<News> {

    @Query("from News x where x.id = ?1 and x.deleted = false")
    Optional<News> findDataById(int id);

    @Query("from News x where (x.category.id = ?1 or (?1 = 0 and x.category.companyId = ?2)) " +
            " and (x.type = ?3 or ?3 = 0)" +
            " and (x.name like concat('%',?4,'%') )" +
            " and (x.tag like concat('%',?5,'%') )" +
            " and x.deleted = false order by x.id desc ")
    Page<News> filter(int cateId, int comId, int type, String name, String tag, Pageable pageable);

    @Query("from News x where x.category.companyId = ?1 and x.category.code is null and x.deleted = false")
    Page<News> filter2(int comId, Pageable pageable);

    @Query("from News x where (x.category.id = ?1 or (?1 = 0 and x.category.companyId = ?2)) " +
            " and (x.type = 2) and x.highLight = true" +
            " and (x.name like concat('%',?3,'%') )" +
            " and (x.tag like concat('%',?4,'%') )" +
            " and x.deleted = false order by x.id desc ")
    Page<News> filterEvent(int cateId, int comId, String name, String tag, Pageable pageable);

    @Query("from News x where x.tag like concat('%',?1,'%') and x.deleted = false")
    Page<News> findSimilar(String tag, Pageable pageable);

    @Query("from News x where x.category.companyId = ?1 and x.deleted = false ")
    List<News> findByCompany(int comId);

    @Query("select count (x) from News x where x.deleted = false and x.category.id = ?1")
    int countByCate(int cateId);

    @Modifying
    @Transactional
    @Query("update News x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);

    @Modifying
    @Transactional
    @Query("update News x set x.view  = (x.view + 1) where x.id = ?1")
    int increaseView(int id);
}
