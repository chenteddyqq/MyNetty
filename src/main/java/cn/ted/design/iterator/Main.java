package cn.ted.design.iterator;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Collection_<String> list = new ArrayList_<>();
        for (int i=0;i<15;i++){
            list.add("s"+i);
        }
        System.out.println(list.size());
        Iterator_ it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}


