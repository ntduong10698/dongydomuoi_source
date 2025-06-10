package com.bksoftwarevn.adminthuocdongy.courseservice.entities.json;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lesson;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLesson {
    private int view;
    private Lesson lesson;
}
