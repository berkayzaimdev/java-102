package TurizmAcenteSistemi.View;

import TurizmAcenteSistemi.Helper.*;
import TurizmAcenteSistemi.Model.*;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
    private JLabel lbl_fiyat;
    private JLabel lbl_giris_tarihi;
    private JLabel lbl_cikis_tarihi;
    private JLabel lbl_oda_ozellikleri;
    private Otel o;
    private String donem;

    public RezervasyonGUI(Otel o,String y,String c,String giris,String cikis)
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
        lbl_otel_telefon.setText(o.getTelefon());
        lbl_otel_adres.setText(o.getAdres());
        lbl_giris_tarihi.setText("Giriş tarihi:"+giris);
        lbl_cikis_tarihi.setText("Çıkış tarihi:"+cikis);
        lbl_otel_tesis.setText("+"+String.join(" +",o.getTesis().split("\n")));
        lbl_yetiskin_sayisi.setText("Yetişkin sayısı:"+y);
        lbl_cocuk_sayisi.setText("Çocuk sayısı:"+c);

        LocalDate girisdate = LocalDate.parse(giris, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long days = ChronoUnit.DAYS.between(girisdate,LocalDate.parse(cikis, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        lbl_kalinacak_gun.setText(days+" gün için.");
        LocalDate startDate = LocalDate.of(girisdate.getYear(), 1, 1);
        LocalDate endDate = LocalDate.of(girisdate.getYear(), 5, 31);

        if (girisdate.isAfter(startDate) && girisdate.isBefore(endDate) || girisdate.isEqual(startDate) || girisdate.isEqual(endDate))
            donem="kis";
        else
            donem="yaz";
        String sql = "SELECT * from otelfiyat WHERE otel_id = "+o.getId();
        String yetiskinfiyat = "0\n0\n0\n0\n0\n0\n0\n0\n",cocukfiyat="0\n0\n0\n0\n0\n0\n0\n0\n";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                System.out.println("denemeee");
                yetiskinfiyat=rs.getString(donem+"_yetiskin_fiyat");
                cocukfiyat=rs.getString(donem+"_cocuk_fiyat");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        String finalYetiskinfiyat = yetiskinfiyat;
        String finalCocukfiyat = cocukfiyat;

        cmb_otel_pansiyon.addItemListener(e ->
        {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                lbl_fiyat.setText("Fiyat:"+((Integer.parseInt(y)*Integer.parseInt(finalCocukfiyat.split("\n")[cmb_otel_pansiyon.getSelectedIndex()]))+(Integer.parseInt(y)*Integer.parseInt(finalYetiskinfiyat.split("\n")[cmb_otel_pansiyon.getSelectedIndex()])))*Integer.parseInt(Long.toString(days))+" TL");
            }
        });

        cmb_oda_tip.addItemListener(e ->
        {
            if(e.getStateChange() == ItemEvent.SELECTED)
            {
                String sql0 = "SELECT * from oda WHERE otel_id = "+o.getId()+" AND tip = ?";
                try
                {
                    PreparedStatement ps0 = DBConnector.getInstance().prepareStatement(sql0);
                    ps0.setString(1,cmb_oda_tip.getSelectedItem().toString());
                    ResultSet rs0 = ps0.executeQuery();
                    while(rs0.next())
                    {
                        int yatak=rs0.getInt("yatak");
                        String tv = rs0.getBoolean("tv") ? "Var" : "Yok";
                        String minibar = rs0.getBoolean("minibar") ? "Var" : "Yok";
                        String oyunkonsolu = rs0.getBoolean("oyunkonsolu") ? "Var" : "Yok";
                        String kasa = rs0.getBoolean("kasa") ? "Var" : "Yok";
                        String projeksiyon = rs0.getBoolean("projeksiyon") ? "Var" : "Yok";
                        int metrekare=rs0.getInt("metrekare");
                        lbl_oda_ozellikleri.setText("Yatak sayısı:"+yatak
                                +" TV:"+tv
                                +" Minibar:"+minibar
                                +" Oyun Konsolu:"+oyunkonsolu
                                +" Kasa:"+kasa
                                +" Projeksiyon:"+projeksiyon
                                +" Oda Boyutu:"+metrekare+" m2");
                    }
                }
                catch(SQLException er)
                {
                    er.printStackTrace();
                }

            }
        });

        btn_rezervasyon.addActionListener(e->
        {
            if(cmb_oda_tip.getSelectedItem() == null)
            {
                Helper.showMsg("fill");
            }
            else
            {
                String sql2 = "SELECT stok from oda WHERE otel_id = "+o.getId()+" AND tip = ?";
                String sql3;
                try
                {
                    PreparedStatement ps2 = DBConnector.getInstance().prepareStatement(sql2);
                    ps2.setString(1,cmb_oda_tip.getSelectedItem().toString());
                    ResultSet rs2 = ps2.executeQuery();
                    int stok=0;
                    while(rs2.next())
                    {
                        System.out.println("stok bulundu");
                        stok=rs2.getInt("stok");
                    }
                    if(stok>1)
                        sql3="UPDATE oda SET stok = "+(stok-1)+" WHERE otel_id = "+o.getId()+" AND tip = ?";
                    else
                        sql3="DELETE FROM oda WHERE otel_id = "+o.getId()+" AND tip = ?";
                    PreparedStatement ps3 = DBConnector.getInstance().prepareStatement(sql3);
                    ps3.setString(1,cmb_oda_tip.getSelectedItem().toString());
                    if(ps3.executeUpdate()!=-1)
                    {
                        dispose();
                        IletisimGUI i = new IletisimGUI();
                    }
                }
                catch(SQLException er)
                {
                    er.printStackTrace();
                }
            }
        });

        loadOdaCombo();
        loadPansiyonCombo();
        setVisible(true);
    }

    public void loadOdaCombo()
    {
        cmb_oda_tip.removeAllItems();
        for(Oda obj : Oda.getList(o.getId()))
            cmb_oda_tip.addItem(obj.getTip());
    }

    public void loadPansiyonCombo()
    {
        cmb_otel_pansiyon.removeAllItems();
        for(String obj : o.getPansiyon().split("\n"))
            cmb_otel_pansiyon.addItem(obj);
    }

    public int fiyatHesapla()
    {
        int total = 0;
        return total;
    }

}
