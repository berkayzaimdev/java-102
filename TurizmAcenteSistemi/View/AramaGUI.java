package TurizmAcenteSistemi.View;
import TurizmAcenteSistemi.Helper.*;
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

    public AramaGUI()
    {
        add(wrapper);
        Helper.setLayout();
        setLocation(TurizmAcenteSistemi.Helper.Helper.screenCenterPoint("x",getSize()), TurizmAcenteSistemi.Helper.Helper.screenCenterPoint("y",getSize()));
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
        });
    }
}
