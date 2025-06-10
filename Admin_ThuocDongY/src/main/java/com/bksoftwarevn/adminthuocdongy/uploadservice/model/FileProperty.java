package com.bksoftwarevn.adminthuocdongy.uploadservice.model;

import lombok.Data;

@Data
public class FileProperty {
    private String name;
    private String uri;
    private long size;
    private String type;
    private long duration;
}
