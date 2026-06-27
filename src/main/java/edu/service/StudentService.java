package edu.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.model.Student;
import edu.repository.StudentRepo;

public class StudentService {

    Scanner scanner = new Scanner(System.in);
    StudentRepo studentRepo = new StudentRepo();

    // Method to validate and add a new student
    public void checkAddStudent() {
        try {

            //get student information from user input
            System.out.println("Please provide the following information:");

            System.out.println("Student ID : ");
            String studentId = scanner.nextLine();

            System.out.println("Registration Number : ");
            String registrationNumber = scanner.nextLine();

            System.out.println("Index Number : ");
            String indexNumber = scanner.nextLine();

            System.out.println("Name : ");
            String name = scanner.nextLine();

            System.out.println("Degree Program : ");
            String degreeProgram = scanner.nextLine();

            System.out.println("Current Year : ");
            int currentYear = scanner.nextInt();

            scanner.nextLine();

            System.out.println("Current Semester : ");
            int currentSemester = scanner.nextInt();

            scanner.nextLine();

            System.out.println("Email : ");
            String email = scanner.nextLine();

            String regexId = "23APP\\d{4}";
            String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";

            // Validate student ID, registration number, and index number
            if (!(studentId.equals(registrationNumber) && studentId.equals(indexNumber) && !studentId.isEmpty() && studentId.matches(regexId))) {
                System.out.println("Invalid student information provided.");
                scanner.nextLine(); 
                return;
            }

            // Check if the student already exists in the repository
            if(studentRepo.getStudentById(studentId) != null) {
                System.out.println("Student with ID " + studentId + " already exists.");
                return;
            }

            // Validate email format
            if (!email.isEmpty() && !email.matches(regexEmail)) {
                System.out.println("Invalid email format.");
                return;
            }

            //validate current year and semester
            if (currentYear < 1 || currentYear > 4) {
                System.out.println("Invalid current year. It should be between 1 and 4.");
                return;
            }

            if (currentSemester < 1 || currentSemester > 2) {
                System.out.println("Invalid current semester. It should be either 1 or 2.");
                return;
            }

            // Create a new Student object and add it to the repository
            Student student = new Student(studentId, registrationNumber, indexNumber, name, degreeProgram, currentYear, currentSemester, email);

            // Add the student to the repository
            studentRepo.addStudent(student);


        } catch (InputMismatchException e) {
            System.out.println("Error: " + e);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    // Method to view all students
    public void viewAllStudents() {
        for (Student student : studentRepo.getStudents()) {
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Registration Number: " + student.getRegistrationNumber());
            System.out.println("Index Number: " + student.getIndexNumber());
            System.out.println("Name: " + student.getName());
            System.out.println("Degree Program: " + student.getDegreeProgram());
            System.out.println("Current Year: " + student.getCurrentYear());
            System.out.println("Current Semester: " + student.getCurrentSemester());
            System.out.println("Email: " + student.getEmail());
            System.out.println("---------------------------");
        }
    }


    // Method to get a student by ID
    public void getStudentById() {

        // Get student ID from user input
        System.out.println("Enter student ID: ");
        String studentId = scanner.nextLine();

        // Search for the student in the repository
        Student student = studentRepo.getStudentById(studentId);

        // Display the student information
        if (student != null) {
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Registration Number: " + student.getRegistrationNumber());
            System.out.println("Index Number: " + student.getIndexNumber());
            System.out.println("Name: " + student.getName());
            System.out.println("Degree Program: " + student.getDegreeProgram());
            System.out.println("Current Year: " + student.getCurrentYear());
            System.out.println("Current Semester: " + student.getCurrentSemester());
            System.out.println("Email: " + student.getEmail());
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

        // Method to update student

   public void updateStudent() {
     try{

    System.out.println("Enter Student ID to update: ");
    String studentId = scanner.nextLine();

    Student student = studentRepo.getStudentById(studentId);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.println("Enter New Name: ");
    String name = scanner.nextLine();

    System.out.println("Enter New Degree Program: ");
    String degreeProgram = scanner.nextLine();

    System.out.println("Enter Current Year: ");
    int currentYear = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter Current Semester: ");
    int currentSemester = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Enter New Email: ");
    String email = scanner.nextLine();

    student.setName(name);
    student.setDegreeProgram(degreeProgram);
    student.setCurrentYear(currentYear);
    student.setCurrentSemester(currentSemester);
    student.setEmail(email);

    boolean updated = studentRepo.updateStudent(student);

    if (updated) {
        System.out.println("Student updated successfully.");
    } else {
        System.out.println("Update failed.");
    }

}catch (Exception e){
        System.out.println("Error: " + e);
}
}


   // Method to delete student
    public void deleteStudent() {

        try{

    System.out.println("Enter Student ID to delete: ");
    String studentId = scanner.nextLine();

    boolean deleted = studentRepo.deleteStudent(studentId);

    if (deleted) {
        System.out.println("Student deleted successfully.");
    } else {
        System.out.println("Student not found.");
    }

    }catch (Exception e){
        System.out.println("Error: " + e);
}
   }

}