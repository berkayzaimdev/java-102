package PatikaKlonu.Model;

import PatikaKlonu.Helper.DBConnector;
import PatikaKlonu.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User
{
    private int id;
    private String name,uname,pass,type;

    public User()
    {

    }

    public User(int id,String name,String uname,String pass,String type)
    {
        this.id=id;
        this.name=name;
        this.uname=uname;
        this.pass=pass;
        this.type=type;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList()
    {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * from user";
        User obj;
        try
        {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return userList;
    }


    public static boolean add(String name,String uname,String pass,String type)
    {
        String query="INSERT INTO user (name,uname,pass,type) VALUES (?,?,?,?)";
        User findUser = User.getFetch(uname);

        if(findUser != null)
        {
            Helper.showMsg("Bu kullanıcı adı kullanılıyor!");
            return false;
        }

        boolean key=true;
        try
        {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,pass);
            pr.setString(4,type);
            int response = pr.executeUpdate();
            if(response==-1)
                Helper.showMsg("error");

            key = response!=-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }


    public static User getFetch(String uname)
    {
        User obj = null;
        String query = "SELECT * FROM user WHERE uname = ?";
        try {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,uname);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static User getFetch(String uname,String pass)
    {
        User obj = null;
        String query = "SELECT * FROM user WHERE uname = ? AND pass = ?";
        try {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,uname);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                switch(rs.getString("type"))
                {
                    case "operator":
                        obj=new Operator();
                        break;
                    default:
                        obj=new User();
                }
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static User getFetch(int id)
    {
        User obj = null;
        String query = "SELECT * FROM user WHERE id = ?";
        try {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static boolean delete(int id)
    {
        ArrayList<Course> courseList = Course.getListByUser(id);
        String query = "DELETE FROM user WHERE id = ?";
        for(Course c : courseList)
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

    public static boolean update(int id,String name,String uname,String pass,String type)
    {
        String query = "UPDATE user SET name=?,uname=?,pass=?,type=? WHERE id = ?";
        User findUser = User.getFetch(uname);
        if(findUser != null && findUser.getId()!=id)
        {
            Helper.showMsg("Bu kullanıcı adı kullanılıyor!");
            return false;
        }
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,uname);
            ps.setString(3,pass);
            ps.setString(4,type);
            ps.setInt(5,id);
            return ps.executeUpdate()!=-1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }


    public static ArrayList<User> searchUserList(String query)
    {
        ArrayList<User> userList = new ArrayList<>();
        User obj;
        try
        {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return userList;
    }


    public static String searchQuery(String name,String uname,String type)
    {
        String query = "SELECT * FROM user WHERE uname LIKE '%{{uname}}%' AND name LIKE '%{{name}}%' AND type LIKE '%{{type}}%'";
        query=query.replace("{{uname}}",uname);
        query=query.replace("{{name}}",name);
        query=query.replace("{{type}}",type);
        return query;
    }

    public static ArrayList<User> getListOnlyEducator()
    {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * from user WHERE type = 'educator'";
        User obj;
        try
        {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return userList;
    }
}
