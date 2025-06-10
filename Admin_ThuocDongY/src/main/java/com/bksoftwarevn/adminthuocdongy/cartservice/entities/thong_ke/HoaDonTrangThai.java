package com.bksoftwarevn.adminthuocdongy.cartservice.entities.thong_ke;

public class HoaDonTrangThai {

    private String trangThai;

    private long soLuong;

    public HoaDonTrangThai() {
    }
    public HoaDonTrangThai(String trangThai, long soLuong) {
        this.trangThai = trangThai;
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public long getSoLuong() {
        return soLuong;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setSoLuong(long soLuong) {
        this.soLuong = soLuong;
    }
}
