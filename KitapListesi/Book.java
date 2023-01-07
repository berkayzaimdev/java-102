package KitapListesi;

public class Book
{
    private String kitapisim,yazarisim,yayimtarihi;
    private int sayfa;
    public Book(String kitapisim,String yazarisim,String yayimtarihi,int sayfa)
    {
        this.kitapisim=kitapisim;
        this.yazarisim=yazarisim;
        this.yayimtarihi=yayimtarihi;
        this.sayfa=sayfa;
    }

    public String getKitapisim() {
        return kitapisim;
    }

    public String getYazarisim() {
        return yazarisim;
    }

    public String getYayimtarihi() {
        return yayimtarihi;
    }

    public int getSayfa() {
        return sayfa;
    }
}
