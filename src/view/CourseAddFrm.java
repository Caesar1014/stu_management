package view;

import dao.CourseDao;
import model.Course;
import utils.DbUtil;
import utils.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CourseAddFrm extends JFrame {
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    // 设置标签
    JLabel label1 = new JLabel("课程编号");
    JLabel label2 = new JLabel("课程名称");
    JLabel label3 = new JLabel("所属学院");
    JLabel label4 = new JLabel("课程地址");
    // 设置文本框
    JTextField t1 = new JTextField(14);
    JTextField t2 = new JTextField(14);
    JTextField t3 = new JTextField(14);
    JTextField t4 = new JTextField(14);
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

    public CourseAddFrm() {
        // 设置窗口标题
        setTitle("课程添加");
        setLayout(null);
        setSize(320, 320);
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
        label4.setFont(font1);
        b1.setFont(font2);
        b2.setFont(font2);
        b1.setIcon(icon1);
        b2.setIcon(icon2);

        // 添加组件
        p1.add(label1);
        p1.add(t1);
        // 设置位置大小
        p1.setBounds(20, 30, 480, 40);
        // 设置左对齐
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p1);

        // 添加组件
        p2.add(label2);
        p2.add(t2);
        p2.add(label3);
        p2.add(t3);
        // 设置位置大小
        p2.setBounds(20, 70, 400, 40);
        // 设置左对齐
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p2);

        // 添加组件
        p3.add(label3);
        p3.add(t3);
        // 设置位置大小
        p3.setBounds(20, 110, 400, 40);
        // 设置左对齐
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p3);

        // 添加组件
        p4.add(label4);
        p4.add(t4);
        // 设置位置大小
        p4.setBounds(20, 150, 400, 50);
        // 设置左对齐
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p4);

        // 添加组件
        p5.add(b1);
        p5.add(b2);
        // 设置位置大小
        p5.setBounds(20, 200, 250, 60);
        add(p5);

        // 添加按钮事件
        // 添加按钮
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cno = t1.getText();
                String cName = t2.getText();
                String college = t3.getText();
                String cAddress = t4.getText();

                if (StringUtil.isEmpty(cno)) {
                    JOptionPane.showMessageDialog(null, "课程号不能为空");
                    return;
                }

                if (!StringUtil.isNumeric(cno)) {
                    JOptionPane.showMessageDialog(null, "课程号必须全为数字");
                    return;
                }

                if (StringUtil.isEmpty(cName)) {
                    JOptionPane.showMessageDialog(null, "课程名不能为空");
                    return;
                }

                if (StringUtil.isEmpty(college)) {
                    JOptionPane.showMessageDialog(null, "课程所属学院不能为空");
                    return;
                }

                if (!StringUtil.isChinese(college)){
                    JOptionPane.showMessageDialog(null, "学院必须全为中文");
                    return;
                }

                if (StringUtil.isEmpty(cAddress)) {
                    JOptionPane.showMessageDialog(null, "上课地点不能为空");
                    return;
                }

                // 存储添加的课程信息
                Course course = new Course(cno, cName, college, cAddress);
                try {
                    Connection conn = DbUtil.getCon();

                    if (new CourseDao().add(conn, course) == 1) {
                        JOptionPane.showMessageDialog(null, "课程添加成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "课程添加失败");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        // 重置按钮
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
            }
        });
    }
}
