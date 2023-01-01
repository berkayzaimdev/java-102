package GeneList;
import java.util.Arrays;

public class MyList<T>
{
    private T[] dizi;
    private int capacity;
    public MyList()
    {
        this.capacity=10;
        olustur();
    }
    public MyList(int capacity)
    {
        this.capacity=capacity;
        olustur();
    }
    public void olustur()
    {
        dizi=(T[]) new Object[capacity];
    }
    public int size()
    {
        int count=0;
        for(T i : dizi)
        {
            if(i!=null)
                count++;
            else
                break;
        }
        if(count==capacity)
        {
            System.out.println("deneme");
            T[] dizitemp=dizi;
            capacity*=2;
            olustur();
            for(int i=0;i<dizitemp.length;i++)
                dizi[i]=dizitemp[i];
            System.out.println("yeni dizi:"+toString());
        }
        return count;
    }
    public void add(T data)
    {
        int indis=size();
        dizi[indis]=data;
    }
    public int getCapacity()
    {
        return capacity;
    }
    public T get(int index)
    {
        return index<0||index>=capacity?null:dizi[index];
    }
    public void remove(int index)
    {
        if(index<0||index>=capacity)
            System.out.println("null");
        else
        {
            for(int i=index;i<capacity-1;i++)
            {
                dizi[i] = dizi[i + 1];
                if (i == capacity - 2)
                    dizi[i + 1] = null;
            }
        }
    }
    public void set(int index,T data)
    {
        if(index>=getCapacity()||index<0)
            System.out.println("null");
        else
            dizi[index]=data;
    }
    @Override
    public String toString()
    {
        String str="[";
        for(T x : dizi)
            str+=x+",";
        char[] temp=str.toCharArray();
        temp[temp.length-1]=']';
        str=String.valueOf(temp);
        return str;
    }
    public int indexOf(T data)
    {
        for(int i=0;i<capacity;i++)
            if(dizi[i]==data)
                return i;
        return -1;
    }
    public int lastIndexOf(T data)
    {
        for(int i=capacity-1;i>=0;i--)
            if(dizi[i]==data)
                return i;
        return -1;
    }

    public boolean isEmpty()
    {
        for(T x:dizi)
            if(x!=null)
                return false;
        return true;
    }

    public T[] toArray()
    {
        T[] yenidizi = (T[]) new Object[capacity];
        for(int i=0;i<capacity;i++)
            yenidizi[i]=dizi[i];
        return (T[]) Arrays.copyOf(yenidizi, yenidizi.length, yenidizi.getClass());
    }
    public void clear()
    {
        for(int i=0;i<getCapacity();i++)
            dizi[i]=null;
    }
    public MyList<T> sublist(int start,int finish)
    {
        MyList<T> yeni = new MyList<>(finish-start+1);
        for(int i=start;i<finish;i++)
            yeni.add(dizi[i]);
        return yeni;
    }
    public boolean contains(T data)
    {
        for(T x : dizi)
            if(x==data)
                return true;
        return false;
    }
}
