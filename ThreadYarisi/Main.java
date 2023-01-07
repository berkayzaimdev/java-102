package ThreadYarisi;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Integer> sayilar = new ArrayList<>();
        for(int i = 1; i <= 10000; i++)
            sayilar.add(i);
        List<List<Integer>> parcalar = new ArrayList<>();
        for(int i=0;i<sayilar.size();i+=2500)
            parcalar.add(sayilar.subList(i, Math.min(i+2500, sayilar.size())));

        ProcessPartitionTask tt1=new ProcessPartitionTask(parcalar.get(0));
        ProcessPartitionTask tt2=new ProcessPartitionTask(parcalar.get(1));
        ProcessPartitionTask tt3=new ProcessPartitionTask(parcalar.get(2));
        ProcessPartitionTask tt4=new ProcessPartitionTask(parcalar.get(3));

        Thread t1 = new Thread(tt1);
        t1.start();

        Thread t2 = new Thread(tt2);
        t2.start();

        Thread t3 = new Thread(tt3);
        t3.start();

        Thread t4 = new Thread(tt4);
        t4.start();

        try
        {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        ArrayList<Integer> tek = new ArrayList<>();
        tek.addAll(tt1.getTek());
        tek.addAll(tt2.getTek());
        tek.addAll(tt3.getTek());
        tek.addAll(tt4.getTek());

        ArrayList<Integer> cift = new ArrayList<>();
        cift.addAll(tt1.getCift());
        cift.addAll(tt2.getCift());
        cift.addAll(tt3.getCift());
        cift.addAll(tt4.getCift());

        int c=0;

        for (Integer i : tek)
        {
            System.out.println(i);
            c++;
        }

        for (Integer i : cift)
        {
            System.out.println(i);
            c++;
        }

        System.out.println("Toplam sayı: "+c);
    }
}
class ProcessPartitionTask implements Runnable
{
    private List<Integer> parca;
    private ArrayList<Integer> tek = new ArrayList<>(2500),cift = new ArrayList<>(2500);
    public ProcessPartitionTask(List<Integer> parca)
    {
        this.parca = parca;
    }

    @Override
    public void run()
    {
        for (Integer i : parca)
        {
            if (i % 2 == 0)
                cift.add(i);
            else
                tek.add(i);
        }
    }
    public ArrayList<Integer> getTek()
    {
        return tek;
    }

    public ArrayList<Integer> getCift() {
        return cift;
    }
}
