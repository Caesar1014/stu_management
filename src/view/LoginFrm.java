package view;
/**
 * 登录界面
 */

import dao.UserDao;
import utils.StringUtil;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginFrm extends JFrame{
    // 设置四个部分p1, p2, p3, p4
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    // 设置图片
    ImageIcon img1 = new ImageIcon("src/img/login.png");
    ImageIcon img2 = new ImageIcon("src/img/clear.png");
    // 设置窗口图标
    Image image = this.getToolkit().getImage("src/img/logo.png");
    // 设置登录按钮
    JButton b1 = new JButton("登录");
    // 设置重置按钮
    JButton b2 = new JButton("重置");
    // 设置标题
    JLabel labelTitle = new JLabel("学生信息管理系统");
    // 设置标签
    JLabel label1 = new JLabel("用户名 ");
    JLabel label2 = new JLabel("密码  ");
    // 设置文本框
    JTextField textField = new JTextField(17);
    JPasswordField passwordField = new JPasswordField(17);
    // 设置标签字体
    Font font = new Font("宋体", Font.BOLD, 15);
    // 设置标题字体
    Font font1 = new Font("宋体", Font.BOLD, 30);
    // 设置事件监听器
    ActionListener loginAction = e -> {
        String name = textField.getText().trim();
        String password = new String(passwordField.getPassword());

        // 用于判断用户名或密码是否为空
        try {
            if (StringUtil.isEmpty(name) || StringUtil.isEmpty(password)) {
                JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
            }
            else if (UserDao.login(name, password)){
                dispose();
                new MainFrm().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "用户名或密码错误");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    ActionListener clearAction = e -> {
        textField.setText("");
        passwordField.setText("");
    };

    public LoginFrm() {
        // 设置窗口标题
        setTitle("欢迎登录");
        // 设置窗口大小
        setSize(450, 300);
        setLayout(null);
        // 设置窗口图标
        this.setIconImage(image);

        // 设置标题
        labelTitle.setFont(font1);
        p1.add(labelTitle);
        p1.setBounds(60, 30, 300, 60);
        add(p1);

        // 设置用户名框
        label1.setFont(font);
        p2.add(label1);
        p2.add(textField);
        p2.setBounds(50, 85, 300, 30);
        add(p2);

        // 设置密码框
        label2.setFont(font);
        p3.add(label2);
        p3.add(passwordField);
        p3.setBounds(53, 120, 300, 30);
        add(p3);

        // 设置登录、重置按钮
        b1.setFont(font);
        b1.setIcon(img1);
        b2.setFont(font);
        b2.setIcon(img2);
        p4.add(b1);
        p4.add(b2);
        p4.setBounds(110, 165, 200, 40);
        add(p4);

        // 添加监听器
        b1.addActionListener(loginAction);
        b2.addActionListener(clearAction);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 禁用缩放
        setResizable(false);
        // 使窗口居中
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws Exception {
        new LoginFrm().setVisible(true);
    }
}

