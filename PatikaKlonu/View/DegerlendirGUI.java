package PatikaKlonu.View;

import PatikaKlonu.Helper.Helper;

import javax.swing.*;
import java.awt.*;

public class DegerlendirGUI extends JFrame
{
    private JPanel wrapper;
    private JTextArea textArea1;
    private JComboBox cmb_yildiz;
    private JLabel lbl_yildiz;
    private JButton btn_gonder;
    public DegerlendirGUI()
    {
        lbl_yildiz.setFont(new Font("Calibri",1,22));
        lbl_yildiz.setAlignmentY(CENTER_ALIGNMENT);
        Helper.setLayout();
        add(wrapper);
        setSize(400,300);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Değerlendirme");
        setResizable(false);
        setVisible(true);
        cmb_yildiz.addActionListener(e->
        {
            lbl_yildiz.setText("");
            for(int i=0;i<Integer.parseInt(cmb_yildiz.getSelectedItem().toString());i++)
                lbl_yildiz.setText(lbl_yildiz.getText()+"*");
        });
        btn_gonder.addActionListener(e->
        {
            if(textArea1.getText().equals(""))
                Helper.showMsg("fill");
            else
            {
                Helper.showMsg("done");
                dispose();
            }
        });
    }

    public static void main(String[] args)
    {
        Helper.setLayout();
        DegerlendirGUI dg = new DegerlendirGUI();
    }
}
