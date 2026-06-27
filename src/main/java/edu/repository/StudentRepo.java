package edu.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import edu.model.Student;

public class StudentRepo {

    private final ArrayList<Student> students = new ArrayList<>();

    private static final String filePath = "Java-OOP-CLI-Application/src/main/java/edu/file/students.txt";  // Path to the file

    public StudentRepo() {
        loadStudentsFromFile(); // Load students from file when the repository is initialized
    }

    // Method to load students from a file
    public void loadStudentsFromFile() {
        students.clear();

        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 8) {
                    Student student = new Student(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4],
                            Integer.parseInt(parts[5]),
                            Integer.parseInt(parts[6]),
                            parts[7]
                    );

                    students.add(student);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading students: " + e);
        }
    }

    // Method to save students to a file
    public void saveStudentsToFile() {

        File file = new File(filePath);
        file.getParentFile().mkdirs();


        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
            for (Student student : students) {
                writer.write(student.getStudentId() + ","
                        + // studentId
                        student.getRegistrationNumber() + ","
                        + // registrationNumber
                        student.getIndexNumber() + ","
                        + // indexNumber
                        student.getName() + ","
                        + // name
                        student.getDegreeProgram() + ","
                        + // degreeProgram
                        student.getCurrentYear() + ","
                        + // currentYear
                        student.getCurrentSemester() + ","
                        + // currentSemester
                        student.getEmail());
                writer.newLine();
            }
            System.out.println("Students saved to file successfully.");
        } catch (Exception e) {
            System.out.println("Error saving students: " + e);
        }
    }

    // Method to get all students
    public ArrayList<Student> getStudents() {
        loadStudentsFromFile(); // Load students from file before returning
        return students;
    }

    // Method to get a student by ID
    public Student getStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // Method to add a student
    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
    }

    // Method to update student
    public boolean updateStudent(Student updatedStudent) {

        

     for (int i = 0; i < students.size(); i++) {

        if (students.get(i).getStudentId()
                .equals(updatedStudent.getStudentId())) {

            students.set(i, updatedStudent);

            saveStudentsToFile();

            return true;
        }
     }

    return false;
} 

// Method to delete student
public boolean deleteStudent(String studentId) {

    
    Student student = getStudentById(studentId);

    if (student != null) {

        students.remove(student);

        saveStudentsToFile();

        return true;
    }

    return false;
}

}



