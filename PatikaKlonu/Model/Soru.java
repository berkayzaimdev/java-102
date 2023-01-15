package PatikaKlonu.Model;

import PatikaKlonu.Helper.DBConnector;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Soru
{
    private int id,quiz_id;
    private String soru_name,A,B,C,D,E;
    private char dogru;

    public Soru()
    {}

    public Soru(int id, int quiz_id, String soru_name, String A, String B, String C, String D, String E, char dogru)
    {
        this.id = id;
        this.quiz_id = quiz_id;
        this.soru_name = soru_name;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.E = E;
        this.dogru = dogru;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getSoru_name() {
        return soru_name;
    }

    public void setSoru_name(String soru_name) {
        this.soru_name = soru_name;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public char getDogru() {
        return dogru;
    }

    public void setDogru(char dogru) {
        this.dogru = dogru;
    }

    public static boolean add(int quiz_id,String soru_name,String A,String B,String C,String D,String E,char dogru)
    {
        String sql = "INSERT INTO soru(quiz_id,soru_name,A,B,C,D,E,dogru) VALUES(?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(sql);
            ps.setInt(1,quiz_id);
            ps.setString(2,soru_name);
            ps.setString(3,A);
            ps.setString(4,B);
            ps.setString(5,C);
            ps.setString(6,D);
            ps.setString(7,E);
            ps.setString(8,Character.toString(dogru));
            return ps.executeUpdate()!=-1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
