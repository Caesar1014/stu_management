package view;

/**
 * 班级添加界面
 */

import dao.ClassTypeDao;
import model.ClassType;
import utils.DbUtil;
import utils.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ClassTypeAddFrm extends JFrame {
    // 设置四个部分
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    // 设置标签
    JLabel label1 = new JLabel("学院 ");
    JLabel label2 = new JLabel("班级 ");
    JLabel label3 = new JLabel("备注 ");
    // 设置文本框
    JTextField t1 = new JTextField(15);
    JTextField t2 = new JTextField(15);
    JTextField t3 = new JTextField(15);
    // 设置按钮
    JButton b1 = new JButton("添加");
    JButton b2 = new JButton("重置");
    // 设置图片
    ImageIcon img1 = new ImageIcon("src/img/login.png");
    ImageIcon img2 = new ImageIcon("src/img/clear.png");
    // 设置窗口图标
    Image image = this.getToolkit().getImage("src/img/logo.png");
    // 设置标签字体
    Font font1 = new Font("宋体", Font.BOLD, 17);
    Font font2 = new Font("宋体", Font.BOLD, 16);

    public ClassTypeAddFrm() {
        // 设置窗口大小
        setSize(500, 400);
        // 设置标题
        setTitle("班级添加");
        setLayout(null);
        // 设置窗口居中显示
        setLocationRelativeTo(null);
        // 禁用缩放
        setResizable(false);
        // 设置窗口图标
        this.setIconImage(image);

        // 设置学院框
        label1.setFont(font1);
        t1.setPreferredSize(new Dimension(200, 23));
        p1.add(label1);
        p1.add(t1);
        p1.setBounds(90, 50, 300, 50);
        add(p1);

        // 设置班级框
        label2.setFont(font1);
        t2.setPreferredSize(new Dimension(200, 23));
        p2.add(label2);
        p2.add(t2);
        p2.setBounds(90, 100, 300, 50);
        add(p2);

        // 设置班级备注框
        label3.setFont(font1);
        t3.setPreferredSize(new Dimension(200, 50));
        p3.add(label3);
        p3.add(t3);
        p3.setBounds(90, 150, 300, 70);
        add(p3);

        // 设置按钮
        b1.setFont(font2);
        b1.setIcon(img1);
        b1.setPreferredSize(new Dimension(90, 30));
        b2.setFont(font2);
        b2.setIcon(img2);
        b2.setPreferredSize(new Dimension(90, 30));
        p4.add(b1);
        p4.add(b2);
        p4.setBounds(90, 240, 300, 50);
        add(p4);

        // 添加事件
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String college = t1.getText();
                String className = t2.getText();
                String classDesc = t3.getText();
                ClassType classType = new ClassType(college, className, classDesc);
                try {
                    Connection conn = DbUtil.getCon();

                    if (StringUtil.isEmpty(college)) {
                        JOptionPane.showMessageDialog(null, "学院不可为空");
                        return;
                    }
                    if (!StringUtil.isChinese(college)) {
                        JOptionPane.showMessageDialog(null, "学院必须为中文");
                        return;
                    }
                    if (StringUtil.isEmpty(className)) {
                        JOptionPane.showMessageDialog(null, "班级不可为空");
                        return;
                    }
                    if (new ClassTypeDao().add(conn, classType) == 1) {
                        JOptionPane.showMessageDialog(null, "班级添加成功");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "班级添加失败");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
            }
        });
    }
}
