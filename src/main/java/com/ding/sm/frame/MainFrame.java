package com.ding.sm.frame;


import com.ding.sm.component.CustomPanel;
import com.ding.sm.entity.Clazz;
import com.ding.sm.entity.Department;
import com.ding.sm.entity.FormatUtil;
import com.ding.sm.factory.ServiceFactory;
import com.ding.sm.utils.AliOSSUtil;
import com.ding.sm.vo.StudentVo;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MainFrame
 * @Description TODO
 * @Author ding
 * @Date 2020/11/15
 **/
public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton 院系管理button;
    private JButton 班级管理button;
    private JButton 学生管理button;
    private JButton 奖惩管理button;
    private JPanel centerPanel;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel rewardPanel;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private JPanel addDepPanel;
    private JTextField depNameField;
    private JButton 选择图片Button;
    private JPanel toolBarPanel;
    private JButton 新增院系Button;
    private JButton 切换显示Button;
    private JLabel LogoLabel;
    private JPanel contentPanel;
    private JButton 新增Button;
    private JPanel rightPanel;
    private JComboBox<Department> depCombobox;
    private JTextField classNameField;
    private JButton 新增班级Button;
    private JPanel treePanel;
    private JPanel classContentPanel;
    private JComboBox<Department> departmentJBox;
    private JComboBox<Clazz> clazzJBox;
    private JTextField searchField;
    private JButton 搜索Button;
    private JButton 新增学生Button;
    private JButton 批量导入Button;
    private JPanel tablePanel;

    private final CardLayout c;
    private String upLoadFileUrl;
    private File file;
    private List<StudentVo> students;
    /**
     * 选择的院系id
     */
    private int departmentId = 0;

    StudentVo studentVo;

    public MainFrame() {
        init();
        c = new CardLayout();
        centerPanel.setLayout(c);
        centerPanel.add("1", departmentPanel);
        centerPanel.add("2", classPanel);
        centerPanel.add("3", studentPanel);
        centerPanel.add("4", rewardPanel);

        studentVo = StudentVo.builder()
                .departmentName("院系")
                .className("班级")
                .studentName("姓名")
                .avatar("https://soul1950.oss-cn-beijing.aliyuncs.com/img/cc.jpg")
                .gender((short)2)
                .birthday(new Date())
                .phone("13951905171")
                .address("江苏南京")
                .build();

        院系管理button.addActionListener(e -> {
            c.show(centerPanel, "1");
            showDepartments();
        });
        班级管理button.addActionListener(e -> {
            c.show(centerPanel, "2");
            showClazz();
        });
        学生管理button.addActionListener(e -> {
            c.show(centerPanel, "3");
        });
        奖惩管理button.addActionListener(e -> {
            c.show(centerPanel, "4");
        });
        showDepartments();
        新增院系Button.addActionListener(e -> {
            //获取左侧面板的可见性
            boolean visible = addDepPanel.isVisible();
            //不可见
            if (!visible) {
                //向右侧展开、背景色变化、可见
                leftPanel.setPreferredSize(new Dimension(180, this.getHeight() - 100));
                addDepPanel.setVisible(true);
            } else {
                //向左侧展开、背景色变化、可见
                leftPanel.setPreferredSize(new Dimension(60, this.getHeight() - 100));
                addDepPanel.setVisible(false);
            }

            leftPanel.revalidate();
        });

        //院系名称文本框焦点监听
        depNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                depNameField.setText("");
            }
        });

        选择图片Button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            //默认打开路径
            fileChooser.setCurrentDirectory(new File("D:\\"));
            //对话框显示的范围在centerPanel内
            int result = fileChooser.showOpenDialog(centerPanel);
            if (result == JFileChooser.APPROVE_OPTION) {
                //选中文件
                file = fileChooser.getSelectedFile();
                String name = file.getAbsolutePath();
                //创建icon对象
                ImageIcon icon = new ImageIcon(name);
                LogoLabel.setPreferredSize(new Dimension(150, 150));
                //图片强制缩放成和JLabel一样大小
                icon = new ImageIcon(icon.getImage().getScaledInstance(LogoLabel.getWidth(), LogoLabel.getHeight(), Image.SCALE_DEFAULT));
                LogoLabel.setIcon(icon);
            }
        });
        新增Button.addActionListener(e -> {
            //上传文件代OSS并返回url
            upLoadFileUrl = AliOSSUtil.ossUpload(file);
            //创建Department对象，并对设置相应属性
            Department department = new Department();
            department.setDepartmentName(depNameField.getText().trim());
            department.setLogo(upLoadFileUrl);
            //调用service实现新增功能
            int n = ServiceFactory.getDepartmentServiceInstance().addDepartment(department);
            if (n == 1) {
                JOptionPane.showMessageDialog(centerPanel, "新增院系成功");
                //新增成功后，将侧边栏隐藏
                leftPanel.setPreferredSize(new Dimension(60, this.getHeight() - 100));
                addDepPanel.setVisible(false);
                //刷新界面数据
                showDepartments();
                //清空文本框
                depNameField.setText("");
                LogoLabel.setIcon(null);
            } else {
                JOptionPane.showMessageDialog(centerPanel, "新增院系失败");
            }
        });

        //获取下拉框选择的院系id
        depCombobox.addActionListener(e -> {
            //得到选中项的索引
            int index = depCombobox.getSelectedIndex();
            //按照索引取出项，就是一个Department对象，然后取出其id
            departmentId = depCombobox.getItemAt(index).getId();
        });

        新增班级Button.addActionListener(e -> {
            Clazz clazz = new Clazz();
            clazz.setDepartmentId(departmentId);
            clazz.setClassName(classNameField.getText().trim());
            int n = ServiceFactory.getClazzServiceInstance().addClazz(clazz);
            if (n == 1) {
                JOptionPane.showMessageDialog(centerPanel, "新增班级成功");
                showClazz();
            } else {
                JOptionPane.showMessageDialog(centerPanel, "新增班级失败");
            }
        });
        学生管理button.addActionListener(e -> {
            c.show(centerPanel,"3");
            //学生数据填充表格
            showStudents(ServiceFactory.getStudentServiceInstance().selectAll());

            //初始化院系下拉框数据，第一条数据为提示信息
            departmentJBox.addItem(Department.builder().departmentName("请选择院系").build());
            List<Department> departments = ServiceFactory.getDepartmentServiceInstance().selectAll();
            for (Department department : departments) {
                departmentJBox.addItem(department);
            }

            //初始化班级下拉框数据
            clazzJBox.addItem(Clazz.builder().className("请选择班级").build());
            List<Clazz> clazzes = ServiceFactory.getClazzServiceInstance().selectAll();
            for (Clazz clazz : clazzes) {
                clazzJBox.addItem(clazz);
            }
            //右侧个人信息面板
            CustomPanel stuInfoPanel = new CustomPanel("D:\\java learning\\student-manager\\src\\main\\resources\\img\\shower.png");
            stuInfoPanel.setPreferredSize(new Dimension(300, 600));
            stuInfoPanel.setLayout(null);
            stuInfoPanel.repaint();
            studentPanel.add(stuInfoPanel, BorderLayout.EAST);
            JLabel title = new JLabel("学生信息");
            title.setFont(new Font("楷体", Font.BOLD, 20));
            title.setForeground(new Color(97, 174, 239));
            title.setBounds(100,50,100,50);
            //stuInfoPanel.add(title);
            //头像
            JLabel avatar = new JLabel("<html><img src= '" + studentVo.getAvatar() + "' width=50 height=50/><ml>");
            avatar.setBounds(120, 110, 50, 50);
            //院系
            JTextField depName = new JTextField(studentVo.getDepartmentName());
            depName.setBounds(50,170,200,40);
            //班级
            JTextField className = new JTextField(studentVo.getClassName());
            className.setBounds(50,220,200,40);
            //姓名
            JTextField stuName = new JTextField(studentVo.getStudentName());
            stuName.setBounds(50,270,200,40);
            //性别
            JTextField gender = new JTextField(FormatUtil.formatGender(studentVo.getGender()));
            gender.setBounds(50,320,200,40);
            //生日
            JTextField birthday = new JTextField(FormatUtil.formatDate(studentVo.getBirthday()));
            birthday.setBounds(50,370,200,40);
            //电话
            JTextField phone = new JTextField(studentVo.getPhone());
            phone.setBounds(50,420,200,40);
            //地址
            JTextField address = new JTextField(studentVo.getAddress());
            address.setBounds(50,470,200,40);
            //操作按钮
            JButton opButton = new JButton("编辑");
            opButton.setBounds(100,520,120,40);

            stuInfoPanel.add(title);
            stuInfoPanel.add(avatar);
            stuInfoPanel.add(depName);
            stuInfoPanel.add(className);
            stuInfoPanel.add(stuName);
            stuInfoPanel.add(gender);
            stuInfoPanel.add(birthday);
            stuInfoPanel.add(phone);
            stuInfoPanel.add(address);
            stuInfoPanel.add(opButton);


        });

        departmentJBox.addItemListener(e1 -> {
            if (e1.getStateChange() == ItemEvent.SELECTED) {
                //排除第一条数据
                int index = departmentJBox.getSelectedIndex();
                if (index != 0) {
                    Integer depId = departmentJBox.getItemAt(index).getId();
                    students = ServiceFactory.getStudentServiceInstance().selectByDepId(depId);
                    showStudents(students);
                    List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getClazzByDepId(depId);
                    clazzJBox.removeAllItems();
                    clazzJBox.addItem(Clazz.builder().className("请选择班级").build());
                    for (Clazz clazz : clazzList){
                        clazzJBox.addItem(clazz);
                    }
                }else {
                    students = ServiceFactory.getStudentServiceInstance().selectAll();
                    showStudents(students);
                    clazzJBox.removeAllItems();
                    clazzJBox.addItem(Clazz.builder().className("请选择班级").build());
                    List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().selectAll();
                    for (Clazz clazz : clazzList) {
                        clazzJBox.addItem(clazz);
                    }
                }
            }
        });
        //班级下拉框监听
        clazzJBox.addItemListener(e2 -> {
            if (e2.getStateChange() == ItemEvent.DESELECTED) {
                int index = clazzJBox.getSelectedIndex();
                if (index != 0) {
                    Integer classId = clazzJBox.getItemAt(index).getId();
                    List<StudentVo> studentList = ServiceFactory.getStudentServiceInstance().selectByClassId(classId);
                    showStudents(studentList);
                }else{
                    //复原数据
                    clazzJBox.removeAllItems();
                    clazzJBox.addItem(Clazz.builder().className("请选择班级").build());
                    List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().selectAll();
                    for (Clazz clazz : clazzList) {
                        clazzJBox.addItem(clazz);
                    }
                }
            }
        });
        搜索Button.addActionListener(e -> {
            students = ServiceFactory.getStudentServiceInstance().selectByKeywords(searchField.getText().trim());
            showStudents(students);
            searchField.setText("");
        });
    }



    public void init() {
        this.setTitle("管理员界面");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(966, 768);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void showClazz() {
        List<Department> departments = ServiceFactory.getDepartmentServiceInstance().selectAll();
        showConbobox(departments);
        showTree(departments);
        showClazz(departments);
    }

    private void showConbobox(List<Department> departments) {
        for (Department department : departments) {
            depCombobox.addItem(department);

        }
    }

    private void showStudents(List<StudentVo> students){
//        CustomPanel stuInfoPanel = new CustomPanel("/D:/qq.png");
//        stuInfoPanel.setPreferredSize(new Dimension(300, 600));
//        JLabel title = new JLabel("学生信息");
//        title.setFont(new Font("楷体", Font.BOLD, 20));
//        title.setForeground(new Color(97, 174, 239));
//        stuInfoPanel.add(title);
//        stuInfoPanel.repaint();
//        studentPanel.add(stuInfoPanel, BorderLayout.EAST);

        //获得学生列表数据
//        List<StudentVo> students = ServiceFacotry.getStudentServiceInstance().selectAll();
        tablePanel.removeAll();
        //创建表格对象
        JTable table = new JTable();
        //创建表格数据模型，并设置给表格
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        //设置表头内容
        model.setColumnIdentifiers(new String[]{"学号","院系","班级","姓名","性别","地址","手机号","出生日期","头像"});
        //遍历list，生成Object数组，数组中的每个元素就是一行记录
        for (StudentVo student : students) {
            Object[] object = new Object[]{
                    student.getId(),student.getDepartmentName(),student.getClassName(),student.getStudentName(),
                    student.getGender(),student.getAddress(),student.getPhone(),student.getBirthday(),student.getAvatar()
            };
            //添加到数据模型
            model.addRow(object);
        }
        //设置最后一列不显示在表格中
        TableColumn tc = table.getColumnModel().getColumn(8);
        tc.setMaxWidth(0);
        tc.setMinWidth(0);
        //获得表头的表格
        JTableHeader header = table.getTableHeader();
        //表头剧中
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        //设置表头字体
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 18));
        //设置表格行高
        table.setRowHeight(35);
        //表格背景色
        table.setBackground(new Color(223, 241, 234));
        //表格内容居中
        DefaultTableCellHeaderRenderer r = new DefaultTableCellHeaderRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        //表格加入滚动面板，并设置水平和垂直方才那个均可按需滚动
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablePanel.add(scrollPane);
        tablePanel.revalidate();

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
//            JOptionPane.showMessageDialog(null,table.getValueAt(row,2).toString() +
//                    table.getValueAt(row,3).toString());
            StudentVo studentVo = StudentVo.builder()
                    .className(table.getValueAt(row,2).toString())
                    .studentName(table.getValueAt(row, 3).toString())
                    .phone(table.getValueAt(row, 6).toString())
                    .address(table.getValueAt(row, 5).toString())
                    .build();
            System.out.println(studentVo);
        });


    }


    private void showTree(List<Department> departments) {
        treePanel.removeAll();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("南京工业职业技术大学");
        for (Department department : departments) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            root.add(group);
            List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getClazzByDepId(department.getId());
            for (Clazz clazz : clazzList) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(clazz.getClassName());
                group.add(node);
            }
        }
        final JTree tree = new JTree(root);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑",Font.PLAIN, 14));
        treePanel.add(tree);
        treePanel.revalidate();
    }

    private void showClazz(List<Department> departments) {
        classContentPanel.removeAll();
        classContentPanel.setLayout(new GridLayout(0, 5, 15, 15));
        Font titleFont = new Font("微软雅黑", Font.PLAIN, 16);
        for (Department department : departments) {
            JPanel depPanel = new JPanel();
            depPanel.setPreferredSize(new Dimension(120, 150));
            depPanel.setBackground(new Color(63, 98, 131));
            depPanel.setLayout(new BorderLayout());
            JLabel depNameLabel = new JLabel(department.getDepartmentName());
            depNameLabel.setFont(titleFont);
            depNameLabel.setForeground(new Color(255, 255, 255));
            depPanel.add(depNameLabel, BorderLayout.NORTH);
            List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getClazzByDepId(department.getId());
            DefaultListModel<Clazz> listModel = new DefaultListModel<>();
            for (Clazz clazz : clazzList) {
                listModel.addElement(clazz);
            }
            JList<Clazz> jList = new JList<>(listModel);
            jList.setBackground(new Color(101, 134, 184));
            JScrollPane srollPane = new JScrollPane(jList);
            depPanel.add(srollPane, BorderLayout.CENTER);
            classContentPanel.add(depPanel);

            //对每个jList添加弹出菜单操作
            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem modifyItem = new JMenuItem("修改");
            JMenuItem deleteItem = new JMenuItem("删除");
            jPopupMenu.add(modifyItem);
            jPopupMenu.add(deleteItem);
            jList.add(jPopupMenu);

            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //选中项对下标
                    int index = jList.getSelectedIndex();
                    //点击鼠标右键
                    if (e.getButton() == 3) {
                        //在鼠标位置弹出菜单
                        jPopupMenu.show(jList, e.getX(), e.getY());
                        //取出选中项的班级对象数据
                        Clazz clazz = jList.getModel().getElementAt(index);
                        //对象"删除"菜单项添加事件监听
                        deleteItem.addActionListener(e1 ->{
                            int choice = JOptionPane.showConfirmDialog(depPanel,"确定删除吗？");
                            //点击了"确定"
                            if (choice == 0) {
                                //根据当前班级的id删除
                                int n = ServiceFactory.getClazzServiceInstance().deleteClazz(clazz.getId());
                                if (n == 1) {
                                    //从list数据模型中移除当前项，先从视觉上删除效果
                                    listModel.remove(index);
                                    //走后端重新调用下数据
                                    showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * 显示所有院系
     */
    private void showDepartments() {
        //移除原有数据
        contentPanel.removeAll();
        //从service层获取到多有院系列表
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        //获取总数
        int len = departmentList.size();
        //根据院系总数算出行数（每行放4个）
        int row = len % 4 == 0 ? len / 4 : len / 4 + 1;
        //创建一个网格布局，每行4列，指定水平和垂直间距
        GridLayout gridLayout = new GridLayout(row, 4, 15, 15);
        contentPanel.setLayout(gridLayout);
        for (Department department : departmentList) {
            //给每个院系对象创建一个面板
            JPanel depPanel = new JPanel();
            depPanel.setPreferredSize(new Dimension(220, 200));
            depPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

            JLabel nameLabel = new JLabel(department.getDepartmentName());
            //设置合适大小
//            depPanel.setPreferredSize(new Dimension(200,200));
//            //将院系名称设置给面板标题
//            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            //新建一个JLabel标签，用来放置院系Logo，并设置大小  width=200 height=105
            JLabel logoLabel = new JLabel("<html><img src= '" + department.getLogo() + "' width='200' height='105'/></html>");
            //占位空白标签
            JLabel blankLabel = new JLabel();
            blankLabel.setPreferredSize(new Dimension(200, 30));
            //删除按钮
            JButton delBtn = new JButton("删除");
            //删除院系
            delBtn.addActionListener(e -> {
                int n = ServiceFactory.getDepartmentServiceInstance().delDepartment(department, department.getId());
                if (n == 1) {
                    JOptionPane.showMessageDialog(centerPanel, "删除院系成功");
                    showDepartments();
                } else {
                    JOptionPane.showMessageDialog(centerPanel, "删除院系失败");
                }
            });

            //院系名称标签加入院系面板
            depPanel.add(nameLabel);
            //图标标签加入院系面板
            depPanel.add(logoLabel);
            //按钮加入院系面板
            depPanel.add(delBtn);
            //delBtn.setBounds(20,0,20,20);
            //院系面板加入主题内容面板
            contentPanel.add(depPanel);
            //刷新主体内容面板
            contentPanel.revalidate();
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here


    }
}
