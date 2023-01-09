package TurizmAcenteSistemi.Model;
import PatikaKlonu.Helper.Helper;
import PatikaKlonu.Model.User;
import TurizmAcenteSistemi.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Otel
{
    private int id,yildiz;
    private String ad,adres,eposta,telefon,tesis,pansiyon;

    public Otel()
    {
    }
    public Otel(int id, int yildiz, String ad, String adres, String eposta, String telefon, String tesis,String pansiyon)
    {
        this.id = id;
        this.yildiz = yildiz;
        this.ad = ad;
        this.adres = adres;
        this.eposta = eposta;
        this.telefon = telefon;
        this.tesis=tesis;
        this.pansiyon=pansiyon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYildiz() {
        return yildiz;
    }

    public void setYildiz(int yildiz) {
        this.yildiz = yildiz;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTesis() {
        return tesis;
    }

    public void setTesis(String tesis) {
        this.tesis = tesis;
    }

    public String getPansiyon() {
        return pansiyon;
    }

    public void setPansiyon(String pansiyon) {
        this.pansiyon = pansiyon;
    }

    public static ArrayList<Otel> getList()
    {
        ArrayList<Otel> list = new ArrayList<>();
        String query = "SELECT * FROM otel";
        Otel obj;
        try
        {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                obj = new Otel();
                obj.setId(rs.getInt("id"));
                obj.setAd(rs.getString("ad"));
                obj.setAdres(rs.getString("adres"));
                obj.setEposta(rs.getString("eposta"));
                obj.setTelefon(rs.getString("telefon"));
                obj.setYildiz(rs.getInt("yildiz"));
                obj.setPansiyon(rs.getString("pansiyon"));
                obj.setTesis(rs.getString("tesis"));
                list.add(obj);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean add(String ad,String adres,String eposta,String telefon,int yildiz,String pansiyon,String tesis)
    {
        if(Otel.getFetch(adres) != null)
        {
            Helper.showMsg("Bu adres kullanılıyor!");
            return false;
        }
        String query = "INSERT INTO otel (ad,adres,eposta,telefon,yildiz,pansiyon,tesis) VALUES (?,?,?,?,?,?,?)";
        boolean key=true;
        try
        {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,ad);
            pr.setString(2,adres);
            pr.setString(3,eposta);
            pr.setString(4,telefon);
            pr.setInt(5,yildiz);
            pr.setString(6,pansiyon);
            pr.setString(7,tesis);
            int response = pr.executeUpdate();
            if(response==-1)
                Helper.showMsg("error");
            key = response!=-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static Otel getFetch(String adres)
    {
        Otel obj = null;
        String query = "SELECT * FROM otel WHERE adres = ?";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,adres);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                obj = new Otel();
                obj.setId(rs.getInt("id"));
                obj.setAd(rs.getString("ad"));
                obj.setAdres(rs.getString("adres"));
                obj.setEposta(rs.getString("eposta"));
                obj.setTelefon(rs.getString("telefon"));
                obj.setYildiz(rs.getInt("yildiz"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    public static Otel getFetch(String ad,boolean overloader)
    {
        Otel obj = null;
        String query = "SELECT * FROM otel WHERE ad = ?";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,ad);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                obj = new Otel();
                obj.setId(rs.getInt("id"));
                obj.setAd(rs.getString("ad"));
                obj.setAdres(rs.getString("adres"));
                obj.setEposta(rs.getString("eposta"));
                obj.setTelefon(rs.getString("telefon"));
                obj.setYildiz(rs.getInt("yildiz"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    public static Otel getFetch(int id)
    {
        Otel obj = null;
        String query = "SELECT * FROM otel WHERE id = ?";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                obj = new Otel();
                obj.setId(rs.getInt("id"));
                obj.setAd(rs.getString("ad"));
                obj.setAdres(rs.getString("adres"));
                obj.setEposta(rs.getString("eposta"));
                obj.setTelefon(rs.getString("telefon"));
                obj.setYildiz(rs.getInt("yildiz"));
                obj.setPansiyon(rs.getString("pansiyon"));
                obj.setTesis(rs.getString("tesis"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }
}
