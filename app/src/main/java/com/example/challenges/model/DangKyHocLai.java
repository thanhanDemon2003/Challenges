package com.example.challenges.model;

public class DangKyHocLai {

    private int id;
    private String trang_thai;
    private String mon_hoc;
    private String tien;

    public DangKyHocLai(int id, String trang_thai, String mon_hoc, String tien) {
        this.id = id;
        this.trang_thai = trang_thai;
        this.mon_hoc = mon_hoc;
        this.tien = tien;
    }

    public DangKyHocLai() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getMon_hoc() {
        return mon_hoc;
    }

    public void setMon_hoc(String mon_hoc) {
        this.mon_hoc = mon_hoc;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }
}
