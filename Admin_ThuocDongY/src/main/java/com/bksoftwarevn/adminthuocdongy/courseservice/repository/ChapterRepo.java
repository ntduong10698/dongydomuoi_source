package com.bksoftwarevn.adminthuocdongy.courseservice.repository;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Chapter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepo extends BaseRepo<Chapter, Integer> {
    
    @Query("from Chapter x where x.id = ?1 and x.deleted = false")
    Optional<Chapter> findDataById(int id);

    @Query("from Chapter x where x.course.id = ?1 and x.deleted = false order by x.stt")
    List<Chapter> findByCourse(int Id);

    @Modifying
    @Transactional
    @Query("update Chapter x set x.deleted = true where x.id = ?1")
    int deleteDataById(int id);
}
