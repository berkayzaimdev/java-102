package PatikaKlonu.Model;

import PatikaKlonu.Helper.DBConnector;
import PatikaKlonu.View.AddSoruGUI;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Quiz
{
    private int id,icerik_id,soru_sayisi,count;
    private Icerik icerik;
    private ArrayList<Soru> sorular;

    public Quiz() {}
    public Quiz(int id, int icerik_id, int soru_sayisi,int count)
    {
        this.id = id;
        this.icerik_id = icerik_id;
        this.soru_sayisi = soru_sayisi;
        this.count=count;
        this.icerik = Icerik.getFetch(icerik_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Icerik getIcerik() {
        return icerik;
    }

    public int getIcerik_id() {
        return icerik_id;
    }

    public void setIcerik_id(int icerik_id) {
        this.icerik_id = icerik_id;
    }

    public int getSoru_sayisi() {
        return soru_sayisi;
    }

    public void setSoru_sayisi(int soru_sayisi) {
        this.soru_sayisi = soru_sayisi;
    }

    public static ArrayList<Quiz> getList()
    {
        ArrayList<Quiz> quizler = new ArrayList<>();
        String sql = "SELECT * from quiz";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt(1);
                int icerik_id = rs.getInt(2);
                int soru_sayisi = rs.getInt(3);
                Quiz q = new Quiz(id,icerik_id,soru_sayisi,Quiz.getCount(icerik_id,id));
                quizler.add(q);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return quizler;
    }

    public int getCount() {
        return count;
    }

    public static int getCount(int icerik_id, int id)
    {
        String sql = "SELECT COUNT(*) AS quiz_number FROM quiz WHERE icerik_id = ? and id <= ? ORDER BY quiz_number ASC";
        //String sql = "SELECT * from quiz WHERE icerik_id = ?";
        int c=0;
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1,icerik_id);
            ps.setInt(2,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                c=rs.getInt("quiz_number");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return c;
    }

    public static boolean add(String icerik_adi,int soru_sayisi)
    {
        String sql = "INSERT INTO quiz(icerik_id,soru_sayisi) VALUES (?,?)";
        try
        {
            PreparedStatement s = DBConnector.getInstance().prepareStatement(sql);
            s.setInt(1,Icerik.getFetch(icerik_adi).getId());
            s.setInt(2,soru_sayisi);
            if(s.executeUpdate()!=-1)
            {
                sql="SELECT * from quiz ORDER BY id DESC LIMIT 1";
                Statement ps = DBConnector.getInstance().createStatement();
                ResultSet rs = ps.executeQuery(sql);
                AddSoruGUI addSoruGUI = new AddSoruGUI(soru_sayisi,soru_sayisi,rs.getInt("id"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
