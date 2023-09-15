import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Frame1BtnStartStopActionAdapter
        implements ActionListener {
    private final QueryPage adapted;

    Frame1BtnStartStopActionAdapter(QueryPage adapted) {
        this.adapted = adapted;
    }


    public void actionPerformed(ActionEvent e) {
        this.adapted.btnStartStopActionPerformed();
    }
}


/* Location:              C:\Users\RedStone\Desktop\物联18级提问记录系统\reverse\InquirySystem.jar!\lib\Frame1_btnStart_actionAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */