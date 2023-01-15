package PatikaKlonu.View;

import PatikaKlonu.Helper.Config;
import PatikaKlonu.Helper.Helper;
import PatikaKlonu.Model.User;

import javax.swing.*;

public class KayitOlGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JButton btn_kayit;

    public KayitOlGUI()
    {
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Kayıt Ol");
        setResizable(false);
        setVisible(true);
        btn_kayit.addActionListener(e ->
        {
            if(Helper.isFieldEmpty(fld_user_name)||Helper.isFieldEmpty(fld_user_uname)||Helper.isFieldEmpty(fld_user_pass))
                Helper.showMsg("fill");
            else
            {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                if(User.add(name,uname,pass,"student"))
                {
                    Helper.showMsg("done");
                    LoginGUI l = new LoginGUI();
                    dispose();
                }
            }
        });
    }
}
