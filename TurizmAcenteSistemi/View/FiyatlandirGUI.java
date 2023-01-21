package TurizmAcenteSistemi.View;
import TurizmAcenteSistemi.Helper.*;
import TurizmAcenteSistemi.Model.Otel;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class FiyatlandirGUI extends JFrame
{
    private int id;
    private Otel otel;
    private JPanel wrapper;
    private JPanel pnl_yaz;
    private JPanel pnl_kis;
    private JButton btn_kaydet;
    private JButton xButton;

    public FiyatlandirGUI(Otel otel)
    {
        this.otel=otel;
        add(wrapper);
        setTitle("Deneme");
        System.out.println("deneme");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        pnl_yaz.setLayout(gridBagLayout);
        pnl_kis.setLayout(gridBagLayout);
        pnl_yaz.setBorder(new LineBorder(new Color(0x000000),1));
        GridBagConstraints yazGBC = new GridBagConstraints();
        yazGBC.gridx = 0;
        yazGBC.gridy = 0;
        yazGBC.gridwidth = 1;
        yazGBC.gridheight = 1;
        yazGBC.weightx = 1.0;
        yazGBC.weighty = 1.0;
        yazGBC.fill = GridBagConstraints.HORIZONTAL;
        yazGBC.anchor = GridBagConstraints.WEST;
        yazGBC.insets = new Insets(1, 1, 1, 1);
        pnl_yaz.add(new JLabel("01/01/2023 - 31/05/2023"),yazGBC);
        yazGBC.gridy++;
        pnl_yaz.add(new JLabel("Pansiyon"),yazGBC);
        yazGBC.gridx++;
        pnl_yaz.add(new JLabel("Yetişkin (12+)"),yazGBC);
        yazGBC.gridx++;
        pnl_yaz.add(new JLabel("Çocuk (4-11)"),yazGBC);
        yazGBC.gridy++;
        pnl_kis.setBorder(new LineBorder(new Color(0),1));
        GridBagConstraints kisGBC = new GridBagConstraints();
        kisGBC.gridx = 0;
        kisGBC.gridy = 0;
        kisGBC.gridwidth = 1;
        kisGBC.gridheight = 1;
        kisGBC.weightx = 1.0;
        kisGBC.weighty = 1.0;
        kisGBC.fill = GridBagConstraints.HORIZONTAL;
        kisGBC.anchor = GridBagConstraints.WEST;
        kisGBC.insets = new Insets(1, 1, 1, 1);
        pnl_kis.add(new JLabel("01/06/2023 - 31/12/2023"),kisGBC);
        kisGBC.gridy++;
        pnl_kis.add(new JLabel("Pansiyon"),kisGBC);
        kisGBC.gridx++;
        pnl_kis.add(new JLabel("Yetişkin (12+)"),kisGBC);
        kisGBC.gridx++;
        pnl_kis.add(new JLabel("Çocuk (4-11)"),kisGBC);
        kisGBC.gridy++;
        for (String i : getPansiyon())
        {
            yazGBC.gridx = 0;
            pnl_yaz.add(new JLabel(i),yazGBC);
            yazGBC.gridx++;
            pnl_yaz.add(new JTextField(), yazGBC);
            yazGBC.gridx++;
            pnl_yaz.add(new JTextField(), yazGBC);
            yazGBC.gridy++;
            kisGBC.gridx = 0;
            pnl_kis.add(new JLabel(i),kisGBC);
            kisGBC.gridx++;
            pnl_kis.add(new JTextField(), kisGBC);
            kisGBC.gridx++;
            pnl_kis.add(new JTextField(), kisGBC);
            kisGBC.gridy++;
        }
        setVisible(true);
        btn_kaydet.addActionListener(e->
        {
            boolean bosmu=false;
            Component[] components1 = pnl_yaz.getComponents();
            Component[] components2 = pnl_kis.getComponents();
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
                String kis_yetiskin_fiyat="",kis_cocuk_fiyat="";
                String yaz_yetiskin_fiyat="",yaz_cocuk_fiyat="";
                for (Component component : pnl_kis.getComponents())
                {
                    if (component instanceof JTextField)
                    {
                        JTextField textField = (JTextField) component;
                        GridBagConstraints gbc = gridBagLayout.getConstraints(textField);
                        if (gbc.gridx == 1)
                            kis_yetiskin_fiyat += textField.getText() + "\n";
                        else if (gbc.gridx == 2)
                            kis_cocuk_fiyat += textField.getText() + "\n";
                    }
                }
                for (Component component : pnl_yaz.getComponents())
                {
                    if (component instanceof JTextField)
                    {
                        JTextField textField = (JTextField) component;
                        GridBagConstraints gbc = gridBagLayout.getConstraints(textField);
                        if (gbc.gridx == 1)
                            yaz_yetiskin_fiyat += textField.getText() + "\n";
                        else if (gbc.gridx == 2)
                            yaz_cocuk_fiyat += textField.getText() + "\n";
                    }
                }
                try
                {
                    boolean varmi=false;
                    String sql1= "SELECT * from otelfiyat WHERE otel_id = "+otel.getId();
                    PreparedStatement ps1 = DBConnector.getInstance().prepareStatement(sql1);
                    ResultSet rs = ps1.executeQuery();
                    while(rs.next())
                    {
                        varmi=true;
                        System.out.println("var");
                    }
                    String sql2;
                    if(varmi)
                        sql2 = "UPDATE otelfiyat SET otel_id = ? , kis_yetiskin_fiyat = ? , kis_cocuk_fiyat = ? , yaz_yetiskin_fiyat = ? , yaz_cocuk_fiyat = ? WHERE otel_id = "+otel.getId();
                    else
                        sql2 = "INSERT INTO otelfiyat(otel_id,kis_yetiskin_fiyat,kis_cocuk_fiyat,yaz_yetiskin_fiyat,yaz_cocuk_fiyat) VALUES(?,?,?,?,?)";
                    PreparedStatement ps2 = DBConnector.getInstance().prepareStatement(sql2);
                    ps2.setInt(1,otel.getId());
                    ps2.setString(2,kis_yetiskin_fiyat);
                    ps2.setString(3,kis_cocuk_fiyat);
                    ps2.setString(4,yaz_yetiskin_fiyat);
                    ps2.setString(5,yaz_cocuk_fiyat);
                    if(ps2.executeUpdate()!=-1)
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

        xButton.addActionListener(e->{dispose();});
    }

    public ArrayList<String> getPansiyon()
    {
        ArrayList<String> pansiyon = new ArrayList<>();
        String p = otel.getPansiyon();
        for(String s : p.split("\n"))
            pansiyon.add(s);
        return pansiyon;
    }
}
