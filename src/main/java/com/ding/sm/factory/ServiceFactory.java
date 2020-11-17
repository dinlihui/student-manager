package com.ding.sm.factory;

import com.ding.sm.serive.AdminSerive;
import com.ding.sm.serive.impl.AdminSeriveImpl;
import com.ding.sm.serive.impl.DepartmentServiceImpl;

/**
 * @ClassName ServiceFactory
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/15
 **/
public class ServiceFactory {
    public static AdminSerive getAdminServiceInstance(){
        return new AdminSeriveImpl();
    }
    public static DepartmentServiceImpl getDepartmentServiceInstance(){
        return new DepartmentServiceImpl();
    }
}
