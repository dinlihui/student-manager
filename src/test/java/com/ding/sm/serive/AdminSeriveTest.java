package com.ding.sm.serive;

import com.ding.sm.factory.ServiceFactory;
import com.ding.sm.utils.ResultEntity;
import org.junit.Test;
import static org.junit.Assert.*;


public class AdminSeriveTest {


    private final AdminSerive adminSerive = ServiceFactory.getAdminServiceInstance();

    @Test
    public void adminLogin() {
    ResultEntity resultEntity = adminSerive.adminLogin("taoranran@qq.com","123123");

    System.out.println(resultEntity);
    }
}