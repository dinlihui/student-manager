package com.ding.sm.serive.impl;

import com.ding.sm.dao.ClazzDao;
import com.ding.sm.entity.Clazz;
import com.ding.sm.entity.Department;
import com.ding.sm.factory.DaoFactory;
import com.ding.sm.serive.ClazzService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ClazzServiceImpl
 * @Description TODO
 * @Author 张守杰
 * @Date 2020/11/20
 **/
public class ClazzServiceImpl implements ClazzService {
    private static final ClazzDao clazzDao = DaoFactory.getClazzDaoInstance();

    @Override
    public List<Clazz> getClazzByDepId(int department) {
        List<Clazz> clazzList = null;
        try {
            clazzList = clazzDao.selectByDepartmentId(department);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clazzList;
    }

    @Override
    public int addClazz(Clazz clazz) {
        int n = 0;
        try {
            n = DaoFactory.getClazzDaoInstance().insertClazz(clazz);
        } catch (SQLException throwables) {
            System.err.println("新增班级出现异常");
        }
        return n;
    }


    @Override
    public int deleteClazz(Integer id) {
        int n = 0;
        try {
            n =clazzDao.deleteClazz(id);
        } catch (SQLException e) {
            System.err.print("删除班级信息出现异常");
        }
        return n;

    }

}
