package com.ding.sm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName FormatUtil
 * @Description TODO
 * @Author 张守杰
 * @Date 2020/11/26
 **/
public class FormatUtil {
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String formatGender(int gender) {
        if (gender == 0) {
            return "保密";
        } else if (gender == 1) {
            return "男";
        } else {
            return "女";
        }
    }
}
