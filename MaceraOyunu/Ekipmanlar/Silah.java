package MaceraOyunu.Ekipmanlar;
import java.util.ArrayList;

public class Silah extends Ekipman
{
    private String isim;
    private int id,hasar,fiyat;

    public Silah(String isim, int id, int hasar, int fiyat)
    {
        this.isim = isim;
        this.id = id;
        this.hasar = hasar;
        this.fiyat = fiyat;
    }
    public static ArrayList<Silah> silahlar()
    {
        ArrayList<Silah> silahlar=new ArrayList<>();
        silahlar.add(new Silah("Tabanca",1,2,15));
        silahlar.add(new Silah("Kılıç",2,3,35));
        silahlar.add(new Silah("Tüfek",3,7,45));
        return silahlar;
    }

    public static Silah getsilahId(int id)
    {
        for(Silah s:Silah.silahlar())
            if(s.getId()==id)
                    return s;
            return null;
    }
    public int getId()
    {
            return id;
    }

    public int getHasar()
    {
        return hasar;
    }

    public int getFiyat()
    {
        return fiyat;
    }

    public String getIsim()
    {
        return isim;
    }

}
