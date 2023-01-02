package PatikaStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static PatikaStore.Notebook.*;
import static PatikaStore.Telefon.*;


public abstract class UrunGrubu
{
    private int id,stok,hafiza,ram;
    private String isim,marka;
    private double fiyat,indirimorani,ekran;
    static ArrayList<Notebook> notebooklar = new ArrayList<>();
    static ArrayList<Telefon> telefonlar = new ArrayList<>();

    public UrunGrubu(int id,String isim,String marka,double fiyat,double indirimorani,int stok,int hafiza,int ram,double ekran)
    {
        this.id=id;
        this.isim=isim;
        this.marka=marka;
        this.fiyat=fiyat;
        this.indirimorani=indirimorani;
        this.stok=stok;
        this.hafiza=hafiza;
        this.ram=ram;
        this.ekran=ekran;
    }

    public static <T extends UrunGrubu> void sil(ArrayList<T> lis)
    {
        Scanner s = new Scanner(System.in);
        System.out.print("ID: ");
        boolean varMi=false;
        int id = s.nextInt();
        Iterator<T> iterator = lis.iterator();
        while(iterator.hasNext()) {
            T n = iterator.next();
            if (id == n.getId())
            {
                iterator.remove();
                varMi = true;
                break;
            }
        }
        if(!varMi)
            System.out.println("Belirtilen ID'ye sahip bir ürün bulunmuyor!");
        else
            System.out.println("Ürün silme işlemi başarılı!");
    }
    public String getMarka()
    {
        return marka;
    }

    public int getId() {
        return id;
    }

    public String getIsim() {
        return isim;
    }
    public double getFiyat() {
        return fiyat;
    }
    public int getHafiza() {
        return hafiza;
    }
    public int getRam() {
        return ram;
    }
    public double getEkran() {
        return ekran;
    }

    public static int getC() {
        return c;
    }
    public static void setC(int c) {
        c = c;
    }
}
