package com.bksoftwarevn.adminthuocdongy.inforsystemservice.entities.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlAliasPayLoad {

    private String alias;

    private int danhMucAliasId;

    private String newAlias;

}
