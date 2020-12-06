package com.ding.sm.serive;

import com.ding.sm.entity.Clazz;

import java.util.List;

/**
 * @ClassName ClazzService
 * @Description TODO
 * @Author 张守杰
 * @Date 2020/11/20
 **/
public interface ClazzService {
    /**
     * 根据院系id查询班级列表
     *
     * @param depId 院系id
     * @return 班级列表
     */
    List<Clazz> getClazzByDepId(int depId);

    /**
     * 查询所有班级
     *
     * @return List<Clazz>
     */
    List<Clazz> selectAll();

    /**
     * 新增班级
     *
     * @param clazz 班级实体
     * @return int
     */
    int addClazz(Clazz clazz);

    /**
     * 删除班级
     *
     * @param clazzId 班级id
     * @return int
     */
    int deleteClazz(int clazzId);
}
