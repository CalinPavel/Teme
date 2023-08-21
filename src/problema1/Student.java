package problema1;

import java.io.Serializable;

class Student implements Serializable {

    private String name;
    private int grade;

    public Student(String name, int grade) {
        try {
            if (grade < 1 || grade > 10) {
                throw new IllegalArgumentException("Grade must be between 1 and 10");
            }
            this.grade = grade;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid grade: " + e.getMessage());
            this.grade=-1;
        }

        try {
            if (!isValidName(name)) {
                throw new IllegalArgumentException("Name cannot contain numbers");
            }
            this.name = name;
        }catch (IllegalArgumentException e){
            System.err.println("Invalid name: " + e.getMessage());
            this.name=null;
        }
    }

    private boolean isValidName(String name) {
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
