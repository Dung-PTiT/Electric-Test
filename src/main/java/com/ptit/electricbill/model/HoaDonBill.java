package com.ptit.electricbill.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonBill {
    private String stt;
    private String maHD;
    private String maKH;
    private String maThang;
    private String tenKH;
    private String diaChi;
    private String soDienHienTai;
    private String soThangTruoc;
    private String soKwh;
    private String loaiDien;
    private String thue;
    private String tien;
    private String ngayTao;

    public HoaDonBill(String maHD, String maKH, String maThang, String tenKH, String diaChi, String soDienHienTai, String soThangTruoc, String soKwh, String thue, String tien, String ngayTao) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maThang = maThang;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.soDienHienTai = soDienHienTai;
        this.soThangTruoc = soThangTruoc;
        this.soKwh = soKwh;
        this.thue = thue;
        this.tien = tien;
        this.ngayTao = ngayTao;
    }
}
