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
    /**
     * 查询所有学生(视图对象)
     * @return 学生视图列表数据
     * @throws SQLException 异常
     */
    List<StudentVo> selectAll() throws SQLException;

    /**
     * 得到
     * @return 学生
     * @throws SQLException 异常
     */
    List<StudentVo> getAll() throws SQLException;

    List<StudentVo> selectByDepId(int depId) throws SQLException;

    List<StudentVo> selectByClassId(int classId) throws SQLException;

    List<StudentVo> selectByKeywords(String keywords) throws SQLException;
}

