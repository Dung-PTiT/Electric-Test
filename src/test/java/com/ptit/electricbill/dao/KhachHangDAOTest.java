package com.ptit.electricbill.dao;

import com.ptit.electricbill.dao.impl.KhachHangDAOImpl;
import com.ptit.electricbill.model.KhachHang;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KhachHangDAOTest {
    @Autowired
    private KhachHangDAOImpl khachHangDAO;

    @Test
    @Rollback
    @Transactional
    public void updateInfomationTest() {
        KhachHang khachHangTest = new KhachHang("1221", "aaa", "04/06/2020",
                "123123129", "Hà Nội", "Nam", "1231231234",
                "04/06/2020", "a@gmail.com", "Sinh hoạt trả trước",
                "disable");
        khachHangDAO.updateInformation(khachHangTest);
        KhachHang khachHangNew = khachHangDAO.getKHByMaKH("1221");
        Assert.assertEquals(khachHangTest.toString(), khachHangNew.toString());
    }

    @Test
    @Rollback
    @Transactional
    public void addKHTest() {
        KhachHang khachHangTest = new KhachHang("9999", "dung",
                "04/06/2020", "123123120",
                "Hanoi", "Nam", "1231231239",
                "04/06/2020", "abcdef@gmail.com",
                "Sinh hoạt", "active");
        khachHangDAO.addKH(khachHangTest);
        KhachHang khachHangNew = khachHangDAO.getKHByMaKH("9999");
        Assert.assertEquals(khachHangTest.toString(), khachHangNew.toString());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteKHTest() {
        khachHangDAO.deleteKH("0002");
        String trangThaiKH = khachHangDAO.getTrangThaiByMaKH("0002");
        Assert.assertEquals(trangThaiKH, "disable");
    }

    @Test
    public void getMDSDTest_SinhHoat() {
        String email = "trongdungk53@gmail.com";
        Assert.assertEquals(khachHangDAO.getMDSD(email), "Sinh hoạt");
    }

    @Test
    public void getMDSDTest_SinhHoatTraTruoc() {
        String email = "ta7.ddinhftieens@gmail.com";
        Assert.assertEquals(khachHangDAO.getMDSD(email), "Sinh hoạt trả trước");
    }
}
