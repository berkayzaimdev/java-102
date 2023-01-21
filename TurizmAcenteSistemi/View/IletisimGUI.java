package TurizmAcenteSistemi.View;
import TurizmAcenteSistemi.Helper.*;
import TurizmAcenteSistemi.Model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IletisimGUI extends JFrame
{
    private JPanel wrapper;
    private JTextField fld_adsoyad;
    private JTextField fld_telefon;
    private JTextField fld_eposta;
    private JTextArea fld_not;
    private JPanel pnl_misafir_bilgileri;
    private JButton btn_kaydet;
    private JPanel pnl_top;

    public IletisimGUI(YonetimGUI main,int y,int c,int oda_id)
    {
        add(wrapper);
        setSize(700,450);
        Helper.setLayout();
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);

        GridBagLayout gridBagLayout = new GridBagLayout();
        pnl_misafir_bilgileri.setLayout(gridBagLayout);
        pnl_misafir_bilgileri.setBorder(new LineBorder(new Color(0),1));
        GridBagConstraints misafirGBC = new GridBagConstraints();
        misafirGBC.gridx = 0;
        misafirGBC.gridy = 0;
        misafirGBC.gridwidth = 1;
        misafirGBC.gridheight = 1;
        misafirGBC.weightx = 1.0;
        misafirGBC.weighty = 1.0;
        misafirGBC.fill = GridBagConstraints.HORIZONTAL;
        misafirGBC.anchor = GridBagConstraints.WEST;
        misafirGBC.insets = new Insets(1, 1, 1, 1);
        pnl_misafir_bilgileri.add(new JLabel("Misafir Bilgileri"),misafirGBC);
        misafirGBC.gridx++;
        pnl_misafir_bilgileri.add(new JLabel("Ad Soyad"),misafirGBC);
        misafirGBC.gridx++;
        pnl_misafir_bilgileri.add(new JLabel("Telefon Numarası"),misafirGBC);
        misafirGBC.gridy++;
        for (int i=1;i<=y;i++)
        {
            misafirGBC.gridx = 0;
            pnl_misafir_bilgileri.add(new JLabel(i+".Yetişkin"),misafirGBC);
            misafirGBC.gridx++;
            pnl_misafir_bilgileri.add(new JTextField(), misafirGBC);
            misafirGBC.gridx++;
            pnl_misafir_bilgileri.add(new JTextField(), misafirGBC);
            misafirGBC.gridy++;
        }
        for (int i=1;i<=c;i++)
        {
            misafirGBC.gridx = 0;
            pnl_misafir_bilgileri.add(new JLabel(i+".Çocuk"),misafirGBC);
            misafirGBC.gridx++;
            pnl_misafir_bilgileri.add(new JTextField(), misafirGBC);
            misafirGBC.gridx++;
            pnl_misafir_bilgileri.add(new JTextField(), misafirGBC);
            misafirGBC.gridy++;
        }
        setVisible(true);

        btn_kaydet.addActionListener(e ->
        {
            boolean bosmu=false;
            Component[] components1 = pnl_misafir_bilgileri.getComponents();
            Component[] components2 = pnl_top.getComponents();
            int totalLength = components1.length + components2.length;
            Component[] allComponents = new Component[totalLength];
            System.arraycopy(components1, 0, allComponents, 0, components1.length);
            System.arraycopy(components2, 0, allComponents, components1.length, components2.length);
            for(Component tf : allComponents)
            {
                if(tf instanceof JTextField)
                    if(Helper.isFieldEmpty((JTextField) tf))
                    {
                        bosmu=true;
                        Helper.showMsg("fill");
                        break;
                    }
            }
            if(!bosmu)
            {
                try
                {
                    if(Rezervasyon.add(oda_id,fld_adsoyad.getText(),fld_telefon.getText(),fld_eposta.getText(),fld_not.getText()))
                    {
                        String adsoyad="";
                        for (Component ct : pnl_misafir_bilgileri.getComponents())
                        {
                            String sql2= "SELECT id from rezervasyon WHERE oda_id = "+oda_id;
                            PreparedStatement ps2 = DBConnector.getInstance().prepareStatement(sql2);
                            ResultSet rs2 = ps2.executeQuery();
                            int rezervasyon_id=0;
                            while(rs2.next())
                                rezervasyon_id=rs2.getInt("id");
                            if (ct instanceof JTextField)
                            {
                                JTextField textField = (JTextField) ct;
                                GridBagConstraints gbc = gridBagLayout.getConstraints(textField);
                                if (gbc.gridx == 1)
                                    adsoyad = textField.getText();
                                else if (gbc.gridx == 2)
                                    Misafir.add(rezervasyon_id,adsoyad,textField.getText());
                            }
                        }
                        Helper.showMsg("done");
                        dispose();
                        main.refreshRezervasyon();
                    }
                    else
                        Helper.showMsg("error");
                }
                catch(SQLException er)
                {
                    er.printStackTrace();
                }
            }
        });
    }
}
