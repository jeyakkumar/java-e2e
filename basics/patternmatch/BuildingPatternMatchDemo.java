package basics.patternmatch;

// Pattern Matching for instanceof (Java 14-17, finalized in Java 17) Eliminates casting boilerplate
public class BuildingPatternMatchDemo {
    
    public String identifyBuilding(Object obj) {
        return switch (obj) {
            case String s -> "This is a building named: " + s;
            case Integer i when i > 0 -> "This is a building with " + i + " floors.";
            case Double d -> "This is a building with height: " + d + " meters.";
            default -> "Unknown building type.";
        };
    }

    public static void main(String[] args) {
        BuildingPatternMatchDemo building = new BuildingPatternMatchDemo();

        System.out.println(building.identifyBuilding("Sky Tower"));
        System.out.println(building.identifyBuilding(15));
        System.out.println(building.identifyBuilding(45.5));
        System.out.println(building.identifyBuilding(true));
    }
}
