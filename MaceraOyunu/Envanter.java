package MaceraOyunu;
import MaceraOyunu.Ekipmanlar.*;

public class Envanter
{
    private boolean su,yemek,odun;
    private Silah silah;
    private Zirh zirh;

    public Envanter()
    {
        su=false;
        yemek=false;
        odun=false;
        this.silah=new Silah("Yumruk",0,0,0);
        this.zirh=new Zirh("Zırhsız",0,0,0);
    }

    public Silah getSilah() {
        return silah;
    }

    public Zirh getZirh() {
        return zirh;
    }

    public boolean isSu()
    {
        return su;
    }
    public boolean isYemek()
    {
        return yemek;
    }
    public boolean isOdun()
    {
        return odun;
    }
    public void setSilah(Silah silah)
    {
        this.silah = silah;
    }

    public void setZirh(Zirh zirh) {
        this.zirh = zirh;
    }

    public void setSu(boolean su) {
        this.su = su;
    }
    public void setYemek(boolean yemek) {
        this.yemek = yemek;
    }
    public void setOdun(boolean odun) {
        this.odun = odun;
    }
}
