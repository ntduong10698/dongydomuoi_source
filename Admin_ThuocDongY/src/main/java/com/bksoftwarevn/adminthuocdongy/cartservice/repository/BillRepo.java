package com.bksoftwarevn.adminthuocdongy.cartservice.repository;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Bill;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.thong_ke.HoaDonTrangThai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepo extends BaseRepo<Bill,Integer> {

    @Query(value = "from Bill b where b.deleted = false and b.id = ?1")
    Optional<Bill> findDataById(int id);

    @Query("from Bill x where x.code = ?1 and x.deleted = false ")
    Optional<Bill> findByCode(String code);

    @Query("from Bill b where b.deleted = false and b.customerId = ?1 order by b.id desc , b.createdTime desc")
    Page<Bill> findByCustomerId(int cusId, Pageable pageable);

    @Query(value = "from Bill b where b.deleted = false order by b.id , b.createdTime desc ")
    Page<Bill> findAll(Pageable pageable);

    @Query("select count(b) from Bill b where b.sourceOrder.companyId = ?1 and b.deleted = false and b.checked = false ")
    int countUnChecked(int comId);

    @Query("select b from Bill b where  " +
            "b.deleted = false " +
            "and (?1 is null or b.createdTime >= ?1 ) and (?2 is null or b.createdTime <= ?2) " +
            "and ( ?3 = 0 or b.customerId = ?3 ) " +
            "and (?4 = 0 or b.status.id = ?4) " +
            "and (?5 = 0 or b.sourceOrder.id = ?5) " +
            "and (b.code like concat('%',?6,'%') )" +
            "and (b.customerName like concat('%',?7,'%') or b.customerEmail like concat('%',?7,'%') or b.customerPhone like concat('%',?7,'%')) " +
            " order by b.createdTime desc")
    Page<Bill> filterDesc(Date startDate , Date endDate , int cusId , int statusId, int sourceId, String code, String text, Pageable pageable);

    @Query("select b from Bill b where  " +
            "b.deleted = false " +
            "and (?1 is null or b.createdTime >= ?1 ) and (?2 is null or b.createdTime <= ?2) " +
            "and ( ?3 = 0 or b.customerId = ?3 ) " +
            "and (?4 = 0 or b.status.id = ?4) " +
            "and (?5 = 0 or b.sourceOrder.id = ?5) " +
            "and (b.code like concat('%',?6,'%') )" +
            "and (b.customerName like concat('%',?7,'%') or b.customerEmail like concat('%',?7,'%') or b.customerPhone like concat('%',?7,'%')) " +
            " order by b.createdTime asc")
    Page<Bill> filterAsc(Date startDate , Date endDate , int cusId , int statusId, int sourceId, String code, String text, Pageable pageable);

    @Query("select b from Bill b where  " +
            "b.deleted = false " +
            "and b.sourceOrder.companyId = ?1 " +
            "and (?2 = 0 or b.status.id = ?2) " +
            "and (b.code like concat('%',?3,'%') )" +
            "and (b.customerName like concat('%',?4,'%') or b.customerEmail like concat('%',?4,'%') or b.customerPhone like concat('%',?4,'%')) " +
            " order by b.createdTime desc")
    Page<Bill> filter2Desc(int comId , int statusId, String code, String text, Pageable pageable);

    @Query("select b from Bill b where  " +
            "b.deleted = false " +
            "and b.sourceOrder.companyId = ?1 " +
            "and (?2 = 0 or b.status.id = ?2) " +
            "and (b.code like concat('%',?3,'%') )" +
            "and (b.customerName like concat('%',?4,'%') or b.customerEmail like concat('%',?4,'%') or b.customerPhone like concat('%',?4,'%')) " +
            " order by b.createdTime asc")
    Page<Bill> filter2Asc(int comId , int statusId, String code, String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Bill b set b.deleted = true where b.id = ?1")
    int deleteDataById(int id);

    @Modifying
    @Transactional
    @Query("update Bill b set b.checked = true where b.id = ?1")
    int check(int id);

    @Modifying
    @Transactional
    @Query("update Bill b set b.checked = true where b.id in (?1) ")
    int checkAll(int[] id);

    @Query("select COUNT(b.status) as soLuong from Bill b where  " +
            "b.deleted = false " +
            "and (?1 is null or b.createdTime >= ?1 ) and (?2 is null or b.createdTime <= ?2) " +
            "and b.status.id = 1 ")
    long countBill(Date startDate , Date endDate);

    @Query("select new com.bksoftwarevn.adminthuocdongy.cartservice.entities.thong_ke.HoaDonTrangThai(b.status.nameStatus, count (b.status)) from Bill b where  " +
            "b.deleted = false " +
            "and (?1 is null or b.createdTime >= ?1 ) and (?2 is null or b.createdTime <= ?2) "+
            " GROUP BY b.status.nameStatus")
    List<HoaDonTrangThai> hoaDonTrangThai(Date startDate , Date endDate);

}
