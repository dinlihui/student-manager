package com.ding.sm.serive;

import com.ding.sm.factory.ServiceFactory;
import com.ding.sm.utils.ResultEntity;
import org.junit.Test;

/**
 * @ClassName AdminServiceTest
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/20
 **/
public class AdminServiceTest {
    private final AdminService adminService = ServiceFactory.getAdminServiceInstance();

    @Test
    public void adminLogin() {
        ResultEntity resultEntity = adminService.adminLogin("test@qq.com", "520198");
        assertEquals(0, resultEntity.getCode());
        System.out.println(resultEntity);
    }

    private void assertEquals(int i, int code) {
    }
}
