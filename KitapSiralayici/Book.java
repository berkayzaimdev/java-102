package KitapSiralayici;

public class Book implements Comparable<Book>
{
    private String isim,yazarisim,tarih;
    private int sayfa;

    public Book(String isim,int sayfa,String yazarisim,String tarih)
    {
        this.isim=isim;
        this.sayfa=sayfa;
        this.yazarisim=yazarisim;
        this.tarih=tarih;
    }
    public int compareTo(Book b)
    {
        return getIsim().compareTo(b.getIsim());
    }

    public String getIsim() {
        return isim;
    }

    public String getYazarisim() {
        return yazarisim;
    }

    public int getSayfa() {
        return sayfa;
    }

    public String getTarih() {
        return tarih;
    }
}
