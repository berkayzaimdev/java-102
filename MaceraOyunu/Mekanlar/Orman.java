package MaceraOyunu.Mekanlar;
import MaceraOyunu.Mekanlar.Mekan;
import MaceraOyunu.Oyuncu;
import MaceraOyunu.Canavar.*;

public class Orman extends SavasMekani
{
    public Orman(Oyuncu oyuncu)
    {
        super(oyuncu,"Orman",new Vampir());
    }
}
