package SigortaYonetimSistemi;

public class InvalidAuthenticationException extends Exception
{
    public InvalidAuthenticationException()
    {
        super("HATA: E-posta veya şifre yanlış.");
    }
}
