package MaceraOyunu;
import java.util.Scanner;
import MaceraOyunu.Mekanlar.*;

public class Oyun {
    Oyuncu oyuncu;
    Mekan mekan;
    public void basla() {
        Scanner s = new Scanner(System.in);
        System.out.println("Macera Oyununa Hoş Geldiniz!");
        System.out.print("Başlamak için karakterinizin ismini girin:");
        String isim = s.nextLine();
        oyuncu = new Oyuncu(isim);
        mekan = null;
        while (true)
        {
            oyuncu.oyuncuBilgisi();
            System.out.println();
            System.out.println("-------Bölgeler-------");
            System.out.println();
            System.out.println("1-Güvenli Ev    -->    Burada düşman yoktur.");
            System.out.println("2-Eşya Dükkanı  -->    Silah ve zırh satın alabilirsiniz.");
            System.out.println("3-Mağara        -->    Düşman:Zombi    Ödül:Yemek");
            System.out.println("4-Orman         -->    Düşman:Vampir   Ödül:Odun");
            System.out.println("5-Nehir         -->    Düşman:Ayı      Ödül:Su");
            System.out.println("6-Maden         -->    Düşman:Yılan    Ödül:Para,silah,zırh");
            System.out.println("0-Oyunu Kapat");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            oyuncu.oyuncuBilgisi();
            int secim = s.nextInt();
            switch (secim)
            {
                case 1:
                    mekan = new GuvenliEv(oyuncu);
                    break;
                case 2:
                    mekan = new AletDukkani(oyuncu);
                    break;
                case 3:
                    mekan = new Magara(oyuncu);
                    break;
                case 4:
                    mekan = new Orman(oyuncu);
                    break;
                case 5:
                    mekan = new Nehir(oyuncu);
                    break;
                case 6:
                    mekan = new Maden(oyuncu);
                    break;
                case 0:
                    mekan = null;
                    break;
                default:
                    mekan = new GuvenliEv(oyuncu);
                    break;
            }
            if (mekan==null)
                break;
            if (!mekan.gidilenMekan())
            {
                System.out.println("Oyunu kaybettiniz..");
                break;
            }
            if (oyuncu.getOdulsayisi() == 3)
            {
                System.out.println("Tebrikler, oyunu kazandınız!!");
                break;
            }
        }
    }
}
