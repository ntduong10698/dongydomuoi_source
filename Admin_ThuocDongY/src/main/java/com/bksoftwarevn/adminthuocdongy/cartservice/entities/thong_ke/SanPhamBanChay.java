package com.bksoftwarevn.adminthuocdongy.cartservice.entities.thong_ke;

public class SanPhamBanChay {

    String productName;

    double amount;

    public SanPhamBanChay() {
    }

    public SanPhamBanChay(String productName, double amount) {
        this.productName = productName;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public double getAmount() {
        return amount;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
