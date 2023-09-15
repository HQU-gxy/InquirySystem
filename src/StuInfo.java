import org.jdesktop.layout.GroupLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StuInfo extends JFrame {
    ImageIcon image = new ImageIcon("aa.png");

    public StuInfo() {
        JScrollPane jScrollPane1 = new JScrollPane();
        setIconImage(this.image.getImage());
        setTitle("显示全部的学生信息");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationByPlatform(true);

        Object[][] content = new Object[100][6];

        try {
            Connection con = (new connection()).getCon();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from student");

            int i = 0;
            while (rs.next()) {
                content[i][0] = rs.getString(1);
                content[i][1] = rs.getString(2);
                content[i][2] = rs.getString(3);
                content[i][3] = rs.getString(7);
                content[i][4] = rs.getInt(4);
                content[i][5] = rs.getInt(6);
                i++;
            }
            s.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        DefaultTableModel model = new DefaultTableModel(content, new String[]{"学号", "班级", "姓名", "性别", "平均分", "点名次数"});
        JTable jTable1 = new JTable(model);
        jScrollPane1.setViewportView(jTable1);

        GroupLayout gl = new GroupLayout(getContentPane());
        getContentPane().setLayout(gl);
        gl.setHorizontalGroup(gl.createParallelGroup(1)
                .add(gl.createSequentialGroup().addContainerGap()
                        .add(jScrollPane1, -1, 500, 32767)
                        .addContainerGap()));
        gl.setVerticalGroup(gl.createParallelGroup(1)
                .add(gl.createSequentialGroup().addContainerGap()
                        .add(jScrollPane1, -2, 400, 32767)
                        .addContainerGap()));
        pack();
    }
}


/* Location:              C:\Users\RedStone\Desktop\物联18级提问记录系统\reverse\InquirySystem.jar!\lib\StuInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */