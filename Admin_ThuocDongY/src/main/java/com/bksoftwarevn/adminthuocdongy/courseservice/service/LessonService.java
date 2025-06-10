package com.bksoftwarevn.adminthuocdongy.courseservice.service;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lesson;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.UserHasLesson;

import java.util.List;

public interface LessonService extends BaseService<Lesson> {

    List<Lesson> findByChapter(int chapterId);

    List<Lesson> findByCourse(int courseId);

    boolean checkViewed(int userId, int lesssonId);

    List<UserHasLesson> userLearned(int userId, int courseId);

    void userLearn(int userId, int lessonId);
}
