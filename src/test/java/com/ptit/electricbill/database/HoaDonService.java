package com.ptit.electricbill.database;

import com.ptit.electricbill.model.HoaDon;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class HoaDonService {
    private Connection connection = JDBCConnection.getInstance().getConnection();

    public HoaDon getHoaDonByMaHD(String maHD) {
        String sql = "SELECT * FROM hoadon WHERE MaHD = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getString("MaHD"));
                hoaDon.setMaKH(rs.getString("MaKH"));
                hoaDon.setMaThang(rs.getString("MaThang"));
                hoaDon.setMaDK(rs.getInt("MaDk"));
                hoaDon.setThue(rs.getDouble("Thue"));
                hoaDon.setTien(rs.getLong("Tien"));
                hoaDon.setThoiGian(rs.getString("ThoiGian"));
                return hoaDon;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
