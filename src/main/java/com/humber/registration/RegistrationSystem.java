package com.humber.registration;

/**
 * Main Registration System - ties everything together
 * Coordinates all four parts of the assignment:
 * A: Student IDs, B: Duplicate checking, C: Enrollments, D: Waitlists 
 */
public class RegistrationSystem {
    
    // the four main pieces we need
    private StudentIdManager studentIdManager;        // Part A: wrapper classes stuff
    private RegistrationValidator validator;          // Part B: Sets for duplicates  
    private EnrollmentTracker enrollmentTracker;     // Part C: Maps for enrollments
    private WaitlistManager waitlistManager;         // Part D: Queues for waitlists
    
    // storage for students and courses
    private java.util.Map<Integer, Student> students;
    private java.util.Map<String, Course> courses;
    
    public RegistrationSystem() {
        // setup all the components
        this.studentIdManager = new StudentIdManager();
        this.validator = new RegistrationValidator();
        this.enrollmentTracker = new EnrollmentTracker();
        this.waitlistManager = new WaitlistManager();
        this.students = new java.util.HashMap<>();
        this.courses = new java.util.HashMap<>();
    }
    
    /**
     * Registers a new student in the system
     * Uses Part A: StudentIdManager for ID generation and wrapper class operations
     */
    public Integer registerNewStudent(String firstName, String lastName, String email, String program) {
        // TODO: Implement student registration
        // 1. Use StudentIdManager to generate unique ID (demonstrates wrapper classes)
        // 2. Create Student object
        // 3. Store in students Map
        // 4. Return the generated student ID
        return null;
    }
    
    /**
     * Adds a new course to the system
     */
    public boolean addCourse(String courseCode, String courseName, String instructor, 
                           Integer maxCapacity, String semester, String description) {
        // TODO: Implement course creation
        // 1. Create Course object
        // 2. Store in courses Map using courseCode as key
        // 3. Return success/failure
        return false;
    }
    
    /**
     * Attempts to register a student for a course
     * This is the main method that uses all four components together!
     */
    public RegistrationResult registerStudentForCourse(Integer studentId, String courseCode) {
        // TODO: Implement the complete registration flow
        // This method should demonstrate all four parts working together:
        
        // Part A: Use StudentIdManager to validate student ID (wrapper class operations)
        // if (!studentIdManager.validateStudentId(studentId)) { ... }
        
        // Part B: Use RegistrationValidator to check for duplicates (Set operations)
        // ValidationResult validationResult = validator.validateRegistration(studentId, courseCode, course, student);
        
        // Part C: If validation passes, use EnrollmentTracker to enroll (Map operations)
        // boolean enrolled = enrollmentTracker.enrollStudent(studentId, courseCode, course, student);
        
        // Part D: If course is full, use WaitlistManager to add to waitlist (Queue operations)
        // boolean addedToWaitlist = waitlistManager.addToWaitlist(studentId, courseCode, student);
        
        return null;
    }
    
    /**
     * Drops a student from a course
     * Demonstrates coordinated operations across components
     */
    public boolean dropStudentFromCourse(Integer studentId, String courseCode) {
        // TODO: Implement drop logic
        // 1. Remove from enrollment (EnrollmentTracker - Maps)
        // 2. Update registration validation state (RegistrationValidator - Sets)
        // 3. Process next waitlisted student if any (WaitlistManager - Queues)
        // 4. Update course capacity
        return false;
    }
    
    /**
     * Gets comprehensive student information
     * Shows data aggregation from multiple components
     */
    public StudentInfo getStudentInfo(Integer studentId) {
        // TODO: Implement student info aggregation
        // Gather data from:
        // - Student basic info from students Map
        // - Current enrollments from EnrollmentTracker (Maps)
        // - Waitlist positions from WaitlistManager (Queues)
        // - Registration history from RegistrationValidator (Sets)
        return null;
    }
    
    /**
     * Gets comprehensive course information
     * Shows data aggregation from multiple components
     */
    public CourseInfo getCourseInfo(String courseCode) {
        // TODO: Implement course info aggregation
        // Gather data from:
        // - Course basic info from courses Map
        // - Current enrollments from EnrollmentTracker (Maps)
        // - Waitlist from WaitlistManager (Queues)
        // - Registration statistics from RegistrationValidator (Sets)
        return null;
    }
    
    /**
     * Processes waitlists when spots open up
     * Demonstrates Queue processing with Map and Set updates
     */
    public java.util.List<Integer> processWaitlistsForCourse(String courseCode, int availableSpots) {
        // TODO: Implement waitlist processing
        // 1. Use WaitlistManager to get next students from Queue
        // 2. For each student, attempt registration using other components
        // 3. Update enrollment Maps, validation Sets, etc.
        // 4. Return list of successfully enrolled student IDs
        return null;
    }
    
    /**
     * Transfers student from one course to another
     * Complex operation involving all components
     */
    public TransferResult transferStudent(Integer studentId, String fromCourseCode, String toCourseCode) {
        // TODO: Implement transfer logic
        // This is complex - involves dropping from one and adding to another
        // Must handle cases where target course is full (waitlist)
        // Should be atomic - either complete transfer or no change
        return null;
    }
    
    /**
     * Gets system-wide statistics
     * Aggregates data from all components
     */
    public SystemStatistics getSystemStatistics() {
        // TODO: Implement statistics aggregation
        // Collect data from all four components:
        // - StudentIdManager: ID usage statistics (wrapper class operations)
        // - RegistrationValidator: Registration statistics (Set operations)
        // - EnrollmentTracker: Enrollment statistics (Map operations)  
        // - WaitlistManager: Waitlist statistics (Queue operations)
        return null;
    }
    
    /**
     * Bulk registration operation
     * Demonstrates batch processing across all components
     */
    public java.util.Map<Integer, RegistrationResult> registerMultipleStudents(
            java.util.List<Integer> studentIds, String courseCode) {
        // TODO: Implement bulk registration
        // Process each student through the normal registration flow
        // Collect results in Map for easy access
        // Handle partial success scenarios
        return null;
    }
    
    /**
     * Searches for students by various criteria
     * Demonstrates querying across different data structures
     */
    public java.util.List<Student> searchStudents(StudentSearchCriteria criteria) {
        // TODO: Implement student search
        // Search through students Map based on criteria
        // Might also need to check enrollment data (Maps) and waitlist data (Queues)
        return null;
    }
    
    /**
     * Searches for courses by various criteria
     */
    public java.util.List<Course> searchCourses(CourseSearchCriteria criteria) {
        // TODO: Implement course search
        // Search through courses Map based on criteria
        // Include enrollment and waitlist information
        return null;
    }
    
    // Getters for individual components (useful for testing)
    public StudentIdManager getStudentIdManager() {
        return studentIdManager;
    }
    
    public RegistrationValidator getRegistrationValidator() {
        return validator;
    }
    
    public EnrollmentTracker getEnrollmentTracker() {
        return enrollmentTracker;
    }
    
    public WaitlistManager getWaitlistManager() {
        return waitlistManager;
    }
    
    public java.util.Map<Integer, Student> getStudents() {
        return new java.util.HashMap<>(students);  // Defensive copy
    }
    
    public java.util.Map<String, Course> getCourses() {
        return new java.util.HashMap<>(courses);   // Defensive copy
    }
    
    /**
     * Result class for registration operations
     */
    public static class RegistrationResult {
        private boolean success;
        private String message;
        private String resultType;  // "ENROLLED", "WAITLISTED", "FAILED"
        private Integer waitlistPosition;
        
        public RegistrationResult(boolean success, String message, String resultType) {
            this.success = success;
            this.message = message;
            this.resultType = resultType;
        }
        
        // Getters and setters
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public String getResultType() { return resultType; }
        public Integer getWaitlistPosition() { return waitlistPosition; }
        public void setWaitlistPosition(Integer waitlistPosition) { this.waitlistPosition = waitlistPosition; }
    }
    
    /**
     * Result class for transfer operations
     */
    public static class TransferResult {
        private boolean success;
        private String message;
        private boolean enrolledInTarget;
        private boolean waitlistedInTarget;
        
        public TransferResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        
        // Getters and setters
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public boolean isEnrolledInTarget() { return enrolledInTarget; }
        public boolean isWaitlistedInTarget() { return waitlistedInTarget; }
        public void setEnrolledInTarget(boolean enrolledInTarget) { this.enrolledInTarget = enrolledInTarget; }
        public void setWaitlistedInTarget(boolean waitlistedInTarget) { this.waitlistedInTarget = waitlistedInTarget; }
    }
    
    /**
     * Comprehensive student information class
     */
    public static class StudentInfo {
        private Student student;
        private java.util.Set<String> enrolledCourses;
        private java.util.Map<String, Integer> waitlistPositions;
        private int totalCredits;
        
        public StudentInfo(Student student) {
            this.student = student;
            this.enrolledCourses = new java.util.HashSet<>();
            this.waitlistPositions = new java.util.HashMap<>();
        }
        
        // Getters and setters
        public Student getStudent() { return student; }
        public java.util.Set<String> getEnrolledCourses() { return enrolledCourses; }
        public java.util.Map<String, Integer> getWaitlistPositions() { return waitlistPositions; }
        public int getTotalCredits() { return totalCredits; }
        public void setTotalCredits(int totalCredits) { this.totalCredits = totalCredits; }
    }
    
    /**
     * Comprehensive course information class
     */
    public static class CourseInfo {
        private Course course;
        private java.util.Set<Integer> enrolledStudents;
        private java.util.List<Integer> waitlistStudents;
        private int availableSpots;
        
        public CourseInfo(Course course) {
            this.course = course;
            this.enrolledStudents = new java.util.HashSet<>();
            this.waitlistStudents = new java.util.ArrayList<>();
        }
        
        // Getters and setters
        public Course getCourse() { return course; }
        public java.util.Set<Integer> getEnrolledStudents() { return enrolledStudents; }
        public java.util.List<Integer> getWaitlistStudents() { return waitlistStudents; }
        public int getAvailableSpots() { return availableSpots; }
        public void setAvailableSpots(int availableSpots) { this.availableSpots = availableSpots; }
    }
    
    /**
     * System-wide statistics class
     */
    public static class SystemStatistics {
        private int totalStudents;
        private int totalCourses;
        private int totalEnrollments;
        private int totalWaitlistEntries;
        private double averageEnrollmentsPerStudent;
        private double averageEnrollmentsPerCourse;
        
        // TODO: Add constructor and getters
        public SystemStatistics() {}
        
        public int getTotalStudents() { return totalStudents; }
        public int getTotalCourses() { return totalCourses; }
        public int getTotalEnrollments() { return totalEnrollments; }
        public int getTotalWaitlistEntries() { return totalWaitlistEntries; }
        public double getAverageEnrollmentsPerStudent() { return averageEnrollmentsPerStudent; }
        public double getAverageEnrollmentsPerCourse() { return averageEnrollmentsPerCourse; }
    }
    
    /**
     * Search criteria classes (placeholder implementations)
     */
    public static class StudentSearchCriteria {
        private String firstName;
        private String lastName;
        private String email;
        private String program;
        
        public StudentSearchCriteria(String firstName, String lastName, String email, String program) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.program = program;
        }
        
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getEmail() { return email; }
        public String getProgram() { return program; }
    }
    
    public static class CourseSearchCriteria {
        private String courseCode;
        private String courseName;
        private String instructor;
        private String semester;
        
        public CourseSearchCriteria(String courseCode, String courseName, String instructor, String semester) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.instructor = instructor;
            this.semester = semester;
        }
        
        public String getCourseCode() { return courseCode; }
        public String getCourseName() { return courseName; }
        public String getInstructor() { return instructor; }
        public String getSemester() { return semester; }
    }
}