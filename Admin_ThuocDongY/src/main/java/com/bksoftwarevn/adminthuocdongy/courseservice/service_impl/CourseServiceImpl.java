package com.bksoftwarevn.adminthuocdongy.courseservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Course;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.UserHasCourse;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.key.UserCourseKey;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.CourseRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.UserHasCourseRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, Integer, CourseRepo> implements CourseService {

    @Autowired
    private UserHasCourseRepo userHasCourseRepo;

    public CourseServiceImpl(CourseRepo repo) {
        super(repo, "Course");
    }

    @Override
    public List<Course> findByIds(List<Integer> ids) {
        try {
            return repo.findDataByIds(ids);
        } catch (Exception ex) {
            log.error("find Courses by id err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Course> findByLecturer(int id) {
        try {
            return repo.findByLecturer(id);
        } catch (Exception ex) {
            log.error("find Course by lecturer err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Set<Course> findByUser(int userId) {
        try {
            return repo.findByUser(userId);
        } catch (Exception ex) {
            log.error("find Courses by user err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Integer> findIdsByUser(int userId) {
        try {
            return repo.findIdsByUser(userId);
        } catch (Exception ex) {
            log.error("find Course id by user err {0}", ex);
            throw ex;
        }
    }

    @Override
    public Page<Integer> findUsersByCourse(int courseId, Pageable pageable) {
        try {
            return userHasCourseRepo.findUsersInCourse(courseId, pageable);
        } catch (Exception ex) {
            log.error("find Course id by user err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean checkUser(int userId, int courseId) {
        try {
            return userHasCourseRepo.checkUser(userId, courseId) > 0;
        } catch (Exception ex) {
            log.error("check Course and user err {0}", ex);
            throw ex;
        }
    }

    @Override
    public UserHasCourse generateCode(int courseId) throws Exception {
        try {
            Optional<Course> course = repo.findDataById(courseId);
            if (course.isPresent()) {
                UserHasCourse userHasCourse = new UserHasCourse();
                userHasCourse.setLevel(0);
                userHasCourse.setCourse(course.get());
                userHasCourse.setDeleted(false);
                userHasCourse.setId(new UserCourseKey(0, courseId));
                userHasCourse.setCode(RandomStringUtils.randomAlphanumeric(32));
                while (userHasCourseRepo.findByCode(userHasCourse.getCode()).isPresent()) {
                    userHasCourse.setCode(RandomStringUtils.randomAlphanumeric(32));
                }
                return userHasCourseRepo.save(userHasCourse);
            } else throw new Exception("not found course");
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public boolean addCourseToUser(int courseId, int userId) {
        try {
            UserHasCourse uhc = new UserHasCourse();
            uhc.setCourse(repo.findDataById(courseId).get());
            uhc.setDeleted(false);
            uhc.setLevel(0);
            uhc.setId(new UserCourseKey(userId, courseId));
            userHasCourseRepo.save(uhc);
            repo.increaseMember(courseId);
            return true;
        } catch (Exception ex) {
            log.error("add Course to user err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean addCourseToUser(String code, int userId) {
        try {
            UserHasCourse uhc = userHasCourseRepo.findByCode(code).get();
            if (uhc.getId().getUserId() == 0) {
                userHasCourseRepo.deleteById(uhc.getId());
                uhc.setId(new UserCourseKey(userId, uhc.getId().getCourseId()));
                userHasCourseRepo.save(uhc);
                repo.increaseMember(uhc.getCourse().getId());
                return true;
            }else return false;

        } catch (Exception ex) {
            log.error("add Course to user err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean removeCoursefromUser(int courseId, int userId) {
        try {
            userHasCourseRepo.delete(new UserCourseKey(userId, courseId));
            repo.decreaseMember(courseId);
            return true;
        } catch (Exception ex) {
            log.error("remove Course from user err {0}", ex);
            throw ex;
        }
    }
}
