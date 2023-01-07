package SigortaYonetimSistemi;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        User u1=new User("Berkay","Zaim","berkay@hotmail.com","1111","Bilgisayar Mühendisi",21);
        User u2=new User("Elon","Musk","elon@gmail.com","5555","Tesla's CEO",50);
        User u3=new User("Cristiano","Ronaldo","cr7@newmail.com","1234","Futbolcu",38);
        AccountManager a = new AccountManager();
        a.kullaniciEkle(new Individual(u1));
        a.kullaniciEkle(new Enterprise(u2));
        a.kullaniciEkle(new Individual(u3));
        System.out.print("Email: ");
        String email=s.nextLine();
        System.out.print("Şifre: ");
        String sifre=s.nextLine();
        for(Account b : a.getKullanicilar())
            b.showUserInfo();
        try
        {
            a.login(sifre,email);
        }
        catch(InvalidAuthenticationException e)
        {
            e.printStackTrace();
        }
    }
}
