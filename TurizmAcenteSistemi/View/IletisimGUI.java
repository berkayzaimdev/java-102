package TurizmAcenteSistemi.View;
import TurizmAcenteSistemi.Helper.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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

    public IletisimGUI(int y,int c,int oda_id)
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
                    String sql= "INSERT INTO rezervasyon(oda_id,adsoyad,telefon,eposta,not) VALUES(?,?,?,?,?)";
                    PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
                    ps.setInt(1,oda_id);
                    ps.setString(2,fld_adsoyad.getText());
                    ps.setString(3,fld_telefon.getText());
                    ps.setString(4,fld_eposta.getText());
                    ps.setString(5,fld_not.getText());
                    if(ps.executeUpdate()!=-1)
                    {
                        Helper.showMsg("done");
                        dispose();
                    }
                }
                catch(SQLException er)
                {
                    er.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args)
    {
        Helper.setLayout();
        IletisimGUI i = new IletisimGUI(2,3,2);
    }
}
