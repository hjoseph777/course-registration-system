package com.humber.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

/**
 * Unit tests for EnrollmentTracker - Part C: Map Collections Demonstration
 * Tests HashMap bidirectional relationships, Map operations, and statistics
 */
public class EnrollmentTrackerTest {
    
    private EnrollmentTracker tracker;
    private Student testStudent1;
    private Student testStudent2; 
    private Course testCourse1;
    private Course testCourse2;
    
    @BeforeEach
    void setUp() {
        tracker = new EnrollmentTracker();
        testStudent1 = new Student(1001, "Alice", "Johnson", "alice@humber.ca", "Computer Science");
        testStudent2 = new Student(1002, "Bob", "Smith", "bob@humber.ca", "Engineering");
        testCourse1 = new Course("CS101", "Programming Fundamentals", "Dr. Smith", 25, "Fall", "Intro to programming");
        testCourse2 = new Course("MATH201", "Calculus I", "Prof. Brown", 30, "Fall", "Differential calculus");
    }
    
    @Test
    void testMapBasedEnrollmentTracking() {
        System.out.println("\n=== PART C: MAP COLLECTIONS DEMONSTRATIONS ===");
        System.out.println("Testing bidirectional Map relationships...");
        
        Integer studentId = 1001;
        String courseCode = "CS101";
        
        // Test enrollment using Map operations
        boolean enrolled = tracker.enrollStudent(studentId, courseCode, testCourse1, testStudent1);
        System.out.println("Enrollment result: " + enrolled);
        assertTrue(enrolled, "Enrollment should succeed");
        
        // Test bidirectional lookup
        Set<Integer> studentsInCourse = tracker.getEnrolledStudents(courseCode);
        Set<String> studentCourses = tracker.getStudentCourses(studentId);
        
        System.out.println("Students in " + courseCode + ": " + studentsInCourse);
        System.out.println("Courses for student " + studentId + ": " + studentCourses);
        
        assertTrue(studentsInCourse.contains(studentId));
        assertTrue(studentCourses.contains(courseCode));
        
        System.out.println("✓ Bidirectional Map relationships working correctly");
    }
    
    @Test
    void testMapComputeIfAbsentOperations() {
        System.out.println("\n--- Map.computeIfAbsent() Demonstration ---");
        
        // Enroll multiple students in same course  
        tracker.enrollStudent(1001, "CS101", testCourse1, testStudent1);
        tracker.enrollStudent(1002, "CS101", testCourse1, testStudent2);
        
        System.out.println("Enrolled 2 students in CS101 using computeIfAbsent()");
        
        Set<Integer> studentsInCS101 = tracker.getEnrolledStudents("CS101");
        System.out.println("CS101 enrollment: " + studentsInCS101);
        System.out.println("Total in CS101: " + studentsInCS101.size());
        
        assertEquals(2, studentsInCS101.size());
        assertTrue(studentsInCS101.contains(1001));
        assertTrue(studentsInCS101.contains(1002));
        
        System.out.println("✓ Map.computeIfAbsent() creating Sets automatically");
    }
    
    @Test 
    void testMapKeyValueRelationships() {
        System.out.println("\n--- Map Key-Value Relationships ---");
        
        // Create complex enrollment scenario
        tracker.enrollStudent(1001, "CS101", testCourse1, testStudent1);
        tracker.enrollStudent(1001, "MATH201", testCourse2, testStudent1);
        tracker.enrollStudent(1002, "CS101", testCourse1, testStudent2);
        
        System.out.println("Created complex enrollment scenario:");
        System.out.println("  Student 1001: CS101, MATH201");
        System.out.println("  Student 1002: CS101");
        
        // Test Map key lookups
        Set<String> student1Courses = tracker.getStudentCourses(1001);
        Set<String> student2Courses = tracker.getStudentCourses(1002);
        Set<Integer> cs101Students = tracker.getEnrolledStudents("CS101");
        Set<Integer> math201Students = tracker.getEnrolledStudents("MATH201");
        
        System.out.println("Map lookups:");
        System.out.println("  Student 1001 courses: " + student1Courses);
        System.out.println("  Student 1002 courses: " + student2Courses);
        System.out.println("  CS101 students: " + cs101Students);
        System.out.println("  MATH201 students: " + math201Students);
        
        assertEquals(2, student1Courses.size());
        assertEquals(1, student2Courses.size());
        assertEquals(2, cs101Students.size());
        assertEquals(1, math201Students.size());
        
        System.out.println("✓ Map key-value relationships maintained correctly");
    }
    
    @Test
    void testEnrollmentStatistics() {
        System.out.println("\n--- Enrollment Statistics via Map Operations ---");
        
        // Enroll students in multiple courses
        tracker.enrollStudent(1001, "CS101", testCourse1, testStudent1);
        tracker.enrollStudent(1002, "CS101", testCourse1, testStudent2);
        tracker.enrollStudent(1001, "MATH201", testCourse2, testStudent1);
        
        System.out.println("Enrollment setup complete");
        
        // Test statistics
        int cs101Enrollment = tracker.getCourseEnrollmentCount("CS101");
        int math201Enrollment = tracker.getCourseEnrollmentCount("MATH201");
        int student1CourseCount = tracker.getStudentCourseCount(1001);
        int student2CourseCount = tracker.getStudentCourseCount(1002);
        
        System.out.println("Statistics via Map operations:");
        System.out.println("  CS101 enrollment: " + cs101Enrollment);
        System.out.println("  MATH201 enrollment: " + math201Enrollment);
        System.out.println("  Student 1001 courses: " + student1CourseCount);
        System.out.println("  Student 1002 courses: " + student2CourseCount);
        
        assertEquals(2, cs101Enrollment);
        assertEquals(1, math201Enrollment);
        assertEquals(2, student1CourseCount);
        assertEquals(1, student2CourseCount);
        
        System.out.println("✓ Statistics calculated correctly using Map.size()");
    }
    
    @Test
    void testMapBasedEnrollmentLookups() {
        System.out.println("\n--- Map-Based Enrollment Lookups ---");
        
        // Setup enrollments
        tracker.enrollStudent(1001, "CS101", testCourse1, testStudent1);
        tracker.enrollStudent(1002, "CS101", testCourse1, testStudent2);
        tracker.enrollStudent(1001, "MATH201", testCourse2, testStudent1);
        
        System.out.println("Demonstrating Map-based enrollment lookups:");
        
        // Check enrollments using the available methods
        Set<String> student1001Courses = tracker.getStudentCourses(1001);
        Set<Integer> cs101StudentsCheck = tracker.getEnrolledStudents("CS101");
        
        System.out.println("Student 1001 courses: " + student1001Courses);
        System.out.println("CS101 enrolled students: " + cs101StudentsCheck);
        
        // Verify some enrollments exist
        assertTrue(student1001Courses.contains("CS101"));
        assertTrue(student1001Courses.contains("MATH201"));
        assertTrue(cs101StudentsCheck.contains(1001));
        assertTrue(cs101StudentsCheck.contains(1002));
        assertFalse(tracker.getStudentCourses(1002).contains("MATH201"));
        
        System.out.println("✓ Map-based enrollment lookups working correctly");
    }
}