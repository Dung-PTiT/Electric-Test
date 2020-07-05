package com.ptit.electricbill.database;

import com.ptit.electricbill.model.DonGia;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DonGiaService {
    private Connection con = JDBCConnection.getInstance().getConnection();

    public DonGia getDonGiaByMaDG(int maDG) {
        String sql = "SELECT * FROM dongia WHERE MaDG = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maDG);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DonGia donGia = new DonGia();
                donGia.setMaDonGia(rs.getInt("MaDG"));
                donGia.setGia(rs.getInt("Gia"));
                donGia.setGhiChu(rs.getString("GhiChu"));
                return donGia;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
