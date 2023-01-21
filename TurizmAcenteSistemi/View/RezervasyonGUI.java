package TurizmAcenteSistemi.View;

import TurizmAcenteSistemi.Helper.*;
import TurizmAcenteSistemi.Model.*;

import javax.swing.*;

public class RezervasyonGUI extends JFrame
{
    private JPanel wrapper;
    private JButton btn_rezervasyon;
    private JComboBox cmb_oda_tip;
    private JComboBox cmb_otel_pansiyon;
    private JLabel lbl_otel_ad;
    private JLabel lbl_otel_sehir;
    private JLabel lbl_otel_bolge;
    private JLabel lbl_otel_telefon;
    private JLabel lbl_otel_adres;
    private JLabel lbl_otel_yildiz;
    private JLabel lbl_yetiskin_sayisi;
    private JLabel lbl_cocuk_sayisi;
    private JLabel lbl_kalinacak_gun;
    private JLabel lbl_otel_tesis;
    private Otel o;

    public RezervasyonGUI(Otel o,String y,String c,String d)
    {
        add(wrapper);
        this.o=o;
        setSize(700,450);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        lbl_otel_ad.setText(o.getAd());
        lbl_otel_yildiz.setText(o.getYildiz()+" Yıldızlı");
        lbl_otel_sehir.setText(o.getSehir());
        lbl_otel_bolge.setText(o.getBolge());
        lbl_otel_adres.setText(o.getAdres());
        lbl_otel_tesis.setText("+"+String.join(" +",o.getTesis().split("\n")));
        lbl_yetiskin_sayisi.setText(y);
        lbl_cocuk_sayisi.setText(c);
        lbl_kalinacak_gun.setText(d);
        loadOdaCombo();
        loadPansiyonCombo();
        setVisible(true);
    }

    public void loadOdaCombo()
    {
        cmb_oda_tip.removeAllItems();
        for(Oda obj : Oda.getList(o.getId()))
            cmb_oda_tip.addItem(new Item(obj.getId(),obj.getTip()));
    }

    public void loadPansiyonCombo()
    {
        cmb_otel_pansiyon.removeAllItems();
        for(String obj : o.getPansiyon().split("\n"))
            cmb_oda_tip.addItem(obj);
    }
}
