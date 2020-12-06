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
    @Override
    public List<Clazz> getClazzByDepId(int depId) {
        List<Clazz> list = null;
        try {
            list = DaoFactory.getClazzDaoInstance().selectByDepartmentId(depId);
        } catch (SQLException e) {
            System.err.println("根据院系id查询班级列表出现异常");
        }
        return list;
    }

    @Override
    public List<Clazz> selectAll() {
        List<Clazz> list = null;
        try {
            list = DaoFactory.getClazzDaoInstance().selectAll();
        } catch (SQLException e) {
            System.err.println("查询所有班级列表出现异常");
        }
        return list;
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
    public int deleteClazz(int clazzId) {
        int n = 0;
        try {
            n = DaoFactory.getClazzDaoInstance().deleteClazz(clazzId);
        } catch (SQLException throwables) {
            System.err.println("删除班级出现异常");
        }
        return n;
    }
}
