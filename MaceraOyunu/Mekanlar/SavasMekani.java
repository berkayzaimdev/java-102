package MaceraOyunu.Mekanlar;
import MaceraOyunu.*;
import MaceraOyunu.Canavar.*;
import MaceraOyunu.Ekipmanlar.*;

import java.util.Random;
import java.util.Scanner;

public abstract class SavasMekani extends Mekan
{
    Canavar canavar;
    public SavasMekani(Oyuncu oyuncu,String isim,Canavar canavar)
    {
        super(oyuncu,isim);
        this.canavar=canavar;
    }
    public boolean odulkontrol()
    {
        String mesaj="Bu mekandaki ödülü zaten aldınız! Başka bir mekan seçiniz..";
        switch (canavar.getOdul())
        {
            case "su":
                if(getOyuncu().getEnvanter().isSu())
                {
                    System.out.println(mesaj);
                    return true;
                }
                else
                    return false;
            case "yemek":
                if(getOyuncu().getEnvanter().isYemek())
                {
                    System.out.println(mesaj);
                    return true;
                }
                else
                    return false;
            case "odun":
                if(getOyuncu().getEnvanter().isOdun())
                {
                    System.out.println(mesaj);
                    return true;
                }
                else
                    return false;
            default:
                return false;
        }
    }
    public boolean gidilenMekan()
    {
        if(!odulkontrol())
        {
            Scanner s = new Scanner(System.in);
            Random r = new Random();
            int dusmansayisi=r.nextInt(1,4);
            System.out.println("Dikkat! Mekanda " + dusmansayisi + " tane " + this.canavar.getIsim() + " var!");
            System.out.print("Savaş veya Kaç (S,K):");
            String sec =s.nextLine().toUpperCase();
            if(sec.equals("S"))
            {
                for(int i=1;i<=dusmansayisi;i++)
                {
                    canavar.setHp(canavar.getTabanhp());
                    boolean baslayan=r.nextBoolean();
                    durumYaz();
                    System.out.println("Dövüşe başlayan:"+(baslayan?getOyuncu().getIsim():canavar.getIsim())+"\n");
                    while(canavar.getHp()>0)
                    {
                        System.out.print("Vur veya Kaç (V,K): ");
                        String sec2 = s.nextLine().toUpperCase();
                        if(sec2.equals("K"))
                            return true;
                        if(baslayan)
                        {
                            oyuncuSaldir(i);
                            if(canavar.getHp()==0)
                                break;
                            canavarSaldir();
                            if(getOyuncu().getHp()==0)
                                break;
                        }
                        else
                        {
                            canavarSaldir();
                            if(getOyuncu().getHp()==0)
                                break;
                            oyuncuSaldir(i);
                        }
                        if(canavar.getHp()!=0&&getOyuncu().getHp()!=0)
                            durumYaz();
                    }
                    if(getOyuncu().getHp()==0)
                        break;
                    System.out.println(i+"."+canavar.getIsim()+" Yenildi!");
                    if(canavar.getPara()>0)
                        System.out.println("Oyuncu parası "+canavar.getPara()+" arttı!");
                    getOyuncu().setPara(getOyuncu().getPara()+canavar.getPara());
                }
            }
            else
                return true;
            if(getOyuncu().getHp()!=0)
            {
                String odul = canavar.getOdul();
                if (odul=="")
                {
                    int rand=r.nextInt(100);
                    if(rand<=15)
                    {
                        if (rand <= 3)
                        {
                            this.getOyuncu().getEnvanter().setSilah(Silah.getsilahId(3));
                            getOyuncu().setHasar((getOyuncu().getHasar())+getOyuncu().getEnvanter().getSilah().getHasar());
                            odul=this.getOyuncu().getEnvanter().getSilah().getIsim();
                        }
                        else if (rand <= 7.5)
                        {
                            this.getOyuncu().getEnvanter().setSilah(Silah.getsilahId(2));
                            getOyuncu().setHasar((getOyuncu().getHasar())+getOyuncu().getEnvanter().getSilah().getHasar());
                            odul=this.getOyuncu().getEnvanter().getSilah().getIsim();
                        }
                        else
                        {
                            this.getOyuncu().getEnvanter().setSilah(Silah.getsilahId(1));
                            getOyuncu().setHasar((getOyuncu().getHasar())+getOyuncu().getEnvanter().getSilah().getHasar());
                            odul=this.getOyuncu().getEnvanter().getSilah().getIsim();
                        }
                    }
                    else if(rand>15&&rand<=30)
                    {
                        if (rand <= 18)
                        {
                            this.getOyuncu().getEnvanter().setZirh(Zirh.getzirhId(3));
                            odul = this.getOyuncu().getEnvanter().getZirh().getIsim();
                        }
                        else if(rand <= 22.5)
                        {
                            this.getOyuncu().getEnvanter().setZirh(Zirh.getzirhId(3));
                            odul = this.getOyuncu().getEnvanter().getZirh().getIsim();
                        }
                        else
                        {
                            this.getOyuncu().getEnvanter().setZirh(Zirh.getzirhId(3));
                            odul = this.getOyuncu().getEnvanter().getZirh().getIsim();
                        }
                        odul+=" Zırh";
                    }
                    else if(rand>30&&rand<=55)
                    {
                        if (rand <= 35)
                        {
                            this.getOyuncu().setPara(this.getOyuncu().getPara() + 10);
                            odul+="10";
                        }
                        else if (rand <= 42.5)
                        {
                            this.getOyuncu().setPara(this.getOyuncu().getPara() + 5);
                            odul+="5";
                        }
                        else
                        {
                            this.getOyuncu().setPara(this.getOyuncu().getPara() + 1);
                            odul+="1";
                        }
                        odul+=" para";
                    }
                    else
                        getOyuncu().setOdulsayisi(getOyuncu().getOdulsayisi()-1);
                }
                System.out.println("Tüm düşmanlar mağlup edildi! "
                        +(odul==""
                        ?"Ödül kazanılamadı."
                        :"Kazanılan ödül: "+odul));
                getOyuncu().setOdulsayisi(getOyuncu().getOdulsayisi()+1);
                switch (canavar.getOdul())
                {
                    case "su" -> getOyuncu().getEnvanter().setSu(true);
                    case "yemek" -> getOyuncu().getEnvanter().setYemek(true);
                    case "odun" -> getOyuncu().getEnvanter().setOdun(true);
                }
                return true;
            }
            else
                return false;
        }
        else
            return true;
    }
    public void oyuncuSaldir(int i)
    {
        int oyuncuatk=getOyuncu().getHasar();
        canavar.setHp(canavar.getHp()-oyuncuatk);
        int canavardmg=canavar.getHp()>=0?oyuncuatk:canavar.getHp()+oyuncuatk;
        if(canavar.getHp()<0)
            canavar.setHp(0);
        System.out.println(i+"."
                +canavar.getIsim()
                +" "
                +canavardmg
                +" hasar aldı!");
    }
    public void canavarSaldir()
    {
        int bloklanmis = canavar.getHasar()-getOyuncu().getEnvanter().getZirh().getBlok();
        getOyuncu().setHp(bloklanmis>0?getOyuncu().getHp()-bloklanmis:0);
        int oyuncudmg=getOyuncu().getHp()>=0
                ?bloklanmis
                :getOyuncu().getHp()+bloklanmis;
        if(getOyuncu().getHp()<0)
            getOyuncu().setHp(0);
        System.out.println(
                getOyuncu().getIsim()
                        +" "
                        +oyuncudmg
                        +" hasar aldı!");
    }
    public void durumYaz()
    {
        getOyuncu().oyuncuBilgisi();
        System.out.println("Düşman:" +canavar.getIsim()
                +" Sağlık:"+canavar.getHp()
                +" Hasar:"+canavar.getHasar()+"\n"
        );
    }
}
