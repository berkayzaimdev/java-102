package SigortaYonetimSistemi;
import java.util.ArrayList;
import java.util.Date;

public class User
{
    private String isim,soyisim,email,sifre,meslek;
    private int yas;
    private ArrayList<Address> adreslistesi;
    private Date songiris=new Date();

    public User(String isim,String soyisim,String email,String sifre,String meslek,int yas)
    {
        this.isim=isim;
        this.soyisim=soyisim;
        this.email=email;
        this.sifre=sifre;
        this.meslek=meslek;
        this.yas=yas;
    }

    public String getIsim() {
        return isim;
    }

    public String getEmail() {
        return email;
    }

    public String getSifre() {
        return sifre;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public int getYas() {
        return yas;
    }

    public String getMeslek() {
        return meslek;
    }

    public Date getSongiris() {
        return songiris;
    }

    public void setSongiris(Date songiris) {
        this.songiris = songiris;
    }

    public ArrayList<Address> getAdreslistesi() {
        return adreslistesi;
    }
    public void adresEkle(Address a)
    {
        adreslistesi.add(a);
    }
    public void adresSil(int i)
    {
        adreslistesi.remove(i);
    }
}
