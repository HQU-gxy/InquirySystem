import java.awt.*;
import javax.swing.*;

public class MainPage extends JFrame {
    JFrame frame;
    ImageIcon image = new ImageIcon("aa.png");
    JButton buttonExit;
    JButton buttonQuery;
    JButton buttonChart;
    JPanel panel;

    public MainPage() {
        super("提问记录系统");
        setIconImage(this.image.getImage());
        this.buttonQuery = new JButton("提问");
        this.buttonExit = new JButton("退出系统");
        this.buttonChart = new JButton("学生信息总表");
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.buttonQuery.setBounds(40, 20, 200, 40);
        this.buttonChart.setBounds(40, 80, 200, 40);
        this.buttonExit.setBounds(40, 140, 200, 40);

        this.buttonQuery.addActionListener(e -> {
            QueryPage.setDefaultLookAndFeelDecorated(true);
            QueryPage queryPage = new QueryPage();
            queryPage.validate();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = queryPage.getSize();
            if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
            }
            queryPage.setLocation((screenSize.width - frameSize.width) / 2, (
                    screenSize.height - frameSize.height) / 2);
            queryPage.setVisible(true);
        });


        this.buttonChart.addActionListener(e -> (new StuInfo()).setVisible(true));
        this.buttonExit.addActionListener(e -> {
            JDialog dialog = new JDialog(MainPage.this.frame, true);
            JOptionPane.showMessageDialog(dialog, "   感谢使用!  ");
            System.exit(0);
        });

        this.panel.add(this.buttonQuery);
        this.panel.add(this.buttonChart);
        this.panel.add(this.buttonExit);
        getContentPane().add(this.panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 240);
        setVisible(true);
        setLocation(500, 200);
    }

    public static void main(String[] args) {
        new Application1();
    }
}


/* Location:              C:\Users\RedStone\Desktop\物联18级提问记录系统\reverse\InquirySystem.jar!\lib\Mainpage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */