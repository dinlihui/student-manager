package com.ding.sm.dao;

import com.ding.sm.dao.impl.ClazzDaoImpl;
import com.ding.sm.entity.Clazz;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ClazzDao
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/23
 **/
public interface ClazzDao {
    /**
     * 按照院系id查询班级
     *
     * @param departmentId 院系id
     * @return List<Clazz> 院系班级集合
     * @throws SQLException 异常
     */
    List<Clazz> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 获取ClazzDao实例
     *
     * @return ClazzDao 实例
     */
    public static ClazzDao getClazzDaoInstance() {
        return new ClazzDaoImpl();
    }

    /**
     * 新增班级
     * @param clazz 入参班级实体
     * @return int
     * @throws SQLException 异常
     */
    int insertClazz(Clazz clazz) throws SQLException;

    int deleteClazz(Integer id) throws SQLException;
}



