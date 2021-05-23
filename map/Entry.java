package map;

public class Entry {
    public String k;
    public Integer value;
    public Entry next;
    public Entry(String k, Integer value) {
        this.k = k;
        this.value = value;
    }
}
