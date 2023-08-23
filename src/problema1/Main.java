package problema1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Test Student:");
        StudentRegister register = new StudentRegister();
        register.addStudent(new Student("Alex", 10));
        register.addStudent(new Student("Bob", 7));
        register.addStudent(new Student("Andrei", -3));
        register.addStudent(new Student("&123", 10));

        register.printStudents();
        System.out.println();

        System.out.println("Test saving:");
        register.saveToFile("loader.bin", register.getArray());

        register.addStudent(new Student("Bogdan", 8));
        register.printStudents();
        System.out.println();


        System.out.println("Test loader:");
        register.loadFromFile("loader.bin");
        register.printStudents();
    }
}


