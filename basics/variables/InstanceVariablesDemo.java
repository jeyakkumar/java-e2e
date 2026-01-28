public class InstanceVariablesDemo {
    
    // Instance variables
    private String instanceVar1 = "Instance Variable 1";
    private int instanceVar2 = 42;

    public void displayInstanceVariables() {
        System.out.println("Instance Variable 1: " + instanceVar1);
        System.out.println("Instance Variable 2: " + instanceVar2);
    }

    public static void main(String[] args) {
        InstanceVariablesDemo demo = new InstanceVariablesDemo();
        demo.displayInstanceVariables();
    }
}
