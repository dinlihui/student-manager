package com.ding.sm.frame;

import com.ding.sm.factory.ServiceFactory;
import com.ding.sm.utils.ResultEntity;
import sun.plugin.dom.html.HTMLBodyElement;

import javax.swing.*;
import javax.xml.ws.Service;
import java.awt.*;

/**
 * @ClassName AdminLoginFrame
 * @Description TODO
 * @Author 15696
 * @Date 2020/11/15
 **/
public class AdminLoginFrame extends JFrame  {
    private JPanel mainPanel;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton resetBtn;

    public AdminLoginFrame(){
        this.setTitle("AdminLoginFrame");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        loginBtn.addActionListener(e ->{

            String account = accountField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            ResultEntity resultEntity = ServiceFactory.getAdminServiceInstance().adminLogin(account,
                    password);
            JOptionPane.showMessageDialog(mainPanel,resultEntity.getMessage());
            if (resultEntity.getCode() == 0) {
                this.dispose();
                new MainFrame();
            }else{
                accountField.setText("");
                passwordField.setText("");
            }
        });

        resetBtn.addActionListener(e -> {
            accountField.setText("");
            passwordField.setText("");
        });
    }

    public static void main(String[] args) {
        new AdminLoginFrame();
    }
}




