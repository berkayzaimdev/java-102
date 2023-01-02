package PatikaStore;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static PatikaStore.Marka.markalar;
import static PatikaStore.Notebook.*;

public class PatikaStore
{
    public void basla()
    {
        Scanner s = new Scanner(System.in);
        int secim;
        do
        {
            System.out.println("\n\nPatikaStore Ürün Yönetim Paneli!");
            System.out.println("--------------------------------");
            System.out.println("1 - Notebook İşlemleri");
            System.out.println("2 - Cep Telefonu İşlemleri");
            System.out.println("3 - Marka Listele");
            System.out.println("0 - Çıkış Yap");
            System.out.print("Seçiminiz:");
            secim = s.nextInt();
            switch(secim)
            {
                case 1:
                    notebookSec();
                    break;
                case 2:
                    telefonSec();
                    break;
                case 3:
                    Comparator<Marka> markaComparator = new Comparator<Marka>()
                    {
                        public int compare(Marka p1, Marka p2) {
                            return p1.getIsim().compareTo(p2.getIsim());
                        }
                    };
                    Arrays.sort(markalar,markaComparator);
                    System.out.print("\nMarkalarımız\n============\n");
                    for(Marka m : markalar)
                        System.out.println("-"+m.getIsim());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lütfen geçerli bir seçim yapınız!");
                    break;
            }
        }
        while(secim!=0);
        System.out.println("Çıkışınız sağlandı.");
    }

    public void notebookSec()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("\n\nNotebook İşlemleri");
        System.out.println("------------------");
        System.out.println("1 - Yeni Notebook ekle");
        System.out.println("2 - Markaya göre listele");
        System.out.println("3 - ID'ye göre listele");
        System.out.println("4 - ID'ye göre sil");
        System.out.println("0 - Geri Dön");
        System.out.print("Seçiminiz:");
        int secim = s.nextInt();
        switch(secim)
        {
            case 1:
                System.out.print("Marka: ");
                String marka;
                marka = s.next();
                boolean varMi = false;
                for(Marka m : markalar)
                    if(marka.equals(m.getIsim()))
                    {
                        varMi = true;
                        break;
                    }
                if(!varMi)
                {
                    System.out.println("Geçersiz bir marka adı girdiniz!");
                    break;
                }
                System.out.print("Ürün adı: ");
                String isim = s.next();
                System.out.print("Fiyat: ");
                double fiyat = s.nextDouble();
                System.out.print("İndirim oranı: ");
                double indirimorani = s.nextDouble();
                System.out.print("Stok miktarı: ");
                int stok = s.nextInt();
                System.out.print("Depolama boyutu: ");
                int hafiza = s.nextInt();
                System.out.print("RAM boyutu: ");
                int ram = s.nextInt();
                System.out.print("Ekran boyutu: ");
                double ekran = s.nextDouble();
                new Notebook(isim,marka,fiyat,indirimorani,stok,hafiza,ram,ekran);
                System.out.println("Yeni Notebook ekleme başarılı!");
                break;
            case 2:
                System.out.print("Marka: ");
                marka = s.next();
                System.out.println("----------------------------------------------------------------------------------------------------");
                System.out.println("| ID | Ürün Adı                      | Fiyat     | Marka     | Depolama  | Ekran     | RAM         |");
                System.out.println("----------------------------------------------------------------------------------------------------");
                for(Notebook n : notebooklar)
                    if(n.getMarka().equals(marka))
                    {
                        System.out.printf("| %-2d | %-29s | %-12.1f | %-9s | %-7d | %-11.1f | %-8d |\n",
                                n.getId(), n.getIsim(), n.getFiyat(), n.getMarka(), n.getHafiza(),
                                n.getEkran(), n.getRam());
                    }
                System.out.println("\n----------------------------------------------------------------------------------------------------");
                break;
            case 3:
                System.out.println("----------------------------------------------------------------------------------------------------");
                System.out.println("| ID | Ürün Adı                      | Fiyat     | Marka     | Depolama  | Ekran     | RAM         |");
                System.out.println("----------------------------------------------------------------------------------------------------");
                for(Notebook n : notebooklar)
                    System.out.printf("| %-2d | %-29s | %-12.1f | %-9s | %-7d | %-11.1f | %-8d |\n",
                            n.getId(), n.getIsim(), n.getFiyat(), n.getMarka(), n.getHafiza(),
                            n.getEkran(), n.getRam());
                System.out.println("----------------------------------------------------------------------------------------------------");
                break;
            case 4:
                sil(notebooklar);
                break;
            case 0:
                break;
            default:
                System.out.println("Lütfen geçerli bir seçim yapınız!");
                break;
        }
    }

    public void telefonSec() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n\nTelefon İşlemleri");
        System.out.println("------------------");
        System.out.println("1 - Yeni Telefon ekle");
        System.out.println("2 - Markaya göre listele");
        System.out.println("3 - ID'ye göre listele");
        System.out.println("4 - ID'ye göre sil");
        System.out.println("0 - Geri Dön");
        System.out.print("Seçiminiz:");
        int secim = s.nextInt();
        switch (secim) {
            case 1:
                System.out.print("Marka: ");
                String marka;
                marka = s.next();
                boolean varMi = false;
                for (Marka m : markalar)
                    if (marka.equals(m.getIsim())) {
                        varMi = true;
                        break;
                    }
                if (!varMi) {
                    System.out.println("Geçersiz bir marka adı girdiniz!");
                    break;
                }
                System.out.print("Ürün adı: ");
                String isim = s.next();
                System.out.print("Fiyat: ");
                double fiyat = s.nextDouble();
                System.out.print("İndirim oranı: ");
                double indirimorani = s.nextDouble();
                System.out.print("Stok miktarı: ");
                int stok = s.nextInt();
                System.out.print("Depolama boyutu: ");
                int hafiza = s.nextInt();
                System.out.print("Pil boyutu: ");
                int pil = s.nextInt();
                System.out.print("RAM boyutu: ");
                int ram = s.nextInt();
                System.out.print("Ekran boyutu: ");
                double ekran = s.nextDouble();
                System.out.print("Renk adı: ");
                String renk = s.next();
                System.out.print("Kamera megapiksel sayısı: ");
                int kamera = s.nextInt();
                new Telefon(isim, marka, fiyat, indirimorani, stok, hafiza, pil, ram, ekran, renk, kamera);
                System.out.println("Yeni Telefon ekleme başarılı!");
                break;
            case 2:
                System.out.print("Marka: ");
                marka = s.next();
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("| ID | Ürün Adı                      | Fiyat     | Marka     | Depolama  | Ekran     | Kamera    | Pil       | RAM       | Renk      | ");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                for (Telefon t : telefonlar)
                    if (t.getMarka().equals(marka)) {
                        System.out.printf("| %-2d | %-29s | %-9.1f | %-9s | %-9d | %-9.1f | %-9d | %-9d | %-9d | %-9s |\n",
                                t.getId(), t.getIsim(), t.getFiyat(), t.getMarka(), t.getHafiza(),
                                t.getEkran(), t.getKamera(),t.getPil(), t.getRam(),t.getRenk());
                    }
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                break;
            case 3:
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("| ID | Ürün Adı                      | Fiyat     | Marka     | Depolama  | Ekran     | Kamera    | Pil       | RAM       | Renk      | ");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                for (Telefon t : telefonlar)
                        System.out.printf("| %-2d | %-29s | %-9.1f | %-9s | %-9d | %-9.1f | %-9d | %-9d | %-9d | %-9s |\n",
                                t.getId(), t.getIsim(), t.getFiyat(), t.getMarka(), t.getHafiza(),
                                t.getEkran(), t.getKamera(),t.getPil(), t.getRam(),t.getRenk());
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                break;
            case 4:
                sil(telefonlar);
                break;
            case 0:
                break;
            default:
                System.out.println("Lütfen geçerli bir seçim yapınız!");
                break;
        }
    }
}
