package com.humber.registration;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * Part C: Enrollment Tracker - course enrollments using Maps
 * Tracks student-course relationships with bidirectional maps
 */
public class EnrollmentTracker {
    
    // course -> students mapping
    private Map<String, Set<Integer>> courseToStudents;
    
    // student -> courses mapping (reverse lookup)
    private Map<Integer, Set<String>> studentToCourses;
    
    // track enrollment history 
    private Map<String, List<EnrollmentRecord>> enrollmentHistory;
    
    // additional enrollment details
    private Map<String, EnrollmentDetails> enrollmentDetails;
    
    public EnrollmentTracker() {
        // setup all the maps
        this.courseToStudents = new HashMap<>();
        this.studentToCourses = new HashMap<>();
        this.enrollmentHistory = new HashMap<>();
        this.enrollmentDetails = new HashMap<>();
    }
    
    /**
     * Enrolls a student in a course - updates both maps
     * Shows bidirectional map relationships
     */
    public boolean enrollStudent(Integer studentId, String courseCode, Course course, Student student) {
        // basic validation
        if (studentId == null || courseCode == null || courseCode.trim().isEmpty()) {
            return false;
        }
        
        // Update courseToStudents Map
        Set<Integer> courseStudents = courseToStudents.computeIfAbsent(courseCode, k -> new HashSet<>());
        
        // Check if student is already enrolled (Set prevents duplicates anyway)
        if (courseStudents.contains(studentId)) {
            return false;  // Already enrolled
        }
        
        // Add student to the course's student Set
        courseStudents.add(studentId);
        
        // Update studentToCourses Map - add course to student's Set  
        // Again using computeIfAbsent for automatic Set creation
        Set<String> studentCourses = studentToCourses.computeIfAbsent(studentId, k -> new HashSet<>());
        studentCourses.add(courseCode);
        
        // Record enrollment details with metadata
        String enrollmentKey = createEnrollmentKey(studentId, courseCode);
        EnrollmentDetails details = new EnrollmentDetails(studentId, courseCode, java.time.LocalDateTime.now());
        enrollmentDetails.put(enrollmentKey, details);
        
        // Record in enrollment history
        List<EnrollmentRecord> history = enrollmentHistory.computeIfAbsent(courseCode, k -> new ArrayList<>());
        EnrollmentRecord record = new EnrollmentRecord(studentId, "ENROLLED", java.time.LocalDateTime.now());
        history.add(record);
        
        return true;  // Successfully enrolled
    }
    
    /**
     * Removes student from course - updates both Maps
     */
    public boolean unenrollStudent(Integer studentId, String courseCode) {
        // Input validation
        if (studentId == null || courseCode == null) {
            return false;
        }
        
        boolean removedFromCourse = false;
        boolean removedFromStudent = false;
        
        // Remove student from courseToStudents Map
        Set<Integer> courseStudents = courseToStudents.get(courseCode);
        if (courseStudents != null) {
            removedFromCourse = courseStudents.remove(studentId);
            
            // Clean up empty Sets (optional - could leave for performance)
            if (courseStudents.isEmpty()) {
                courseToStudents.remove(courseCode);
            }
        }
        
        // Remove course from studentToCourses Map
        Set<String> studentCourses = studentToCourses.get(studentId);
        if (studentCourses != null) {
            removedFromStudent = studentCourses.remove(courseCode);
            
            // Clean up empty Sets
            if (studentCourses.isEmpty()) {
                studentToCourses.remove(studentId);
            }
        }
        
        // Only record if actually unenrolled from both Maps
        if (removedFromCourse && removedFromStudent) {
            // Update enrollment details
            String enrollmentKey = createEnrollmentKey(studentId, courseCode);
            EnrollmentDetails details = enrollmentDetails.get(enrollmentKey);
            if (details != null) {
                details.setEnrollmentStatus("DROPPED");
            }
            
            // Update enrollment history with drop record
            List<EnrollmentRecord> history = enrollmentHistory.get(courseCode);
            if (history != null) {
                EnrollmentRecord record = new EnrollmentRecord(studentId, "DROPPED", java.time.LocalDateTime.now());
                history.add(record);
            }
            
            return true;
        }
        
        return false;  // Wasn't enrolled in both Maps consistently
    }
    
    /**
     * Gets all students enrolled in a specific course
     * Demonstrates Map value retrieval and defensive copying
     */
    public Set<Integer> getEnrolledStudents(String courseCode) {
        // Get Set from courseToStudents Map
        Set<Integer> courseStudents = courseToStudents.get(courseCode);
        
        if (courseStudents == null) {
            // Course doesn't exist in Map - return empty Set
            return new HashSet<>();
        }
        
        // Return defensive copy to prevent external modification of our internal Set
        return new HashSet<>(courseStudents);
    }
    
    /**
     * Gets all courses a student is enrolled in
     */
    public Set<String> getStudentCourses(Integer studentId) {
        // Get Set from studentToCourses Map
        Set<String> studentCourses = studentToCourses.get(studentId);
        
        if (studentCourses == null) {
            // Student doesn't exist in Map - return empty Set
            return new HashSet<>();
        }
        
        // Return defensive copy to maintain encapsulation
        return new HashSet<>(studentCourses);
    }
    
    /**
     * Gets enrollment count for a course
     * Simple Map-based counting
     */
    public int getCourseEnrollmentCount(String courseCode) {
        // Get Set from Map and return its size()
        Set<Integer> courseStudents = courseToStudents.get(courseCode);
        
        // Handle null/missing course case
        if (courseStudents == null) {
            return 0;  // No enrollments for this course
        }
        
        return courseStudents.size();
    }
    
    /**
     * Gets total number of courses a student is taking
     */
    public int getStudentCourseCount(Integer studentId) {
        // Similar to course enrollment count but for students
        Set<String> studentCourses = studentToCourses.get(studentId);
        
        if (studentCourses == null) {
            return 0;  // Student not enrolled in any courses
        }
        
        return studentCourses.size();
    }
    
    /**
     * Finds students enrolled in multiple specified courses
     * Demonstrates Map processing with Set operations
     */
    public Set<Integer> findStudentsInAllCourses(Set<String> courseCodes) {
        if (courseCodes == null || courseCodes.isEmpty()) {
            return new HashSet<>();
        }
        
        Set<Integer> result = null;
        
        // Get student Sets for each course from Map
        for (String courseCode : courseCodes) {
            Set<Integer> courseStudents = getEnrolledStudents(courseCode);
            
            if (result == null) {
                // First course - start with its students
                result = new HashSet<>(courseStudents);
            } else {
                // Use Set.retainAll() to find intersection
                // This keeps only students who are also in this course
                result.retainAll(courseStudents);
            }
            
            // Early exit if no common students remain
            if (result.isEmpty()) {
                break;
            }
        }
        
        // Return students who are in ALL the specified courses
        return result != null ? result : new HashSet<>();
    }
    
    /**
     * Finds students enrolled in any of the specified courses  
     * Demonstrates Map processing with Set union
     */
    public Set<Integer> findStudentsInAnyCourse(Set<String> courseCodes) {
        Set<Integer> result = new HashSet<>();
        
        if (courseCodes == null || courseCodes.isEmpty()) {
            return result;
        }
        
        // Get student Sets for each course from Map
        for (String courseCode : courseCodes) {
            Set<Integer> courseStudents = getEnrolledStudents(courseCode);
            
            // Use Set.addAll() to find union
            // This adds all students from this course to our result
            result.addAll(courseStudents);
        }
        
        // Return students who are in ANY of the specified courses
        return result;
    }
    
    /**
     * Gets enrollment statistics across all courses
     * Shows Map iteration and aggregation
     */
    public EnrollmentStatistics getEnrollmentStatistics() {
        EnrollmentStatistics stats = new EnrollmentStatistics();
        
        if (courseToStudents.isEmpty()) {
            return stats;  // No data to process
        }
        
        // Calculate statistics by iterating through courseToStudents Map
        // Use Map.entrySet() for efficient iteration
        int totalEnrollments = 0;
        int minEnrollment = Integer.MAX_VALUE;
        int maxEnrollment = Integer.MIN_VALUE;
        String mostPopularCourse = "";
        String leastPopularCourse = "";
        
        for (Map.Entry<String, Set<Integer>> entry : courseToStudents.entrySet()) {
            String courseCode = entry.getKey();
            Set<Integer> students = entry.getValue();
            int enrollmentCount = students.size();
            
            totalEnrollments += enrollmentCount;
            
            // Track min and max enrollments
            if (enrollmentCount > maxEnrollment) {
                maxEnrollment = enrollmentCount;
                mostPopularCourse = courseCode;
            }
            if (enrollmentCount < minEnrollment) {
                minEnrollment = enrollmentCount;
                leastPopularCourse = courseCode;
            }
        }
        
        // Set calculated statistics
        stats.totalCourses = courseToStudents.size();
        stats.totalEnrollments = totalEnrollments;
        stats.averageEnrollmentPerCourse = (double) totalEnrollments / courseToStudents.size();
        stats.mostPopularCourse = mostPopularCourse;
        stats.leastPopularCourse = leastPopularCourse;
        
        return stats;
    }
    
    /**
     * Transfers student from one course to another
     * Demonstrates atomic Map operations
     */
    public boolean transferStudent(Integer studentId, String fromCourseCode, String toCourseCode) {
        // This should be atomic - either both operations succeed or both fail
        
        // 1. Check if student is enrolled in fromCourse
        Set<String> studentCourses = studentToCourses.get(studentId);
        if (studentCourses == null || !studentCourses.contains(fromCourseCode)) {
            return false;  // Student not enrolled in source course
        }
        
        // 2. Check if student is already in toCourse
        if (studentCourses.contains(toCourseCode)) {
            return false;  // Already enrolled in target course
        }
        
        // 3. Attempt the transfer (atomic-like operation)
        boolean removedFromOld = unenrollStudent(studentId, fromCourseCode);
        if (removedFromOld) {
            // Try to enroll in new course
            boolean addedToNew = enrollStudent(studentId, toCourseCode, null, null);
            
            if (!addedToNew) {
                // Rollback - re-enroll in original course
                enrollStudent(studentId, fromCourseCode, null, null);
                return false;
            }
            
            // Record transfer in history
            List<EnrollmentRecord> fromHistory = enrollmentHistory.get(fromCourseCode);
            if (fromHistory != null) {
                EnrollmentRecord record = new EnrollmentRecord(studentId, "TRANSFERRED_OUT", java.time.LocalDateTime.now());
                record.setNotes("Transferred to " + toCourseCode);
                fromHistory.add(record);
            }
            
            List<EnrollmentRecord> toHistory = enrollmentHistory.get(toCourseCode);
            if (toHistory != null) {
                EnrollmentRecord record = new EnrollmentRecord(studentId, "TRANSFERRED_IN", java.time.LocalDateTime.now());
                record.setNotes("Transferred from " + fromCourseCode);
                toHistory.add(record);
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Bulk enrollment operation
     * Shows efficient Map batch processing
     */
    public Map<Integer, Boolean> enrollMultipleStudents(Set<Integer> studentIds, String courseCode) {
        Map<Integer, Boolean> results = new HashMap<>();
        
        // Process all enrollments and collect results
        for (Integer studentId : studentIds) {
            boolean success = enrollStudent(studentId, courseCode, null, null);
            results.put(studentId, success);
        }
        
        return results;
    }
    
    /**
     * Gets enrollment details with metadata
     */
    public EnrollmentDetails getEnrollmentDetails(Integer studentId, String courseCode) {
        // Create key from student ID and course code
        String enrollmentKey = createEnrollmentKey(studentId, courseCode);
        
        // Return enrollment details from Map
        return enrollmentDetails.get(enrollmentKey);
    }
    
    /**
     * Gets complete enrollment history for a course
     */
    public List<EnrollmentRecord> getCourseEnrollmentHistory(String courseCode) {
        // Get List from enrollmentHistory Map
        List<EnrollmentRecord> history = enrollmentHistory.get(courseCode);
        
        if (history == null) {
            return new ArrayList<>();  // No history for this course
        }
        
        // Return defensive copy to prevent modification
        return new ArrayList<>(history);
    }
    
    // Helper method to create enrollment detail key
    private String createEnrollmentKey(Integer studentId, String courseCode) {
        return studentId + ":" + courseCode;
    }
    
    // Getters for testing and external access
    public Map<String, Set<Integer>> getCourseToStudentsMap() {
        // Return deep copy to protect internal state
        Map<String, Set<Integer>> copy = new HashMap<>();
        for (Map.Entry<String, Set<Integer>> entry : courseToStudents.entrySet()) {
            copy.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return copy;
    }
    
    public Map<Integer, Set<String>> getStudentToCoursesMap() {
        // Similar deep copy for student-to-courses Map
        Map<Integer, Set<String>> copy = new HashMap<>();
        for (Map.Entry<Integer, Set<String>> entry : studentToCourses.entrySet()) {
            copy.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return copy;
    }
    
    /**
     * Inner class for enrollment statistics
     */
    public static class EnrollmentStatistics {
        private int totalCourses;
        private int totalEnrollments;
        private double averageEnrollmentPerCourse;
        private String mostPopularCourse;
        private String leastPopularCourse;
        
        // Package-private constructor and field access for internal use
        public EnrollmentStatistics() {}
        
        // Getters
        public int getTotalCourses() { return totalCourses; }
        public int getTotalEnrollments() { return totalEnrollments; }
        public double getAverageEnrollmentPerCourse() { return averageEnrollmentPerCourse; }
        public String getMostPopularCourse() { return mostPopularCourse; }
        public String getLeastPopularCourse() { return leastPopularCourse; }
    }
    
    /**
     * Inner class for enrollment details
     */
    public static class EnrollmentDetails {
        private Integer studentId;
        private String courseCode;
        private java.time.LocalDateTime enrollmentDate;
        private String enrollmentStatus;
        private String grade;
        
        // TODO: Add constructor and getters/setters
        public EnrollmentDetails() {}
        
        public EnrollmentDetails(Integer studentId, String courseCode, java.time.LocalDateTime enrollmentDate) {
            this.studentId = studentId;
            this.courseCode = courseCode;
            this.enrollmentDate = enrollmentDate;
            this.enrollmentStatus = "ACTIVE";
        }
        
        // Getters and setters
        public Integer getStudentId() { return studentId; }
        public String getCourseCode() { return courseCode; }
        public java.time.LocalDateTime getEnrollmentDate() { return enrollmentDate; }
        public String getEnrollmentStatus() { return enrollmentStatus; }
        public String getGrade() { return grade; }
        public void setEnrollmentStatus(String enrollmentStatus) { this.enrollmentStatus = enrollmentStatus; }
        public void setGrade(String grade) { this.grade = grade; }
    }
    
    /**
     * Inner class for enrollment history records
     */
    public static class EnrollmentRecord {
        private Integer studentId;
        private String action;  // "ENROLLED", "DROPPED", "TRANSFERRED"
        private java.time.LocalDateTime timestamp;
        private String notes;
        
        public EnrollmentRecord(Integer studentId, String action, java.time.LocalDateTime timestamp) {
            this.studentId = studentId;
            this.action = action;
            this.timestamp = timestamp;
        }
        
        // Getters
        public Integer getStudentId() { return studentId; }
        public String getAction() { return action; }
        public java.time.LocalDateTime getTimestamp() { return timestamp; }
        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
    }
}