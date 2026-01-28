import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LocalVariablesDemo {


    // old standard

    List<String> names = new ArrayList<>();
    Map<String, Integer> scores = new HashMap<>();

   
    private void testLocalVar() {
         //define local variables

        var names_var = new ArrayList<String>();
        names_var.add("John");
        names_var.add("Jane");

        var scores_var = new HashMap<String, Integer>();
        scores_var.put("John", 90);
        scores_var.put("Jane", 95);
        // Print local variable values
        for (var name : names_var) {    
            System.out.println("Name: " + name);
        }
        for (var entry : scores_var.entrySet()) {
            System.out.println("Name: " + entry.getKey() + ", Score: " + entry.getValue());
        }

    }   

    private void classLevelOldStandard() {
        names.add("Alice");
        names.add("Bob");   
        scores.put("Alice", 85);
        scores.put("Bob", 88);


        // Print instance variable values
        for (String name : names) {
            System.out.println("Name: " + name);
        }
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println("Name: " + entry.getKey() + ", Score: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        LocalVariablesDemo demo = new LocalVariablesDemo();
        demo.testLocalVar();
        demo.classLevelOldStandard();
    }
}