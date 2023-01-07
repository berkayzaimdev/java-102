package SigortaYonetimSistemi;

public class BusinessAddress implements Address
{
    private final String tip="İş Adresi";
    private String ulke,il,telefon;
    private int id;
    public BusinessAddress(String ulke,String il,String telefon,int id)
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
