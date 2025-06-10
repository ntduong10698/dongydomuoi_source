package com.bksoftwarevn.adminthuocdongy.courseservice.service;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Course;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.UserHasCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface CourseService extends BaseService<Course>{

    List<Course> findByIds(List<Integer> ids);

    List<Course> findByLecturer(int id);

    Set<Course> findByUser(int userId);

    List<Integer> findIdsByUser(int userId);

    Page<Integer> findUsersByCourse(int courseId, Pageable pageable);

    boolean checkUser(int userId, int courseId);

    UserHasCourse generateCode(int courseId) throws Exception;

    boolean addCourseToUser(int courseId, int userId);

    boolean addCourseToUser(String code, int userId);

    boolean removeCoursefromUser(int courseId, int userId);
}
