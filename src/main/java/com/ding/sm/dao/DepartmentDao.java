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
    List<Department> getAll() throws SQLException;
}
