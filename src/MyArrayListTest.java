import java.util.Iterator;

public class MyArrayListTest {

    public static void main(String[] args) {
        MyList<String> list = new MyArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++){
            list.add(String.valueOf(c));
        }
        Iterator<String> iter = list.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next()); }
        list.add("ZZZzz");
        iter = list.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next()); }
        System.out.println(list.get(list.size()-1));
    }

}
