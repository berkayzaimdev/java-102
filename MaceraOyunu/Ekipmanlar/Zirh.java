package MaceraOyunu.Ekipmanlar;

import java.util.ArrayList;

public class Zirh extends Ekipman
{
    private String isim;
    private int id,blok,fiyat;

    public Zirh(String isim, int id, int blok, int fiyat)
    {
        this.isim = isim;
        this.id = id;
        this.blok = blok;
        this.fiyat = fiyat;
    }
    public static ArrayList<Zirh> zirhlar()
    {
        ArrayList<Zirh> zirhlar=new ArrayList<>();
        zirhlar.add(new Zirh("Hafif",1,1,15));
        zirhlar.add(new Zirh("Orta",2,3,25));
        zirhlar.add(new Zirh("Ağır",3,5,40));
        return zirhlar;
    }

    public static Zirh getzirhId(int id)
    {
        for(Zirh z:Zirh.zirhlar())
            if(z.getId()==id)
                return z;
        return null;
    }
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getBlok()
    {
        return blok;
    }

    public void setBlok(int blok)
    {
        this.blok = blok;
    }

    public int getFiyat()
    {
        return fiyat;
    }

    public void setFiyat(int fiyat)
    {
        this.fiyat = fiyat;
    }

    public String getIsim()
    {
        return isim;
    }

    public void setIsim(String isim)
    {
        this.isim = isim;
    }
}
