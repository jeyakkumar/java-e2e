package basics.sealedclass;

public class SealedClassDemo {
    
    private static Animal animal = new Dog();
    private static Animal anotherAnimal = new Cat();
    private static Animal thirdAnimal = new Bird();

    private String animalSound(Animal animal) {
        return switch (animal) {
            case Dog d -> "Woof";
            case Cat c -> "Meow";
            case Bird b -> "Tweet";
        };
    }

    public static void main(String[] args) {
        System.out.println("Sealed Class Demo");
        SealedClassDemo demo = new SealedClassDemo();
        // In a switch: compiler ensures all subtypes are handled
        var sound = demo.animalSound(animal);
        var anotherSound = demo.animalSound(anotherAnimal);
        var thirdSound = demo.animalSound(thirdAnimal);
        System.out.println("Dog sound: " + sound);
        System.out.println("Cat sound: " + anotherSound);
        System.out.println("Bird sound: " + thirdSound);
    }
}
