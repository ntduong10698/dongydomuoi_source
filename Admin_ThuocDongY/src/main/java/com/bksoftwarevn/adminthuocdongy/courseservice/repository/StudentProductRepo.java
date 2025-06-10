package com.bksoftwarevn.adminthuocdongy.courseservice.repository;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.StudentProduct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentProductRepo extends BaseRepo<StudentProduct, Integer> {

    @Query("from StudentProduct x where (?1 = 0 or x.course.id = ?1) and (x.course.lecturer.companyId = ?2) " +
            "and ( ?3 is null or x.enableIndex = ?3) " +
            "and ( ?4 is null or x.enableOnline = ?4) " +
            "and ( ?5 is null or x.enableOffline = ?5) " +
            "and x.deleted = false order by x.id desc")
    List<StudentProduct> findByCourse(int courseId, int comId, Boolean enableIndex, Boolean enableOnline, Boolean enableOffline);

    @Modifying
    @Transactional
    @Query("update StudentProduct x set x.deleted = true where x.id= ?1")
    int deleteDataById(int id);
}
