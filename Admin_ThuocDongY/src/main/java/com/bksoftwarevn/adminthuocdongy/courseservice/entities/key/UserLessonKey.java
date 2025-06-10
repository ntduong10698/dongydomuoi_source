package com.bksoftwarevn.adminthuocdongy.courseservice.entities.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLessonKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "bai_hoc_id")
    private int lessonId;
}
