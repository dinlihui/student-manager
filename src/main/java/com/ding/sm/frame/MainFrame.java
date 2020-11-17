package com.ding.sm.frame;

import com.ding.sm.entity.Department;
import com.ding.sm.factory.ServiceFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

import static sun.plugin.security.JDK11ClassFileTransformer.init;

/**
 * @ClassName MainFrame
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/16
 **/
public class MainFrame {

    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton 院系管理Button;
    private JButton 班级管理Button;
    private JButton 学生管理Button;
    private JButton 奖惩管理Button;
    private JPanel centerPanel;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel rewardPanel;

    private final CardLayout c;

    public MainFrame() {
        init();
        院系管理Button.setOpaque(false);
        班级管理Button.setOpaque(false);
        学生管理Button.setOpaque(false);
        奖惩管理Button.setOpaque(false);
        c = new CardLayout();
        centerPanel.setLayout(c);
        centerPanel.add("1", departmentPanel);
        centerPanel.add("2", classPanel);
        centerPanel.add("3", studentPanel);
        centerPanel.add("4", rewardPanel);

        院系管理Button.addActionListener(e -> {
            c.show(centerPanel, "1");
        });

        班级管理Button.addActionListener(e -> {
            c.show(centerPanel, "2");
        });
        学生管理Button.addActionListener(e -> {
            c.show(centerPanel, "3");
        });
        奖惩管理Button.addActionListener(e -> {
            c.show(centerPanel, "4");
        });
        showDepartments();
    }

    private void showDepartments() {
        departmentPanel.removeAll();
        List<Department>  departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();

        int len = departmentList.size();

        int row = len % 4 == 0 ? len / 4 : len / 4 + 1;

        GridLayout gridLayout = new GridLayout(row,4,15,15);
        departmentPanel.setLayout(gridLayout);
        for (Department department:departmentList){
            JPanel dePanel = new JPanel();

            dePanel.setPreferredSize(new Dimension(200,200));
            dePanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));

            JLabel logoLabel = new JLabel("<html><img src ='" + department.getLogo()+"'width=200 height=200/</html>");
            dePanel.add(logoLabel);
            departmentPanel.add(dePanel);
            departmentPanel.revalidate();
        }


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(new MainFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

