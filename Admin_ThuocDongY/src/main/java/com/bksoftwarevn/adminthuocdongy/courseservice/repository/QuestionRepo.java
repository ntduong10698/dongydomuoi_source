package com.bksoftwarevn.adminthuocdongy.courseservice.repository;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Question;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QuestionRepo extends BaseRepo<Question, Integer> {

    @Query("from Question x where x.lesson.id = ?1 and x.deleted = false order by x.stt")
    List<Question> findByLesson(int id);

    @Modifying
    @Transactional
    @Query("update Question x set x.deleted = true where x.id= ?1")
    int deleteDataById(int id);
}
