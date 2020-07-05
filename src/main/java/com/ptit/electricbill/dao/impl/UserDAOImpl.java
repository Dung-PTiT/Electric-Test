package com.ptit.electricbill.dao.impl;


import com.ptit.electricbill.dao.UserDAO;
import com.ptit.electricbill.entity.UserEntity;
import com.ptit.electricbill.model.User;
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
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserEntity getByUsername(String username) {
        try {
            String hql = "SELECT u FROM UserEntity u WHERE u.username = :username";
            Query query = entityManager.createQuery(hql);
            query.setParameter("username", username);
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean checkExistUser(String username) {
        String hql = "SELECT u.username FROM UserEntity u";
        Query query = entityManager.createQuery(hql);
        List<String> usernameList = query.getResultList();
        if (usernameList.contains(username)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO thanhvien (username, password, role) VALUES ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getRole() + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public List<String> getCustomerID() {
        String sql = "SELECT  distinct(MaKH) from khachhang where TrangThai = 'active'";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public String getMDSD(String maKH) {
        String sql = "SELECT MucDichSD FROM khachhang where MaKH = " + maKH + "";
        Query query = entityManager.createNativeQuery(sql);
        return (String) query.getSingleResult();
    }

    @Override
    public void deleteUser(Integer userID) {
        String sql = "DELETE FROM thanhvien WHERE (id = '" + userID + "')";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection con) throws SQLException {
                try (PreparedStatement stmt = con.prepareStatement(
                        "SELECT * FROM thanhvien as t where t.username = '" + username + "'")) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(rs.getString("role"));
                    }
                }
            }
        });
        return user;
    }
}
