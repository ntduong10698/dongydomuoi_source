package com.bksoftwarevn.adminthuocdongy.courseservice.entities.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterView {

    private String chapter;

    private List<LessonView> lessons;
}
