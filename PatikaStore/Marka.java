package PatikaStore;

import java.util.Comparator;

public class Marka
{
    private int id;
    private String isim;
    static int c=0;

    static Marka[] markalar = new Marka[9];

    public Marka(String isim)
    {
        this.isim=isim;
        markalar[c++]=this;
        this.id=c;
    }

    public String getIsim()
    {
        return isim;
    }
}

