package com.bksoftwarevn.adminthuocdongy.courseservice.repository;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lesson;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepo extends BaseRepo<Lesson, Integer>{

    @Query("from Lesson x where x.id = ?1 and x.deleted = false")
    Optional<Lesson> findDataById(int id);

    @Query("from Lesson x where x.chapter.id = ?1 and x.deleted = false order by x.stt asc ")
    List<Lesson> findByChapterId(int comId);

    @Query("from Lesson x where x.chapter.course.id = ?1 and x.deleted = false order by x.stt asc ")
    List<Lesson> findByCourse(int courseID);

    @Modifying
    @Transactional
    @Query("update Lesson x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
