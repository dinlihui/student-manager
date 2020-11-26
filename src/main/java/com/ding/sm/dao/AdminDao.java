package com.ding.sm.dao;


import com.ding.sm.entity.Admin;
import java.sql.SQLException;

/**
 * @ClassName Admin
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/15
 **/
public interface AdminDao {
    /**
     * 根据账号查找管理员
     *
     * @param account:账号入参
     * @throws SQLException 该方法会抛出SQL异常
     */
    Admin findAdminByAccount(String account) throws SQLException;
}


