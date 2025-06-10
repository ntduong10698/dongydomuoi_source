package com.bksoftwarevn.adminthuocdongy.cartservice.entities.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillForm {

    private int companyId;

    private String noteCSKHl;

    private int customerId;

    private String customerName;

    private String customerPhone;

    private String customerEmail;

    private String customerAddress;

    private String companyName;

    private int status;

    private List<BillDetailForm> billDetailForms;

    private String coupon;
}
