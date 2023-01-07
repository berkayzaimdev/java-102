package SigortaYonetimSistemi;

import java.util.ArrayList;
import static SigortaYonetimSistemi.Account.AuthenticationStatus.*;

public abstract class Account implements Comparable<Account>
{
    enum AuthenticationStatus {SUCCESS, FAIL}
    private User kullanici;
    private ArrayList<Insurance> sigortalar=new ArrayList<>();
    private AuthenticationStatus status=FAIL;
    public Account(User kullanici)
    {
        this.kullanici=kullanici;
    }

    public final void showUserInfo()
    {
        System.out.println(
                "\n\n=========================\nİsim:"+kullanici.getIsim()+"\n"
               +"Soyisim:"+kullanici.getSoyisim()+"\n"
               +"Email:"+kullanici.getEmail()+"\n"
               +"Yaş:"+kullanici.getYas()+"\n"
               +"Meslek:"+kullanici.getMeslek()+"\n"
               +"Kullandığı Adresler:\n=========================");
        /*for(Address a : kullanici.getAdreslistesi())
        {
            System.out.println(
                    "Adres Tipi:"+""+"\n"
                    +"Adres Tipi:"+""+"\n"
                    +"Adres Tipi:"+""+"\n"
                    +"Adres Tipi:"+""+"\n"
                    +"Adres Tipi:"+""+"\n"
            );
        }*/
    }

    public AuthenticationStatus getStatus()
    {
        return status;
    }
    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }
    public User getKullanici() {
        return kullanici;
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
