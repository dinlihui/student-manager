package com.ding.sm.dao.impl;

import com.ding.sm.dao.AdminDao;
import com.ding.sm.entity.Admin;
import com.ding.sm.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName AdminDaoImpl
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/15
 **/
public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin findAdminByAccount(String account) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = JdbcUtil.getConnection();
        String sql = "SELECT * FROM t_admin WHERE account = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        Admin admin = null;
        while (rs.next()) {
            int id = rs.getInt("id");
            String adminAccount = rs.getString("account");
            String password = rs.getString("password");
            String adminName = rs.getString("admin_name");
            admin = new Admin();
            admin.setId(id);
            admin.setAccount(adminAccount);
            admin.setPassword(password);
            admin.setAdminName(adminName);

        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return admin;


    }
}





