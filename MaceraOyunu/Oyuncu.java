package MaceraOyunu;
import MaceraOyunu.Karakterler.*;
import java.util.Scanner;

public class Oyuncu
{
    private Envanter envanter=new Envanter();
    private int hasar,hp,para,odulsayisi=0;
    private int tabanhp;
    private String isim;
    public Oyuncu(String isim)
    {
        this.isim=isim;
        karakterSec();
    }
    public void karakterSec()
    {
        Scanner s=new Scanner(System.in);
        Karakterler[] karakterler = {new Samuray(),new Okcu(),new Sovalye()};
        System.out.println("==========================================");
        for(Karakterler karakter : karakterler)
        {
            System.out.printf("%-8s ID:%-2d Hasar:%-2d Sağlık:%-3d Para:%d\n",
                    karakter.isim,
                    karakter.id,
                    karakter.hasar,
                    karakter.hp,
                    karakter.para);
        }
        System.out.println("==========================================");
        System.out.print("Seçtiğiniz karakterin ID'si:");
        int x=s.nextInt();
        for (Karakterler value : karakterler)
            if (x == value.id) {
                this.hp = value.hp;
                this.tabanhp=value.hp;
                this.hasar = value.hasar;
                this.para = value.para;
                System.out.println("Seçtiğiniz karakter:" + value.isim);
            }
    }
    public void oyuncuBilgisi()
    {
        System.out.println("\nOyuncu:" +this.getIsim()
                +" Sağlık:"+this.getHp()
                +" Hasar:"+this.getHasar()
                +" Blok:"+this.getEnvanter().getZirh().getBlok()
                +" Para:"+(this.getPara()));
    }
    public String getIsim() {
        return isim;
    }

    public Envanter getEnvanter() {
        return envanter;
    }

    public int getOdulsayisi()
    {
        return odulsayisi;
    }

    public int getHasar()
    {
        return hasar;
    }
    public int getHp()
    {
        return hp;
    }

    public int getPara() {
        return para;
    }
    public int getTabanhp()
    {
        return tabanhp;
    }
    public void setOdulsayisi(int odulsayisi)
    {
        this.odulsayisi = odulsayisi;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public void setPara(int para) {
        this.para = para;
    }

    public void setHasar(int hasar)
    {
        this.hasar = hasar;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }
}
