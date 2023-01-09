package TurizmAcenteSistemi.View;
import PatikaKlonu.Model.Course;
import TurizmAcenteSistemi.Helper.*;
import TurizmAcenteSistemi.Model.Otel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YonetimGUI extends JFrame
{
    private JPanel wrapper;
    private JTabbedPane tab_yonetim;
    private JPanel pnl_otel_list;
    private JScrollPane scr_otel_list;
    private JTable tbl_otel_list;
    private JPanel pnl_otel_form;
    private JPanel pnl_otel_top;
    private JTextField fld_otel_ad;
    private JTextField fld_otel_adres;
    private JTextField fld_otel_eposta;
    private JTextField fld_otel_telefon;
    private JComboBox cmb_otel_yildiz;
    private JButton btn_otel_add;
    private JPanel pnl_rezervasyon_list;

    private DefaultTableModel mdl_otel_list;
    private Object[] row_otel_list;

    public YonetimGUI()
    {
        add(wrapper);
        setSize(1000,650);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        mdl_otel_list = new DefaultTableModel();
        Object[] col_otel_list = {"ID","Otel Adı","Adres","Eposta","Telefon Numarası","Yıldız"};
        mdl_otel_list.setColumnIdentifiers(col_otel_list);
        row_otel_list = new Object[col_otel_list.length];
        tbl_otel_list.setModel(mdl_otel_list);
        refreshOtel();

        btn_otel_add.addActionListener(e ->
        {
            if(Helper.isFieldEmpty(fld_otel_ad)||Helper.isFieldEmpty(fld_otel_adres)||Helper.isFieldEmpty(fld_otel_eposta)||Helper.isFieldEmpty(fld_otel_telefon))
                Helper.showMsg("fill");
            else
            {
                refreshOtel();
            }
        });
    }

    public void refreshOtel()
    {
        System.out.println("deneme");
        DefaultTableModel clearModel = (DefaultTableModel) tbl_otel_list.getModel();
        clearModel.setRowCount(0);
        for(Otel obj :Otel.getList())
        {
            int i=0;
            row_otel_list[i++] = obj.getId();
            row_otel_list[i++] = obj.getAd();
            row_otel_list[i++] = obj.getAdres();
            row_otel_list[i++] = obj.getEposta();
            row_otel_list[i++] = obj.getTelefon();
            row_otel_list[i] = obj.getYildiz();
            mdl_otel_list.addRow(row_otel_list);
        }
    }

    public static void main(String[] args)
    {
        Helper.setLayout();
        YonetimGUI yonetimGUI = new YonetimGUI();
    }
}
