package view;

import dao.StudentDao;
import dao.StudyDao;
import model.Student;
import model.Study;
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

public class StudyManageFrm extends JFrame {
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
    JLabel label1 = new JLabel("学生编号");
    JLabel label2 = new JLabel("学生名称");
    JLabel label3 = new JLabel("学生编号");
    JLabel label4 = new JLabel("学生名称");
    JLabel label5 = new JLabel("课程编号");
    JLabel label6 = new JLabel("课程名称");
    JLabel label7 = new JLabel("课程成绩");
    // 设置文本框
    JTextField t1 = new JTextField(10);
    JTextField t2 = new JTextField(12);
    JTextField t3 = new JTextField(15);
    JTextField t4 = new JTextField(15);
    JTextField t5 = new JTextField(15);
    JTextField t6 = new JTextField(15);
    JTextField t7 = new JTextField(15);

    String[] columnName = {"学生编号", "学生名称", "课程编号", "课程名称", "课程成绩"};
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
    private JTable fillTable(Study study) throws Exception {
        Connection conn = null;

        table.getTableHeader().setReorderingAllowed(false);
        table.setPreferredSize(new Dimension(600, 300));

        try {
            conn = DbUtil.getCon();
            ResultSet res = new StudyDao().search(conn, study);

            while (res.next()) {
                tableModel.addRow(new String[]{res.getString("stu_sc.stuID"), res.getString("stu_student.stuName"),
                        res.getString("stu_sc.cno"), res.getString("stu_course.cname"),
                        res.getString("stu_sc.grade")});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return table;
    }

    public StudyManageFrm() throws Exception {
        // 设置窗口标题
        setTitle("学习信息维护");
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
        t2.setPreferredSize(new Dimension(200, 23));
        b1.setIcon(icon1);
        b1.setFont(font2);
        b1.setPreferredSize(new Dimension(90, 23));
        p1.add(label1);
        p1.add(t1);
        p1.add(label2);
        p1.add(t2);
        p1.add(b1);
        p1.setBounds(110, 20, 490, 50);
        add(p1);

        // 设置表格界面
        jScrollPane = new JScrollPane(this.fillTable(new Study()));
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
        label7.setFont(font1);

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

        // 界面的第五部分
        p8.add(label7);
        p8.add(t7);
        // 左对齐
        p8.setLayout(new FlowLayout(FlowLayout.LEFT));
        p8.setBounds(50, 200, 300, 30);
        p3.add(p8);

        // 界面的第六部分
        p9.add(b2);
        p9.add(b3);
        // 居中
        p9.setLayout(new FlowLayout(FlowLayout.CENTER));
        p9.setBounds(50, 250, 300, 40);
        p3.add(p9);

        p3.setBorder(BorderFactory.createTitledBorder("学习信息修改"));
        p3.setBounds(130, 180, 400, 300);
        add(p3);

        // 添加按钮事件
        // 查找按钮事件
        b1.addActionListener(e -> {
            String stuName = t2.getText();
            String stuID = t1.getText();
            Connection conn = null;
            ResultSet res = null;
            ResultSet res1 = null;
            Vector<Study> studies = new Vector<>();

            Study study = new Study();
            Student student = new Student();
            student.setStuName(stuName);
            student.setStuID(stuID);

            try {
                conn = DbUtil.getCon();
                res = new StudentDao().search(conn, student);

                while (res.next()) {
                    stuName = res.getString("stuName");
                    res1 = new StudyDao().stuSearch(conn, stuName, stuID);
                    while(res1.next()) {
                        Study study1 = new Study();
                        study1.setNo(res1.getString("cno"));
                        study1.setStuId(res1.getString("stuID"));
                        studies.add(study1);
                    }
                }

                // 创建表格模式，并不允许修改表格
                tableModel = new DefaultTableModel(data, columnName) {
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return false;
                    }
                };

                // 判断是否存在该寝室
                if (!studies.isEmpty()) {
                    JTable table1 = new JTable();
                    table1.getTableHeader().setReorderingAllowed(false);

                    for (Study study1 : studies) {
                        table1 = fillTable(study1);
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
                                t7.setText((String) table.getValueAt(row, 4));
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
            String stuID = t3.getText();
            String stuName = t4.getText();
            String cNo = t5.getText();
            String cName = t6.getText();
            String cGrade = t7.getText();

            Study study = new Study(stuID, cNo, cGrade);
            Connection conn = null;

            try {
                conn = DbUtil.getCon();

                if (StringUtil.isEmpty(cNo)){
                    JOptionPane.showMessageDialog(null, "课程编号不能为空");
                    return;
                }
                if (StringUtil.isEmpty(stuID)){
                    JOptionPane.showMessageDialog(null, "学生编号不能为空");
                    return;
                }
                if (StringUtil.isEmpty(cName)){
                    JOptionPane.showMessageDialog(null, "课程名称不能为空");
                    return;
                }
                if (StringUtil.isEmpty(stuName)){
                    JOptionPane.showMessageDialog(null, "学生名称不能为空");
                    return;
                }
                if (StringUtil.isEmpty(cGrade)){
                    JOptionPane.showMessageDialog(null, "课程成绩不能为空");
                    return;
                }

                if (new StudyDao().update(conn, study) == 1) {
                    JOptionPane.showMessageDialog(null, "学习信息修改成功");
                    dispose();
                    new StudyManageFrm().setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(null, "学习信息修改失败");
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
            String stuID = t3.getText();
            String cNo = t5.getText();
            Connection conn = null;

            try {
                conn = DbUtil.getCon();

                if(new StudyDao().delete(conn, cNo, stuID) == 1) {
                    JOptionPane.showMessageDialog(null, "学习信息删除成功");
                    dispose();
                    new StudyManageFrm().setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "学习信息删除失败");
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
                    t7.setText((String) table.getValueAt(row, 4));
                }
            }
        });
    }
}
