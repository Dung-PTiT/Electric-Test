package com.ptit.electricbill.dao;


import com.ptit.electricbill.dao.impl.ThueDAOImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThueDAOTest {
    @Autowired
    private ThueDAOImpl thueDAO;

    @Test
    public void getGiaThueTest() {
        Double ex = 0.1;
        Assert.assertEquals(ex, thueDAO.getGiaThue());
    }

    public void getMaThueTest() {
        Integer ex = 1;
        Assert.assertEquals(ex, thueDAO.getMaThue());
    }
}
