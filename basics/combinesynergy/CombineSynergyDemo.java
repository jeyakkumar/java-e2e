package basics.combinesynergy;

public class CombineSynergyDemo {

    //pattern: Combine Synergy of Sealed Classes and Records
    private String describeShape(Shape shape) {
        return switch (shape) {
            case Circle (var r) -> "Circle with radius: " + r;
            case Rectangle (var w, var h) -> "Rectangle with width: " + w + " and height: " + h;
        };
    }
    public static void main(String[] args) {
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);

        CombineSynergyDemo demo = new CombineSynergyDemo();
        System.out.println(demo.describeShape(circle));
        System.out.println(demo.describeShape(rectangle));
    }
}
