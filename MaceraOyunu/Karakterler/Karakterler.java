package MaceraOyunu.Karakterler;

public abstract class Karakterler
{
    public int id,hasar,hp,para;
    public String isim;
    Karakterler(int id,int hasar,int hp,int para,String isim)
    {
        this.id=id;
        this.hasar=hasar;
        this.hp=hp;
        this.para=para;
        this.isim=isim;
    }
}
