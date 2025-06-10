package com.bksoftwarevn.adminthuocdongy.courseservice.entities;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.key.UserLessonKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tai_khoan_has_bai_hoc")
public class UserHasLesson {

    @EmbeddedId
    private UserLessonKey id;

    private int view;

    @ManyToOne
    @MapsId("lessonId")
    @JoinColumn(name = "bai_hoc_id")
    private Lesson lesson;
}
