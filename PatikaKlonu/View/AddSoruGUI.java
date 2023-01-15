package PatikaKlonu.View;

import PatikaKlonu.Helper.Config;
import PatikaKlonu.Helper.Helper;
import PatikaKlonu.Model.Soru;

import javax.swing.*;

public class AddSoruGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_soru_name;
    private JTextField fld_A;
    private JTextField fld_B;
    private JTextField fld_C;
    private JTextField fld_D;
    private JTextField fld_E;
    private JComboBox cmb_correct;
    private JButton btn_soru_add;
    private JLabel lbl_soru;
    private int i;

    public AddSoruGUI(int soru_sayisi,int i,int quiz_id)
    {
        Helper.setLayout();
        add(wrapper);
        setSize(700,500);
        lbl_soru.setText((soru_sayisi-i+1)+".soru");
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        if(i==0) {
            Helper.showMsg("done");
            dispose();
        }
        btn_soru_add.addActionListener(e->
        {
            if(Soru.add(quiz_id,fld_soru_name.getText(),fld_A.getText(),fld_B.getText(),fld_C.getText(),fld_D.getText(),fld_E.getText(),cmb_correct.getSelectedItem().toString().charAt(0)))
            {
                dispose();
                AddSoruGUI addSoruGUI = new AddSoruGUI(soru_sayisi,i-1,quiz_id);
            }
        });
    }
}
