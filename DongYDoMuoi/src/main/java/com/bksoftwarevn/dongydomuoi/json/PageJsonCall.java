package com.bksoftwarevn.dongydomuoi.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageJsonCall<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private boolean last;
}
