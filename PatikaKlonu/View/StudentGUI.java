package PatikaKlonu.View;

import PatikaKlonu.Helper.Config;
import PatikaKlonu.Helper.DBConnector;
import PatikaKlonu.Helper.Helper;
import PatikaKlonu.Helper.Item;
import PatikaKlonu.Model.Course;
import PatikaKlonu.Model.Icerik;
import PatikaKlonu.Model.Patika;
import PatikaKlonu.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentGUI extends JFrame
{
    private JPanel wrapper;
    private JLabel lbl_ogrenci_welcome;
    private JTabbedPane tabbedPane1;
    private JTable tbl_patika_list;
    private JTextField fld_patika_id;
    private JButton btn_patika_kayit;
    private JTable tbl_ders_list;
    private JTextField fld_ders_id;
    private JButton btn_ders_kayit;
    private JTable tbl_ders_list_2;
    private JTable tbl_icerik_list;
    private JTextField fld_icerik_id;
    private JComboBox cmb_quiz_list;
    private JButton btn_quiz_coz;
    private JButton btn_cikis;
    private User u;

    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;

    private DefaultTableModel mdl_ders_list;
    private Object[] row_ders_list;

    private DefaultTableModel mdl_ders_list_2;
    private Object[] row_ders_list_2;

    private DefaultTableModel mdl_icerik_list;
    private Object[] row_icerik_list;

    private DefaultComboBoxModel mdl_quiz_list;
    private JLabel lbl_soru;
    private JRadioButton rdb_a,rdb_b,rdb_c,rdb_d,rdb_e;
    public StudentGUI(User u)
    {
        this.u=u;
        add(wrapper);
        lbl_ogrenci_welcome.setText("Hoşgeldiniz "+u.getName());
        setSize(700,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Öğrenci Paneli");
        setResizable(false);
        setVisible(true);

        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID","Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        tbl_patika_list.setDefaultEditor(Object.class,null);
        tbl_patika_list.setModel(mdl_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        refreshPatikaList();

        mdl_ders_list = new DefaultTableModel();
        Object[] col_ders_list = {"ID","Patika Adı","Ders Adı"};
        mdl_ders_list.setColumnIdentifiers(col_ders_list);
        tbl_ders_list.setDefaultEditor(Object.class,null);
        tbl_ders_list.setModel(mdl_ders_list);
        row_ders_list = new Object[col_ders_list.length];
        refreshDersList();

        mdl_ders_list_2 = new DefaultTableModel();
        Object[] col_ders_list_2 = {"ID","Patika Adı","Ders Adı"};
        mdl_ders_list_2.setColumnIdentifiers(col_ders_list_2);
        tbl_ders_list_2.setDefaultEditor(Object.class,null);
        tbl_ders_list_2.setModel(mdl_ders_list_2);
        row_ders_list_2 = new Object[col_ders_list_2.length];
        refreshDersList_2();

        mdl_icerik_list = new DefaultTableModel();
        Object[] col_icerik_list = {"ID","Ders Adı","İçerik Başlığı","İçerik Açıklaması","YouTube linki"};
        mdl_icerik_list.setColumnIdentifiers(col_icerik_list);
        tbl_icerik_list.setDefaultEditor(Object.class,null);
        tbl_icerik_list.setModel(mdl_icerik_list);
        row_icerik_list = new Object[col_icerik_list.length];
        refreshIcerikList();

        mdl_quiz_list = new DefaultComboBoxModel();
        cmb_quiz_list.setModel(mdl_quiz_list);

        tbl_patika_list.getSelectionModel().addListSelectionListener(e ->
        {
            try
            {
                fld_patika_id.setText(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
            }
            catch(Exception ex){}
        });

        tbl_ders_list.getSelectionModel().addListSelectionListener(e ->
        {
            try
            {
                fld_ders_id.setText(tbl_ders_list.getValueAt(tbl_ders_list.getSelectedRow(), 0).toString());
            }
            catch(Exception ex){}
        });

        tbl_icerik_list.getSelectionModel().addListSelectionListener(e ->
        {
            try
            {
                fld_icerik_id.setText(tbl_icerik_list.getValueAt(tbl_icerik_list.getSelectedRow(), 0).toString());
                refreshCmbQuizList(Integer.parseInt(tbl_icerik_list.getValueAt(tbl_icerik_list.getSelectedRow(), 0).toString()));
            }
            catch(Exception ex){}
        });

        btn_patika_kayit.addActionListener(e->
        {
            if(Helper.isFieldEmpty(fld_patika_id))
                Helper.showMsg("fill");
            else
            {
                if(addPatika(Integer.parseInt(fld_patika_id.getText())))
                {
                    Helper.showMsg("done");
                    refreshPatikaList();
                    refreshDersList();
                }
                else
                    Helper.showMsg("error");
            }
        });

        btn_ders_kayit.addActionListener(e->
        {
            if(Helper.isFieldEmpty(fld_ders_id))
                Helper.showMsg("fill");
            else
            {
                if(addDers(Integer.parseInt(fld_ders_id.getText())))
                {
                    Helper.showMsg("done");
                    refreshDersList();
                    refreshDersList_2();
                    refreshIcerikList();
                }
                else
                    Helper.showMsg("error");
            }
        });

        btn_quiz_coz.addActionListener(e->
        {
            if(Helper.isFieldEmpty(fld_icerik_id)||cmb_quiz_list.getSelectedItem()==null)
                Helper.showMsg("fill");
            else
            {
                String sql="SELECT * from quiz WHERE icerik_id = ? ORDER BY id ASC LIMIT ?,1";
                try
                {
                    PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
                    ps.setInt(1,Integer.parseInt(fld_icerik_id.getText()));
                    ps.setInt(2,Integer.parseInt(cmb_quiz_list.getSelectedItem().toString().split(" ")[1])-1);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next())
                    {
                        int quiz_id = rs.getInt("id");
                        int soru_sayisi = rs.getInt("soru_sayisi");
                        int dogru=0;
                        Helper.setLayout();
                        SoruCozumGUI sorucozumGUI = new SoruCozumGUI(quiz_id,soru_sayisi,0);
                    }
                }
                catch(SQLException er)
                {
                    er.printStackTrace();
                }
            }
        });

        btn_cikis.addActionListener(e ->
        {
            dispose();
            LoginGUI l = new LoginGUI();
        });
    }

    public void refreshPatikaList()
    {
        fld_patika_id.setText("");
        DefaultTableModel clearmodel = (DefaultTableModel) tbl_patika_list.getModel();
        clearmodel.setRowCount(0);
        for(Patika p : Patika.getListByUser(u.getId()))
        {
            int i=0;
            row_patika_list[i++]=p.getId();
            row_patika_list[i]=p.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    public void refreshDersList()
    {
        fld_ders_id.setText("");
        DefaultTableModel clearmodel = (DefaultTableModel) tbl_ders_list.getModel();
        clearmodel.setRowCount(0);
        for(Course d : Course.getListByStudent(u.getId()))
        {
            int i=0;
            row_ders_list[i++]=d.getId();
            row_ders_list[i++]=d.getPatika().getName();
            row_ders_list[i]=d.getName();
            mdl_ders_list.addRow(row_ders_list);
        }
    }

    public void refreshDersList_2()
    {
        DefaultTableModel clearmodel = (DefaultTableModel) tbl_ders_list_2.getModel();
        clearmodel.setRowCount(0);
        for(Course d : Course.getListByStudent_2(u.getId()))
        {
            int i=0;
            row_ders_list_2[i++]=d.getId();
            row_ders_list_2[i++]=d.getPatika().getName();
            row_ders_list_2[i]=d.getName();
            mdl_ders_list_2.addRow(row_ders_list_2);
        }
    }

    public void refreshCmbQuizList(int id)
    {
        String sql = "SELECT * from quiz WHERE icerik_id = ?";
        cmb_quiz_list.removeAllItems();
        int i=0;
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                cmb_quiz_list.addItem(new Item(++i,"Quiz "+i));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void refreshIcerikList()
    {
        DefaultTableModel clearmodel = (DefaultTableModel) tbl_icerik_list.getModel();
        clearmodel.setRowCount(0);
        for(Icerik j : Icerik.getListByStudent(u.getId()))
        {
            int i=0;
            row_icerik_list[i++]=j.getId();
            row_icerik_list[i++]=Course.getFetch(j.getCourse_id()).getName();
            row_icerik_list[i++]=j.getBaslik();
            row_icerik_list[i++]=j.getAciklama();
            row_icerik_list[i]=j.getLink();
            mdl_icerik_list.addRow(row_icerik_list);
        }
    }


    public boolean addPatika(int patika_id)
    {
        String sql = "INSERT INTO patika_sign_in (user_id,patika_id) VALUES (?,?)";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1,u.getId());
            ps.setInt(2,patika_id);
            return ps.executeUpdate()!=-1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addDers(int course_id)
    {
        String sql = "INSERT INTO course_sign_in (user_id,course_id) VALUES (?,?)";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1,u.getId());
            ps.setInt(2,course_id);
            return ps.executeUpdate()!=-1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
