package TurizmAcenteSistemi.View;
import TurizmAcenteSistemi.Helper.*;
import TurizmAcenteSistemi.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JCheckBox chk01,chk02,chk03,chk04,chk05,chk06,chk07;
    private JCheckBox chk11,chk12,chk13,chk14,chk15,chk16,chk17;
    private JPanel pnl_oda_add;
    private JScrollPane scr_oda_add;
    private JTable tbl_oda_list;
    private JComboBox cmb_oda_tip;
    private JTextField fld_oda_yatak;
    private JTextField fld_oda_stok;
    private JCheckBox chk_tv;
    private JCheckBox chk_minibar;
    private JCheckBox chk_oyunkonsolu;
    private JButton btn_oda_add;
    private JTextField fld_oda_m2;
    private JCheckBox chk_kasa;
    private JCheckBox chk_projeksiyon;
    private JComboBox<String> cmb_oda_otel;
    private JTable tbl_rezervasyon_list;
    private JButton btn_rezervasyon;
    private JTextField fld_otel_sehir;
    private JTextField fld_otel_bolge;

    private JCheckBox[] chk_otel_pansiyon={chk01,chk02,chk03,chk04,chk05,chk06,chk07};
    private JCheckBox[] chk_otel_tesis={chk11,chk12,chk13,chk14,chk15,chk16,chk17};
    private JPopupMenu otelMenu;
    private JPopupMenu rezervasyonMenu;


    private DefaultTableModel mdl_otel_list;
    private Object[] row_otel_list;
    private DefaultTableModel mdl_oda_list;
    private Object[] row_oda_list;
    private DefaultTableModel mdl_rezervasyon_list;
    private Object[] row_rezervasyon_list;


    public YonetimGUI()
    {
        add(wrapper);
        setSize(1100,750);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        otelMenu = new JPopupMenu();
        JMenuItem ozelliklerMenu = new JMenuItem("Özellikler");
        JMenuItem fiyatlandirMenu = new JMenuItem("Fiyatlandır");
        otelMenu.add(ozelliklerMenu);
        otelMenu.add(fiyatlandirMenu);

        rezervasyonMenu = new JPopupMenu();
        JMenuItem misafirlerMenu = new JMenuItem("Misafirler");
        rezervasyonMenu.add(misafirlerMenu);

        mdl_otel_list = new DefaultTableModel();
        Object[] col_otel_list = {"ID","Otel Adı","Şehir","Bölge","Adres","Eposta","Telefon Numarası","Yıldız"};
        mdl_otel_list.setColumnIdentifiers(col_otel_list);
        row_otel_list = new Object[col_otel_list.length];
        refreshOtel();
        tbl_otel_list.setModel(mdl_otel_list);
        tbl_otel_list.setComponentPopupMenu(otelMenu);


        mdl_oda_list = new DefaultTableModel();
        Object[] col_oda_list = {"ID","Otel Adı","Oda Tipi","Yatak Sayısı","Stok Sayısı","TV","Minibar","Oyun Konsolu","Kasa","Projeksiyon","Metrekare"};
        mdl_oda_list.setColumnIdentifiers(col_oda_list);
        row_oda_list = new Object[col_oda_list.length];
        refreshOda();
        tbl_oda_list.setModel(mdl_oda_list);
        refreshCmbOtel();

        mdl_rezervasyon_list = new DefaultTableModel();
        Object[] col_rezervasyon_list = {"ID","Oda ID","Ad Soyad","Telefon","E-posta","Rezervasyon Notu"};
        mdl_rezervasyon_list.setColumnIdentifiers(col_rezervasyon_list);
        row_rezervasyon_list = new Object[col_rezervasyon_list.length];
        refreshRezervasyon();
        tbl_rezervasyon_list.setModel(mdl_rezervasyon_list);
        tbl_rezervasyon_list.setComponentPopupMenu(rezervasyonMenu);

        btn_otel_add.addActionListener(e ->
        {
            if(Helper.isFieldEmpty(fld_otel_ad)||Helper.isFieldEmpty(fld_otel_sehir)||Helper.isFieldEmpty(fld_otel_bolge)||Helper.isFieldEmpty(fld_otel_adres)||Helper.isFieldEmpty(fld_otel_eposta)||Helper.isFieldEmpty(fld_otel_telefon)||cmb_otel_yildiz.getSelectedIndex()==0)
                Helper.showMsg("fill");
            else
            {
                String tesis="";
                String pansiyon="";
                for(JCheckBox chk : chk_otel_tesis)
                {
                    if(chk.isSelected())
                        tesis+=chk.getText()+"\n";
                }
                for(JCheckBox chk : chk_otel_pansiyon)
                {
                    if(chk.isSelected())
                        pansiyon+=chk.getText()+"\n";
                }
                if(Otel.add(fld_otel_ad.getText(),fld_otel_sehir.getText(),fld_otel_bolge.getText(),fld_otel_adres.getText(),fld_otel_eposta.getText(),fld_otel_telefon.getText(), Integer.parseInt(cmb_otel_yildiz.getSelectedItem().toString()),pansiyon,tesis))
                {
                    Helper.showMsg("done");
                    refreshOtel();
                    refreshCmbOtel();
                }
                else
                    Helper.showMsg("error");
            }
        });

        tbl_otel_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Point point = e.getPoint();
                int selected_row = tbl_otel_list.rowAtPoint(point);
                tbl_otel_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        ozelliklerMenu.addActionListener(e->
        {
            int select_id = Integer.parseInt(tbl_otel_list.getValueAt(tbl_otel_list.getSelectedRow(),0).toString());
            OtelOzellikleriGUI otelOzellikleriGUI = new OtelOzellikleriGUI(Otel.getFetch(select_id));
        });

        fiyatlandirMenu.addActionListener(e->
        {
            int select_id = Integer.parseInt(tbl_otel_list.getValueAt(tbl_otel_list.getSelectedRow(),0).toString());
            FiyatlandirGUI f = new FiyatlandirGUI(Otel.getFetch(select_id));
        });

        misafirlerMenu.addActionListener(e->
        {
            int select_id = Integer.parseInt(tbl_rezervasyon_list.getValueAt(tbl_rezervasyon_list.getSelectedRow(),0).toString());
            MisafirlerGUI m = new MisafirlerGUI(Misafir.getList(select_id));
        });

        btn_oda_add.addActionListener(e ->
        {
            if(cmb_oda_tip.getSelectedIndex()==0||Helper.isFieldEmpty(fld_oda_stok) ||Helper.isFieldEmpty(fld_oda_yatak) ||Helper.isFieldEmpty(fld_oda_m2))
                Helper.showMsg("fill");
            else
            {
                if(Oda.getFetch(cmb_oda_tip.getSelectedItem().toString(),Otel.getFetch(cmb_oda_otel.getSelectedItem().toString(),true).getId())!=null)
                    Helper.showMsg("Aynı tipteki oda tekrar eklenemez!");
                else if(Oda.add(Otel.getFetch(cmb_oda_otel.getSelectedItem().toString(),true).getId()
                        ,cmb_oda_tip.getSelectedItem().toString()
                        ,Integer.parseInt(fld_oda_stok.getText())
                        ,Integer.parseInt(fld_oda_yatak.getText())
                        ,Integer.parseInt(fld_oda_m2.getText()),
                        chk_tv.isSelected(),
                        chk_minibar.isSelected(),
                        chk_oyunkonsolu.isSelected(),
                        chk_kasa.isSelected(),
                        chk_projeksiyon.isSelected()))
                {
                    Helper.showMsg("done");
                    refreshOda();
                }
            }
        });

        btn_rezervasyon.addActionListener(e ->
        {
            AramaGUI a = new AramaGUI(this);
        });
    }

    public void refreshOtel()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_otel_list.getModel();
        clearModel.setRowCount(0);
        for(Otel obj : Otel.getList())
        {
            int i=0;
            row_otel_list[i++] = obj.getId();
            row_otel_list[i++] = obj.getAd();
            row_otel_list[i++] = obj.getSehir();
            row_otel_list[i++] = obj.getBolge();
            row_otel_list[i++] = obj.getAdres();
            row_otel_list[i++] = obj.getEposta();
            row_otel_list[i++] = obj.getTelefon();
            row_otel_list[i] = obj.getYildiz();
            mdl_otel_list.addRow(row_otel_list);
        }
    }

    public void refreshOda()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_oda_list.getModel();
        clearModel.setRowCount(0);
        for(Oda obj : Oda.getList())
        {
            int i=0;
            row_oda_list[i++] = obj.getId();
            row_oda_list[i++] = obj.getOtel().getAd();
            row_oda_list[i++] = obj.getTip();
            row_oda_list[i++] = obj.getYatak();
            row_oda_list[i++] = obj.getStok();
            row_oda_list[i++] = obj.isTv()?"Var":"Yok";
            row_oda_list[i++] = obj.isMinibar()?"Var":"Yok";
            row_oda_list[i++] = obj.isOyunkonsolu()?"Var":"Yok";
            row_oda_list[i++] = obj.isKasa()?"Var":"Yok";
            row_oda_list[i++] = obj.isProjeksiyon()?"Var":"Yok";
            row_oda_list[i] = obj.getMetrekare();
            mdl_oda_list.addRow(row_oda_list);
        }
    }

    public void refreshRezervasyon()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rezervasyon_list.getModel();
        clearModel.setRowCount(0);
        for(Rezervasyon obj : Rezervasyon.getList())
        {
            int i=0;
            row_rezervasyon_list[i++] = obj.getId();
            row_rezervasyon_list[i++] = obj.getOda_id();
            row_rezervasyon_list[i++] = obj.getAdsoyad();
            row_rezervasyon_list[i++] = obj.getTelefon();
            row_rezervasyon_list[i++] = obj.getEposta();
            row_rezervasyon_list[i] = obj.getNote();
            mdl_rezervasyon_list.addRow(row_rezervasyon_list);
        }
    }

    public void refreshCmbOtel()
    {
        cmb_oda_otel.removeAllItems();
        for(Otel obj : Otel.getList())
            cmb_oda_otel.addItem(obj.getAd());
    }

    public static void main(String[] args)
    {
        Helper.setLayout();
        YonetimGUI yonetimGUI = new YonetimGUI();
    }
}
