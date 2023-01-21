package TurizmAcenteSistemi.Model;
import TurizmAcenteSistemi.Helper.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Rezervasyon
{
    private int oda_id,id;
    private String adsoyad,telefon,eposta,note;

    public Rezervasyon(int oda_id,String adsoyad,String telefon,String eposta,String note)
    {
        this.oda_id=oda_id;
        this.adsoyad=adsoyad;
        this.telefon=telefon;
        this.eposta=eposta;
        this.note=note;
    }

    public Rezervasyon() {

    }

    public int getOda_id() {
        return oda_id;
    }

    public void setOda_id(int oda_id) {
        this.oda_id = oda_id;
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

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static ArrayList<Rezervasyon> getList()
    {
        ArrayList<Rezervasyon> list = new ArrayList<>();
        String query = "SELECT * FROM rezervasyon";
        Rezervasyon obj=null;
        try
        {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                obj = new Rezervasyon();
                obj.setId(rs.getInt("id"));
                obj.setOda_id(rs.getInt("oda_id"));
                obj.setAdsoyad(rs.getString("adsoyad"));
                obj.setTelefon(rs.getString("telefon"));
                obj.setEposta(rs.getString("eposta"));
                obj.setNote(rs.getString("note"));
                list.add(obj);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean add(int oda_id,String adsoyad,String telefon,String eposta,String note)
    {
        try
        {
            String sql = "INSERT INTO rezervasyon (oda_id,adsoyad,telefon,eposta,note) VALUES (?,?,?,?,?)";
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1, oda_id);
            ps.setString(2, adsoyad);
            ps.setString(3, telefon);
            ps.setString(4, eposta);
            ps.setString(5, note);
            return ps.executeUpdate()!=-1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
