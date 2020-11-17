package com.ding.sm.dao;

import com.ding.sm.entity.Department;
import com.ding.sm.factory.DaoFactory;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDaoTest {
   private final DepartmentDao departmentDao = DaoFactory.getDepartmentDaoInstance();
    @Test
    public void getAll() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDao.getAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        assert departmentList != null;
        departmentList.forEach(System.out::println);
    }
}