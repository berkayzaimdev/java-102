package PatikaKlonu.View;

import PatikaKlonu.Helper.Config;
import PatikaKlonu.Helper.Helper;
import PatikaKlonu.Model.Course;
import PatikaKlonu.Model.Icerik;
import PatikaKlonu.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EducatorGUI extends JFrame
{
    private JPanel wrapper;
    private JLabel lbl_educator_welcome;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_course_list;
    private JTable tbl_course_list;
    private JTable tbl_icerik_list;
    private JComboBox cmb_course_name;
    private JTextField fld_icerik_searchname;
    private JButton btn_icerik_search;
    private JTextField fld_icerik_name;
    private JTextField fld_icerik_aciklama;
    private JTextField fld_icerik_link;
    private JButton btn_icerik_add;
    private JComboBox cmb_course_list;

    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;

    private DefaultTableModel mdl_icerik_list;
    private Object[] row_icerik_list;


    private User u;
    public EducatorGUI(User u)
    {
        this.u=u;
        Helper.setLayout();
        add(wrapper);
        lbl_educator_welcome.setText("Hoşgeldiniz "+u.getName());
        setSize(700,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        mdl_course_list = new DefaultTableModel();
        Object[] col_course_list = {"ID","Ders Adı","Programlama Dili"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        tbl_course_list.setModel(mdl_course_list);
        refreshCourseList();

        mdl_icerik_list = new DefaultTableModel();
        Object[] col_icerik_list = {"ID","Ders Adı","İçerik Adı","İçerik Açıklaması","YouTube linki"};
        mdl_icerik_list.setColumnIdentifiers(col_icerik_list);
        row_icerik_list = new Object[col_icerik_list.length];
        tbl_icerik_list.setModel(mdl_icerik_list);
        refreshIcerikList();

        for(Course c : Course.getListByUser(u.getId()))
        {
            cmb_course_name.addItem(c.getName());
            cmb_course_list.addItem(c.getName());
        }
        btn_icerik_add.addActionListener(e ->
        {
            if(Helper.isFieldEmpty(fld_icerik_name)||Helper.isFieldEmpty(fld_icerik_aciklama)||Helper.isFieldEmpty(fld_icerik_link)||cmb_course_list.getSelectedIndex()==0)
                Helper.showMsg("fill");
            else
            {
                if(Icerik.add(fld_icerik_name.getText(),fld_icerik_aciklama.getText(),fld_icerik_link.getText(),cmb_course_list.getSelectedItem().toString()))
                {
                    Helper.showMsg("done");
                    refreshIcerikList();
                }
            }
        });
    }

    public void refreshCourseList()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        for(Course c : Course.getListByUser(u.getId()))
        {
            int i=0;
            row_course_list[i++]=c.getId();
            row_course_list[i++]=c.getName();
            row_course_list[i]=c.getLang();
            mdl_course_list.addRow(row_course_list);
        }
    }

    public void refreshIcerikList()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_icerik_list.getModel();
        clearModel.setRowCount(0);
        for(Icerik i : Icerik.getList())
        {
            int j=0;
            row_icerik_list[j++]=i.getId();
            row_icerik_list[j++]=Course.getFetch(i.getCourse_id()).getName();
            row_icerik_list[j++]=i.getBaslik();
            row_icerik_list[j++]=i.getAciklama();
            row_icerik_list[j]=i.getLink();
            mdl_icerik_list.addRow(row_icerik_list);
        }
    }
}
