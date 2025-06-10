package com.bksoftwarevn.adminthuocdongy.marketing.entities.json;

import com.bksoftwarevn.adminthuocdongy.marketing.entities.Combo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ComboView {
    private Combo combo;
    private List<Integer> products;
}
