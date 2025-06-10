package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailJson {
    private String header;

    private String content;
}
