package com.bksoftwarevn.adminthuocdongy.productservice.entities.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceView {

    private int id;

    private String name;

    private String alias;

    private double cost;

    private String image;

    private String preview;
}
