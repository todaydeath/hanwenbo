import java.util.ArrayList;
import java.util.HashSet;

public class Test {
    public static void main(String[] args){
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(1);
        Integer integer = new Integer(2);
        Integer integer1 = new Integer(2);
        Integer integer2 = new Integer(1);
        arrayList.add(integer);
        arrayList.add(integer1);
        arrayList.add(integer2);

        HashSet hashSet = new HashSet();
        for(int i=0;i<arrayList.size();i++){
            hashSet.add(arrayList.get(i));
        }

        System.out.println(hashSet);
    }
}
