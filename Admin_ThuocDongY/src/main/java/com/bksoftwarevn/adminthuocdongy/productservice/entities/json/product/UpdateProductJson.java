package com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductJson {

    private Product product;

    private List<ProductCost> costs = new ArrayList<>();

    private List<ProductFile> files = new ArrayList<>();

    private List<CateJson> categories = new ArrayList<>();

    private List<ProductHasProperty> properties = new ArrayList<>();
}
