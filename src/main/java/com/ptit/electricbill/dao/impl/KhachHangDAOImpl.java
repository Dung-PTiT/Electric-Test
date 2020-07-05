package com.ptit.electricbill.dao.impl;

import com.ptit.electricbill.dao.KhachHangDAO;
import com.ptit.electricbill.model.KhachHang;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class KhachHangDAOImpl implements KhachHangDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object> getAll() {
        String sql = "SELECT * FROM khachhang where TrangThai != 'disable'";
        Query query = entityManager.createNativeQuery(sql);
        List<Object> userList = query.getResultList();
        return userList;
    }

    @Override
    public Object getByMaKH(String maKH) {
        String sql = "SELECT * FROM khachhang where MaKH = '" + maKH + "'";
        Query query = entityManager.createNativeQuery(sql);
        Object user = query.getResultList();
        return user;
    }

    @Override
    public void updateInformation(KhachHang KH) {
        String sql = "UPDATE khachhang SET TenKH = '" + KH.getTenKhachHang() + "',Ngaysinh = '" + KH.getNgaySinh() + "',CMND = '" + KH.getSoCMND() + "',Diachi = '" + KH.getDiaChi() + "',MailAddress = '" + KH.getMailAddress() + "',Gioitinh = '" + KH.getGioiTinh() + "',SoDienThoai = '" + KH.getSoDienThoai() + "',NgayBDSD = '" + KH.getNgayBDSD() + "',MucdichSD = '" + KH.getMucDichSD() + "' WHERE (MaKH = '" + KH.getMaKhachHang() + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void addKH(KhachHang KH) {
        String sql = "INSERT INTO khachhang (MaKH,TenKH,Ngaysinh,CMND,Diachi,MailAddress,Gioitinh,SoDienThoai,NgayBDSD,MucdichSD,TrangThai) VALUES ('" + KH.getMaKhachHang() + "', '" + KH.getTenKhachHang() + "', '" + KH.getNgaySinh() + "', '" + KH.getSoCMND() + "', '" + KH.getDiaChi() + "', '" + KH.getMailAddress() + "', '" + KH.getGioiTinh() + "', '" + KH.getSoDienThoai() + "', '" + KH.getNgayBDSD() + "', '" + KH.getMucDichSD() + "', '" + KH.getTrangThai() + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void deleteKH(String idKH) {
        String sql = "UPDATE khachhang SET TrangThai = 'disable' WHERE (MaKH = '" + idKH + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public String getMDSD(String mailAdd) {
        String sql = "SELECT MucDichSD FROM khachhang where MailAddress = '" + mailAdd + "'";
        Query query = entityManager.createNativeQuery(sql);
        return (String) query.getSingleResult();
    }

    @Override
    public String getTrangThaiByMaKH(String maKH) {
        String sql = "SELECT TrangThai FROM khachhang where MaKH = '" + maKH + "'";
        Query query = entityManager.createNativeQuery(sql);
        return (String) query.getSingleResult();
    }

    @Override
    public KhachHang getKHByMaKH(String maKH) {
        KhachHang khachHang = new KhachHang();
        org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection con) throws SQLException {
                try (PreparedStatement stmt = con.prepareStatement(
                        "SELECT * FROM khachhang as kh where kh.MaKH = '" + maKH + "'")) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        khachHang.setMaKhachHang(rs.getString("MaKH"));
                        khachHang.setTenKhachHang(rs.getString("TenKH"));
                        khachHang.setNgaySinh(rs.getString("Ngaysinh"));
                        khachHang.setSoCMND(rs.getString("CMND"));
                        khachHang.setDiaChi(rs.getString("Diachi"));
                        khachHang.setMailAddress(rs.getString("MailAddress"));
                        khachHang.setGioiTinh(rs.getString("Gioitinh"));
                        khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
                        khachHang.setNgayBDSD(rs.getString("NgayBDSD"));
                        khachHang.setMucDichSD(rs.getString("MucdichSD"));
                        khachHang.setTrangThai(rs.getString("TrangThai"));
                    }
                }
            }
        });
        return khachHang;
    }
}
