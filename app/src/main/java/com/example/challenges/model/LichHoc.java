package com.example.challenges.model;

public class LichHoc {

    private int id;
    private String giang_duong;
    private String phong;
    private String ma_mon;
    private String ten_mon;
    private String ca_hoc;
    private String ngay;
    private String ten_giang_vien;

    public LichHoc(int id, String giang_duong, String phong, String ma_mon, String ten_mon, String ca_hoc, String ngay, String ten_giang_vien) {
        this.id = id;
        this.giang_duong = giang_duong;
        this.phong = phong;
        this.ma_mon = ma_mon;
        this.ten_mon = ten_mon;
        this.ca_hoc = ca_hoc;
        this.ngay = ngay;
        this.ten_giang_vien = ten_giang_vien;
    }

    public LichHoc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGiang_duong() {
        return giang_duong;
    }

    public void setGiang_duong(String giang_duong) {
        this.giang_duong = giang_duong;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getMa_mon() {
        return ma_mon;
    }

    public void setMa_mon(String ma_mon) {
        this.ma_mon = ma_mon;
    }

    public String getTen_mon() {
        return ten_mon;
    }

    public void setTen_mon(String ten_mon) {
        this.ten_mon = ten_mon;
    }

    public String getCa_hoc() {
        return ca_hoc;
    }

    public void setCa_hoc(String ca_hoc) {
        this.ca_hoc = ca_hoc;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTen_giang_vien() {
        return ten_giang_vien;
    }

    public void setTen_giang_vien(String ten_giang_vien) {
        this.ten_giang_vien = ten_giang_vien;
    }

}
