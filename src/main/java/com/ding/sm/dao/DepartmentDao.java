package com.ding.sm.dao;

import com.ding.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepartmentDao
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/17
 **/
public interface DepartmentDao {
    /**
     * 查询所有院系
     * @return
     * @throws SQLException
     */
    List<Department> getAll() throws SQLException;

    /**
     *
     * @param department 入参
     * @return int
     * @throws SQLException 异常
     */
    int insertDepartment(Department department) throws SQLException;
    /**
     * 删除
     *
     * @param department
     * @return
     * @throws SQLException
     */
    int deleteDepartment(Department department, int id) throws SQLException;
}
