package PatikaStore;

public class Telefon extends UrunGrubu
{
    protected static int c=1;
    private int pil,kamera;
    private String renk;

    public Telefon(String isim,String marka,double fiyat,double indirimorani,int stok,int hafiza,int pil,int ram,double ekran,String renk,int kamera)
    {
        super(c++,isim,marka,fiyat,indirimorani,stok,hafiza,ram,ekran);
        this.pil=pil;
        this.renk=renk;
        this.kamera=kamera;
        telefonlar.add(this);
    }

    public int getPil() {
        return pil;
    }

    public int getKamera() {
        return kamera;
    }

    public String getRenk() {
        return renk;
    }

    public static void setC(int c) {
        Telefon.c = c;
    }
}
