package TurizmAcenteSistemi.View;
import TurizmAcenteSistemi.Helper.*;
import TurizmAcenteSistemi.Model.Otel;

import javax.swing.*;

public class AramaGUI extends JFrame
{
    private JPanel wrapper;
    private JTextField fld_otel_ad;
    private JTextField fld_otel_giris;
    private JTextField fld_otel_cikis;
    private JButton araButton;
    private JSpinner spn_yetiskin_sayisi;
    private JSpinner spn_cocuk_sayisi;

    public AramaGUI(YonetimGUI main)
    {
        add(wrapper);
        Helper.setLayout();
        setLocation(Helper.screenCenterPoint("x",getSize()), Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setSize(350,320);
        setVisible(true);

        araButton.addActionListener(e ->
        {
            if(Helper.isFieldEmpty(fld_otel_ad)||Helper.isFieldEmpty(fld_otel_giris)||Helper.isFieldEmpty(fld_otel_cikis))
                Helper.showMsg("fill");
            else if(Integer.parseInt(spn_yetiskin_sayisi.getValue().toString())<0||Integer.parseInt(spn_cocuk_sayisi.getValue().toString())<0)
                Helper.showMsg("negative");
            else if(Integer.parseInt(spn_yetiskin_sayisi.getValue().toString())+Integer.parseInt(spn_cocuk_sayisi.getValue().toString())==0)
                Helper.showMsg("zero");
            else
            {
                Otel o = Otel.getFetch(fld_otel_ad.getText(),true,true);
                if(o==null)
                    Helper.showMsg("null");
                else
                {
                    dispose();
                    RezervasyonGUI r = new RezervasyonGUI(main,o,spn_yetiskin_sayisi.getValue().toString(),spn_cocuk_sayisi.getValue().toString(),fld_otel_giris.getText(),fld_otel_cikis.getText());
                }
            }
        });
    }
}
