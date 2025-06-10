package com.bksoftwarevn.adminthuocdongy.courseservice.entities.json;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChapterUser {

    private String chapter;

    private List<UserLesson> lessons;
}
