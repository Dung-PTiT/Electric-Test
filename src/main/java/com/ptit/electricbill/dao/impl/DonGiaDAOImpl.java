package com.ptit.electricbill.dao.impl;

import com.ptit.electricbill.dao.DonGiaDAO;
import com.ptit.electricbill.model.DonGia;
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
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DonGiaDAOImpl implements DonGiaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object> getAll() {
        String sql = "SELECT  * from dongia";
        Query query = entityManager.createNativeQuery(sql);
        List<Object> donGiaList = query.getResultList();
        return donGiaList;
    }

    @Override
    public void update(int MaDG, int gia) {
        String sql = "UPDATE dongia SET Gia = '" + gia + "' WHERE (MaDG = '" + MaDG + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void add(DonGia donGia) {
        String sql = "INSERT INTO dongia (Gia,GhiChu) VALUES ('" + donGia.getGia() + "', '" + donGia.getGhiChu() + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(int IDDonGia) {
        String sql = "DELETE FROM dongia where MaDG = '" + IDDonGia + "'";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<Integer> getGia(String loaiDonGia) {
        String sql = "select gia as g from dongia where GhiChu = '" + loaiDonGia + "' order by g asc";
        Query query = entityManager.createNativeQuery(sql);
        List<Integer> giaList = query.getResultList();
        return giaList;
    }

    @Override
    public Integer getMaDG(Integer gia, String ghiChu) {
        String sql = "SELECT MaDG FROM dongia where Gia = " + gia + " and GhiChu = '" + ghiChu + "'";
        Query query = entityManager.createNativeQuery(sql);
        return (Integer) query.getSingleResult();
    }

    @Override
    public DonGia getDonGiaByMaDG(Integer maDG) {
        DonGia donGia = new DonGia();
        org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection con) throws SQLException {
                try (PreparedStatement stmt = con.prepareStatement(
                        "SELECT * FROM dongia as d where d.MaDG = " + maDG + "")) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        donGia.setMaDonGia(rs.getInt("MaDG"));
                        donGia.setGia(rs.getInt("Gia"));
                        donGia.setGhiChu(rs.getString("GhiChu"));
                    }
                }
            }
        });
        return donGia;
    }

    @Override
    public List<DonGia> getDonGiaAll() {
        List<DonGia> donGiaList = new ArrayList<>();
        org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection con) throws SQLException {
                try (PreparedStatement stmt = con.prepareStatement(
                        "SELECT * FROM dongia as d")) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        DonGia donGia = new DonGia();
                        donGia.setMaDonGia(rs.getInt("MaDG"));
                        donGia.setGia(rs.getInt("Gia"));
                        donGia.setGhiChu(rs.getString("GhiChu"));
                        donGiaList.add(donGia);
                    }
                }
            }
        });
        return donGiaList;
    }
}
