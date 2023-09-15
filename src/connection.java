
import net.ucanaccess.jdbc.UcanaccessDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class connection {
    public Connection getCon() {
        try {
            String path = new java.io.File("Student.mdb").getAbsolutePath();
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            return DriverManager.getConnection(UcanaccessDriver.URL_PREFIX + path);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库连接失败");
            return null;
        }
    }
}


/* Location:               C:\Users\RedStone\Desktop\物联18级提问记录系统\reverse\InquirySystem.jar!\lib\connection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */