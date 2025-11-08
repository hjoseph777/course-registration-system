package com.humber.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for StudentIdManager - Part A wrapper stuff
 * Integer autoboxing/unboxing demos
 */
public class StudentIdManagerTest {
    
    private StudentIdManager manager;
    
    @BeforeEach
    void setUp() {
        manager = new StudentIdManager();
    }
    
    @Test
    void testWrapperClassAutoboxingDemo() {
        System.out.println("\n=== PART A: INTEGER WRAPPER CLASS DEMONSTRATIONS ===");
        System.out.println("Testing autoboxing (int -> Integer) and unboxing (Integer -> int)...");
        
        // generate some IDs - should auto-box to Integer
        Integer studentId1 = manager.generateNewStudentId();
        Integer studentId2 = manager.generateNewStudentId();
        
        System.out.println("Generated Student IDs:");
        System.out.println("  ID 1: " + studentId1 + " (Integer object)");
        System.out.println("  ID 2: " + studentId2 + " (Integer object)");
        
        // check if autoboxing worked
        assertTrue(studentId1 instanceof Integer);
        assertTrue(studentId2 instanceof Integer);
        assertTrue(studentId2 > studentId1, "IDs should increment");
        
        System.out.println("✓ Autoboxing successful: int primitives converted to Integer objects");
    }
    
    @Test
    void testWrapperClassUnboxingDemo() {
        System.out.println("\n--- Unboxing Demonstration ---");
        
        Integer wrapperId = manager.generateNewStudentId();
        System.out.println("Original Integer: " + wrapperId);
        
        // this should unbox Integer -> int automatically  
        boolean isValid = manager.validateStudentId(wrapperId);
        System.out.println("Validation result: " + isValid);
        System.out.println("✓ Unboxing successful: Integer object converted to int for comparison");
        
        assertTrue(isValid);
    }
    
    @Test
    void testStringToIntegerParsing() {
        System.out.println("\n--- String Parsing Demonstration ---");
        
        // test valid string
        String validId = "12345";
        Integer parsed = manager.parseStudentId(validId);
        
        System.out.println("Parsing '" + validId + "' -> " + parsed);
        assertEquals(12345, parsed.intValue());
        System.out.println("✓ Valid parsing successful");
        
        // test junk input 
        String invalidId = "ABC123";
        Integer invalidParsed = manager.parseStudentId(invalidId);
        
        System.out.println("Parsing '" + invalidId + "' -> " + invalidParsed);
        assertNull(invalidParsed);
        System.out.println("✓ Invalid parsing handled correctly (returned null)");
    }
    
    @Test
    void testIntegerComparisonMethods() {
        System.out.println("\n--- Integer Comparison Methods ---");
        
        Integer id1 = 100;  // autobox
        Integer id2 = 200;  
        Integer id3 = 100;  
        
        int comparison1 = manager.compareStudentIds(id1, id2);
        int comparison2 = manager.compareStudentIds(id2, id1);
        int comparison3 = manager.compareStudentIds(id1, id3);
        
        System.out.println("Comparing " + id1 + " vs " + id2 + ": " + comparison1);
        System.out.println("Comparing " + id2 + " vs " + id1 + ": " + comparison2);
        System.out.println("Comparing " + id1 + " vs " + id3 + ": " + comparison3);
        
        assertTrue(comparison1 < 0, "100 should be less than 200");
        assertTrue(comparison2 > 0, "200 should be greater than 100");
        assertEquals(0, comparison3, "100 should equal 100");
        
        System.out.println("✓ Integer.compare() method working correctly");
    }
    
    @Test
    void testUsedStudentIds() {
        System.out.println("\n--- Getting Used Student IDs (Set<Integer>) ---");
        
        // make some IDs
        manager.generateNewStudentId();
        manager.generateNewStudentId();
        manager.generateNewStudentId();
        
        java.util.Set<Integer> usedIds = manager.getUsedStudentIds();
        System.out.println("All used Student IDs: " + usedIds);
        System.out.println("Total count: " + usedIds.size());
        
        assertTrue(usedIds.size() >= 3);
        System.out.println("✓ Set<Integer> collection working properly");
    }
}