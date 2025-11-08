package com.humber.registration;

import java.util.Set;
import java.util.HashSet;

/**
 * Part B: Registration Validator - duplicate prevention with Sets
 * Uses HashSet to prevent duplicate registrations
 */
public class RegistrationValidator {
    
    // different registration states using Sets
    private Set<String> activeRegistrations;
    private Set<String> completedRegistrations;
    private Set<String> droppedRegistrations;
    private Set<String> pendingRegistrations;
    
    public RegistrationValidator() {
        // init all the sets - using HashSet for O(1) lookups
        this.activeRegistrations = new HashSet<>();
        this.completedRegistrations = new HashSet<>();
        this.droppedRegistrations = new HashSet<>();
        this.pendingRegistrations = new HashSet<>();
    }
    
    /**
     * Checks if student already registered for course
     * Main duplicate prevention logic
     */
    public boolean isAlreadyRegistered(Integer studentId, String courseCode) {
        String registrationKey = createRegistrationKey(studentId, courseCode);
        
        // fast Set.contains() - should be O(1) 
        boolean isActive = activeRegistrations.contains(registrationKey);
        boolean isPending = pendingRegistrations.contains(registrationKey);
        
        return isActive || isPending;
    }
    
    /**
     * Checks if new registration is allowed
     */
    public ValidationResult validateRegistration(Integer studentId, String courseCode, Course course, Student student) {
        // Basic input validation
        if (studentId == null || courseCode == null || courseCode.trim().isEmpty()) {
            return new ValidationResult(false, "Invalid student ID or course code", "INVALID_INPUT");
        }
        
        if (course == null || student == null) {
            return new ValidationResult(false, "Course or student information not found", "NOT_FOUND");
        }
        
        // Check for duplicates using Sets
        if (isAlreadyRegistered(studentId, courseCode)) {
            return new ValidationResult(false, 
                "Student is already registered or pending for this course", 
                "DUPLICATE_REGISTRATION");
        }
        
        // Check course capacity
        if (!course.hasAvailableSpots()) {
            return new ValidationResult(false, 
                "Course is full - no available spots", 
                "COURSE_FULL");
        }
        
        // Additional checks could go here: prerequisites, schedule conflicts, etc.
        
        return new ValidationResult(true, "Registration validation successful", "VALID");
    }
    
    /**
     * Registers the student-course combination in appropriate Sets
     */
    public boolean recordRegistration(Integer studentId, String courseCode) {
        String registrationKey = createRegistrationKey(studentId, courseCode);
        
        // Check if already actively registered
        if (activeRegistrations.contains(registrationKey)) {
            return false;  // Already registered
        }
        
        // Add to active registrations Set
        boolean added = activeRegistrations.add(registrationKey);
        
        if (added) {
            // Remove from pending if it was there (student got off waitlist)
            pendingRegistrations.remove(registrationKey);
            
            // The beauty of Sets: add() returns false if element already exists
            // This provides built-in duplicate prevention!
        }
        
        return added;
    }
    
    /**
     * Removes registration from active Set when student drops
     */
    public boolean recordDroppedRegistration(Integer studentId, String courseCode) {
        String registrationKey = createRegistrationKey(studentId, courseCode);
        
        // Remove from active registrations
        boolean removedFromActive = activeRegistrations.remove(registrationKey);
        
        // Also remove from pending (in case they were waitlisted)
        boolean removedFromPending = pendingRegistrations.remove(registrationKey);
        
        // Add to dropped registrations for historical tracking
        if (removedFromActive || removedFromPending) {
            droppedRegistrations.add(registrationKey);
            return true;
        }
        
        return false;  // Wasn't registered to begin with
    }
    
    /**
     * Adds to pending registrations Set (for waitlisted students)
     */
    public boolean addPendingRegistration(Integer studentId, String courseCode) {
        String registrationKey = createRegistrationKey(studentId, courseCode);
        
        // Check if already actively registered - can't be pending if already enrolled
        if (activeRegistrations.contains(registrationKey)) {
            return false;  // Already actively registered
        }
        
        // Check if already pending
        if (pendingRegistrations.contains(registrationKey)) {
            return false;  // Already on waitlist/pending
        }
        
        // Add to pending registrations Set
        boolean added = pendingRegistrations.add(registrationKey);
        
        // This demonstrates using multiple Sets for state management
        // Each Set represents a different state in our registration workflow
        return added;
    }
    
    /**
     * Gets all courses a student is currently registered for
     * Demonstrates Set filtering and processing
     */
    public Set<String> getStudentCourses(Integer studentId) {
        Set<String> studentCourses = new HashSet<>();
        
        // Filter activeRegistrations Set by student ID
        String studentPrefix = studentId + ":";  // Our key format is "studentId:courseCode"
        
        for (String registrationKey : activeRegistrations) {
            if (registrationKey.startsWith(studentPrefix)) {
                // Extract course code from the key
                String courseCode = registrationKey.substring(studentPrefix.length());
                studentCourses.add(courseCode);
            }
        }
        
        // Return new Set containing just the course codes
        // This demonstrates Set filtering and transformation
        return studentCourses;
    }
    
    /**
     * Gets all students registered for a specific course
     * Another example of Set filtering and processing
     */
    public Set<Integer> getCourseStudents(String courseCode) {
        Set<Integer> courseStudents = new HashSet<>();
        
        // Filter activeRegistrations Set by course code
        String courseSuffix = ":" + courseCode;  // Our key format is "studentId:courseCode"
        
        for (String registrationKey : activeRegistrations) {
            if (registrationKey.endsWith(courseSuffix)) {
                // Extract student ID from the key
                String studentIdStr = registrationKey.substring(0, 
                    registrationKey.length() - courseSuffix.length());
                
                try {
                    Integer studentId = Integer.valueOf(studentIdStr);
                    courseStudents.add(studentId);
                } catch (NumberFormatException e) {
                    // Skip malformed keys (shouldn't happen with our controlled input)
                    continue;
                }
            }
        }
        
        // Return new Set containing just the student IDs
        return courseStudents;
    }
    
    /**
     * Demonstrates Set operations like union, intersection, difference
     */
    public Set<String> findCommonRegistrations(Integer studentId1, Integer studentId2) {
        // Get courses for both students using our existing method
        Set<String> student1Courses = getStudentCourses(studentId1);
        Set<String> student2Courses = getStudentCourses(studentId2);
        
        // Create a copy of student1's courses for intersection
        Set<String> commonCourses = new HashSet<>(student1Courses);
        
        // Use Set.retainAll() for intersection operation
        // This modifies commonCourses to keep only elements also in student2Courses
        commonCourses.retainAll(student2Courses);
        
        // Return Set of courses both students are taking
        // This demonstrates the power of Set intersection operations
        return commonCourses;
    }
    
    /**
     * Bulk validation for multiple registrations
     * Shows working with Sets at scale
     */
    public java.util.Map<String, ValidationResult> validateMultipleRegistrations(
            Integer studentId, Set<String> courseCodes) {
        
        java.util.Map<String, ValidationResult> results = new java.util.HashMap<>();
        
        // Iterate through Set of course codes
        for (String courseCode : courseCodes) {
            // For bulk validation, we'll do basic checks without full Course/Student objects
            ValidationResult result;
            
            if (isAlreadyRegistered(studentId, courseCode)) {
                result = new ValidationResult(false, 
                    "Already registered for " + courseCode, 
                    "DUPLICATE_REGISTRATION");
            } else {
                result = new ValidationResult(true, 
                    "Validation passed for " + courseCode, 
                    "VALID");
            }
            
            results.put(courseCode, result);
        }
        
        // Collect results in Map for easy lookup by course code
        return results;
    }
    
    // Helper method to create unique registration key
    private String createRegistrationKey(Integer studentId, String courseCode) {
        // Create consistent key format for Set storage
        // Format: "studentId:courseCode" (e.g., "1001:CS101")
        return studentId + ":" + courseCode.toUpperCase().trim();
    }
    
    // Getters for testing and external access
    public Set<String> getActiveRegistrations() {
        return new HashSet<>(activeRegistrations);  // Return copy to protect internal Set
    }
    
    public Set<String> getPendingRegistrations() {
        return new HashSet<>(pendingRegistrations);
    }
    
    public Set<String> getCompletedRegistrations() {
        return new HashSet<>(completedRegistrations);
    }
    
    public Set<String> getDroppedRegistrations() {
        return new HashSet<>(droppedRegistrations);
    }
    
    /**
     * Inner class for validation results
     * Cleaner than returning boolean and having separate error message methods
     */
    public static class ValidationResult {
        private boolean isValid;
        private String errorMessage;
        private String errorCode;
        
        public ValidationResult(boolean isValid, String errorMessage, String errorCode) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
            this.errorCode = errorCode;
        }
        
        // TODO: Add getters and useful methods
        public boolean isValid() { return isValid; }
        public String getErrorMessage() { return errorMessage; }
        public String getErrorCode() { return errorCode; }
    }
}