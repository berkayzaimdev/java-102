package PatikaKlonu.Model;

import PatikaKlonu.Helper.DBConnector;
import PatikaKlonu.Helper.Helper;

import java.sql.*;
import java.util.ArrayList;

public class Icerik
{
    private int id,course_id;
    private String baslik,aciklama,link;
    private Course course;

    public Icerik(int id,int course_id,String baslik, String aciklama, String link)
    {
        this.id=id;
        this.course_id=course_id;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.link = link;
    }

    public Icerik() {
        
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static ArrayList<Icerik> getList()
    {
        Icerik i = null;
        ArrayList<Icerik> icerikler = new ArrayList<>();
        String sql = "SELECT * from icerik";
        try
        {
            Statement ps = DBConnector.getInstance().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                int id = rs.getInt("id");
                int course_id = rs.getInt("course_id");
                String baslik = rs.getString("baslik");
                String aciklama = rs.getString("aciklama");
                String link = rs.getString("link");
                i = new Icerik(id,course_id,baslik,aciklama,link);
                icerikler.add(i);
                System.out.println("Başlık: "+i.getBaslik()+"\nAçıklama:"+aciklama);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return icerikler;
    }

    public static boolean add(String baslik,String aciklama,String link,String course_name)
    {
        System.out.println("Baslik:"+baslik);
        System.out.println("Aciklama:"+aciklama);
        System.out.println("Link:"+link);
        System.out.println("Course name:"+course_name);
        String query="INSERT INTO icerik (baslik,aciklama,link,course_id)VALUES (?,?,?,?)";
        try
        {
            PreparedStatement s = DBConnector.getInstance().prepareStatement(query);
            s.setString(1,baslik);
            s.setString(2,aciklama);
            s.setString(3,link);
            s.setInt(4,Course.getFetch(course_name).getId());
            System.out.println(course_name);
            return s.executeUpdate()!=-1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static Icerik getFetch(int id)
    {
        String sql = "SELECT * from icerik WHERE id = ?";
        Icerik i = null;
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                i=new Icerik();
                i.setId(rs.getInt("id"));
                i.setCourse_id(rs.getInt("course_id"));
                i.setAciklama(rs.getString("aciklama"));
                i.setBaslik(rs.getString("baslik"));
                i.setLink(rs.getString("link"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return i;
    }

    public static Icerik getFetch(String icerik_adi)
    {
        String sql = "SELECT * from icerik WHERE baslik = ?";
        Icerik i = null;
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setString(1,icerik_adi);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                i=new Icerik();
                i.setId(rs.getInt("id"));
                i.setCourse_id(rs.getInt("course_id"));
                i.setAciklama(rs.getString("aciklama"));
                i.setBaslik(rs.getString("baslik"));
                i.setLink(rs.getString("link"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return i;
    }
}
