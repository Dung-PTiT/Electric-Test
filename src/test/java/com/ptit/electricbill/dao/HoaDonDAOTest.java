package com.ptit.electricbill.dao;

import com.ptit.electricbill.model.HoaDon;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

// 5 Tests
@RunWith(SpringRunner.class)
@SpringBootTest
public class HoaDonDAOTest {

    @Autowired
    private HoaDonDAO hoaDonDAO;

    @Autowired
    private UtilsDAO utilsDAO;

    @Test
    @Rollback
    @Transactional
    public void addTest() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHD("0001-032020");
        hoaDon.setMaKH("0001");
        hoaDon.setMaThang("032020");
        hoaDon.setThue(0.1);
        hoaDon.setMaDK(59);
        hoaDon.setTien(187660);
        hoaDon.setThoiGian("25-06-2020 22:15:56");
        hoaDonDAO.add(hoaDon);
        HoaDon hoaDonNew = hoaDonDAO.getHoaDonByMaHD("0001-032020");
        Assert.assertEquals(hoaDonNew.toString(), hoaDon.toString());
    }

    @Test
    @Rollback
    @Transactional
    public void deleteTest() {
        hoaDonDAO.deleteHoaDon("0001-012020");
        boolean checkResult = utilsDAO.kiemTraTonTai("hoadon", "MaHD", "MaHD", "0001-012020");
        Assert.assertEquals(true, checkResult);
    }

    @Test
    public void getMaKHTest() {
        List<String> maKHList = hoaDonDAO.getMaKH();
        List<String> maKHListTest = Arrays.asList("0001", "0002", "1221");
        Assert.assertEquals(maKHListTest.toString(), maKHList.toString());
    }

    @Test
    public void getMaThangTest() {
        List<String> maThangList = hoaDonDAO.getMaThang();
        List<String> maThangListTest = Arrays.asList("012020", "022020");
        Assert.assertEquals(maThangListTest.toString(), maThangList.toString());
    }

    @Test
    public void getMaHDByMaDKTest() {
        String maHD = hoaDonDAO.getMaHDByMaDK("50");
        String maHDTest =  "0001-012020";
        Assert.assertEquals(maHDTest, maHD);
    }
}
