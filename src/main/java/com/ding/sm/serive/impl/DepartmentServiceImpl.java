package com.ding.sm.serive.impl;

import com.ding.sm.dao.DepartmentDao;
import com.ding.sm.entity.Department;
import com.ding.sm.factory.DaoFactory;
import com.ding.sm.serive.DepartmentService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepartmentServiceImpl
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/17
 **/
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao = DaoFactory.getDepartmentDaoInstance();
    @Override
    public List<Department> selectAll() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDao.getAll();
        }catch(SQLException e){
            System.err.println("查询院系信息出现异常");
        }
        return departmentList;
    }
}
