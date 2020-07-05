package com.ptit.electricbill.dao;

import com.ptit.electricbill.model.DonGia;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsDAOTest {
    @Autowired
    private UtilsDAO utilsDAO;

    @Test
    public void kiemTraDonGiaTonTaiTest_DaTonTai() {
        DonGia donGia = new DonGia();
        donGia.setGia(1678);
        donGia.setGhiChu("Sinh hoạt");
        boolean checkResult = utilsDAO.kiemTraDonGiaTonTai(donGia);
        Assert.assertEquals(checkResult, false);
    }

    @Test
    public void kiemTraDonGiaTonTaiTest_ChuaTonTai() {
        DonGia donGia = new DonGia();
        donGia.setGia(9999);
        donGia.setGhiChu("Sinh hoạt");
        boolean checkResult = utilsDAO.kiemTraDonGiaTonTai(donGia);
        Assert.assertEquals(checkResult, true);
    }

    @Test
    public void testKiemTraGiaTrung_CoTrung() {
        boolean check = utilsDAO.kiemTraGiaTrung(1678);
        Assert.assertEquals(false, check);
    }

    @Test
    public void testKiemTraGiaTrung_KhongTrung() {
        boolean check = utilsDAO.kiemTraGiaTrung(9999);
        Assert.assertEquals(true, check);
    }

    @Test
    public void testKiemTraSoLuongGia_SinhHoat() {
        String ghiChu = "Sinh hoạt";
        boolean checkResult = utilsDAO.kiemTraSoLuongGia(ghiChu);
        Assert.assertEquals(checkResult, false);
    }

    @Test
    public void testKiemTraSoLuongGia_SinhHoatTraTruoc() {
        String ghiChu = "Sinh hoạt trả trước";
        boolean checkResult = utilsDAO.kiemTraSoLuongGia(ghiChu);
        Assert.assertEquals(checkResult, false);
    }

    @Test
    public void testKiemTraTonTai_CoTonTai() {
        String columnOut = "Gia";
        String columnIn = "MaDG";
        String tableName = "dongia";
        String maDG = "71";
        boolean check = utilsDAO.kiemTraTonTai(tableName, columnOut, columnIn, maDG);
        Assert.assertEquals(check, false);
    }

    @Test
    public void testKiemTraTonTai_KhongTonTai() {
        String columnOut = "Gia";
        String columnIn = "MaDG";
        String tableName = "dongia";
        String maDG = "90";
        boolean check = utilsDAO.kiemTraTonTai(tableName, columnOut, columnIn, maDG);
        Assert.assertEquals(check, true);
    }

    @Test
    public void testKiemTraTonTaiUpdateKH_CoTruongBiTrung() {
        String tableName = "khachhang";
        String columnOut = "MaKH";
        String columnIn = "CMND";
        String value = "125666888";// Chứng minh thư ND trùng
        String maKH = "0001";
        boolean check = utilsDAO.kiemTraTonTaiUpdateKH(tableName, columnOut, columnIn, value, maKH);
        Assert.assertEquals(check, false);
    }

    @Test
    public void testKiemTraTonTaiUpdateKH_KhongCoTruongBiTrung() {
        String tableName = "khachhang";
        String columnOut = "MaKH";
        String columnIn = "CMND";
        String value = "125777999";// Chứng minh thư ND không trùng
        String maKH = "0001";
        boolean check = utilsDAO.kiemTraTonTaiUpdateKH(tableName, columnOut, columnIn, value, maKH);
        Assert.assertEquals(check, true);
    }
}
