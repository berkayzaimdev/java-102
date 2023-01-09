package TurizmAcenteSistemi.Model;
import TurizmAcenteSistemi.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Otel
{
    private int id,yildiz;
    private String ad,adres,eposta,telefon;

    public Otel()
    {
    }
    public Otel(int id, int yildiz, String ad, String adres, String eposta, String telefon)
    {
        this.id = id;
        this.yildiz = yildiz;
        this.ad = ad;
        this.adres = adres;
        this.eposta = eposta;
        this.telefon = telefon;
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

    public static ArrayList<Otel> getList()
    {
        ArrayList<Otel> list = new ArrayList<>();
        String query = "SELECT id,ad,adres,eposta,telefon,yildiz FROM otel";
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
