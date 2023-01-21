package TurizmAcenteSistemi.View;
import TurizmAcenteSistemi.Helper.Helper;
import TurizmAcenteSistemi.Model.Misafir;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MisafirlerGUI extends JFrame
{
    private JPanel wrapper;
    private JTable tbl_misafir_list;

    private DefaultTableModel mdl_misafir_list;
    private Object[] row_misafir_list;

    public MisafirlerGUI(ArrayList<Misafir> misafirler)
    {
        add(wrapper);
        setSize(500,350);
        setLocation(Helper.screenCenterPoint("x",getSize()), Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Misafirler");
        setVisible(true);

        mdl_misafir_list = new DefaultTableModel();
        Object[] col_misafir_list = {"ID","Ad Soyad","Telefon"};
        mdl_misafir_list.setColumnIdentifiers(col_misafir_list);
        row_misafir_list = new Object[col_misafir_list.length];
        tbl_misafir_list.setModel(mdl_misafir_list);
        for(Misafir m : misafirler)
        {
            int i=0;
            row_misafir_list[i++]=m.getId();
            row_misafir_list[i++]=m.getAdsoyad();
            row_misafir_list[i]=m.getTelefon();
            mdl_misafir_list.addRow(row_misafir_list);
        }
    }
}
