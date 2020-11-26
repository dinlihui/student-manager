package com.ding.sm.dao;

import com.ding.sm.entity.Clazz;
import com.ding.sm.factory.DaoFactory;
import com.google.gson.internal.$Gson$Preconditions;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ClazzDaoTest {
    private final ClazzDao clazzDao = DaoFactory.getClazzDaoInstance();

    @Test
    public void selectByDepartmentId() {
        List<Clazz> clazzList = null;
        try {
            clazzList = clazzDao.selectByDepartmentId(5);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(clazzList);
    }


}