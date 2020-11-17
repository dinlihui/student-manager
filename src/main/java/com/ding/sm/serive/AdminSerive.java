package com.ding.sm.serive;

import com.ding.sm.utils.ResultEntity;

import java.awt.*;

/**
 * @ClassName AdminSerive
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/15
 **/
public interface AdminSerive {

    ResultEntity adminLogin(String account,String password);

    List selectAll();
}
