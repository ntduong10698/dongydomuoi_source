package com.bksoftwarevn.adminthuocdongy.marketing.entities.json;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Promotion;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PromoView {
    private Promotion promotion;
    private List<Integer> products;
}
