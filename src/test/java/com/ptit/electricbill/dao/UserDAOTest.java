package com.ptit.electricbill.dao;

import com.ptit.electricbill.model.User;
import com.ptit.electricbill.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDAOTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void checkExistTest_TonTai() {
        String username = "admin@gmail.com";
        Assert.assertEquals(true, userDAO.checkExistUser(username));
    }

    @Test
    public void checkExistTest_KhongTonTai() {
        String username = "admin1@gmail.com";
        Assert.assertEquals(false, userDAO.checkExistUser(username));
    }

    @Test
    public void getCustomerIDTest() {
        List<String> listEx = new ArrayList<String>();
        listEx.add("0001");
        listEx.add("0002");
        listEx.add("1233");
        listEx.add("1234");
        List<String> list = userDAO.getCustomerID();
        Assert.assertEquals(listEx.toString(), list.toString());
    }

    @Test
    public void getMDSDTest_SinhHoat() {
        String ex = "Sinh hoạt";
        String maKH = "0001";
        Assert.assertEquals(ex, userDAO.getMDSD(maKH));
    }

    @Test
    public void getMDSDTest_SinhHoatTraTruoc() {
        String ex = "Sinh hoạt trả trước";
        String maKH = "0002";
        Assert.assertEquals(ex, userDAO.getMDSD(maKH));
    }

    @Test
    @Rollback
    @Transactional
    public void addUserTest() {
        User user = new User("dungzin@gmai.com", "$2a$12$NdTdQ.sZ79xxur3S7PI97uYGLxkuFFvc.sw3R9w9pW1Ixe93IpNZ.", "ROLE_ADMIN");
        userDAO.addUser(user);
        User userNew = userDAO.getUserByUsername("dungzin@gmai.com");
        Assert.assertEquals(userNew.getUsername(), user.getUsername());
        Assert.assertEquals(userNew.getRole(), user.getRole());
        Assert.assertEquals(userNew.getPassword(), user.getPassword());
    }
}
