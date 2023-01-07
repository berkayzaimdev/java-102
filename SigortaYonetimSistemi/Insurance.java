package SigortaYonetimSistemi;
import java.util.Date;

public abstract class Insurance
{
    private String sigortaismi;
    private double sigortaucreti;
    private Date tarih;
    public abstract int calculate();
}
