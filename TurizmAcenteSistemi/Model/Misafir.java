package TurizmAcenteSistemi.Model;

import TurizmAcenteSistemi.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Misafir
{
    private int rezervasyon_id,id;
    private String adsoyad,telefon;

    public Misafir(int rezervasyon_id,String adsoyad,String telefon)
    {
        this.rezervasyon_id=rezervasyon_id;
        this.adsoyad=adsoyad;
        this.telefon=telefon;
    }

    public Misafir()
    {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdsoyad() {
        return adsoyad;
    }

    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public static void add(int rezervasyon_id, String adsoyad, String telefon)
    {
        try
        {
            String sql = "INSERT INTO misafir (rezervasyon_id,adsoyad,telefon) VALUES (?,?,?)";
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1, rezervasyon_id);
            ps.setString(2, adsoyad);
            ps.setString(3, telefon);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Misafir> getList(int rezervasyon_id)
    {
        ArrayList<Misafir> list = new ArrayList<>();
        String query = "SELECT * FROM misafir WHERE rezervasyon_id = "+rezervasyon_id;
        Misafir obj=null;
        try
        {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                obj = new Misafir();
                obj.setId(rs.getInt("id"));
                obj.setAdsoyad(rs.getString("adsoyad"));
                obj.setTelefon(rs.getString("telefon"));
                list.add(obj);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
