package com.bksoftwarevn.adminthuocdongy.productservice.entities.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageJson<T> {

    public PageJson(Page<T> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.last = page.isLast();
    }

    private List<T> content;
    private int totalPages;
    private long totalElements;
    private boolean last;
}
