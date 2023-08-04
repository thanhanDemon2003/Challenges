package com.example.challenges.model;

public class LichThi {
    private int id;
    private String lop;
    private String phong;
    private String ma_mon;
    private String ten_mon;
    private String ca_thi;
    private String ngay;
    private String dot_thi;
    private String gv1;

    public LichThi(int id, String lop, String phong, String ma_mon, String ten_mon, String ca_thi, String ngay, String dot_thi, String gv1) {
        this.id = id;
        this.lop = lop;
        this.phong = phong;
        this.ma_mon = ma_mon;
        this.ten_mon = ten_mon;
        this.ca_thi = ca_thi;
        this.ngay = ngay;
        this.dot_thi = dot_thi;
        this.gv1 = gv1;
    }

    public LichThi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
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

    public String getCa_thi() {
        return ca_thi;
    }

    public void setCa_thi(String ca_thi) {
        this.ca_thi = ca_thi;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getDot_thi() {
        return dot_thi;
    }

    public void setDot_thi(String dot_thi) {
        this.dot_thi = dot_thi;
    }

    public String getGv1() {
        return gv1;
    }

    public void setGv1(String gv1) {
        this.gv1 = gv1;
    }
}
