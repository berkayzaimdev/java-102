package PatikaStore;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        Marka[] markalar = {
                new Marka("Samsung"),new Marka("Lenovo"),new Marka("Apple"),new Marka("Huawei")
                ,new Marka("Casper"),new Marka("Asus"),new Marka("HP"),new Marka("Xiaomi"),new Marka("Monster")};
        PatikaStore ps = new PatikaStore();
        ps.basla();
    }
}
