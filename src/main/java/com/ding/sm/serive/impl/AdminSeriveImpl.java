package com.ding.sm.serive.impl;

import com.ding.sm.dao.AdminDao;
import com.ding.sm.entity.Admin;
import com.ding.sm.factory.DaoFactory;
import com.ding.sm.serive.AdminSerive;
import com.ding.sm.utils.ResultEntity;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

import java.awt.*;
import java.sql.SQLException;

/**
 * @ClassName AdminSeriveImpl
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/15
 **/
public class AdminSeriveImpl implements AdminSerive {

    private final AdminDao adminDao = DaoFactory.getAdminDaoInstance();

    @Override
    public ResultEntity adminLogin(String account, String password) {
        ResultEntity resultEntity;
        Admin admin = null;
        try {

            admin = adminDao.findAdminByAccount(account);
        }catch (SQLException e){
            System.err.println("根据账号查找管理员出现sql异常");
        }
      if (admin != null) {
          if (DigestUtils.md5Hex(password).equals(admin.getPassword())) {
              resultEntity = ResultEntity.builder().code(0).message("登录成功").data(admin).build();
          } else {
              resultEntity = ResultEntity.builder().code(1).message("密码错误").build();
          }
      }else {
          resultEntity = ResultEntity.builder().code(2).message("账号不存在").build();
      }
      return resultEntity;
    }

    @Override
    public List selectAll() {
        return null;
    }
}
