package PatikaStore;

import java.util.ArrayList;

public class Notebook extends UrunGrubu
{
    private static int c=1;


    public Notebook(String isim,String marka,double fiyat,double indirimorani,int stok,int hafiza,int ram,double ekran)
    {
        super(c++,isim,marka,fiyat,indirimorani,stok,hafiza,ram,ekran);
        notebooklar.add(this);
    }
}
