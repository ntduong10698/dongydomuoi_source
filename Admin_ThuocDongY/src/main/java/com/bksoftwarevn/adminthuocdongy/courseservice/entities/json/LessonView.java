package com.bksoftwarevn.adminthuocdongy.courseservice.entities.json;

import com.bksoftwarevn.adminthuocdongy.courseservice.entities.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonView {
    private int id;
    private String lesson;
    private String url;
    private boolean free;
    private String videoTime;

    public LessonView(Lesson lesson){
        this.id = lesson.getId();
        this.lesson = lesson.getName();
        this.videoTime = lesson.getVideoTime();
        this.free = lesson.isFree();
        if (this.free)
            this.url = lesson.getUrl();
        else this.url = null;
    }
}
