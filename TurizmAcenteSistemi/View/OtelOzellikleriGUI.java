package TurizmAcenteSistemi.View;
import PatikaKlonu.Helper.Config;
import TurizmAcenteSistemi.Helper.Helper;
import TurizmAcenteSistemi.Model.Otel;

import javax.swing.*;

public class OtelOzellikleriGUI extends JFrame
{
    private Otel otel;
    private JPanel wrapper;
    private JLabel lbl_otel_name;
    private JList lst_otel_pansiyon;
    private JList lst_otel_tesis;
    public OtelOzellikleriGUI(Otel otel)
    {
        this.otel=otel;
        add(wrapper);
        setSize(500,350);
        setLocation(Helper.screenCenterPoint("x",getSize()), Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        lbl_otel_name.setText("Otel adı:"+otel.getAd());
        DefaultListModel mdl_otel_tesis = new DefaultListModel();
        DefaultListModel mdl_otel_pansiyon = new DefaultListModel();
        int i=0;
        for(String s : otel.getTesis().split("\n"))
        {
            mdl_otel_tesis.add(i++,s);
        }
        i=0;
        for(String s : otel.getPansiyon().split("\n"))
        {
            mdl_otel_pansiyon.add(i++,s);
        }
        lst_otel_tesis.setModel(mdl_otel_tesis);
        lst_otel_pansiyon.setModel(mdl_otel_pansiyon);
    }
    public static void main(String[] args)
    {
        Helper.setLayout();
    }
}
