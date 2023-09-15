 import java.awt.Dimension;
 import java.awt.Toolkit;
 
 
 public class Application1
 {

   public Application1() {
     MainPage mainPage = new MainPage();
     MainPage.setDefaultLookAndFeelDecorated(true);
     mainPage.validate();

     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     Dimension frameSize = mainPage.getSize();
     if (frameSize.height > screenSize.height) {
       frameSize.height = screenSize.height;
     }
     if (frameSize.width > screenSize.width) {
       frameSize.width = screenSize.width;
     }
     mainPage.setLocation((screenSize.width - frameSize.width) / 2, (
         screenSize.height - frameSize.height) / 2);
     mainPage.setVisible(true);
   }
 }


/* Location:              C:\Users\RedStone\Desktop\物联18级提问记录系统\reverse\InquirySystem.jar!\lib\Application1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */