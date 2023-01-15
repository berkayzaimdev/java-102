package PatikaKlonu.View;
import PatikaKlonu.Helper.*;
import PatikaKlonu.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame
{
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_cikis;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JTextField fld_search_user_name;
    private JTextField fld_search_user_uname;
    private JComboBox cmb_search_user_type;
    private JButton btn_search;
    private JPanel pnl_patika_list;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;
    private JPanel pnl_user_top;
    private JTable tbl_icerik_list;
    private JTable tbl_quiz_list;
    private JTextField fld_icerik_id;
    private JButton btn_icerik_sil;
    private JTextField fld_quiz_id;
    private JButton btn_quiz_sil;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private DefaultTableModel mdl_icerik_list;
    private Object[] row_icerik_list;
    private DefaultTableModel mdl_quiz_list;
    private Object[] row_quiz_list;


    private final Operator operator;
    public OperatorGUI(Operator operator){
        this.operator=operator;
        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Yönetim Paneli");
        setVisible(true);
        lbl_welcome.setText("Hoşgeldiniz "+operator.getName());

        mdl_user_list=new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if(column==0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list={"ID","Ad Soyad","Kullanıcı Adı","Şifre","Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        row_user_list= new Object[col_user_list.length];

        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(e ->
        {
            try
            {
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_user_id.setText(select_user_id);
            }
            catch(Exception ex)
            {}
        });

        tbl_icerik_list.getSelectionModel().addListSelectionListener(e ->
        {
            try
            {
                String select_icerik_id = tbl_icerik_list.getValueAt(tbl_icerik_list.getSelectedRow(), 0).toString();
                fld_icerik_id.setText(select_icerik_id);
            }
            catch(Exception ex)
            {}
        });

        tbl_quiz_list.getSelectionModel().addListSelectionListener(e ->
        {
            try
            {
                String select_quiz_id = tbl_quiz_list.getValueAt(tbl_quiz_list.getSelectedRow(), 0).toString();
                fld_quiz_id.setText(select_quiz_id);
            }
            catch(Exception ex)
            {}
        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE)
            {
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString();
                String user_uname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();
                String pass = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();
                String type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();

                if(User.update(user_id,user_name,user_uname,pass,type))
                    Helper.showMsg("done");

                loadUserModel();
                loadEducatorCombo();
                loadCourseModel();
            }
        });

        patikaMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Güncelle");
        JMenuItem deleteMenu = new JMenuItem("Sil");
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);

        updateMenu.addActionListener(e->
        {
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
            UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPatikaModel();
                    loadPatikaCombo();
                }
            });
        });

        deleteMenu.addActionListener(e->
        {
            if(Helper.confirm("sure"))
            {
                int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
                if(Patika.delete(select_id))
                {
                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                }
                else
                    Helper.showMsg("error");
            }
        });


        mdl_patika_list=new DefaultTableModel();
        Object[] col_patika_list = {"ID","Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();

        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(patikaMenu);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Point point = e.getPoint();
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        mdl_course_list = new DefaultTableModel();
        Object[] col_course_list = {"ID","Ders Adı","Programlama Dili","Patika","Eğitmen"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadCourseModel();

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadPatikaCombo();
        loadEducatorCombo();


        mdl_icerik_list = new DefaultTableModel();
        Object[] col_icerik_list = {"ID","Ders ID","İçerik Başlığı","İçerik Açıklaması","Programlama Dili"};
        mdl_icerik_list.setColumnIdentifiers(col_icerik_list);
        row_icerik_list = new Object[col_icerik_list.length];
        tbl_icerik_list.setModel(mdl_icerik_list);
        loadIcerikModel();

        mdl_quiz_list = new DefaultTableModel();
        Object[] col_quiz_list = {"ID","İçerik ID","Soru Sayısı","Sıra"};
        mdl_quiz_list.setColumnIdentifiers(col_quiz_list);
        row_quiz_list = new Object[col_quiz_list.length];
        tbl_quiz_list.setModel(mdl_quiz_list);
        loadQuizModel();

        btn_user_add.addActionListener(e->
        {
            if(Helper.isFieldEmpty(fld_user_name)||Helper.isFieldEmpty(fld_user_uname)||Helper.isFieldEmpty(fld_user_pass))
                Helper.showMsg("fill");
            else
            {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if(User.add(name,uname,pass,type))
                {
                    Helper.showMsg("done");
                    loadUserModel();
                    loadEducatorCombo();
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);
                }
            }
        });
        btn_user_delete.addActionListener(e ->
        {
           if(Helper.isFieldEmpty(fld_user_id))
           {
               Helper.showMsg("fill");
           }
           else
           {
               if(Helper.confirm("sure"))
               {
                   int id =Integer.parseInt(fld_user_id.getText());
                   if(User.delete(id))
                   {
                       Helper.showMsg("done");
                       loadUserModel();
                       loadEducatorCombo();
                       loadCourseModel();
                       fld_user_id.setText(null);
                   }
                   else
                       Helper.showMsg("error");
               }
           }
        });

        btn_search.addActionListener(e ->
        {
            String name = fld_search_user_name.getText();
            String uname = fld_search_user_uname.getText();
            String type = cmb_search_user_type.getSelectedItem().toString();
            loadUserModel(User.searchUserList(User.searchQuery(name,uname,type)));
        });

        btn_patika_add.addActionListener(e ->
        {
            if(Helper.isFieldEmpty(fld_patika_name))
                Helper.showMsg("fill");
            else
            {
                if(Patika.add(fld_patika_name.getText()))
                {
                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    fld_patika_name.setText(null);
                }
                else
                    Helper.showMsg("error");
            }
        });
        btn_course_add.addActionListener(e->
        {
            Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();
            if(Helper.isFieldEmpty(fld_course_name)||Helper.isFieldEmpty(fld_course_lang))
                Helper.showMsg("fill");
            else
            {
                if(Course.add(userItem.getKey(),patikaItem.getKey(),fld_course_name.getText(),fld_course_lang.getText()))
                {
                    Helper.showMsg("done");
                    loadCourseModel();
                }
                else
                    Helper.showMsg("error");
            }
        });

        btn_icerik_sil.addActionListener(e->
        {
            if(Helper.isFieldEmpty(fld_icerik_id))
                Helper.showMsg("fill");
            else
            {
                if(Helper.confirm("sure"))
                {
                    if(Icerik.delete(Integer.parseInt(fld_icerik_id.getText())))
                    {
                        Helper.showMsg("done");
                        loadIcerikModel();
                        loadQuizModel();
                    }
                    else
                        Helper.showMsg("error");
                }
            }
        });
        btn_quiz_sil.addActionListener(e->
        {
            if(Helper.isFieldEmpty(fld_quiz_id))
                Helper.showMsg("fill");
            else
            {
                if(Helper.confirm("sure"))
                {
                    if(Quiz.delete(Integer.parseInt(fld_quiz_id.getText())))
                    {
                        Helper.showMsg("done");
                        loadQuizModel();
                    }
                    else
                        Helper.showMsg("error");
                }
            }
        });
        btn_cikis.addActionListener(e ->
        {
            dispose();
            LoginGUI l = new LoginGUI();
        });
    }


    private void loadCourseModel()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        for(Course obj : Course.getList())
        {
            int i=0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLang();
            row_course_list[i++] = obj.getPatika().getName();
            row_course_list[i++] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);
        }
    }

    public void loadPatikaModel()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        for(Patika obj : Patika.getList())
        {
            int i=0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }
    public void loadUserModel()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for(User obj : User.getList())
        {
            int i=0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadUserModel(ArrayList<User> list)
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for(User obj : list)
        {
            int i=0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadPatikaCombo()
    {
        cmb_course_patika.removeAllItems();
        for(Patika obj : Patika.getList())
            cmb_course_patika.addItem(new Item(obj.getId(), obj.getName()));
    }
    private void loadEducatorCombo()
    {
        cmb_course_user.removeAllItems();
        for(User obj : User.getListOnlyEducator())
            cmb_course_user.addItem(new Item(obj.getId(), obj.getName()));
    }

    public void loadIcerikModel()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_icerik_list.getModel();
        clearModel.setRowCount(0);
        for(Icerik obj : Icerik.getList())
        {
            int i=0;
            row_icerik_list[i++] = obj.getId();
            row_icerik_list[i++] = obj.getCourse_id();
            row_icerik_list[i++] = obj.getBaslik();
            row_icerik_list[i++] = obj.getAciklama();
            row_icerik_list[i] = obj.getLink();
            mdl_icerik_list.addRow(row_icerik_list);
        }
    }

    public void loadQuizModel()
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_quiz_list.getModel();
        clearModel.setRowCount(0);
        for(Quiz obj : Quiz.getList())
        {
            int i=0;
            row_quiz_list[i++] = obj.getId();
            row_quiz_list[i++] = obj.getIcerik_id();
            row_quiz_list[i++] = obj.getSoru_sayisi();
            row_quiz_list[i] = "Quiz "+obj.getCount(obj.getIcerik_id(),obj.getId());
            mdl_quiz_list.addRow(row_quiz_list);
        }
    }
}
