package MaceraOyunu.Mekanlar;
import MaceraOyunu.*;

public abstract class Mekan
{
    private boolean location=true;
    private Oyuncu oyuncu;
    public String isim;
    public Mekan(Oyuncu oyuncu,String isim)
    {
        this.oyuncu=oyuncu;
        this.isim=isim;
    }
    public boolean gidilenMekan()
    {
        return true;
    }

    public void setLocation(boolean location)
    {
        this.location = location;
    }

    public Oyuncu getOyuncu() {
        return oyuncu;
    }
}
