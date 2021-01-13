package view;

import dao.StudyDao;
import model.Study;
import utils.DbUtil;
import utils.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class StudyAddFrm extends JFrame {
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    // 设置标签
    JLabel label1 = new JLabel("学生编号");
    JLabel label2 = new JLabel("课程编号");
    JLabel label3 = new JLabel("课程成绩");
    // 设置文本框
    JTextField t1 = new JTextField(14);
    JTextField t2 = new JTextField(14);
    JTextField t3 = new JTextField(14);
    // 设置按钮
    JButton b1 = new JButton("添加");
    JButton b2 = new JButton("重置");
    // 设置图标
    ImageIcon icon1 = new ImageIcon("src/img/login.png");
    ImageIcon icon2 = new ImageIcon("src/img/clear.png");
    // 设置窗口图标
    Image image = this.getToolkit().getImage("src/img/logo.png");
    // 设置字体
    Font font1 = new Font("宋体", Font.BOLD, 15);
    Font font2 = new Font("宋体", Font.BOLD, 14);

    public StudyAddFrm() {
        // 设置窗口标题
        setTitle("学习信息添加");
        setLayout(null);
        setSize(300, 300);
        // 设置窗口居中显示
        setLocationRelativeTo(null);
        // 禁用缩放
        setResizable(false);
        // 设置窗口图标
        this.setIconImage(image);

        // 设置字体
        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(font1);
        b1.setFont(font2);
        b2.setFont(font2);
        b1.setIcon(icon1);
        b2.setIcon(icon2);

        // 添加组件
        p1.add(label1);
        p1.add(t1);
        // 设置位置大小
        p1.setBounds(10, 30, 480, 40);
        // 设置左对齐
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p1);

        // 添加组件
        p2.add(label2);
        p2.add(t2);
        p2.add(label3);
        p2.add(t3);
        // 设置位置大小
        p2.setBounds(10, 70, 400, 40);
        // 设置左对齐
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p2);

        // 添加组件
        p3.add(label3);
        p3.add(t3);
        // 设置位置大小
        p3.setBounds(10, 110, 400, 50);
        // 设置左对齐
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p3);

        // 添加组件
        p4.add(b1);
        p4.add(b2);
        // 设置位置大小
        p4.setBounds(10, 170, 240, 50);
        add(p4);

        // 添加按钮事件
        b1.addActionListener(e -> {
            String stuID = t1.getText();
            String cNo = t2.getText();
            String cGrade = t3.getText();

            if (StringUtil.isEmpty(stuID)){
                JOptionPane.showMessageDialog(null, "学生编号不能为空");
                return;
            }

            if (!StringUtil.isNumeric(stuID)) {
                JOptionPane.showMessageDialog(null, "学生编号必须全为数字");
                return;
            }

            if (StringUtil.isEmpty(cNo)){
                JOptionPane.showMessageDialog(null, "课程编号不能为空");
                return;
            }

            if (!StringUtil.isNumeric(cNo)) {
                JOptionPane.showMessageDialog(null, "课程编号必须全为数字");
                return;
            }

            if (StringUtil.isEmpty(cGrade)){
                JOptionPane.showMessageDialog(null, "课程成绩不能为空");
                return;
            }

            // 存储添加的学习信息
            Study study = new Study(stuID, cNo, cGrade);
            try {
                Connection conn = DbUtil.getCon();

                if (new StudyDao().add(conn, study) == 1) {
                    JOptionPane.showMessageDialog(null, "学习信息添加成功");
                }else {
                    JOptionPane.showMessageDialog(null, "学习信息添加失败");
                }
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // 重置按钮
        b2.addActionListener(e -> {
            t1.setText("");
            t2.setText("");
            t3.setText("");
        });
    }
}
