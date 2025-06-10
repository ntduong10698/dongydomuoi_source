package com.bksoftwarevn.adminthuocdongy.courseservice.repository;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CourseRepo extends BaseRepo<Course, Integer> {

    @Query("select distinct uc.course from UserHasCourse uc where uc.id.userId = ?1  and uc.deleted = false")
    Set<Course> findByUser(int userId);

    @Query("select uc.course.id from UserHasCourse uc where uc.id.userId = ?1  and uc.deleted = false")
    List<Integer> findIdsByUser(int userId);

    @Query("from Course x where x.id = ?1 and x.deleted = false")
    Optional<Course> findDataById(int id);

    @Query("from Course x where x.id in (?1) and x.deleted = false")
    List<Course> findDataByIds(List<Integer> ids);

    @Query("from Course x where x.lecturer.id = ?1 and x.deleted = false order by x.id desc ")
    List<Course> findByLecturer(int lecturerId);

    @Modifying
    @Transactional
    @Query("update Course x set x.member = x.member + 1 where x.id = ?1")
    int increaseMember(int courseId);

    @Modifying
    @Transactional
    @Query("update Course x set x.member = x.member - 1 where x.id = ?1")
    int decreaseMember(int courseId);

    @Modifying
    @Transactional
    @Query("update Course x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
