package view;

import dao.StudentDao;
import model.Student;
import utils.DbUtil;
import utils.StringUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentManageFrm extends JFrame {
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();
    JPanel p7 = new JPanel();
    JPanel p8 = new JPanel();
    JPanel p9 = new JPanel();
    // 设置标签
    JLabel label1 = new JLabel("姓名");
    JLabel label2 = new JLabel("学院");
    JLabel label3 = new JLabel("姓名");
    JLabel label4 = new JLabel("学号");
    JLabel label5 = new JLabel("性别");
    JLabel label6 = new JLabel("年龄");
    JLabel label7 = new JLabel("学院");
    JLabel label8 = new JLabel("班级");
    JLabel label9 = new JLabel("籍贯");
    JLabel label10 = new JLabel("身份证号");
    JLabel label11 = new JLabel("学生寝室");
    JLabel label12 = new JLabel("联系方式");
    // 设置文本框
    JTextField t1 = new JTextField(12);
    JTextField t2 = new JTextField(12);
    JTextField t3 = new JTextField(15);
    JTextField t4 = new JTextField(15);
    JTextField t5 = new JTextField(15);
    JTextField t6 = new JTextField(15);
    JTextField t7 = new JTextField(15);
    JTextField t8 = new JTextField(15);
    JTextField t9 = new JTextField(15);
    JTextField t10 = new JTextField(15);
    JTextField t11 = new JTextField(14);
    JTextField t12 = new JTextField(13);

    String[] columnName = {"姓名", "年龄", "性别", "籍贯", "身份证号", "电话", "学号", "班级", "学院", "寝室"};
    String[][] data = {};

    // 设置表格
    DefaultTableModel tableModel = new DefaultTableModel(data, columnName){
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    JTable table = new JTable(tableModel);
    // 设置滚动条
    JScrollPane jScrollPane = null;
    // 设置按钮
    JButton b1 = new JButton("查找");
    JButton b2 = new JButton("修改");
    JButton b3 = new JButton("删除");
    // 设置图标
    ImageIcon icon1 = new ImageIcon("src/img/search.png");
    ImageIcon icon2 = new ImageIcon("src/img/modify.png");
    ImageIcon icon3 = new ImageIcon("src/img/delete.png");
    // 设置窗口图标
    Image image = this.getToolkit().getImage("src/img/logo.png");
    // 设置字体
    Font font1 = new Font("宋体", Font.BOLD, 15);
    Font font2 = new Font("宋体", Font.BOLD, 13);

    // 初始化表格
    private JTable fillTable(Student student) throws Exception {
        Connection conn = null;

        // 重写方法使表格无法编辑

        //table.setModel(tableModel);
        //table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        //table.setPreferredSize(new Dimension(550, 300));
        table.setPreferredSize(new Dimension(600, 300));

        try {
            conn = DbUtil.getCon();
            ResultSet res = new StudentDao().search(conn, student);
            while (res.next()) {
                tableModel.addRow(new String[]{res.getString("stuName"), res.getString("stuAge"),
                                               res.getString("stuSex"), res.getString("stuHometown"),
                                               res.getString("stuPersonalID"), res.getString("stuTel"),
                                               res.getString("stuID"), res.getString("stuClass"),
                                               res.getString("stuCollege"), res.getString("stuBedroom")});
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeCon(conn);
        }

        return table;
    }

    public StudentManageFrm() throws Exception {
        // 设置窗口标题
        setTitle("学生信息维护");
        setLayout(null);
        // 设置窗口大小
        //setSize(650, 500);
        setSize(700, 550);
        // 设置窗口居中显示
        setLocationRelativeTo(null);
        // 禁用缩放
        setResizable(false);
        // 设置窗口图标
        this.setIconImage(image);

        // 设置字体及图标
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
        label11.setFont(font1);
        label12.setFont(font1);
        b2.setFont(font2);
        b2.setIcon(icon2);
        b3.setFont(font2);
        b3.setIcon(icon3);

        // 设置查找框
        t1.setPreferredSize(new Dimension(200, 23));
        b1.setIcon(icon1);
        b1.setFont(font2);
        b1.setPreferredSize(new Dimension(90, 23));
        p1.add(label1);
        p1.add(t1);
        p1.add(label2);
        p1.add(t2);
        p1.add(b1);
        p1.setBounds(110, 20, 450, 50);
        add(p1);

        // 设置表格界面
        jScrollPane = new JScrollPane(this.fillTable(new Student()));
        jScrollPane.setPreferredSize(new Dimension(600, 100));
        p2.add(jScrollPane);
        p2.setBounds(50, 70, 600, 100);
        add(p2);

        // 设置信息修改界面
        p3.setLayout(null);

        // 界面的第一部分
        p4.add(label3);
        p4.add(t3);
        p4.add(label4);
        p4.add(t4);
        // 左对齐
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        p4.setBounds(10, 30, 500, 30);
        p3.add(p4);
        // 界面的第二部分
        p5.add(label5);
        p5.add(t5);
        p5.add(label6);
        p5.add(t6);
        // 左对齐
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        p5.setBounds(10, 70, 500, 30);
        p3.add(p5);
        // 界面的第三部分
        p6.add(label7);
        p6.add(t7);
        p6.add(label8);
        p6.add(t8);
        // 左对齐
        p6.setLayout(new FlowLayout(FlowLayout.LEFT));
        p6.setBounds(10, 110, 500, 30);
        p3.add(p6);
        // 界面的第四部分
        p7.add(label9);
        p7.add(t9);
        p7.add(label10);
        p7.add(t10);
        // 左对齐
        p7.setLayout(new FlowLayout(FlowLayout.LEFT));
        p7.setBounds(10, 150, 500, 30);
        p3.add(p7);
        // 界面的第五部分
        p8.add(label11);
        p8.add(t11);
        p8.add(label12);
        p8.add(t12);
        // 左对齐
        p8.setLayout(new FlowLayout(FlowLayout.LEFT));
        p8.setBounds(10, 190, 500, 30);
        p3.add(p8);
        // 界面的第六部分
        p9.add(b2);
        p9.add(b3);
        // 居中
        p9.setLayout(new FlowLayout(FlowLayout.CENTER));
        p9.setBounds(10, 230, 500, 40);
        p3.add(p9);
        p3.setBorder(BorderFactory.createTitledBorder("学生信息修改"));
        p3.setBounds(50, 180, 550, 300);
        add(p3);

        // 查找按钮事件
        b1.addActionListener(e -> {
            String stuName = t1.getText();
            String stuCollege = t2.getText();
            Connection conn = null;
            ResultSet res = null;
            Vector<Student> students = new Vector<>();

            Student student = new Student();
            student.setStuName(stuName);
            student.setStuCollege(stuCollege);

            try {
                conn = DbUtil.getCon();
                res = new StudentDao().search(conn, student);

                while (res.next()) {
                    Student student1 = new Student();
                    student1.setStuName(res.getString("stuName"));
                    student1.setStuCollege(res.getString("stuCollege"));
                    students.add(student1);
                }

                // 创建表格模式，并不允许修改表格
                tableModel = new DefaultTableModel(data, columnName){
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return false;
                    }
                };

                // 判断是否存在该学生
                if (!students.isEmpty()) {
                    JTable table1 = new JTable();
                    table1.getTableHeader().setReorderingAllowed(false);
                    for (Student stu : students) {
                        table1 = fillTable(stu);
                    }
                    table1.setModel(tableModel);
                    table1 = new JTable(tableModel);
                    table1.setPreferredSize(new Dimension(600, 300));
                    table1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            int row = table.getSelectedRow();
                            if (row >= 0) {
                                t3.setText((String)table.getValueAt(row, 0));
                                t4.setText((String)table.getValueAt(row, 6));
                                t5.setText((String)table.getValueAt(row, 2));
                                t6.setText((String)table.getValueAt(row, 1));
                                t7.setText((String)table.getValueAt(row, 8));
                                t8.setText((String)table.getValueAt(row, 7));
                                t9.setText((String)table.getValueAt(row, 3));
                                t10.setText((String)table.getValueAt(row, 4));
                                t11.setText((String)table.getValueAt(row, 9));
                                t12.setText((String)table.getValueAt(row, 5));
                            }
                        }
                    });
                    jScrollPane = new JScrollPane(table1);
                    jScrollPane.setPreferredSize(new Dimension(600, 100));
                    p2.add(jScrollPane);
                    p2.setBounds(50, 70, 600, 100);
                    add(p2);
                }
                else {
                    JOptionPane.showMessageDialog(null, "无此学生，请重新输入学生信息");
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                if (res != null) {
                    try{
                        res.close();
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }
                try{
                    DbUtil.closeCon(conn);
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
        // 修改按钮事件
        b2.addActionListener(e -> {
            String stuName = t3.getText();
            String stuAge = t6.getText();
            String stuSex = t5.getText();
            String stuTel = t12.getText();
            String stuID = t4.getText();
            String stuClass = t8.getText();
            String stuCollege = t7.getText();
            String stuPersonalID = t10.getText();
            String stuHometown = t9.getText();
            String stuBedroom = t11.getText();

            Student student = new Student(stuName, stuAge, stuSex, stuTel, stuID, stuClass,
                                          stuCollege, stuPersonalID, stuHometown, stuBedroom);
            Connection conn = null;
            try{
                conn = DbUtil.getCon();

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
                if (!StringUtil.isNumeric(stuPersonalID)) {
                    JOptionPane.showMessageDialog(null, "身份证号必须全为数字");
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

                if (new StudentDao().modify(conn, student) == 1) {
                    JOptionPane.showMessageDialog(null, "学生信息修改成功");
                    dispose();
                    new StudentManageFrm().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "学生信息修改失败");
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                try {
                    DbUtil.closeCon(conn);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        // 删除按钮事件
        b3.addActionListener(e -> {
            String id = t4.getText();
            Connection conn = null;

            try{
                conn = DbUtil.getCon();

                if (new StudentDao().delete(conn, id) == 1) {
                    JOptionPane.showMessageDialog(null, "学生信息删除成功");
                    dispose();
                    new StudentManageFrm().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "学生信息删除失败");
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // 添加事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    t3.setText((String)table.getValueAt(row, 0));
                    t4.setText((String)table.getValueAt(row, 6));
                    t5.setText((String)table.getValueAt(row, 2));
                    t6.setText((String)table.getValueAt(row, 1));
                    t7.setText((String)table.getValueAt(row, 8));
                    t8.setText((String)table.getValueAt(row, 7));
                    t9.setText((String)table.getValueAt(row, 3));
                    t10.setText((String)table.getValueAt(row, 4));
                    t11.setText((String)table.getValueAt(row, 9));
                    t12.setText((String)table.getValueAt(row, 5));
                }
            }
        });
    }
}
