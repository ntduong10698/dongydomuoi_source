package com.bksoftwarevn.adminthuocdongy.cartservice.repository;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.BillDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BillDetailRepo extends BaseRepo<BillDetail, Integer> {

    @Query(value = "from BillDetail bd where bd.deleted = false and bd.bill.id = ?1")
    List<BillDetail> findByBillId(int id);

    @Modifying
    @Transactional
    @Query(value = "update BillDetail bd set bd.deleted = true where bd.id = ?1")
    int deleteDataById(int id);

//    @Query("select new com.bksoftwarevn.adminthuocdongy.cartservice.entities.thong_ke.SanPhamBanChay(b.productName, sum (b.amount))" +
//            " from BillDetail b where  " +
//            "b.deleted = false " +
//            "and (?1 is null or b.createdTime >= ?1 ) and (?2 is null or b.createdTime <= ?2) "+
//            " GROUP BY b.productName " +
//            "order by b.amount "
//            )
//    List<SanPhamBanChay> sanPhamBanChay(Date startDate , Date endDate);
//ORDER BY seatNumber LIMIT 1;
//4. JPA Setup
}
