package PatikaKlonu.Model;

import PatikaKlonu.Helper.DBConnector;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;

public class Patika
{
    private int id;
    private String name;

    public Patika(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Patika> getList()
    {
        ArrayList<Patika> patikalist = new ArrayList<>();
        Patika obj;
        try
        {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patika");
            while(rs.next())
            {
                obj = new Patika(rs.getInt("id"),rs.getString("name"));
                patikalist.add(obj);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return patikalist;
    }

    public static boolean add(String name)
    {
        String query = "INSERT INTO patika (name) VALUES (?)";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,name);
            return ps.executeUpdate() != -1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean update(int id,String name)
    {
        String query = "UPDATE patika SET name = ? WHERE id = ?";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,name);
            ps.setInt(2,id);
            return ps.executeUpdate() != -1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public static Patika getFetch(int id)
    {
        Patika obj = null;
        String query = "SELECT * FROM patika WHERE id = ?";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                obj = new Patika(rs.getInt("id"),rs.getString("name"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    public static boolean delete(int id)
    {
        String query = "DELETE FROM patika WHERE id = ?";
        ArrayList<Course> courseList = Course.getList();
        for(Course c : courseList)
            if(c.getPatika().getId()==id)
                Course.delete(c.getId());
        try {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setInt(1,id);
            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<Patika> getListByUser(int user_id)
    {
        ArrayList<Patika> patikalar = new ArrayList<>();
        String sql = "SELECT * FROM patika WHERE id NOT IN (SELECT patika_id FROM patika_sign_in WHERE user_id = ?)";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1,user_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                patikalar.add(new Patika(id,name));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return patikalar;
    }
}
