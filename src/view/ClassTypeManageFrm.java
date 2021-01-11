package view;

import dao.ClassTypeDao;
import model.ClassType;
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

/**
 * 班级维护界面
 */
public class ClassTypeManageFrm extends JFrame {
    JPanel p1= new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();
    // 设置按钮
    JButton button = new JButton("查找");
    JButton b1 = new JButton("修改");
    JButton b2 = new JButton("删除");
    // 设置标签
    JLabel label1 = new JLabel("班级名");
    JLabel label2 = new JLabel("学院");
    JLabel label3 = new JLabel("班级编号");
    JLabel label4 = new JLabel("学院");
    JLabel label5 = new JLabel("班级名");
    JLabel label6 = new JLabel("备注");
    // 设置文本框
    JTextField textField = new JTextField(10);
    JTextField textField1 = new JTextField(10);
    JTextField t1 = new JTextField(10);
    JTextField t2 = new JTextField(14);
    JTextField t3 = new JTextField(14);
    JTextField t4 = new JTextField(20);
    // 设置表格标题
    String[] columnName = {"班级编号", "学院", "班级名", "备注"};
    String[][] data = {};
    // 设置表格
    DefaultTableModel tableModel = new DefaultTableModel(data, columnName){
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    JTable table = new JTable(tableModel);
    // 设置表格滚动条
    JScrollPane jScrollPane = null;
    // 设置图标
    ImageIcon icon = new ImageIcon("src/img/search.png");
    ImageIcon icon1 = new ImageIcon("src/img/modify.png");
    ImageIcon icon2 = new ImageIcon("src/img/delete.png");
    // 设置窗口图标
    Image image = this.getToolkit().getImage("src/img/logo.png");
    // 设置字体
    Font font1 = new Font("宋体", Font.BOLD, 15);
    Font font2 = new Font("宋体", Font.BOLD, 13);

    // 初始化表格
    private JTable fillTable(ClassType classType) throws Exception {
        Connection conn = null;

        table.setPreferredSize(new Dimension(400, 300));
        // 设置表格中列的位置不可拖动
        table.getTableHeader().setReorderingAllowed(false);

        try {
            conn = DbUtil.getCon();
            ResultSet res = new ClassTypeDao().search(conn, classType);
            while (res.next()) {
                tableModel.addRow(new String[]{res.getString("id"), res.getString("college"),
                                               res.getString("className"), res.getString("classDesc")});
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtil.closeCon(conn);
        }

        return table;
    }

    // 获取所有的班级信息
    private JTable getAllClassInfo() throws Exception {
        ClassType classType = new ClassType();
        JTable table1 = null;
        Connection conn = null;
        ResultSet res = null;
        DefaultTableModel tableModel1 = new DefaultTableModel(data, columnName){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        try {
            conn = DbUtil.getCon();
            res = new ClassTypeDao().search(conn, classType);
            while (res.next()) {
                tableModel1.addRow(new String[]{res.getString("id"), res.getString("college"),
                        res.getString("className"), res.getString("classDesc")});
            }
            table1 = new JTable(tableModel1);
            table1.setModel(tableModel1);
        }catch (Exception ex) {
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

        return table1;
    }

    public ClassTypeManageFrm() throws Exception {
        // 设置窗口标题
        setTitle("班级维护");
        setLayout(null);
        // 设置窗口大小
        setSize(530, 470);
        // 设置窗口居中显示
        setLocationRelativeTo(null);
        // 禁用缩放
        setResizable(false);
        // 设置窗口
        this.setIconImage(image);

        // 设置查找框
        label1.setFont(font1);
        label2.setFont(font1);
        textField.setPreferredSize(new Dimension(200, 23));
        button.setIcon(icon);
        button.setFont(font2);
        button.setPreferredSize(new Dimension(90, 23));
        p1.add(label1);
        p1.add(textField);
        p1.add(label2);
        p1.add(textField1);
        p1.add(button);
        p1.setBounds(30, 20, 480, 50);
        add(p1);

        // 设置表格界面
        jScrollPane = new JScrollPane(this.fillTable(new ClassType()));
        jScrollPane.setPreferredSize(new Dimension(400, 100));
        p2.add(jScrollPane);
        p2.setBounds(10, 70, 500, 100);
        add(p2);

        // 设置信息修改界面
        label3.setFont(font1);
        label4.setFont(font1);
        label5.setFont(font1);
        label6.setFont(font1);
        t4.setPreferredSize(new Dimension(200, 50));
        b1.setIcon(icon1);
        b1.setFont(font2);
        b2.setIcon(icon2);
        b2.setFont(font2);
        p3.add(label3);
        p3.add(t1);
        p3.add(label4);
        p3.add(t2);
        p3.add(label5);
        p3.add(t3);
        p3.add(p4);
        p4.add(label6);
        p4.add(t4);
        p4.setPreferredSize(new Dimension(400, 100));
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        p5.add(b1);
        p5.add(b2);
        p5.setLayout(new FlowLayout(FlowLayout.CENTER));
        p5.setPreferredSize(new Dimension(400, 100));
        p3.add(p5, BorderLayout.SOUTH);
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3.setBorder(BorderFactory.createTitledBorder("班级信息修改"));
        p3.setBounds(40, 180, 420, 220);
        add(p3);

        // 设置事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    t1.setText((String)table.getValueAt(row, 0));
                    t2.setText((String)table.getValueAt(row, 1));
                    t3.setText((String)table.getValueAt(row, 2));
                    t4.setText((String)table.getValueAt(row, 3));
                }
            }
        });

        // 查找按钮事件
        button.addActionListener(e -> {
            String className = textField.getText();
            String college = textField1.getText();
            Vector<ClassType> classTypes = new Vector<>();
            ClassType classType = new ClassType();
            classType.setClassName(className);
            classType.setCollege(college);

            Connection conn = null;
            ResultSet res = null;

            try {
                conn = DbUtil.getCon();
                res = new ClassTypeDao().search(conn, classType);

                while (res.next()) {
                    ClassType classType1 = new ClassType();
                    classType1.setClassName(res.getString("className"));
                    classType1.setCollege(res.getString("college"));
                    classTypes.add(classType1);
                }

                // 创建表格模式，并不允许修改表格
                tableModel = new DefaultTableModel(data, columnName){
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return false;
                    }
                };

                if (!classTypes.isEmpty()) {
                    JTable table1 = new JTable();

                    for (ClassType ct: classTypes) {
                        table1 = fillTable(ct);
                    }
                    table1.setModel(tableModel);
                    table1 = new JTable(tableModel);
                    // 设置表格中列的位置不可拖动
                    table1.getTableHeader().setReorderingAllowed(false);
                    table1.setPreferredSize(new Dimension(400, 300));
                    // 重新设置表格的鼠标点击事件
                    table1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            int row = table.getSelectedRow();
                            if (row >= 0) {
                                t1.setText((String)table.getValueAt(row, 0));
                                t2.setText((String)table.getValueAt(row, 1));
                                t3.setText((String)table.getValueAt(row, 2));
                                t4.setText((String)table.getValueAt(row, 3));
                            }
                        }
                    });
                    jScrollPane = new JScrollPane(table1);
                    jScrollPane.setPreferredSize(new Dimension(400, 100));
                    p2.add(jScrollPane);
                    p2.setBounds(10, 70, 500, 100);
                    add(p2);
                }
                else {
                    JOptionPane.showMessageDialog(null, "无此班级，请重新输入");
                }
            } catch (Exception ex) {
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
        b1.addActionListener(e -> {
            try {
                Connection conn = DbUtil.getCon();
                String id = t1.getText();
                String college = t2.getText();
                String className = t3.getText();
                // 为空则提示
                if (StringUtil.isEmpty(id)){
                    JOptionPane.showMessageDialog(null, "班级编号不可为空");
                    return;
                }
                if (!StringUtil.isNumeric(id)) {
                    JOptionPane.showMessageDialog(null, "班级编号必须为数字");
                }
                if (StringUtil.isEmpty(college)){
                    JOptionPane.showMessageDialog(null, "学院不可为空");
                    return;
                }
                if (!StringUtil.isChinese(college)){
                    JOptionPane.showMessageDialog(null, "学院必须为中文");
                    return;
                }
                if (StringUtil.isEmpty(className)){
                    JOptionPane.showMessageDialog(null, "班级名不可为空");
                    return;
                }

                ClassType classType = new ClassType(Integer.parseInt(t1.getText()), t2.getText(), t3.getText(), t4.getText());
                if (new ClassTypeDao().update(conn, classType) == 1){
                    JOptionPane.showMessageDialog(null, "修改成功");
                    dispose();
                    new ClassTypeManageFrm().setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "修改失败");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        b2.addActionListener(e -> {
            String id = t1.getText();
            try {
                Connection conn = DbUtil.getCon();
                if (StringUtil.isEmpty(t1.getText())){
                    JOptionPane.showMessageDialog(null, "请选择班级");
                }
                else if (new ClassTypeDao().delete(conn, id) == 1){
                    JOptionPane.showMessageDialog(null, "删除成功");
                    dispose();
                    new ClassTypeManageFrm().setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "删除失败");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
