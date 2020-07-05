package com.ptit.electricbill.database;

import com.ptit.electricbill.model.KhachHang;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class KhachHangService {
    private Connection connection = JDBCConnection.getInstance().getConnection();

    public KhachHang getKHByMakH(String maKH) {
        try {
            String sql = "SELECT * FROM khachhang WHERE MaKH = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maKH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MaKH"));
                kh.setTenKhachHang(rs.getString("TenKH"));
                kh.setNgaySinh(rs.getString("Ngaysinh"));
                kh.setSoCMND(rs.getString("CMND"));
                kh.setDiaChi(rs.getString("Diachi"));
                kh.setMailAddress(rs.getString("MailAddress"));
                kh.setGioiTinh(rs.getString("Gioitinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setNgayBDSD(rs.getString("NgayBDSD"));
                kh.setMucDichSD(rs.getString("MucDichSD"));
                kh.setTrangThai(rs.getString("TrangThai"));
                return kh;
            }
        } catch (Exception e) {
            System.out.println("Loi CSDL: " + e);
        }
        return null;
    }

    public void deleteKHByMaKh(String maKH) {
        try {
            String sql = "DELETE FROM khachhang WHERE MaKH = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maKH);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Loi CSDL: " + e);
        }
    }
}
