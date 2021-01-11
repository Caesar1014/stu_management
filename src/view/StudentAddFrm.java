package view;
/**
 * 学生添加界面
 */

import dao.ClassTypeDao;
import dao.StudentDao;
import model.ClassType;
import model.Student;
import utils.DbUtil;
import utils.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class StudentAddFrm extends JFrame {
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();
    // 设置标签
    JLabel label1 = new JLabel("学生姓名");
    JLabel label2 = new JLabel("学生学院");
    JLabel label3 = new JLabel("学生班级");
    JLabel label4 = new JLabel("学生学号");
    JLabel label5 = new JLabel("学生性别");
    JLabel label6 = new JLabel("学生年龄");
    JLabel label7 = new JLabel("学生籍贯");
    JLabel label8 = new JLabel("身份证号");
    JLabel label9 = new JLabel("学生寝室");
    JLabel label10 = new JLabel("联系方式");
    // 设置文本框
    JTextField t1 = new JTextField(10);
    JTextField t2 = new JTextField(10);
    JTextField t3 = new JTextField(15);
    JTextField t4 = new JTextField(15);
    JTextField t5 = new JTextField(13);
    JTextField t6 = new JTextField(13);
    JTextField t7 = new JTextField(14);
    JTextField t8 = new JTextField(12);
    // 设置下拉框
    JComboBox<String> comboBox = new JComboBox<>();
    JComboBox<String> comboBox1 = new JComboBox<>();
    // 设置单选框
    JRadioButton r1 = new JRadioButton("男");
    JRadioButton r2 = new JRadioButton("女");
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

    // 填充学院框
    public void fillComboBox() {
        Connection conn = null;
        ResultSet res = null;
        try{
            conn = DbUtil.getCon();
            res = new ClassTypeDao().search(conn, new ClassType());
            comboBox.addItem("");
            while (res.next()) {
                String msg = res.getString("college");
                boolean flag = false;
                for (int i = 0; i < comboBox.getItemCount(); i++) {
                    if (msg.equals(comboBox.getItemAt(i))){
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    comboBox.addItem(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StudentAddFrm() {
        // 设置窗口标题
        setTitle("学生添加");
        setLayout(null);
        // 设置窗口大小
        //setSize(450, 350);
        setSize(475, 425);
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
        label5.setFont(font1);
        label6.setFont(font1);
        label7.setFont(font1);
        label8.setFont(font1);
        label9.setFont(font1);
        label10.setFont(font1);
        b1.setFont(font2);
        b2.setFont(font2);
        r1.setFont(font1);
        r2.setFont(font1);
        // 设置按钮图标
        b1.setIcon(icon1);
        b2.setIcon(icon2);
        // 将单选框放入同一组，确保单选
        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);

        // 添加组件
        p1.add(label1);
        p1.add(t1);
        p1.add(label2);
        p1.add(comboBox);
        // 设置位置大小
        p1.setBounds(10, 30, 480, 40);
        // 设置左对齐
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p1);

        // 添加组件
        p2.add(label3);
        p2.add(t2);
        p2.add(label4);
        p2.add(t3);
        // 设置位置大小
        p2.setBounds(10, 70, 490, 40);
        // 设置左对齐
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p2);

        // 添加组件
        p3.add(label5);
        p3.add(r1);
        p3.add(r2);
        p3.add(label6);
        p3.add(t4);
        // 设置位置大小
        p3.setBounds(10, 110, 400, 40);
        // 设置左对齐
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p3);

        // 添加组件
        p4.add(label7);
        p4.add(t5);
        p4.add(label8);
        p4.add(t6);
        // 设置位置大小
        p4.setBounds(10, 160, 450, 50);
        // 设置左对齐
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p4);

        // 添加组件
        p5.add(label9);
        p5.add(t7);
        p5.add(label10);
        p5.add(t8);
        // 设置位置大小
        p5.setBounds(10, 210, 450, 50);
        // 设置左对齐
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(p5);

        // 添加组件
        p6.add(b1);
        p6.add(b2);
        // 设置位置大小
        p6.setBounds(10, 280, 400, 50);
        add(p6);

        // 初始化下拉框
        this.fillComboBox();

        // 添加按钮事件
        // 添加按钮
        b1.addActionListener(e -> {
            String stuName = t1.getText();
            String stuAge = t4.getText();
            String stuSex = "";
            String stuTel = t8.getText();
            String stuID = t3.getText();
            String stuClass = t2.getText();
            String stuCollege = (String) comboBox.getSelectedItem();
            String stuPersonalID = t6.getText();
            String stuHometown = t5.getText();
            String stuBedroom = t7.getText();

            if (StringUtil.isEmpty(stuName)){
                JOptionPane.showMessageDialog(null, "学生姓名不能为空");
                return;
            }
            if (!StringUtil.isChinese(stuName)) {
                JOptionPane.showMessageDialog(null, "学生姓名必须全为中文");
                return;
            }
            if (StringUtil.isEmpty(stuAge)){
                JOptionPane.showMessageDialog(null, "学生年龄不能为空");
                return;
            }
            if (!StringUtil.isNumeric(stuAge)) {
                JOptionPane.showMessageDialog(null, "学生年龄必须全为数字");
                return;
            }
            if (StringUtil.isEmpty(stuTel)){
                JOptionPane.showMessageDialog(null, "联系方式不能为空");
                return;
            }
            if (!StringUtil.isMobileNo(stuTel)) {
                JOptionPane.showMessageDialog(null, "联系方式格式错误");
                return;
            }
            if (StringUtil.isEmpty(stuCollege)){
                JOptionPane.showMessageDialog(null, "学院不能为空");
                return;
            }
            if (!StringUtil.isChinese(stuCollege)){
                JOptionPane.showMessageDialog(null, "学院必须全为中文");
                return;
            }
            if (StringUtil.isEmpty(stuID)){
                JOptionPane.showMessageDialog(null, "学号不能为空");
                return;
            }
            if (!StringUtil.isNumeric(stuID)){
                JOptionPane.showMessageDialog(null, "学号必须全为数字");
                return;
            }
            if (StringUtil.isEmpty(stuClass)){
                JOptionPane.showMessageDialog(null, "班级不能为空");
                return;
            }
            if (StringUtil.isEmpty(stuPersonalID)){
                JOptionPane.showMessageDialog(null, "身份证号不能为空");
                return;
            }
            if (StringUtil.isEmpty(stuHometown)){
                JOptionPane.showMessageDialog(null, "学生籍贯地不能为空");
                return;
            }
            if (StringUtil.isEmpty(stuBedroom)){
                JOptionPane.showMessageDialog(null, "学生寝室不能为空");
                return;
            }

            if (r1.isSelected()){
                stuSex = "男";
            }
            else if (r2.isSelected()){
                stuSex = "女";
            }

            if (StringUtil.isEmpty(stuSex)){
                JOptionPane.showMessageDialog(null, "性别不能为空");
                return;
            }

            // 将添加的学生信息进行存储
            Student student = new Student(stuName, stuAge, stuSex, stuTel, stuID, stuClass, stuCollege, stuPersonalID, stuHometown, stuBedroom);
            try {
                Connection conn = DbUtil.getCon();

                if (new StudentDao().add(conn, student) == 1){
                    JOptionPane.showMessageDialog(null, "学生添加成功");
                }
                else{
                    JOptionPane.showMessageDialog(null, "学生添加失败");
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        // 重置按钮
        b2.addActionListener(e -> {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");
            t7.setText("");
            t8.setText("");
            comboBox.setSelectedItem("");
        });
    }
}
