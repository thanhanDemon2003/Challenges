package com.example.challenges.model;

public class DangKyHocLai {

    private int id;
    private Integer is_active;
    private String trang_thai;
    private String ten_mon;
    private String tien;

    public DangKyHocLai(int id, Integer is_active, String trang_thai, String ten_mon, String tien) {
        this.id = id;
        this.is_active = is_active;
        this.trang_thai = trang_thai;
        this.ten_mon = ten_mon;
        this.tien = tien;
    }

    public String getTen_mon() {
        return ten_mon;
    }

    public void setTen_mon(String ten_mon) {
        this.ten_mon = ten_mon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer isIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }
}
