package PatikaKlonu.Helper;

public class Item
{
    private int key;
    private String value;

    public Item(int key,String value)
    {
        this.key=key;
        this.value=value;
    }
    public Item(String key,int value)
    {
        this.key=value;
        this.value=key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
