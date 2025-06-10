package com.bksoftwarevn.adminthuocdongy.courseservice.service_impl;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lesson;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.UserHasLesson;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.key.UserLessonKey;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.ChapterRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.LessonRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.repository.UserLessonRepo;
import com.bksoftwarevn.adminthuocdongy.courseservice.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LessonServiceImpl extends BaseServiceImpl<Lesson, Integer, LessonRepo> implements LessonService {

    public LessonServiceImpl(LessonRepo repo) {
        super(repo, "Lesson");
    }

    @Autowired
    private ChapterRepo chapterRepo;

    @Autowired
    private UserLessonRepo userLessonRepo;

    @Override
    public List<Lesson> findByChapter(int chapterId) {
        try {
            return repo.findByChapterId(chapterId);
        } catch (Exception ex) {
            log.error("find Lesson by chapter err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<Lesson> findByCourse(int courseId) {
        try {
            return repo.findByCourse(courseId);
        } catch (Exception ex) {
            log.error("find Lesson by course err {0}", ex);
            throw ex;
        }
    }

    @Override
    public boolean checkViewed(int userId, int lesssonId) {
        try {
            return userLessonRepo.checkViewByUser(userId, lesssonId).map(
                    uhl -> {
                        int maxView = uhl.getLesson().getChapter().getCourse().getMaxView();
                        if (maxView == 0)
                            return true;
                        else return uhl.getView() < maxView;
                    }
            ).orElse(true);
        } catch (Exception ex) {
            log.error("check view err {0}", ex);
            throw ex;
        }
    }

    @Override
    public List<UserHasLesson> userLearned(int userId, int courseID) {
        try {
            return userLessonRepo.findByUser(userId, courseID);
        } catch (Exception ex) {
            log.error("find Lesson by user err {0}", ex);
            throw ex;
        }
    }

    @Override
    public void userLearn(int userId, int lessonId) {
        try {
            UserLessonKey id = new UserLessonKey(userId, lessonId);
            if (userLessonRepo.existsById(id))
                userLessonRepo.view(id);
            else {
                UserHasLesson uhl = new UserHasLesson();
                uhl.setId(id);
                uhl.setLesson(repo.findDataById(lessonId).orElse(null));
                uhl.setView(1);
                userLessonRepo.save(uhl);
            }
        } catch (Exception ex) {
            log.error("user learn lesson err {0}", ex);
            throw ex;
        }
    }
}
