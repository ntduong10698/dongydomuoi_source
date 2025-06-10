package com.bksoftwarevn.adminthuocdongy.courseservice.repository;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.UserHasLesson;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.key.UserLessonKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserLessonRepo extends JpaRepository<UserHasLesson, UserLessonKey> {

    @Modifying
    @Transactional
    @Query("update UserHasLesson uhl set uhl.view = uhl.view + 1 where uhl.id = ?1")
    void view(UserLessonKey id);

    @Query("from UserHasLesson x where x.id.userId = ?1 and x.id.lessonId = ?2")
    Optional<UserHasLesson> checkViewByUser(int userId, int lessonId);

    @Query("select x from UserHasLesson x  where x.id.userId = ?1 and x.lesson.chapter.course.id = ?2")
    List<UserHasLesson> findByUser(int userId, int courseId);
}
