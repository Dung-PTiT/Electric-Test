package com.ptit.electricbill.database;

import com.ptit.electricbill.model.DienKe;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DienKeService {
    private Connection connection = JDBCConnection.getInstance().getConnection();

    public DienKe getDienKeByMaDK(int maDK) {
        String sql = "SELECT * FROM dienke WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, maDK);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DienKe dienKe = new DienKe();
                dienKe.setId(rs.getInt("id"));
                dienKe.setMaKH(rs.getString("MaKH"));
                dienKe.setMaThang(rs.getString("MaThang"));
                dienKe.setSoDienMoi(rs.getInt("SoDienMoi"));
                dienKe.setSoDienCu(rs.getInt("SoDienCu"));
                dienKe.setStatus(rs.getInt("Status"));
                return dienKe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
