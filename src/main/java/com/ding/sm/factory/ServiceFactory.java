package com.ding.sm.factory;

import com.ding.sm.serive.AdminService;
import com.ding.sm.serive.ClazzService;
import com.ding.sm.serive.DepartmentService;
import com.ding.sm.serive.impl.AdminServiceImpl;
import com.ding.sm.serive.impl.AdminServiceImpl;
import com.ding.sm.serive.impl.ClazzServiceImpl;
import com.ding.sm.serive.impl.DepartmentServiceImpl;

/**
 * @ClassName ServiceFactory
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/15
 **/
public class ServiceFactory {
    public static AdminService getAdminServiceInstance() {

        return new AdminServiceImpl();
    }

    public static DepartmentService getDepartmentServiceInstance() {
        return new DepartmentServiceImpl();
    }


    public static ClazzService getClazzServiceInstance() {
        return new ClazzServiceImpl();
    }
}
