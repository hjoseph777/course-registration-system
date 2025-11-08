package com.humber.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Part B tests - Set stuff for duplicate checking
 */
public class RegistrationValidatorTest {
    
    private RegistrationValidator validator;
    private Student testStudent;
    private Course testCourse;
    
    @BeforeEach
    void setUp() {
        validator = new RegistrationValidator();
        testStudent = new Student(1001, "John", "Doe", "john@humber.ca", "Computer Science");
        testCourse = new Course("CS101", "Programming Fundamentals", "Dr. Smith", 25, "Fall", "Intro to programming");
    }
    
    @Test
    void testSetBasedDuplicatePrevention() {
        System.out.println("\n=== PART B: SET COLLECTIONS DEMONSTRATIONS ===");
        System.out.println("Testing HashSet duplicate prevention...");
        
        Integer studentId = 1001;
        String courseCode = "CS101";
        
        // try to register first time
        boolean firstAttempt = validator.isAlreadyRegistered(studentId, courseCode);
        System.out.println("First check - Already registered? " + firstAttempt);
        assertFalse(firstAttempt, "Should not be registered initially");
        
        // add to active set 
        validator.recordRegistration(studentId, courseCode);
        System.out.println("Added to active registrations using Set.add()");
        
        // now try again - should be blocked
        boolean secondAttempt = validator.isAlreadyRegistered(studentId, courseCode);
        System.out.println("Second check - Already registered? " + secondAttempt);
        assertTrue(secondAttempt, "Should detect duplicate registration");
        
        System.out.println("✓ HashSet duplicate prevention working correctly");
    }
    
    @Test
    void testSetContainsOperations() {
        System.out.println("\n--- Set.contains() Fast Lookup Demo ---");
        
        Integer studentId = 1001;
        String courseCode1 = "CS101";
        String courseCode2 = "MATH201";
        
        // add some stuff
        validator.recordRegistration(studentId, courseCode1);
        validator.addPendingRegistration(studentId, courseCode2);
        
        System.out.println("Added registrations:");
        System.out.println("  Active: Student " + studentId + " in " + courseCode1);
        System.out.println("  Pending: Student " + studentId + " in " + courseCode2);
        
        // test contains() - should be O(1) lookup
        boolean activeCheck = validator.isAlreadyRegistered(studentId, courseCode1);
        boolean pendingCheck = validator.isAlreadyRegistered(studentId, courseCode2);
        boolean nonExistentCheck = validator.isAlreadyRegistered(studentId, "BIO301");
        
        System.out.println("Set.contains() results:");
        System.out.println("  CS101: " + activeCheck + " (should be true)");
        System.out.println("  MATH201: " + pendingCheck + " (should be true)");
        System.out.println("  BIO301: " + nonExistentCheck + " (should be false)");
        
        assertTrue(activeCheck);
        assertTrue(pendingCheck);
        assertFalse(nonExistentCheck);
        
        System.out.println("✓ Set.contains() operations working efficiently");
    }
    
    @Test
    void testValidationWithSets() {
        System.out.println("\n--- Registration Validation Using Sets ---");
        
        Integer studentId = 1001;
        String courseCode = "CS101";
        
        // Test initial validation (should pass)
        RegistrationValidator.ValidationResult result1 = 
            validator.validateRegistration(studentId, courseCode, testCourse, testStudent);
        
        System.out.println("Initial validation: " + result1.isValid() + " - " + result1.getErrorMessage());
        assertTrue(result1.isValid());
        
        // Add to active registrations
        validator.recordRegistration(studentId, courseCode);
        
        // Test duplicate validation (should fail due to Set duplicate prevention)
        RegistrationValidator.ValidationResult result2 = 
            validator.validateRegistration(studentId, courseCode, testCourse, testStudent);
        
        System.out.println("Duplicate validation: " + result2.isValid() + " - " + result2.getErrorMessage());
        assertFalse(result2.isValid());
        assertEquals("DUPLICATE_REGISTRATION", result2.getErrorCode());
        
        System.out.println("✓ Set-based validation logic working correctly");
    }
    
    @Test
    void testMultipleSetOperations() {
        System.out.println("\n--- Multiple Set States (Active vs Pending) ---");
        
        // Test moving from pending to active
        Integer studentId = 1002;
        String courseCode = "MATH201";
        
        // Add to pending first
        validator.addPendingRegistration(studentId, courseCode);
        System.out.println("Added to pending set");
        
        boolean isPending1 = validator.isAlreadyRegistered(studentId, courseCode);
        System.out.println("Is registered (pending): " + isPending1);
        assertTrue(isPending1);
        
        // Move to active (should remove from pending automatically)
        validator.recordRegistration(studentId, courseCode);
        System.out.println("Moved to active set (should auto-remove from pending)");
        
        boolean isActive = validator.isAlreadyRegistered(studentId, courseCode);
        System.out.println("Is registered (active): " + isActive);
        assertTrue(isActive);
        
        System.out.println("✓ Multiple Set state management working correctly");
    }
    
    @Test
    void testSetSizeAndBulkOperations() {
        System.out.println("\n--- Set Size and Bulk Operations ---");
        
        // Add multiple registrations
        for (int i = 1001; i <= 1005; i++) {
            validator.recordRegistration(i, "CS101");
        }
        
        for (int i = 2001; i <= 2003; i++) {
            validator.addPendingRegistration(i, "MATH201");
        }
        
        System.out.println("Added 5 active and 3 pending registrations");
        
        // The sets should contain the expected number of unique registrations
        // Note: We can't directly access set size without adding getter methods,
        // but we can verify the registrations exist
        
        boolean activeExists = validator.isAlreadyRegistered(1003, "CS101");
        boolean pendingExists = validator.isAlreadyRegistered(2002, "MATH201");
        
        System.out.println("Random active registration exists: " + activeExists);
        System.out.println("Random pending registration exists: " + pendingExists);
        
        assertTrue(activeExists);
        assertTrue(pendingExists);
        
        System.out.println("✓ Set bulk operations and uniqueness maintained");
    }
}