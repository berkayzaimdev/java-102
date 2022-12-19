package MaceraOyunu.Mekanlar;
import MaceraOyunu.*;
import MaceraOyunu.Ekipmanlar.*;
import java.util.Scanner;

public class AletDukkani extends NormalMekan
{
    public AletDukkani(Oyuncu oyuncu)
    {
        super(oyuncu,"Alet Dükkanı");
    }
    public boolean gidilenMekan()
    {
        Scanner s=new Scanner(System.in);
        boolean cikis=false;
        System.out.println("--------Mağazaya Hoşgeldiniz!--------");
        while(!cikis)
        {
            System.out.println("\n\n1-Silahlar\n2-Zırhlar\n3-Çıkış Yap");
            int secim;
            do
            {
                System.out.print("Seçiminiz(1,2,3): ");
                secim = s.nextInt();
            }
            while (secim > Silah.silahlar().size()||secim<1);
            switch(secim)
            {
                case 1:
                    silahYaz();
                    silahAl();
                    break;
                case 2:
                    zirhYaz();
                    zirhAl();
                    break;
                case 3:
                    System.out.println("Çıkış sağlandı.");
                    cikis = true;
                    break;
            }
        }
        return true;
    }
    public void zirhYaz()
    {
        System.out.println("\n-------Zırhlar-------");
        for (Zirh z : Zirh.zirhlar())
        {
            System.out.println(z.getId()
                    + "-" + z.getIsim()
                    + ", Bloklama: "
                    + z.getBlok()
                    + ", Fiyatı: " + z.getFiyat());
        }
        System.out.println("0-Çıkış Yap");
    }
    public void silahYaz()
    {
        System.out.println("\n-------Silahlar-------");
        for (Silah s : Silah.silahlar())
        {
            System.out.println(s.getId()
                    + "-" + s.getIsim()
                    + ", Hasar: "
                    + s.getHasar()
                    + ", Fiyatı: " + s.getFiyat());
        }
        System.out.println("0-Çıkış Yap");
    }
    public void zirhAl()
    {
        Scanner s=new Scanner(System.in);
        int secim;
        do
        {
            System.out.print("Paranız:"+getOyuncu().getPara()+"\nZırh Seçiminiz(1,2,3): ");
            secim = s.nextInt();
        }while (secim > Zirh.zirhlar().size()||secim<0);

        if (secim != 0)
        {
            Zirh secilenZirh = Zirh.getzirhId(secim);
            if(secilenZirh != null)
            {
                if (secilenZirh.getFiyat() > getOyuncu().getPara())
                {
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                    getOyuncu().oyuncuBilgisi();
                }
                else
                {
                    System.out.println(secilenZirh.getIsim() + " zırhını aldınız!");
                    getOyuncu().setPara(getOyuncu().getPara() - secilenZirh.getFiyat());
                    System.out.println("Güncel paranız : " + getOyuncu().getPara());
                    System.out.println("Önceki zırhınız : " + getOyuncu().getEnvanter().getZirh().getIsim());
                    getOyuncu().getEnvanter().setZirh(secilenZirh);
                    System.out.println("Yeni silahınız : " + getOyuncu().getEnvanter().getZirh().getIsim());
                }
            }
        }
    }
    public void silahAl()
    {
        Scanner s=new Scanner(System.in);
        int secim = 0;
        do
        {
            System.out.print("Paranız:"+getOyuncu().getPara()+"\nSilah Seçiminiz(1,2,3): ");
            secim = s.nextInt();
        }while (secim > Silah.silahlar().size()||secim<0);

        if (secim != 0)
        {
            Silah secilenSilah = Silah.getsilahId(secim);
            if(secilenSilah != null)
            {
                if (secilenSilah.getFiyat() > getOyuncu().getPara())
                {
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                    getOyuncu().oyuncuBilgisi();
                }
                else
                {
                    System.out.println(secilenSilah.getIsim() + " silahını aldınız!");
                    getOyuncu().setPara(getOyuncu().getPara() - secilenSilah.getFiyat());
                    System.out.println("Güncel paranız : " + getOyuncu().getPara());
                    System.out.println("Önceki silahınız : " + getOyuncu().getEnvanter().getSilah().getIsim());
                    getOyuncu().getEnvanter().setSilah(secilenSilah);
                    getOyuncu().setHasar((getOyuncu().getHasar())+getOyuncu().getEnvanter().getSilah().getHasar());
                    System.out.println("Yeni silahınız : " + getOyuncu().getEnvanter().getSilah().getIsim());
                }
            }
        }
    }
}
