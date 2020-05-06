package cn.ted.design.abstractFactory;

public class MainTest {

    public static void main(String[] args) throws Exception {

        AnimalFactory a = (AnimalFactory) Class.forName("cn.ted.design.abstractFactory.DeSecondAnimalFactory").getDeclaredConstructor().newInstance();

        BaseAnimal b = a.createAnimal();

        b.eating();
    }
}
