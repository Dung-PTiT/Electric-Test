package com.ptit.electricbill.testTinhToan;

import com.ptit.electricbill.controller.ConfigController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class tinhToanTest {

    @Autowired
    private ConfigController configController;

    @Test
    public void tinhTien_SinhHoat() {
        List<Integer> giaList = Arrays.asList(1678, 1734, 2014, 2536, 2834, 2927);
        String loaiDienSD = "Sinh hoạt";
        double thue = 0.1;
//        System.out.println("99: "+ configController.tinhTien(giaList, 99, loaiDienSD, thue));
        Assert.assertEquals(0, configController.tinhTien(giaList, 0, loaiDienSD, thue));
        Assert.assertEquals(90444,configController.tinhTien(giaList, 49, loaiDienSD, thue));
        Assert.assertEquals(92290,configController.tinhTien(giaList, 50, loaiDienSD, thue));
        Assert.assertEquals(94197,configController.tinhTien(giaList, 51, loaiDienSD, thue));
        Assert.assertEquals(185753,configController.tinhTien(giaList, 99, loaiDienSD, thue));
        Assert.assertEquals(187660,configController.tinhTien(giaList, 100, loaiDienSD, thue));
        Assert.assertEquals(189875,configController.tinhTien(giaList, 101, loaiDienSD, thue));
        Assert.assertEquals(406985,configController.tinhTien(giaList, 199, loaiDienSD, thue));
        Assert.assertEquals(409200,configController.tinhTien(giaList, 200, loaiDienSD, thue));
        Assert.assertEquals(411990,configController.tinhTien(giaList, 201, loaiDienSD, thue));
        Assert.assertEquals(685370,configController.tinhTien(giaList, 299, loaiDienSD, thue));
        Assert.assertEquals(688160,configController.tinhTien(giaList, 300, loaiDienSD, thue));
        Assert.assertEquals(691277,configController.tinhTien(giaList, 301, loaiDienSD, thue));
        Assert.assertEquals(996783,configController.tinhTien(giaList, 399, loaiDienSD, thue));
        Assert.assertEquals(999900,configController.tinhTien(giaList, 400, loaiDienSD, thue));
        Assert.assertEquals(1003120,configController.tinhTien(giaList, 401, loaiDienSD, thue));
        Assert.assertEquals(1318650,configController.tinhTien(giaList, 499, loaiDienSD, thue));
        Assert.assertEquals(1321870,configController.tinhTien(giaList, 500, loaiDienSD, thue));
        Assert.assertEquals(1325090,configController.tinhTien(giaList, 501, loaiDienSD, thue));
    }
}
