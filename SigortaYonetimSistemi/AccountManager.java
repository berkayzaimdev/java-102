package SigortaYonetimSistemi;

import java.util.ArrayList;
import java.util.TreeSet;

import static SigortaYonetimSistemi.Account.AuthenticationStatus.*;

public class AccountManager
{
    private ArrayList<Account> kullanicilar = new ArrayList<>();
    public Account login(String sifre,String email) throws InvalidAuthenticationException
    {
        Account bulunan = null;
        boolean basarisiz=true;
        for(Account a : kullanicilar)
        {
            if(a.getKullanici().getSifre().equals(sifre)&&a.getKullanici().getEmail().equals(email))
            {
                a.setStatus(SUCCESS);
                bulunan=a;
                basarisiz=false;
                break;
            }
        }
        if(basarisiz)
            throw new InvalidAuthenticationException();
        return bulunan;
    }

    public void kullaniciEkle(Account a)
    {
        this.kullanicilar.add(a);
    }

    public ArrayList<Account> getKullanicilar()
    {
        return this.kullanicilar;
    }
}
