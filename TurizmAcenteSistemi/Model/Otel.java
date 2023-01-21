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
    private String ad,sehir,bolge,adres,eposta,telefon,tesis,pansiyon;

    public Otel()
    {
    }
    public Otel(int id, int yildiz, String ad, String sehir, String bolge,String adres, String eposta, String telefon, String pansiyon,String tesis)
    {
        this.id = id;
        this.yildiz = yildiz;
        this.ad = ad;
        this.sehir=sehir;
        this.bolge=bolge;
        this.adres = adres;
        this.eposta = eposta;
        this.telefon = telefon;
        this.pansiyon=pansiyon;
        this.tesis=tesis;
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

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getBolge() {
        return bolge;
    }

    public void setBolge(String bolge) {
        this.bolge = bolge;
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
        Otel obj=null;
        try
        {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                obj = new Otel();
                obj.setId(rs.getInt("id"));
                obj.setAd(rs.getString("ad"));
                obj.setSehir(rs.getString("sehir"));
                obj.setBolge(rs.getString("bolge"));
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

    public static boolean add(String ad,String sehir,String bolge,String adres,String eposta,String telefon,int yildiz,String pansiyon,String tesis)
    {
        if(Otel.getFetch(adres) != null)
        {
            Helper.showMsg("Bu adres kullanılıyor!");
            return false;
        }
        String query = "INSERT INTO otel (ad,sehir,bolge,adres,eposta,telefon,yildiz,pansiyon,tesis) VALUES (?,?,?,?,?,?,?,?,?)";
        boolean key=true;
        try
        {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,ad);
            pr.setString(2,sehir);
            pr.setString(3,bolge);
            pr.setString(4,adres);
            pr.setString(5,eposta);
            pr.setString(6,telefon);
            pr.setInt(7,yildiz);
            pr.setString(8,pansiyon);
            pr.setString(9,tesis);
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
                obj.setSehir(rs.getString("sehir"));
                obj.setBolge(rs.getString("bolge"));
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
                obj.setSehir(rs.getString("sehir"));
                obj.setBolge(rs.getString("bolge"));
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

    public static Otel getFetch(String s,boolean overloader1,boolean overloader2)
    {
        Otel obj = null;
        String query = "SELECT * FROM otel WHERE ad LIKE ? OR sehir LIKE ? OR bolge LIKE ?";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,s);
            ps.setString(2,s);
            ps.setString(3,s);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                obj = new Otel();
                obj.setId(rs.getInt("id"));
                obj.setAd(rs.getString("ad"));
                obj.setSehir(rs.getString("sehir"));
                obj.setBolge(rs.getString("bolge"));
                obj.setAdres(rs.getString("adres"));
                obj.setEposta(rs.getString("eposta"));
                obj.setTelefon(rs.getString("telefon"));
                obj.setTesis(rs.getString("tesis"));
                obj.setPansiyon(rs.getString("pansiyon"));
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
                obj.setSehir(rs.getString("sehir"));
                obj.setBolge(rs.getString("bolge"));
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
