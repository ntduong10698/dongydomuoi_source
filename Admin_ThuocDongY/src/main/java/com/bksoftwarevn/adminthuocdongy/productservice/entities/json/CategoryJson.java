package com.bksoftwarevn.adminthuocdongy.productservice.entities.json;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryJson implements Serializable {

    public CategoryJson(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.icon = category.getIcon();
        this.alias = category.getAlias();
        this.smallest = category.isSmallest();
        this.childs = new ArrayList<>();
    }

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String icon;

    private String alias;

    private boolean smallest;

    private String level;

    private List<CategoryJson> childs;
}
