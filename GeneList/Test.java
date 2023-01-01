package GeneList;

import java.util.Arrays;

public class Test
{
    public static void main(String[] args)
    {
        MyList<Integer> lis = new MyList<>();
        for(int i=1;i<20;i++)
            lis.add(i);
        System.out.println("İlk liste:"+lis.toString());
        lis.remove(3);
        System.out.println("3.indisteki eleman silindikten sonra ilk liste:"+lis.toString());
        lis.set(0,40);
        System.out.println("0.indise '40' atıldıktan sonra ilk liste:"+lis.toString());
        System.out.println("İlk listede null'un soldan indisi:"+lis.indexOf(null));
        System.out.println("İlk listede null'un sağdan indisi:"+lis.lastIndexOf(null));
        System.out.println("İlk listenin boyutu:"+lis.getCapacity());
        MyList<Integer> lis2 = lis.sublist(5,10);
        System.out.println("İkinci liste:"+lis2.toString());
        System.out.println("İkinci listenin boyutu:"+lis2.getCapacity());
        System.out.println("İkinci listenin 2.indisindeki eleman:"+lis2.get(2));
        System.out.println("İkinci listenin 10.indisindeki eleman:"+lis2.get(10));
        System.out.println("İkinci listeden türeyen Integer dizisi:"+ Arrays.toString(lis2.toArray()));
        System.out.println("İkinci listenin boş olma durumu:"+lis2.isEmpty());
        lis2.clear();
        System.out.println("Temizlenmiş ikinci liste:"+lis2.toString());
        System.out.println("İkinci listenin boş olma durumu:"+lis2.isEmpty());
        System.out.println("İlk listede 19'un bulunma durumu:"+lis.contains(19));
        System.out.println("İlk listede 21'in bulunma durumu:"+lis.contains(21));
    }
}
