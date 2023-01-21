package TurizmAcenteSistemi.Model;

import PatikaKlonu.Helper.Helper;
import TurizmAcenteSistemi.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Oda
{
    private int id,otel_id,yatak,stok,metrekare;
    private String tip;
    private boolean tv,minibar,oyunkonsolu,kasa,projeksiyon;
    private Otel otel;

    public Oda(int id, int otel_id, int yatak, int stok, int metrekare, String tip, boolean tv, boolean minibar, boolean oyunkonsolu, boolean kasa, boolean projeksiyon)
    {
        this.id = id;
        this.otel_id = otel_id;
        this.yatak = yatak;
        this.stok = stok;
        this.metrekare = metrekare;
        this.tip = tip;
        this.tv = tv;
        this.minibar = minibar;
        this.oyunkonsolu = oyunkonsolu;
        this.kasa = kasa;
        this.projeksiyon = projeksiyon;
        this.otel=Otel.getFetch(otel_id);
    }

    public Oda()
    {

    }

    public Otel getOtel() {
        return otel;
    }

    public void setOtel(Otel otel) {
        this.otel = otel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOtel_id() {
        return otel_id;
    }

    public void setOtel_id(int otel_id) {
        this.otel_id = otel_id;
    }

    public int getYatak() {
        return yatak;
    }

    public void setYatak(int yatak) {
        this.yatak = yatak;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getMetrekare() {
        return metrekare;
    }

    public void setMetrekare(int metrekare) {
        this.metrekare = metrekare;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isOyunkonsolu() {
        return oyunkonsolu;
    }

    public void setOyunkonsolu(boolean oyunkonsolu) {
        this.oyunkonsolu = oyunkonsolu;
    }

    public boolean isKasa() {
        return kasa;
    }

    public void setKasa(boolean kasa) {
        this.kasa = kasa;
    }

    public boolean isProjeksiyon() {
        return projeksiyon;
    }

    public void setProjeksiyon(boolean projeksiyon) {
        this.projeksiyon = projeksiyon;
    }

    public static ArrayList<Oda> getList()
    {
        ArrayList<Oda> list = new ArrayList<>();
        String query = "SELECT * FROM oda";
        Oda obj=null;
        try
        {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                obj = new Oda();
                obj.setId(rs.getInt("id"));
                obj.setOtel_id(rs.getInt("otel_id"));
                obj.setTip(rs.getString("tip"));
                obj.setYatak(rs.getInt("yatak"));
                obj.setStok(rs.getInt("stok"));
                obj.setTv(rs.getBoolean("tv"));
                obj.setMinibar(rs.getBoolean("minibar"));
                obj.setOyunkonsolu(rs.getBoolean("oyunkonsolu"));
                obj.setKasa(rs.getBoolean("kasa"));
                obj.setProjeksiyon(rs.getBoolean("projeksiyon"));
                obj.setOtel(Otel.getFetch(rs.getInt("otel_id")));
                obj.setMetrekare(rs.getInt("metrekare"));
                list.add(obj);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return list;
    }


    public static boolean add(int otel_id, String tip, int stok, int yatak, int metrekare, boolean tv, boolean minibar, boolean oyunkonsolu, boolean kasa, boolean projeksiyon)
    {
        String query="INSERT INTO oda (otel_id,tip,yatak,stok,metrekare,tv,minibar,oyunkonsolu,kasa,projeksiyon) VALUES (?,?,?,?,?,?,?,?,?,?)";
        boolean key=true;
        try
        {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,otel_id);
            pr.setString(2,tip);
            pr.setInt(3,yatak);
            pr.setInt(4,stok);
            pr.setInt(5,metrekare);
            pr.setBoolean(6,tv);
            pr.setBoolean(7,minibar);
            pr.setBoolean(8,oyunkonsolu);
            pr.setBoolean(9,kasa);
            pr.setBoolean(10,projeksiyon);
            int response = pr.executeUpdate();
            if(response==-1)
                Helper.showMsg("error");
            key = response!=-1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static Oda getFetch(String tip,int otel_id)
    {
        Oda obj = null;
        String query = "SELECT * FROM oda WHERE tip = ? AND otel_id = ?";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1,tip);
            ps.setInt(2,otel_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                obj = new Oda();
                obj.setId(rs.getInt("id"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }
}
