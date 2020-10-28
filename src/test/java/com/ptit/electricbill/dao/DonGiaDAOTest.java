package com.ptit.electricbill.dao;

import com.ptit.electricbill.model.DonGia;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//8 Tests
@RunWith(SpringRunner.class)
@SpringBootTest
public class DonGiaDAOTest {

    @Autowired
    private DonGiaDAO donGiaDAO;

    @Autowired
    private UtilsDAO utilsDAO;


    @Test
    @Rollback
    @Transactional
    public void addTest() {
        DonGia donGia = new DonGia();
        donGia.setGia(4000);
        donGia.setGhiChu("Sinh hoạt");
        donGiaDAO.add(donGia);
        int maDG = donGiaDAO.getMaDG(4000, "Sinh hoạt");
        donGia.setMaDonGia(maDG);
        DonGia donGiaNew = donGiaDAO.getDonGiaByMaDG(maDG);
        Assert.assertEquals(donGiaNew.toString(), donGia.toString());
    }

    @Test
    @Rollback
    @Transactional
    public void updateTest() {
        donGiaDAO.update(71, 1700);
        DonGia donGiaNew = donGiaDAO.getDonGiaByMaDG(71);
        Assert.assertEquals(donGiaNew.getGia(), 1700);
    }

    @Test
    @Rollback
    @Transactional
    public void deleteTest() {
        donGiaDAO.delete(77);
        boolean checkResult = utilsDAO.kiemTraTonTai("dongia", "MaDG", "MaDG", "77");
        Assert.assertEquals(true, checkResult);
    }

    @Test
    public void getGia_SinhHoat() {
        List<Integer> giaList = donGiaDAO.getGia("Sinh hoạt");
        List<Integer> giaListExpected = Arrays.asList(1678, 1734, 2014, 2536, 2834, 2927);
        Assert.assertEquals(giaListExpected.toString(), giaList.toString());
    }

    @Test
    public void getGia_SinhHoatTraTruoc() {
        List<Integer> giaList = donGiaDAO.getGia("Sinh hoạt trả trước");
        List<Integer> giaListExpected = Arrays.asList(2416);
        Assert.assertEquals(giaListExpected.toString(), giaList.toString());
    }

    @Test
    public void getMaDG() {
        int expectedValue = 71;
        int maDG = donGiaDAO.getMaDG(1678, "Sinh hoạt");
        Assert.assertEquals(maDG, expectedValue);
    }

    @Test
    public void getDonGiaByMaDGTest() {
        DonGia donGiaTest = new DonGia();
        donGiaTest.setMaDonGia(71);
        donGiaTest.setGia(1678);
        donGiaTest.setGhiChu("Sinh hoạt");
        DonGia donGia = donGiaDAO.getDonGiaByMaDG(71);
        Assert.assertEquals(donGiaTest.toString(), donGia.toString());
    }

    @Test
    public void getDonGiaAllTest() {
        List<DonGia> donGiaList = donGiaDAO.getDonGiaAll();
        List<DonGia> donGiaDAOListTest = new ArrayList<>();
        donGiaDAOListTest.add(new DonGia(71, 1678, "Sinh hoạt"));
        donGiaDAOListTest.add(new DonGia(72, 1734, "Sinh hoạt"));
        donGiaDAOListTest.add(new DonGia(73, 2014, "Sinh hoạt"));
        donGiaDAOListTest.add(new DonGia(74, 2536, "Sinh hoạt"));
        donGiaDAOListTest.add(new DonGia(75, 2834, "Sinh hoạt"));
        donGiaDAOListTest.add(new DonGia(76, 2927, "Sinh hoạt"));
        donGiaDAOListTest.add(new DonGia(77, 2416, "Sinh hoạt trả trước"));
        Assert.assertEquals(donGiaDAOListTest.toString(), donGiaList.toString());
    }
}
