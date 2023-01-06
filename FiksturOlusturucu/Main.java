package FiksturOlusturucu;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Lig kaç takımlı olsun?: ");
        int takim = s.nextInt();
        List<String> takimlar = new ArrayList<>();
        for (int i=1; i<takim+1; i++)
        {
            System.out.print(i+".Takım: ");
            String team = s.next();
            takimlar.add(team);
        }
        if (takim%2 != 0)
        {
            takimlar.add("Bay");
            takim++;
        }
        List<String> tempTakimlar = new ArrayList<>();
        while(takimlar.size()>0)
        {
            int index = (int)(Math.random()*takimlar.size());
            tempTakimlar.add(takimlar.get(index));
            takimlar.remove(takimlar.get(index));
        }
        takimlar = tempTakimlar;
        int hafta = takim - 1;
        int macsayisi = takim / 2;
        LinkedHashMap<String, ArrayList<ArrayList<String>>> maclar = new LinkedHashMap<>();
        for (int i=0; i<hafta; i++)
        {
            String round = String.valueOf(i + 1);
            maclar.put(round, new ArrayList<ArrayList<String>>());
        }
        for (int i=0; i<hafta; i++)
        {
            ArrayList<String> ev = new ArrayList<>();
            ArrayList<String> dep = new ArrayList<>();

            for (int j=0; j<macsayisi; j++)
            {
                ev.add(takimlar.get(j));
                dep.add(takimlar.get((takim-1)-j));
            }
            String round = String.valueOf(i + 1);
            maclar.get(round).add(ev);
            maclar.get(round).add(dep);
            List<String> yeniTakimlar = new ArrayList<>();
            yeniTakimlar.add(takimlar.get(0));
            yeniTakimlar.add(takimlar.get((takim - 1)));
            for (int k=1; k<takimlar.size() - 1; k++)
                yeniTakimlar.add(takimlar.get(k));

           takimlar = yeniTakimlar;
        }
        for (int i=0; i<2*hafta; i++)
        {
            System.out.println("\n\n      "+(i + 1) + ".Hafta\n===================");
            for (int j=0; j<macsayisi; j++)
            {
                if (hafta>i)
                    System.out.println(maclar.get(String.valueOf(i + 1)).get(0).get(j) + " - " +  maclar.get(String.valueOf(i + 1)).get(1).get(j));
                else
                    System.out.println(maclar.get(String.valueOf(i + 1 - hafta)).get(1).get(j) + " - " +  maclar.get(String.valueOf(i + 1 - hafta)).get(0).get(j));
            }
            System.out.println("===================");
        }
    }
}
