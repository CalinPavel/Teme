package problema1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRegister {

    private List<Student> students;

    public StudentRegister() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if(student.getGrade() != -1 && !student.getName().equals("Invalid"))
            students.add(student);
    }

    public void saveToFile(String filename, List<Student> studentArray) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Student student : studentArray) {
                oos.writeObject(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) throws IOException {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            while (true) {
                try {
                    Object obj = objectIn.readObject();
                    if (obj != null) {
                        if (obj instanceof Student) {
                            Student student = (Student) obj;
                            students.add(student);
                        }
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            objectIn.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStudents() {
        for (Student student : students) {
            System.out.println(student.getName() + " " + student.getGrade());
        }
    }

    public List<Student> getArray(){
        return students;
    }

}
