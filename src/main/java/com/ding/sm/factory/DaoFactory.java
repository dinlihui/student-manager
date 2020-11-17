package com.ding.sm.factory;


import com.ding.sm.dao.AdminDao;
import com.ding.sm.dao.DepartmentDao;
import com.ding.sm.dao.impl.AdminDaoImpl;
import com.ding.sm.dao.impl.DepartmentDaoImpl;
import com.ding.sm.entity.Department;


/**
 * @ClassName DaoFactory
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/15
 **/
public class DaoFactory {

    public static AdminDao getAdminDaoInstance(){

        return new AdminDaoImpl();
    }

    public static DepartmentDao getDepartmentDaoInstance() {
        return new DepartmentDaoImpl();
    }
}
