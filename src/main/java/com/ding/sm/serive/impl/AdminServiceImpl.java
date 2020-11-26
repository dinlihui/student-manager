package com.ding.sm.serive.impl;

import com.ding.sm.dao.AdminDao;
import com.ding.sm.entity.Admin;
import com.ding.sm.factory.DaoFactory;
import com.ding.sm.serive.AdminService;
import com.ding.sm.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/20
 **/
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = DaoFactory.getAdminDaoInstance();

    @Override
    public ResultEntity adminLogin(String account, String password) {
        ResultEntity resultEntity;
        Admin admin = null;
        try {
            //根据账号查找管理员信息，捕获SQL异常
            admin = adminDao.findAdminByAccount(account);
        } catch (SQLException e) {
            System.out.println("根据账号查找管理员信息出现SQL异常");
        }
        //根据账号查找到了记录
        if (admin != null) {
            //比较密码，此时需要将用户客户端传过来的密码进行md5加密后才能比对
            if (DigestUtils.md5Hex(password).equals(admin.getPassword())) {
                resultEntity = ResultEntity.builder().code(0).message("登录成功").build();
            } else {
                resultEntity = ResultEntity.builder().code(1).message("密码错误").build();
            }

        } else {
            resultEntity = ResultEntity.builder().code(2).message("账号不存在").build();
        }
        return resultEntity;
    }
}
