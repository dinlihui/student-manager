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
    Admin findAdminByAccount (String account) throws SQLException;
}


