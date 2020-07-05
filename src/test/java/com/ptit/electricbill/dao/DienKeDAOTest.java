package com.ptit.electricbill.dao;

import com.ptit.electricbill.database.DienKeService;
import com.ptit.electricbill.model.DienKe;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DienKeDAOTest {

    @Autowired
    private DienKeDAO dienKeDAO;

    @Autowired
    private UtilsDAO utilsDAO;

    @Test
    @Rollback
    @Transactional
    public void addTest() {
        DienKe dienKeTest = new DienKe();
        dienKeTest.setMaKH("0001");
        dienKeTest.setMaThang("052020");
        dienKeTest.setSoDienMoi(100);
        dienKeTest.setSoDienCu(80);
        dienKeTest.setStatus(0);
        dienKeDAO.add(dienKeTest);
        int IDDienKeTest = dienKeDAO.getIDDienKe(dienKeTest.getMaKH(), dienKeTest.getMaThang());
        dienKeTest.setId(IDDienKeTest);
        DienKe dienKeNew = dienKeDAO.getDienKeByID(IDDienKeTest);
        Assert.assertEquals(dienKeNew.toString(), dienKeTest.toString());
    }

    @Test
    @Rollback
    @Transactional
    public void deleteTest() {
        dienKeDAO.delete(53);
        boolean checkResult = utilsDAO.kiemTraTonTai("dienke", "id", "id", "53");
        Assert.assertEquals(true, checkResult);
    }

    @Test
    public void checkSoDienTest_CoTonTai() {
        boolean checkResult = dienKeDAO.checkSoDien("0001", "012020");
        Assert.assertEquals(false, checkResult);
    }

    @Test
    public void checkSoDienTest_KhongTonTai() {
        boolean checkResult = dienKeDAO.checkSoDien("0001", "022020");
        Assert.assertEquals(false, checkResult);
    }

    @Test
    @Rollback
    @Transactional
    public void updateDienKeStatusTest() {
        dienKeDAO.updateDienKeStatus(59);
        int status = dienKeDAO.getStatusDienKe(59);
        Assert.assertEquals(1, status);
    }

    @Test
    public void getIDDienKeTest() {
        int ID = dienKeDAO.getIDDienKe("0001", "012020");
        Assert.assertEquals(50, ID);
    }

    @Test
    public void getStatusDienKeTest() {
        int status = dienKeDAO.getStatusDienKe(50);
        Assert.assertEquals(1, status);
    }

    @Test
    public void getDienKeByIDTest() {
        DienKe dienKeTest = new DienKe(50, "0001", "012020", 155, 0, 1);
        DienKe dienKe = dienKeDAO.getDienKeByID(50);
        Assert.assertEquals(dienKeTest.toString(), dienKe.toString());
    }
}
