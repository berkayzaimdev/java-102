package KitapSiralayici;

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Set<Book> kitaplar = new TreeSet<>();
        kitaplar.add(new Book("Martin Eden", 520, "Jack London", "1909"));
        kitaplar.add(new Book("Simyacı", 197, "Paulo Coelho", "1988"));
        kitaplar.add(new Book("Dönüşüm", 80, "Franz Kafka", "1915"));
        kitaplar.add(new Book("Körlük", 352, "Jose Saramago", "1995"));
        kitaplar.add(new Book("Gazap Üzümleri", 438, "John Steinback", "1939"));
        System.out.println("Alfabetik Olarak Sıralanmış Kitaplar:");
        for (Book k : kitaplar)
        {
            System.out.println(
                    "=========================\nKitap:" +k.getIsim()+
                    "\nYazar:"+k.getYazarisim()
                    +"\nSayfa Sayısı:"+k.getSayfa()
                    +"\nYayımlanma Tarihi:"+k.getTarih()+"\n=========================\n\n");
        }
        Set<Book> siralanmis = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b2.getSayfa() - b1.getSayfa();
            }
        });
        siralanmis.addAll(kitaplar);
        System.out.println("Sayfa Sayısına Göre Sıralanmış Kitaplar:");
        for (Book k : siralanmis)
        {
            System.out.println(
                    "=========================\nKitap:" +k.getIsim()+
                            "\nYazar:"+k.getYazarisim()
                            +"\nSayfa Sayısı:"+k.getSayfa()
                            +"\nYayımlanma Tarihi:"+k.getTarih()+"\n=========================\n\n");
        }
    }
}
