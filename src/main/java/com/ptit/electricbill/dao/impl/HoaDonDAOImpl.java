package com.ptit.electricbill.dao.impl;

import com.ptit.electricbill.dao.HoaDonDAO;
import com.ptit.electricbill.model.HoaDon;
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
public class HoaDonDAOImpl implements HoaDonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(HoaDon hoaDon) {
        String sql = "INSERT INTO hoadon (MaHD,MaKH,MaThang,Thue,MaDK,Tien,ThoiGian) VALUES ('" + hoaDon.getMaHD() + "', '" + hoaDon.getMaKH() + "', '" + hoaDon.getMaThang() + "', '" + hoaDon.getThue() + "', '" + hoaDon.getMaDK() + "', '" + hoaDon.getTien() + "', '" + hoaDon.getThoiGian() + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<Object> getAllDetail() {
        String sql = "SELECT hoadon.MaHD, hoadon.MaThang, hoadon.MaKH, khachhang.TenKH, khachhang.Diachi, dienke.SoDienMoi, dienke.SoDienCu, khachhang.MucDichSD, hoadon.Thue, hoadon.Tien, hoadon.ThoiGian from hoadon, khachhang, dienke where hoadon.MaKH = khachhang.MaKH and hoadon.MaDK = dienke.id group by hoadon.MaHD order by hoadon.MaKH asc, hoadon.MaThang desc";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public Object getBill(String maKH, String maThang) {
        String sql = "SELECT hoadon.MaHD, hoadon.MaThang, hoadon.MaKH, khachhang.TenKH, khachhang.Diachi, dienke.SoDienMoi, dienke.SoDienCu, khachhang.MucDichSD, hoadon.Thue, hoadon.Tien, hoadon.ThoiGian from hoadon, khachhang, dienke where hoadon.MaKH = khachhang.MaKH and hoadon.MaDK = dienke.id and hoadon.MaKH ='" + maKH + "' and hoadon.MaThang='" + maThang + "'  group by hoadon.MaHD order by hoadon.MaKH asc, hoadon.MaThang desc";
        Query query = entityManager.createNativeQuery(sql);
        return query.getSingleResult();
    }

    @Override
    public List<Object> getBillByColumn(String maKH, String maThang) {
        if (maKH.equals("") && maThang.equals("")) {
            String sql = "SELECT hoadon.MaHD, hoadon.MaThang, hoadon.MaKH, khachhang.TenKH, khachhang.Diachi, dienke.SoDienMoi, dienke.SoDienCu, khachhang.MucDichSD, hoadon.Thue, hoadon.Tien, hoadon.ThoiGian from hoadon, khachhang, dienke where hoadon.MaKH = khachhang.MaKH and hoadon.MaDK = dienke.id group by hoadon.MaHD order by hoadon.MaKH asc, hoadon.MaThang desc";
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        } else if (maKH.equals("")) {
            String sql = "SELECT hoadon.MaHD, hoadon.MaThang, hoadon.MaKH, khachhang.TenKH, khachhang.Diachi, dienke.SoDienMoi, dienke.SoDienCu, khachhang.MucDichSD, hoadon.Thue, hoadon.Tien, hoadon.ThoiGian from hoadon, khachhang, dienke where hoadon.MaKH = khachhang.MaKH and hoadon.MaDK = dienke.id and hoadon.MaThang = " + maThang + " group by hoadon.MaHD order by hoadon.MaKH asc, hoadon.MaThang desc";
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        } else if (maThang.equals("")) {
            String sql = "SELECT hoadon.MaHD, hoadon.MaThang, hoadon.MaKH, khachhang.TenKH, khachhang.Diachi, dienke.SoDienMoi, dienke.SoDienCu, khachhang.MucDichSD, hoadon.Thue, hoadon.Tien, hoadon.ThoiGian from hoadon, khachhang, dienke where hoadon.MaKH = khachhang.MaKH and hoadon.MaDK = dienke.id and hoadon.MaKH = " + maKH + " group by hoadon.MaHD order by hoadon.MaKH asc, hoadon.MaThang desc";
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        } else {
            String sql = "SELECT hoadon.MaHD, hoadon.MaThang, hoadon.MaKH, khachhang.TenKH, khachhang.Diachi, dienke.SoDienMoi, dienke.SoDienCu, khachhang.MucDichSD, hoadon.Thue, hoadon.Tien, hoadon.ThoiGian from hoadon, khachhang, dienke where hoadon.MaKH = khachhang.MaKH and hoadon.MaDK = dienke.id and hoadon.MaKH = " + maKH + " and hoadon.MaThang = " + maThang + " group by hoadon.MaHD order by hoadon.MaKH asc, hoadon.MaThang desc";
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        }
    }

    @Override
    public List<String> getMaKH() {
        String sql = "SELECT distinct (MaKH) from hoadon order by MaKH asc ";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public List<String> getMaThang() {
        String sql = "SELECT distinct (MaThang) from hoadon order by MaThang asc";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public String getMaHDByMaDK(String maDK) {
        String sql = "SELECT MaHD FROM hoadon where MaDK = " + maDK + "";
        Query query = entityManager.createNativeQuery(sql);
        List<String> resultList = query.getResultList();
        if (resultList.size() != 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public void deleteHoaDon(String maHD) {
        String sql = "DELETE FROM hoadon WHERE (MaHD = '" + maHD + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public HoaDon getHoaDonByMaHD(String maHD) {
        HoaDon hoaDon = new HoaDon();
        org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection con) throws SQLException {
                try (PreparedStatement stmt = con.prepareStatement(
                        "SELECT * FROM hoadon as h where h.MaHD = '" + maHD + "'")) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        hoaDon.setMaHD(rs.getString("MaHD"));
                        hoaDon.setMaKH(rs.getString("MaKH"));
                        hoaDon.setMaThang(rs.getString("MaThang"));
                        hoaDon.setMaDK(rs.getInt("MaDK"));
                        hoaDon.setThue(rs.getDouble("Thue"));
                        hoaDon.setTien(rs.getInt("Tien"));
                        hoaDon.setThoiGian(rs.getString("ThoiGian"));
                    }
                }
            }
        });
        return hoaDon;
    }
}
