package PatikaKlonu.View;

import PatikaKlonu.Helper.Config;
import PatikaKlonu.Helper.Helper;
import PatikaKlonu.Model.*;

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
    private JTable tbl_quiz_list;
    private JComboBox cmb_icerik_list;
    private JTextField fld_soru_sayisi;
    private JButton btn_quiz_add;

    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;

    private DefaultTableModel mdl_icerik_list;
    private Object[] row_icerik_list;

    private DefaultTableModel mdl_quiz_list;
    private Object[] row_quiz_list;

    private User u;
    public EducatorGUI(User u)
    {
        for(Course c : Course.getListByUser(u.getId()))
        {
            cmb_course_name.addItem(c.getName());
            cmb_course_list.addItem(c.getName());
        }
        for(Icerik i : Icerik.getList())
            cmb_icerik_list.addItem(i.getBaslik());

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

        mdl_quiz_list = new DefaultTableModel();
        Object[] col_quiz_list = {"ID","İçerik Adı","Quiz","Soru Sayısı"};
        mdl_quiz_list.setColumnIdentifiers(col_quiz_list);
        row_quiz_list = new Object[col_quiz_list.length];
        tbl_quiz_list.setModel(mdl_quiz_list);
        refreshQuizList();

        btn_icerik_add.addActionListener(e ->
        {
            if(Helper.isFieldEmpty(fld_icerik_name)||Helper.isFieldEmpty(fld_icerik_aciklama)||Helper.isFieldEmpty(fld_icerik_link))
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

        btn_quiz_add.addActionListener(e ->
        {
            if(Helper.isFieldEmpty(fld_soru_sayisi))
                Helper.showMsg("fill");
            else
            {
                if(Quiz.add(cmb_icerik_list.getSelectedItem().toString(),Integer.parseInt(fld_soru_sayisi.getText())))
                {
                    Helper.showMsg("done");
                    refreshQuizList();
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
            row_icerik_list[j++]=i.getCourse().getName();
            row_icerik_list[j++]=i.getBaslik();
            row_icerik_list[j++]=i.getAciklama();
            row_icerik_list[j]=i.getLink();
            mdl_icerik_list.addRow(row_icerik_list);
        }
    }

    public void refreshQuizList()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_quiz_list.getModel();
        clearModel.setRowCount(0);
        for(Quiz q : Quiz.getList())
        {
            int i=0;
            row_quiz_list[i++]=q.getId();
            row_quiz_list[i++]=q.getIcerik().getBaslik();
            row_quiz_list[i++]="Quiz "+q.getCount();
            row_quiz_list[i]=q.getSoru_sayisi();
            mdl_quiz_list.addRow(row_quiz_list);
        }
    }
}
