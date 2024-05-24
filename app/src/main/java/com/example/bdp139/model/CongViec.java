package com.example.bdp139.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int ma;
    private String ten, noidung, ngayHoanThanh;
    private int tinhtrang;
    private boolean isCongTac;

    public CongViec(int ma, String ten, String noidung, String ngayHoanThanh, int tinhtrang, boolean isCongTac) {
        this.ma = ma;
        this.ten = ten;
        this.noidung = noidung;
        this.ngayHoanThanh = ngayHoanThanh;
        this.tinhtrang = tinhtrang;
        this.isCongTac = isCongTac;
    }

    public CongViec(String ten, String noidung, String ngayHoanThanh, int tinhtrang, boolean isCongTac) {
        this.ten = ten;
        this.noidung = noidung;
        this.ngayHoanThanh = ngayHoanThanh;
        this.tinhtrang = tinhtrang;
        this.isCongTac = isCongTac;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(String ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public boolean isCongTac() {
        return isCongTac;
    }

    public void setCongTac(boolean congTac) {
        isCongTac = congTac;
    }
}
