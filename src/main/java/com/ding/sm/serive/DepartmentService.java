package com.ding.sm.serive;

import com.ding.sm.entity.Department;

import java.util.List;

/**
 * @ClassName DepartmentService
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/17
 **/
public interface DepartmentService {
    List<Department> selectAll();
}
