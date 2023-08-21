package problema1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentRegister register = new StudentRegister();
        register.addStudent(new Student("Alex", 10));
        register.addStudent(new Student("Bob", 5));
        register.addStudent(new Student("Andrei", 2));

        register.printStudents();
        System.out.println("////////////////");

        register.saveToFile("loader.bin", register.getArray());

        register.addStudent(new Student("Bogdan", 8));
        register.printStudents();

        System.out.println("////////////////");

        register.loadFromFile("loader.bin");

        register.printStudents();
    }
}


