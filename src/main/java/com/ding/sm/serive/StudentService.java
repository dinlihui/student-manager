package com.ding.sm.serive;

import com.ding.sm.entity.Student;
import com.ding.sm.vo.StudentVo;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/29
 **/
public interface StudentService {
    /**
     * 查询所以学生
     *
     * @return list
     */
    List<StudentVo> selectAll();

    /**
     * 根据院系id查询学生
     *
     * @param depId 院系id
     * @return list
     */
    List<StudentVo> selectByDepId(int depId);

    /**
     * 根据班级id查询学生
     *
     * @param classId 班级id
     * @return list
     */
    List<StudentVo> selectByClassId(int classId);

    /**
     * 根据关键字查询学生
     *
     * @param keywords 关键字
     * @return list
     */
    List<StudentVo> selectByKeywords(String keywords);

    /**
     * 更新学生信息
     *
     * @param student 学生对象
     * @return int
     */
    int updateStudent(Student student);

    /**
     * 删除学生
     *
     * @param id id
     * @return int
     */
    int deleteById(String id);

    /**
     * 新增学生
     *
     * @param student 学生对象
     * @return int
     */
    int insertStudent(Student student);

    /**
     * 根据院系统计学生数
     *
     * @param departmentId 院系id
     * @return 学生数量
     */
    int countByDepartmentId(int departmentId);

    /**
     * 根据班级统计学生数量
     *
     * @param classId 班级id
     * @return 学生数量
     */
    int countStudentByClassId(int classId);
}