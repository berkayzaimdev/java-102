package PatikaKlonu.View;

import PatikaKlonu.Helper.Config;
import PatikaKlonu.Helper.DBConnector;
import PatikaKlonu.Helper.Helper;
import PatikaKlonu.Model.Soru;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SoruCozumGUI extends JFrame{
    private JRadioButton rdb_a;
    private JRadioButton rdb_b;
    private JRadioButton rdb_c;
    private JRadioButton rdb_d;
    private JRadioButton rdb_e;
    private JButton btn_isaretle;
    private JPanel wrapper;
    private JLabel lbl_soru;
    private static int dogru=0;
    private static int yanlis=0;
    private static int bos=0;
    private String cevap;

    public SoruCozumGUI(int quiz_id,int soru_sayisi,int i)
    {
        Helper.setLayout();
        add(wrapper);
        setSize(700,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        String sql = "SELECT * FROM soru WHERE quiz_id = ? ORDER BY id ASC LIMIT 1 OFFSET ?";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1,quiz_id);
            ps.setInt(2,i);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                lbl_soru.setText(rs.getString("soru_name"));
                rdb_a.setText(rs.getString("A"));
                rdb_b.setText(rs.getString("B"));
                rdb_c.setText(rs.getString("C"));
                rdb_d.setText(rs.getString("D"));
                rdb_e.setText(rs.getString("E"));
                cevap=rs.getString(rs.getString("dogru"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        btn_isaretle.addActionListener(e->
        {
            boolean dogrumu=false,yanlismi=false;
            for(Component r : wrapper.getComponents())
                if(r instanceof JRadioButton)
                    if(((JRadioButton) r).isSelected())
                        if(((JRadioButton) r).getText().equals(cevap))
                        {
                            dogru++;
                            dogrumu=true;
                            break;
                        }
                        else
                        {
                            yanlis++;
                            yanlismi=true;
                            break;
                        }
            if(!yanlismi&&!dogrumu)
                bos++;
            System.out.println("d:"+dogru+" y:"+yanlis+" b:"+bos);
            if(soru_sayisi-1==i)
            {
                Helper.showMsg("Doğru sayısı:"+dogru+"\nYanlış Sayısı:"+yanlis+"\nBoş Sayısı:"+bos);
                dogru=0;
                yanlis=0;
                bos=0;
                dispose();
            }
            else
            {
                SoruCozumGUI x = new SoruCozumGUI(quiz_id,soru_sayisi,i+1);
                dispose();
            }
        });
    }

}
