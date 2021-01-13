package view;
/**
 * 系统主界面
 */

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrm extends JFrame {
    // 设置菜单栏
    JMenuBar menuBar = new JMenuBar();
    JMenu[] menus = new JMenu[]{new JMenu("班级管理"), new JMenu("学生管理"), new JMenu("生活管理"), new JMenu("学习管理"), new JMenu("关于我们")};
    JMenuItem[][] menuItems = new JMenuItem[][]{{new JMenuItem("班级添加"), new JMenuItem("班级维护")},
                                                {new JMenuItem("学生添加"), new JMenuItem("学生维护")},
                                                {new JMenuItem("生活信息添加"), new JMenuItem("生活信息维护")},
                                                {new JMenuItem("课程信息添加"), new JMenuItem("课程信息维护"), new JMenuItem("学习信息添加"), new JMenuItem("学习信息维护")},
                                                {new JMenuItem("关于")}};
    // 设置图标
    ImageIcon img1 = new ImageIcon("src/img/class_manage.png");
    ImageIcon img2 = new ImageIcon("src/img/stu_manage.png");
    ImageIcon img3 = new ImageIcon("src/img/about.png");
    ImageIcon img4 = new ImageIcon("src/img/life_manage.png");
    ImageIcon img5 = new ImageIcon("src/img/study_manage.png");
    // 设置窗口图标
    Image image = this.getToolkit().getImage("src/img/logo.png");
    // 设置事件监听器
    // 班级添加
    ActionListener a1 = e -> {
        ClassTypeAddFrm classTypeAddFrm = new ClassTypeAddFrm();
        classTypeAddFrm.setVisible(true);
    };
    // 班级维护
    ActionListener a2 = e -> {
        ClassTypeManageFrm classTypeManageFrm = null;
        try {
            classTypeManageFrm = new ClassTypeManageFrm();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assert classTypeManageFrm != null;
        classTypeManageFrm.setVisible(true);
    };
    // 学生添加
    ActionListener a3 = e -> {
        StudentAddFrm studentAddFrm = new StudentAddFrm();
        studentAddFrm.setVisible(true);
    };
    // 学生维护
    ActionListener a4 = e -> {
        try {
            StudentManageFrm studentManageFrm = new StudentManageFrm();
            studentManageFrm.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    // 生活信息添加
    ActionListener a5 = e -> {
        BedroomAddFrm bedroomAddFrm = new BedroomAddFrm();
        bedroomAddFrm.setVisible(true);
    };
    // 生活信息维护
    ActionListener a6 = e -> {
        try{
            BedroomManageFrm bedroomManageFrm = new BedroomManageFrm();
            bedroomManageFrm.setVisible(true);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    // 课程添加
    ActionListener a7 = e -> {
        CourseAddFrm courseAddFrm = new CourseAddFrm();
        courseAddFrm.setVisible(true);
    };
    // 课程修改
    ActionListener a8 = e -> {
        try {
            CourseManageFrm courseManageFrm = new CourseManageFrm();
            courseManageFrm.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    // 学习信息添加
    ActionListener a9 = e -> {
        StudyAddFrm studyAddFrm = new StudyAddFrm();
        studyAddFrm.setVisible(true);
    };
    // 学习信息修改
    ActionListener a10 = e -> {
        try {
            StudyManageFrm studyManageFrm = new StudyManageFrm();
            studyManageFrm.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    // 关于我们
    ActionListener a11 = e -> {
        JOptionPane.showMessageDialog(null, "这是一款学生信息管理系统", "关于我们", JOptionPane.PLAIN_MESSAGE);
    };

    public MainFrm(){
        // 设置窗口大小
        setSize(500, 400);
        // 设置窗口标题
        setTitle("学生管理系统");
        setLayout(null);
        // 设置窗口居中显示
        setLocationRelativeTo(null);
        // 窗口初始最大化
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // 设置窗口图标
        this.setIconImage(image);

        //设置菜单栏
        menus[0].setIcon(img1);
        menus[1].setIcon(img2);
        menus[2].setIcon(img4);
        menus[3].setIcon(img5);
        menus[4].setIcon(img3);
        // 添加事件监听器
        menuItems[0][0].addActionListener(a1);
        menuItems[0][1].addActionListener(a2);
        menuItems[1][0].addActionListener(a3);
        menuItems[1][1].addActionListener(a4);
        menuItems[2][0].addActionListener(a5);
        menuItems[2][1].addActionListener(a6);
        menuItems[3][0].addActionListener(a7);
        menuItems[3][1].addActionListener(a8);
        menuItems[3][2].addActionListener(a9);
        menuItems[3][3].addActionListener(a10);
        menuItems[4][0].addActionListener(a11);
        for (int i = 0; i < menus.length; i++) {
            menuBar.add(menus[i]);
            for (int j = 0; j < menuItems[i].length; j++) {
                menus[i].add(menuItems[i][j]);
            }
        }
        this.setJMenuBar(menuBar);
    }
}
