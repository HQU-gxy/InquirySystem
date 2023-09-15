import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class QueryPage extends JFrame {
    JDialog jdialog;
    JPanel contentPane;
    JLabel lblTimeDis = new JLabel();
    JLabel txtNameDis = new JLabel();
    JLabel scoreLab = new JLabel();
    JTextField scoreField = new JTextField();
    JButton btnStartStop = new JButton();
    JButton btnSub = new JButton();
    ImageIcon image = new ImageIcon("aa.png");
    public static final ArrayList<String> arrName = new ArrayList<>();
    public static final ArrayList<String> arrName2 = new ArrayList<>();

    Timer ti;

    public QueryPage() {
        this.ti = new Timer(1, e -> {
            Random ran = new Random();
            int size = QueryPage.arrName.size();
            String[] values = new String[size];
            for (int i = 0; i < size; i++) {
                values[i] = QueryPage.arrName.get(i);
            }
            int a = Math.abs(ran.nextInt() % values.length);
            QueryPage.this.txtNameDis.setText(values[a]);
        });
        try {
            Connection con = (new connection()).getCon();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select xuehao,name from student");
            while (rs.next()) {
                String id = rs.getString("xuehao");
                String name = rs.getString("name");
                arrName.add(id + name);
                arrName2.add(name);
            }
            s.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.btnSub.addActionListener(e -> {
            boolean result = false;
            if (QueryPage.this.scoreField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(QueryPage.this.jdialog, "分数不能为空");
                result = true;
            }
            if (!result)
                try {
                    int score = Integer.parseInt(QueryPage.this.scoreField.getText().trim());
                    String txtname1 = QueryPage.this.txtNameDis.getText().trim();
                    String txtname = txtname1.replaceAll("[0-9]", "");
                    Connection con = (new connection()).getCon();
                    PreparedStatement pstmt0 = con.prepareStatement("select Attend,Absent,Mark from student where name=?");
                    pstmt0.setString(1, txtname);
                    ResultSet rs = pstmt0.executeQuery();
                    int total = 0;
                    int mark = 0;
                    while (rs.next()) {
                        total = rs.getInt(2);
                        mark = rs.getInt(3);
                    }
                    PreparedStatement pstmt = con.prepareStatement("update student set Attend =?,Absent=?,Mark=? where name=?");
                    pstmt.setInt(1, (total + score) / (mark + 1));
                    pstmt.setInt(2, total + score);
                    pstmt.setInt(3, mark + 1);
                    pstmt.setString(4, txtname);
                    pstmt.executeUpdate();
                    QueryPage.this.setVisible(false);
                    JOptionPane.showMessageDialog(QueryPage.this.contentPane, "添加学生信息成功");

                } catch (NumberFormatException | SQLException e1) {
                    e1.printStackTrace();
                }

        });
    }

    private void jbInit() {
        this.contentPane = (JPanel) getContentPane();

        this.contentPane.setLayout(null);

        setSize(new Dimension(400, 300));

        setTitle("随机提问");

        getTime();

        setIconImage(this.image.getImage());

        this.lblTimeDis.setFont(new Font("Dialog", Font.PLAIN, 15));
        this.lblTimeDis.setBounds(new Rectangle(30, 24, 320, 15));
        this.lblTimeDis.setText(getTime());

        this.txtNameDis.setFont(new Font("Dialog", Font.BOLD, 35));
        this.txtNameDis.setText("快开始吧🙃");
        this.txtNameDis.setBounds(new Rectangle(44, 68, 320, 30));
        this.txtNameDis.setVisible(true);

        this.btnStartStop.setBounds(new Rectangle(150, 130, 90, 30));
        this.btnStartStop.setFont(new Font("Dialog", Font.PLAIN, 20));
        this.btnStartStop.setText("开始");
        this.btnStartStop.addActionListener(new Frame1BtnStartStopActionAdapter(this));

        this.scoreLab.setFont(new Font("Dialog", Font.BOLD, 20));
        this.scoreLab.setText("计分：");
        this.scoreLab.setBounds(new Rectangle(100, 170, 79, 30));

        this.scoreField.setFont(new Font("Dialog", Font.BOLD, 18));
        this.scoreField.setText("");
        this.scoreField.setBounds(new Rectangle(150, 170, 130, 30));

        this.btnSub.setBounds(new Rectangle(150, 210, 90, 30));
        this.btnSub.setFont(new Font("Dialog", Font.PLAIN, 20));
        this.btnSub.setText("记录");

        this.contentPane.add(this.txtNameDis);
        this.contentPane.add(this.btnStartStop);
        this.contentPane.add(this.btnSub);
        this.contentPane.add(this.scoreLab);
        this.contentPane.add(this.scoreField);
        this.contentPane.add(this.lblTimeDis);

        Timer tiTime = new Timer(1000, e -> QueryPage.this.lblTimeDis.setText(QueryPage.this.getTime()));
        tiTime.start();
    }


    public String getTime() {
        Calendar c = Calendar.getInstance();
        String year = Integer.toString(c.get(Calendar.YEAR));
        String month = Integer.toString(c.get(Calendar.MONTH));
        String date = Integer.toString(c.get(Calendar.DATE));
        String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        String minute = Integer.toString(c.get(Calendar.MINUTE));
        String second = Integer.toString(c.get(Calendar.SECOND));
        return "现在时刻北京时间" + year + "年" + month + "月" + date + "日" +
                hour + "时" + minute + " 分" + second + "秒";
    }

    public void btnStartStopActionPerformed() {
        if (this.btnStartStop.getText().equals("开始")) {
            this.btnStartStop.setText("结束");
            this.ti.start();
        } else {
            this.btnStartStop.setText("开始");
            this.ti.stop();
        }
    }


}


/* Location:              C:\Users\RedStone\Desktop\物联18级提问记录系统\reverse\InquirySystem.jar!\lib\Frame1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */