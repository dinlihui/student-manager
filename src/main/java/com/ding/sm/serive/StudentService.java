package com.ding.sm.serive;

import com.ding.sm.vo.StudentVo;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/29
 **/
public interface StudentService {
    List<StudentVo> selectAll();

    List<StudentVo> selectByDepId(int depId) ;

    List<StudentVo> selectByClassId(int classId) ;

    List<StudentVo> selectByKeywords(String keywords) ;
}