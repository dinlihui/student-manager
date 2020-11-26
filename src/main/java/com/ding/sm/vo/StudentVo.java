package com.ding.sm.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName StudentVo
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentVo {
    private String id;
    private String departmentId;
    private String departmentName;
    private String className;
    private Integer classId;
    private String studentName;
    private String phone;
    private short gender;
    private Date birthday;
    private String avatar;
    private String address;
}

