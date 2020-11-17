package com.ding.sm.dao;

import com.ding.sm.entity.Admin;
import com.ding.sm.factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AdminDaoTest {

    private final AdminDao adminDao = DaoFactory.getAdminDaoInstance();

    @Test
    public void findAdminByAccount() {
        Admin admin;
        try {
            admin = adminDao.findAdminByAccount("taoranran@qq.com");
            assertEquals("陶然然",admin.getAdminName());
            System.out.println(admin);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}