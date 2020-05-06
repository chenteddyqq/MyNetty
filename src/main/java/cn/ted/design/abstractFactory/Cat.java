package cn.ted.design.abstractFactory;

public class Cat extends BaseAnimal {
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

    public void eating(){
        System.out.println("cat is eating ...");
    }

}
