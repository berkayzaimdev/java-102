package PatikaKlonu.Model;

import PatikaKlonu.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course
{
    private int id,user_id;
    private int patika_id;
    private String name,lang;

    private Patika patika;
    private User educator;

    public Course(int id, int user_id, int patika_id, String name, String lang)
    {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.name = name;
        this.lang = lang;
        this.patika = Patika.getFetch(patika_id);
        this.educator = User.getFetch(user_id);
    }

    public Course() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }

    public static ArrayList<Course> getList()
    {
        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        try
        {
            Statement st= DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course");
            while(rs.next())
            {
                int id = rs.getInt("id");
                int user_id=rs.getInt("user_id");
                int patika_id=rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id,user_id,patika_id,name,lang);
                courseList.add(obj);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return courseList;
    }

    public static boolean add(int user_id,int patika_id,String name,String lang)
    {
        String query = "INSERT INTO course (user_id,patika_id,name,lang) VALUES (?,?,?,?)";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setInt(1,user_id);
            ps.setInt(2,patika_id);
            ps.setString(3,name);
            ps.setString(4,lang);
            return ps.executeUpdate()!=-1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }


    public static ArrayList<Course> getListByUser(int user_id)
    {
        ArrayList<Course> courseList = new ArrayList<>();
        Course obj;
        try
        {
            Statement st= DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE user_id = "+user_id);
            while(rs.next())
            {
                int id = rs.getInt("id");
                int userID=rs.getInt("user_id");
                int patika_id=rs.getInt("patika_id");
                String name = rs.getString("name");
                String lang = rs.getString("lang");
                obj = new Course(id,userID,patika_id,name,lang);
                courseList.add(obj);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return courseList;
    }


    public static boolean delete(int id)
    {
        String query = "DELETE FROM course WHERE id = ?";
        try {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setInt(1,id);
            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Course getFetch(int id)
    {
        String query = "SELECT * from course WHERE id = ?";
        Course c = null;
        try
        {
            PreparedStatement s = DBConnector.getInstance().prepareStatement(query);
            s.setInt(1,id);
            ResultSet rs = s.executeQuery();
            while(rs.next())
            {
                c = new Course();
                c.setName(rs.getString("name"));
                c.setLang(rs.getString("lang"));
                c.setPatika_id(rs.getInt("patika_id"));
                c.setUser_id(rs.getInt("user_id"));
                c.setPatika(Patika.getFetch(c.getPatika_id()));
                c.setEducator(User.getFetch(c.getUser_id()));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return c;
    }

    public static Course getFetch(String course_name)
    {
        String query = "SELECT * from course WHERE name = ?";
        Course c = null;
        System.out.println("Kurs tablosunda aranan değer :"+course_name);
        try
        {
            PreparedStatement s = DBConnector.getInstance().prepareStatement(query);
            s.setString(1,course_name);
            ResultSet rs = s.executeQuery();
            while(rs.next())
            {
                c = new Course();
                c.setName(rs.getString("name"));
                c.setLang(rs.getString("lang"));
                c.setId(rs.getInt("id"));
                c.setPatika_id(rs.getInt("patika_id"));
                c.setUser_id(rs.getInt("user_id"));
                c.setPatika(Patika.getFetch(c.getPatika_id()));
                c.setEducator(User.getFetch(c.getUser_id()));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return c;
    }
}
