package cn.ted.design.strategy;

public class Cat {
    private final int height;

    public Cat(int h){
        this.height = h;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "height=" + height +
                '}';
    }

}
