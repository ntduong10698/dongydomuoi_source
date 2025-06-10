package com.bksoftwarevn.adminthuocdongy.courseservice.entities;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.key.UserCourseKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tai_khoan_has_khoa_hoc")
public class UserHasCourse implements Serializable {

    @EmbeddedId
    private UserCourseKey id;

    private boolean deleted;

    private int level;

    @Column(length = 32)
    private String code;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "khoa_hoc_id")
    private Course course;
}
