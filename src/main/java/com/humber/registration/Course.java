package com.humber.registration;

/**
 * Course class represents individual courses in our system
 * Each course has basic info like code, name, capacity, etc
 * This will be used throughout the registration system
 */
public class Course {
    private String courseCode;      // like "COMP1234" or "MATH101" 
    private String courseName;      // full course title
    private String instructor;      // who's teaching this course
    private Integer maxCapacity;    // maximum number of students
    private Integer currentEnrolled; // how many are currently registered
    private String semester;        // Fall, Winter, Summer
    private String description;     // course description
    
    // Default constructor
    public Course() {
        this.currentEnrolled = 0;  // start with zero enrollments
    }
    
    // Main constructor with required fields
    public Course(String courseCode, String courseName, String instructor, 
                  Integer maxCapacity, String semester, String description) {
        this.courseCode = courseCode != null ? courseCode.trim().toUpperCase() : "";
        this.courseName = courseName != null ? courseName.trim() : "";
        this.instructor = instructor != null ? instructor.trim() : "";
        this.maxCapacity = maxCapacity != null ? maxCapacity : 0;
        this.semester = semester != null ? semester.trim() : "";
        this.description = description != null ? description.trim() : "";
        this.currentEnrolled = 0;  // always start with 0 enrollments
    }
    
    // Check if course has spots left
    public boolean hasAvailableSpots() {
        return currentEnrolled < maxCapacity;
    }
    
    // How many spots are left
    public int getAvailableSpots() {
        int available = maxCapacity - currentEnrolled;
        return Math.max(0, available);  // don't go negative
    }
    
    // Add one more student - called when someone registers
    public boolean incrementEnrollment() {
        if (hasAvailableSpots()) {
            currentEnrolled++;
            return true;
        }
        return false;  // course is full
    }
    
    // Remove a student - called when they drop
    public boolean decrementEnrollment() {
        if (currentEnrolled > 0) {
            currentEnrolled--;
            return true;
        }
        return false;  // can't go below zero
    }
    
    // getters and setters - boring but necesary
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getInstructor() {
        return instructor;
    }
    
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    
    public Integer getMaxCapacity() {
        return maxCapacity;
    }
    
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    public Integer getCurrentEnrolled() {
        return currentEnrolled;
    }
    
    public void setCurrentEnrolled(Integer currentEnrolled) {
        this.currentEnrolled = currentEnrolled;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Course course = (Course) obj;
        // Compare based on courseCode - should be unique identifier
        return courseCode != null && courseCode.equals(course.courseCode);
    }
    
    @Override
    public int hashCode() {
        return courseCode != null ? courseCode.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return String.format("Course{code='%s', name='%s', instructor='%s', enrolled=%d/%d}", 
                           courseCode, courseName, instructor, currentEnrolled, maxCapacity);
    }
}