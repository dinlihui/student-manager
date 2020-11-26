package com.ding.sm.dao.impl;

import com.ding.sm.dao.StudentDao;
import com.ding.sm.entity.Student;
import com.ding.sm.utils.JdbcUtil;
import com.ding.sm.vo.StudentVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentDaoImpl
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/23
 **/
public class StudentDaoImpl implements StudentDao {
    @Override
    public List<StudentVo> selectAll() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = JdbcUtil.getConnection();
        String sql = "select t1.*,t2.class_name,t3.department_name\n"+
                "from t_student t1\n"+
                "left join t_class t2\n"+
                "on t1.class_id = t2.id\n"+
                "left join t_department t3\n"+
                "on t2.id = t3.id";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVo> list = new ArrayList<>();
        while (rs.next()){
            StudentVo student = StudentVo.builder()
                    .id(rs.getString("id"))
                    .departmentName(rs.getString("id"))
                    .className(rs.getString("class_name"))
                    .studentName(rs.getString("student_name"))
                    .phone(rs.getString("phone"))
                    .gender(rs.getShort("gender"))
                    .avatar(rs.getString("avatar"))
                    .birthday(rs.getDate("birthday"))
                    .address(rs.getString("address"))
                    .build();
            list.add(student);

        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return list;
    }
}
