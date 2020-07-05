package com.ptit.electricbill.dao.impl;

import com.ptit.electricbill.dao.DienKeDAO;
import com.ptit.electricbill.model.DienKe;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class DienKeDAOImpl implements DienKeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object> getAll() {
        String sql = "SELECT  * from dienke order by MaKH desc, MaThang desc";
        Query query = entityManager.createNativeQuery(sql);
        List<Object> dienKeList = query.getResultList();
        return dienKeList;
    }

    @Override
    public void add(DienKe dienKe) {
        String sql = "INSERT INTO dienke (MaKH,MaThang,SoDienCu,SoDienMoi,Status) VALUES ('" + dienKe.getMaKH() + "', '" + dienKe.getMaThang() + "', '" + dienKe.getSoDienCu() + "', '" + dienKe.getSoDienMoi() + "', '" + dienKe.getStatus() + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void delete(int IDDienKe) {
        String sql = "DELETE FROM dienke where id = '" + IDDienKe + "'";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<Object> searchByKHID(String KHID) {
        String sql = "SELECT  * from dienke where MaKH = '" + KHID + "' order by MaThang desc";
        Query query = entityManager.createNativeQuery(sql);
        List<Object> dienKeList = query.getResultList();
        return dienKeList;
    }

    @Override
    public boolean checkSoDien(String maKH, String maThang) {
        String sql = "SELECT * FROM dienke WHERE MaKH = " + maKH + " AND MaThang = " + maThang + "";
        Query query = entityManager.createNativeQuery(sql);
        List<Object> resultList;
        try {
            resultList = query.getResultList();
            if (resultList.size() == 0) {
                return true;
            }
        } catch (NoResultException nre) {
        }
        return false;
    }

    @Override
    public void updateDienKeStatus(int IDDienKe) {
        String sql = "UPDATE dienke SET Status = '1' WHERE (id = '" + IDDienKe + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public Integer getIDDienKe(String maDK, String maThang) {
        String sql = "SELECT id from dienke where MaKH = '" + maDK + "' and MaThang = '" + maThang + "'";
        Query query = entityManager.createNativeQuery(sql);
        return (Integer) query.getSingleResult();
    }

    @Override
    public Integer getStatusDienKe(Integer maDK) {
        String sql = "SELECT Status from dienke where id = '" + maDK + "'";
        Query query = entityManager.createNativeQuery(sql);
        return (Integer) query.getSingleResult();
    }

    @Override
    public DienKe getDienKeByID(Integer id) {
        DienKe dienKe = new DienKe();
        org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection con) throws SQLException {
                try (PreparedStatement stmt = con.prepareStatement(
                        "SELECT * FROM dienke as d where d.id = " + id + "")) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        dienKe.setId(rs.getInt("id"));
                        dienKe.setMaKH(rs.getString("MaKH"));
                        dienKe.setMaThang(rs.getString("MaThang"));
                        dienKe.setSoDienMoi(rs.getInt("SoDienMoi"));
                        dienKe.setSoDienCu(rs.getInt("SoDienCu"));
                        dienKe.setStatus(rs.getInt("Status"));
                    }
                }
            }
        });
        return dienKe;
    }
}
