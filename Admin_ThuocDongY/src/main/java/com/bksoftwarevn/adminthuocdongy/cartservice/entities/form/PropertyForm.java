package com.bksoftwarevn.adminthuocdongy.cartservice.entities.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyForm implements Serializable {
    private String property;
    private String value;
}
