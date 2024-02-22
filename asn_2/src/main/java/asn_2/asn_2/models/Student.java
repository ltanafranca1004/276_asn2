package asn_2.asn_2.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @Column
    private String name;

    @Column
    private double weight;

    @Column
    private double height;

    @Column(length = 50)
    private String hairColor;

    @Column
    private double gpa;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(length = 255)
    private String major;

    @Column
    private Integer enrollmentYear;

    @Column(length = 255)
    private String favoriteSubject;

    @Column(length = 255)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Column
    private Boolean isScholarshipRecipient;

    @Column(columnDefinition = "TEXT")
    private String extracurricularActivities;

    public Student() { 
    }

    public Student(String name, double weight, double height, String hairColor, double gpa, Date dateOfBirth,
                   String major, Integer enrollmentYear, String favoriteSubject, String email, String phoneNumber,
                   Boolean isScholarshipRecipient, String extracurricularActivities) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hairColor = hairColor;
        this.gpa = gpa;
        this.dateOfBirth = dateOfBirth;
        this.major = major;
        this.enrollmentYear = enrollmentYear;
        this.favoriteSubject = favoriteSubject;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isScholarshipRecipient = isScholarshipRecipient;
        this.extracurricularActivities = extracurricularActivities;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getFavoriteSubject() {
        return favoriteSubject;
    }

    public void setFavoriteSubject(String favoriteSubject) {
        this.favoriteSubject = favoriteSubject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsScholarshipRecipient() {
        return isScholarshipRecipient;
    }

    public void setIsScholarshipRecipient(Boolean isScholarshipRecipient) {
        this.isScholarshipRecipient = isScholarshipRecipient;
    }

    public String getExtracurricularActivities() {
        return extracurricularActivities;
    }

    public void setExtracurricularActivities(String extracurricularActivities) {
        this.extracurricularActivities = extracurricularActivities;
    }



    
    
}