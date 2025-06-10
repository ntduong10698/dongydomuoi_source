package com.bksoftwarevn.adminthuocdongy.courseservice.repository;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.UserHasCourse;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.key.UserCourseKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserHasCourseRepo extends JpaRepository<UserHasCourse, UserCourseKey> {

    @Query("from UserHasCourse uhc where uhc.id.userId = ?1 and uhc.deleted = false and uhc.course.deleted = false ")
    List<UserHasCourse> findByUser(int userId);

    @Query("select uhc.id.userId from UserHasCourse uhc where uhc.id.courseId = ?1 and uhc.deleted = false order by uhc.id.userId desc")
    Page<Integer> findUsersInCourse(int courseID, Pageable pageable);

    @Query("from UserHasCourse  uhc where uhc.code = ?1 and uhc.deleted = false ")
    Optional<UserHasCourse> findByCode(String code);

    @Query("select count (uhc) from UserHasCourse uhc where uhc.id.userId = ?1 and uhc.id.courseId = ?2 and uhc.deleted = false ")
    int checkUser(int userId, int courseId);

    @Modifying
    @Transactional
    @Query("update UserHasCourse uhc set uhc.deleted = true where uhc.id = ?1")
    int delete(UserCourseKey id);
}
