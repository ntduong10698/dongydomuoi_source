package com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CateJson {
    private int cateId;
    private int productTypeId;
    private boolean deleted;
    private boolean newCate;
}
