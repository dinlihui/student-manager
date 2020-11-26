package com.ding.sm.dao.impl;

import com.ding.sm.dao.DepartmentDao;
import com.ding.sm.entity.Department;
import com.ding.sm.utils.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DepartmentDaoImpl
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/17
 **/

public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public List<Department> getAll() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = JdbcUtil.getConnection();
        String sql = "SELECT * FROM t_department";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Department> departmentList = new ArrayList<>();
        while (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setDepartmentName(rs.getString("department_name"));
            department.setLogo(rs.getString("Logo"));
            departmentList.add(department);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return departmentList;
    }

    @Override
    public int insertDepartment(Department department) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = JdbcUtil.getConnection();
        String sql = "INSERT INTO t_department(department_name, logo) VALUES(?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, department.getDepartmentName());
        pstmt.setString(2, department.getLogo());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }
    @Override
    public int deleteDepartment(Department department,int id) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = JdbcUtil.getConnection();
        String sql = "DELETE FROM t_department WHERE id = " + id;
        PreparedStatement pstmt = connection.prepareStatement(sql);
        // pstmt.setString(1, String.valueOf(departmentId));s
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

}


