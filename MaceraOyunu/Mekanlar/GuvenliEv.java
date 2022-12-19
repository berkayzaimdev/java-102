package MaceraOyunu.Mekanlar;
import MaceraOyunu.*;

public class GuvenliEv extends NormalMekan
{
    public GuvenliEv(Oyuncu oyuncu)
    {
        super(oyuncu,"Güvenli Ev");
        oyuncu.setHp(oyuncu.getTabanhp());
        System.out.println("Canınız yenilendi!");
    }
}