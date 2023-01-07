package KitapListesi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Book> kitaplar = new ArrayList<>();
        kitaplar.add(new Book("Karamazov Kardeşler", "Fyodor Dostoevsky", "1880", 824));
        kitaplar.add(new Book("Martin Eden", "Jack London", "1909", 520));
        kitaplar.add(new Book("David Copperfield", "Charles Dickens", "1850", 959));
        kitaplar.add(new Book("Moby Dick", "Herman Melville", "1851", 704));
        kitaplar.add(new Book("Dönüşüm", "Franz Kafka", "1915", 80));
        kitaplar.add(new Book("Uçurtmayı Vurmasınlar", "Harper Lee", "1953", 324));
        kitaplar.add(new Book("Savaş Sanatı", "Sun Tzu", "M.Ö. 400", 80));
        kitaplar.add(new Book("Köprü", "F. Scott Fitzgerald", "1914", 180));
        kitaplar.add(new Book("Satranç", "Stefan Zweig", "1943", 85));
        kitaplar.add(new Book("Dracula", "Bram Stoker", "1897", 447));


        System.out.println("Tüm kitaplar:");
        for(Book b : kitaplar)
            System.out.println("=========================\n" +
                    "Kitap adı: " +b.getKitapisim()+"\n"+
                    "Yazar adı: " +b.getYazarisim()+"\n"+
                    "Sayfa sayısı: "+ b.getSayfa()+"\n"+
                    "Yayımlanma tarihi: "+b.getYayimtarihi()+"\n=========================\n\n");

        Map<String, String> isimyazar = kitaplar.stream()
                .collect(Collectors.toMap(Book::getKitapisim, Book::getYazarisim));

        List<Book> newkitaplar = kitaplar.stream()
                .filter(book -> book.getSayfa() > 100)
                .collect(Collectors.toList());

        System.out.println("100 sayfadan fazla kitaplar:\n");
        for (Book b : newkitaplar)
            System.out.println(b.getKitapisim() + " -> " + isimyazar.get(b.getKitapisim()));
    }
}
