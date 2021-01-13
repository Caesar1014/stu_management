package view;

import dao.CourseDao;
import model.Course;
import utils.DbUtil;
import utils.StringUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class CourseManageFrm extends JFrame {
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    JPanel p6 = new JPanel();
    JPanel p7 = new JPanel();
    JPanel p8 = new JPanel();
    // 设置标签
    JLabel label1 = new JLabel("课程名称");
    JLabel label2 = new JLabel("所属学院");
    JLabel label3 = new JLabel("课程编号");
    JLabel label4 = new JLabel("课程名称");
    JLabel label5 = new JLabel("所属学院");
    JLabel label6 = new JLabel("上课地址");
    // 设置文本框
    JTextField t1 = new JTextField(12);
    JTextField t2 = new JTextField(10);
    JTextField t3 = new JTextField(15);
    JTextField t4 = new JTextField(15);
    JTextField t5 = new JTextField(15);
    JTextField t6 = new JTextField(15);

    String[] columnName = {"课程编号", "课程名称", "所属学院", "上课地址"};
    String[][] data = {};
    // 设置表格
    DefaultTableModel tableModel = new DefaultTableModel(data, columnName) {
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
    private JTable fillTable(Course course) throws Exception {
        Connection conn = null;

        table.getTableHeader().setReorderingAllowed(false);
        table.setPreferredSize(new Dimension(600, 300));

        try {
            conn = DbUtil.getCon();
            ResultSet res = new CourseDao().search(conn, course);

            while (res.next()) {
                tableModel.addRow(new String[]{res.getString("cno"), res.getString("cname"),
                        res.getString("college"), res.getString("caddress")});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return table;
    }

    public CourseManageFrm() throws Exception {
        // 设置窗口标题
        setTitle("课程信息维护");
        setLayout(null);
        // 设置窗口大小
        setSize(700, 550);
        // 设置窗口居中显示
        setLocationRelativeTo(null);
        // 禁用缩放
        setResizable(false);
        // 设置窗口图标
        this.setIconImage(image);

        // 设置查找框
        label1.setFont(font1);
        label2.setFont(font1);
        t1.setPreferredSize(new Dimension(200, 23));
        b1.setIcon(icon1);
        b1.setFont(font2);
        b1.setPreferredSize(new Dimension(90, 23));
        p1.add(label1);
        p1.add(t1);
        p1.add(label2);
        p1.add(t2);
        p1.add(b1);
        p1.setBounds(110, 15, 490, 60);
        add(p1);

        // 设置表格界面
        jScrollPane = new JScrollPane(this.fillTable(new Course()));
        jScrollPane.setPreferredSize(new Dimension(600, 100));
        p2.add(jScrollPane);
        p2.setBounds(50, 70, 600, 110);
        add(p2);

        // 设置信息修改界面
        p3.setLayout(null);
        // 设置字体及图标
        label3.setFont(font1);
        label4.setFont(font1);
        label5.setFont(font1);
        label6.setFont(font1);
        b2.setFont(font2);
        b2.setIcon(icon2);
        b3.setFont(font2);
        b3.setIcon(icon3);

        // 界面的第一部分
        p4.add(label3);
        p4.add(t3);
        // 左对齐
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        p4.setBounds(50, 40, 300, 30);
        p3.add(p4);

        // 界面的第二部分
        p5.add(label4);
        p5.add(t4);
        // 左对齐
        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
        p5.setBounds(50, 80, 300, 30);
        p3.add(p5);

        // 界面的第三部分
        p6.add(label5);
        p6.add(t5);
        // 左对齐
        p6.setLayout(new FlowLayout(FlowLayout.LEFT));
        p6.setBounds(50, 120, 300, 30);
        p3.add(p6);

        // 界面的第四部分
        p7.add(label6);
        p7.add(t6);
        // 左对齐
        p7.setLayout(new FlowLayout(FlowLayout.LEFT));
        p7.setBounds(50, 160, 300, 30);
        p3.add(p7);

        // 界面的第六部分
        p8.add(b2);
        p8.add(b3);
        // 居中
        p8.setLayout(new FlowLayout(FlowLayout.CENTER));
        p8.setBounds(50, 210, 300, 40);
        p3.add(p8);

        p3.setBorder(BorderFactory.createTitledBorder("寝室信息修改"));
        p3.setBounds(130, 180, 400, 300);
        add(p3);

        // 添加按钮事件
        // 查找按钮事件
        b1.addActionListener(e -> {
            String cName = t1.getText();
            String college = t2.getText();
            Connection conn = null;
            ResultSet res = null;
            Vector<Course> courses = new Vector<>();

            Course course = new Course();
            course.setCname(cName);
            course.setCollege(college);

            try {
                conn = DbUtil.getCon();
                res = new CourseDao().search(conn, course);

                while (res.next()) {
                    Course course1 = new Course();
                    course1.setCname(res.getString("cname"));
                    course1.setCollege(res.getString("college"));
                    courses.add(course1);
                }

                // 创建表格模式，并不允许修改表格
                tableModel = new DefaultTableModel(data, columnName) {
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return false;
                    }
                };

                // 判断是否存在该寝室
                if (!courses.isEmpty()) {
                    JTable table1 = new JTable();
                    table1.getTableHeader().setReorderingAllowed(false);

                    for (Course course1 : courses) {
                        table1 = fillTable(course1);
                    }
                    table1.setModel(tableModel);
                    table1 = new JTable(tableModel);
                    table1.setPreferredSize(new Dimension(600, 300));
                    table1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            int row = table.getSelectedRow();
                            if (row >= 0) {
                                t3.setText((String) table.getValueAt(row, 0));
                                t4.setText((String) table.getValueAt(row, 1));
                                t5.setText((String) table.getValueAt(row, 2));
                                t6.setText((String) table.getValueAt(row, 3));
                            }
                        }
                    });
                    jScrollPane = new JScrollPane(table1);
                    jScrollPane.setPreferredSize(new Dimension(600, 100));
                    p2.add(jScrollPane);
                    p2.setBounds(50, 70, 600, 110);
                    add(p2);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 添加修改按钮事件
        b2.addActionListener(e -> {
            String cNo = t3.getText();
            String cName = t4.getText();
            String college = t5.getText();
            String cAddress = t6.getText();

            Course course = new Course(cNo, cName, college, cAddress);
            Connection conn = null;

            try {
                conn = DbUtil.getCon();

                if (StringUtil.isEmpty(cNo)){
                    JOptionPane.showMessageDialog(null, "课程编号不能为空");
                    return;
                }
                if (StringUtil.isEmpty(cName)){
                    JOptionPane.showMessageDialog(null, "课程名称不能为空");
                    return;
                }
                if (StringUtil.isEmpty(college)){
                    JOptionPane.showMessageDialog(null, "所属学院不能为空");
                    return;
                }
                if (StringUtil.isEmpty(cAddress)){
                    JOptionPane.showMessageDialog(null, "上课地址不能为空");
                    return;
                }

                if (new CourseDao().update(conn, course) == 1) {
                    JOptionPane.showMessageDialog(null, "课程信息修改成功");
                    dispose();
                    new CourseManageFrm().setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(null, "课程信息修改失败");
            }catch (Exception ex) {
                ex.printStackTrace();
            }finally {
                try {
                    DbUtil.closeCon(conn);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 添加删除按钮事件
        b3.addActionListener(e -> {
            String cNo = t3.getText();
            Connection conn = null;

            try {
                conn = DbUtil.getCon();

                if(new CourseDao().delete(conn, cNo) == 1) {
                    JOptionPane.showMessageDialog(null, "课程信息删除成功");
                    dispose();
                    new CourseManageFrm().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "课程信息删除失败");
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 添加表格点击事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    t3.setText((String) table.getValueAt(row, 0));
                    t4.setText((String) table.getValueAt(row, 1));
                    t5.setText((String) table.getValueAt(row, 2));
                    t6.setText((String) table.getValueAt(row, 3));
                }
            }
        });
    }
}
