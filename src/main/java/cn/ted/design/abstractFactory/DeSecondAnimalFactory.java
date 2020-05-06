package cn.ted.design.abstractFactory;

public class DeSecondAnimalFactory extends AnimalFactory {
    @Override
    public BaseAnimal createAnimal() {
        return new Dog();
    }
}
