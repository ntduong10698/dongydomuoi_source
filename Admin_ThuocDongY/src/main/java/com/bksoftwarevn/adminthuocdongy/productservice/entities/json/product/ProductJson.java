package com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductJson {

    private Product product;

    private List<ProductCost> costs = new ArrayList<>();

    private List<ProductFile> files = new ArrayList<>();

    private List<Category> categories = new ArrayList<>();

    private List<ProductHasProperty> properties = new ArrayList<>();

    private ProductStatistic statistic;

    private Object promotion;
}
