package MaceraOyunu.Canavar;

public abstract class Canavar
{
    private String isim,odul;
    private int hasar,hp,para;
    private final int tabanhp;
    Canavar(String isim,int hasar,int hp,int para,String odul)
    {
        this.isim=isim;
        this.hasar=hasar;
        this.hp=hp;
        this.para=para;
        this.odul=odul;
        this.tabanhp=hp;
    }

    public String getIsim() {
        return isim;
    }

    public String getOdul() {
        return odul;
    }

    public int getHasar()
    {
        return hasar;
    }
    public int getHp()
    {
        return hp;
    }
    public int getPara()
    {
        return para;
    }
    public void setHasar(int hasar)
    {
        this.hasar = hasar;
    }
    public int getTabanhp()
    {
        return tabanhp;
    }
    public void setHp(int hp)
    {
        this.hp = hp;
    }
}
