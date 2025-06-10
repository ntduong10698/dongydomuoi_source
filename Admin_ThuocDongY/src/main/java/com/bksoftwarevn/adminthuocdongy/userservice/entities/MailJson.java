package com.bksoftwarevn.adminthuocdongy.userservice.entities;

import lombok.Data;

@Data
public class MailJson {
    private String header;
    private String content;

    public MailJson(String header, String content) {
        this.header = header;
        this.content = content;
    }
}
