package cn.ted.design.strategy;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Cat[] a = {new Cat(9),new Cat(5),new Cat(3)};
        Sorter<Cat> sorter = new Sorter<>();
        sorter.sort(a,(o1,o2)->{
            if(o1.getHeight()<o2.getHeight()) return -1;
            else if(o1.getHeight()<o2.getHeight()) return 1;
            return 0;
        });
        System.out.println(Arrays.toString(a));
    }
}
