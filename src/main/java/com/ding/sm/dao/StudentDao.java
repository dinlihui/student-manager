package com.ding.sm.dao;

import com.ding.sm.vo.StudentVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName StudentDao
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/23
 **/
public interface StudentDao {

    List<StudentVo> selectAll() throws SQLException;
}
