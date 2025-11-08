package com.humber.registration;

/**
 * Student class - holds student info  
 * Basic stuff like ID, name, email etc.
 */
public class Student {
    private Integer studentId;  // using Integer wrapper for Part A
    private String firstName;
    private String lastName;
    private String email;
    private String program;
    
    // empty constructor
    public Student() {
        // empty for now
    }
    
    // main constructor 
    public Student(Integer studentId, String firstName, String lastName, String email, String program) {
        this.studentId = studentId;
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = lastName != null ? lastName.trim() : "";
        this.email = email != null ? email.trim().toLowerCase() : "";  // normalize email
        this.program = program != null ? program.trim() : "";
    }
    
    // standard getters and setters
    public Integer getStudentId() {
        return studentId;
    }
    
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getProgram() {
        return program;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }
    
    // equals and hashCode - important for using students in Sets and Maps
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Student student = (Student) obj;
        // Compare based on student ID primarily - wrapper class equality
        return studentId != null && studentId.equals(student.studentId);
    }
    
    @Override
    public int hashCode() {
        // Base hashCode on studentId for consistency with equals
        return studentId != null ? studentId.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s %s', email='%s', program='%s'}", 
                           studentId, firstName, lastName, email, program);
    }
}