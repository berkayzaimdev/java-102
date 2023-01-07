package SigortaYonetimSistemi;

public class HomeAddress implements Address
{
    private final String tip="Ev Adresi";
    private String ulke,il,telefon;
    private int id;
    public HomeAddress(String ulke,String il,String telefon,int id)
    {
        this.ulke=ulke;
        this.il=il;
        this.telefon=telefon;
        this.id=id;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
