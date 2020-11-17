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
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * from t_department";
        PreparedStatement psmt = connection.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        List<Department>departmentList =new ArrayList<>();
        while (rs.next()){
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setDepartmentName(rs.getString("department_name"));
            department.setLogo(rs.getString("logo"));
            departmentList.add(department);
        }
        rs.close();
        psmt.close();
        jdbcUtil.closeConnection();
        return departmentList;

    }
}
