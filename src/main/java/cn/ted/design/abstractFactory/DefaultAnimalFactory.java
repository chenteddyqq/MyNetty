package cn.ted.design.abstractFactory;

public class DefaultAnimalFactory extends AnimalFactory {
    @Override
    public BaseAnimal createAnimal() {
        return new Cat(1);
    }
}
