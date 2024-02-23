package asn_2.asn_2.controllers;

import java.util.List;
import java.util.Map;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import asn_2.asn_2.models.Student;
import asn_2.asn_2.models.StudentRepository;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    // Existing method to view all students
    @GetMapping("/students/view")
    public String getAllStudents(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "students/showAll";
    }

    // Map the root URL to the showAll view
    @GetMapping("/")
    public String root(Model model) {
        return getAllStudents(model); // Call the existing method by its correct name
    }

    // Method to add a new student using request parameters
    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response) {
        System.out.println("ADD student");

        // Extract student attributes from the request parameters
        String newName = newStudent.get("name");
        double newWeight = Double.parseDouble(newStudent.get("weight"));
        double newHeight = Double.parseDouble(newStudent.get("height"));
        String newHairColor = newStudent.get("hairColor");
        double newGpa = Double.parseDouble(newStudent.get("gpa"));
        Date newDateOfBirth = newStudent.get("dateOfBirth") != null ? Date.valueOf(newStudent.get("dateOfBirth")) : null;
        String newMajor = newStudent.get("major");
        Integer newEnrollmentYear = newStudent.get("enrollmentYear") != null ? Integer.parseInt(newStudent.get("enrollmentYear")) : null;
        String newFavoriteSubject = newStudent.get("favoriteSubject");
        String newEmail = newStudent.get("email");
        String newPhoneNumber = newStudent.get("phoneNumber");
        Boolean newIsScholarshipRecipient = newStudent.get("isScholarshipRecipient") != null ? Boolean.parseBoolean(newStudent.get("isScholarshipRecipient")) : null;
        String newExtracurricularActivities = newStudent.get("extracurricularActivities");

        // Create and save the new student entity
        Student student = new Student(newName, newWeight, newHeight, newHairColor, newGpa, newDateOfBirth, 
                                    newMajor, newEnrollmentYear, newFavoriteSubject, newEmail, 
                                    newPhoneNumber, newIsScholarshipRecipient, newExtracurricularActivities);
        studentRepo.save(student);
        
        // Set the response status code to 201 Created
        response.setStatus(HttpServletResponse.SC_CREATED);
        
        // Redirect to the view that lists all students
        return "redirect:/students/view";
    }



    // Method to delete a student
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentRepo.deleteById(id);
        return "redirect:/students/view"; // Redirect to view all students after deleting
    }

    // Method to edit a student's details
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable("id") Integer id, Model model) {
        Student student = studentRepo.findById(id).orElse(null);
        if (student != null) {
            model.addAttribute("student", student);
            return "students/edit"; // Path to the Thymeleaf template that contains the form for editing a student
        } else {
            // Handle the case where the student doesn't exist
            return "redirect:/students/view";
        }
    }

    // Method to update a student's details
    @PostMapping("/students/update")
    public String updateStudent(@RequestParam Map<String, String> updatedStudent, @RequestParam("id") Integer id) {
        Student student = studentRepo.findById(id).orElse(null);
        if (student != null) {
            // Update fields from request parameters
            student.setName(updatedStudent.get("name"));
            student.setWeight(Double.parseDouble(updatedStudent.get("weight")));
            student.setHeight(Double.parseDouble(updatedStudent.get("height")));
            student.setHairColor(updatedStudent.get("hairColor"));
            student.setGpa(Double.parseDouble(updatedStudent.get("gpa")));
            student.setDateOfBirth(Date.valueOf(updatedStudent.get("dateOfBirth")));
            student.setMajor(updatedStudent.get("major"));
            student.setEnrollmentYear(Integer.parseInt(updatedStudent.get("enrollmentYear")));
            student.setFavoriteSubject(updatedStudent.get("favoriteSubject"));
            student.setEmail(updatedStudent.get("email"));
            student.setPhoneNumber(updatedStudent.get("phoneNumber"));
            student.setIsScholarshipRecipient(Boolean.parseBoolean(updatedStudent.get("isScholarshipRecipient")));
            student.setExtracurricularActivities(updatedStudent.get("extracurricularActivities"));
            studentRepo.save(student);
        }
        return "redirect:/students/view"; // Redirect to view all students after updating
    }
    
    // Method to show the add student form
    @GetMapping("/add")
    public String showAddStudentForm() {
        return "students/add"; // Corrected path with the subdirectory
    }
}
