package com.bksoftwarevn.adminthuocdongy.courseservice.entities.json;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Chapter;
import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lesson;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChapterAdmin {

    private Chapter chapter;

    private List<Lesson> lessons;
}
