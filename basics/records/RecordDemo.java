package basics.records;

public class RecordDemo {
    
    private void createAndDisplayRecord() {
        Person person = new Person("Alice", 30);
        
        System.out.println("Name: " + person.name());
        System.out.println("Age: " + person.age());
    }
    public static void main(String[] args) {
        RecordDemo demo = new RecordDemo();
        demo.createAndDisplayRecord();
    }
}
