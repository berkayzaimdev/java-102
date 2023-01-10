package PatikaKlonu.View;

import PatikaKlonu.Helper.Config;
import PatikaKlonu.Helper.Helper;
import PatikaKlonu.Model.User;

import javax.swing.*;

public class StudentGUI extends JFrame
{
    private JPanel wrapper;
    private User u;
    public StudentGUI(User u)
    {
        this.u=u;
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
    }
}
