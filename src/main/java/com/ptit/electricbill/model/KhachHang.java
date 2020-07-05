package com.ptit.electricbill.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private String ngaySinh;
    private String soCMND;
    private String diaChi;
    private String gioiTinh;
    private String soDienThoai;
    private String ngayBDSD;
    private String mailAddress;
    private String mucDichSD;
    private String trangThai;


    public KhachHang(String maKhachHang, String tenKhachHang, String ngaySinh, String soCMND, String diaChi, String gioiTinh, String soDienThoai, String ngayBDSD, String mailAddress, String mucDichSD) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.ngaySinh = ngaySinh;
        this.soCMND = soCMND;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.ngayBDSD = ngayBDSD;
        this.mailAddress = mailAddress;
        this.mucDichSD = mucDichSD;
    }
}
