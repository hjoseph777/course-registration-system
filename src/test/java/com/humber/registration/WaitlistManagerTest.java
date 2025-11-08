package com.humber.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for WaitlistManager - Part D queue stuff
 * LinkedList as Queue, FIFO operations, waitlist processing
 */
public class WaitlistManagerTest {
    
    private WaitlistManager manager;
    
    @BeforeEach
    void setUp() {
        manager = new WaitlistManager();
    }
    
    private Student createTestStudent(int id) {
        return new Student(id, "Student" + id, "Test", "student" + id + "@humber.ca", "Computer Science");
    }
    
    @Test
    void testQueueBasedFIFOProcessing() {
        System.out.println("\n=== PART D: QUEUE COLLECTIONS DEMONSTRATIONS ===");
        System.out.println("Testing FIFO (First In, First Out) Queue operations...");
        
        String courseCode = "CS101";
        
        // Add students to waitlist using Queue.offer()
        boolean added1 = manager.addToWaitlist(1001, courseCode, createTestStudent(1001));
        boolean added2 = manager.addToWaitlist(1002, courseCode, createTestStudent(1002));
        boolean added3 = manager.addToWaitlist(1003, courseCode, createTestStudent(1003));
        
        System.out.println("Added to waitlist:");
        System.out.println("  Student 1001: " + added1);
        System.out.println("  Student 1002: " + added2);
        System.out.println("  Student 1003: " + added3);
        
        assertTrue(added1 && added2 && added3, "All students should be added to waitlist");
        
        // Check waitlist size
        int waitlistSize = manager.getWaitlistSize(courseCode);
        System.out.println("Current waitlist size: " + waitlistSize);
        assertEquals(3, waitlistSize);
        
        System.out.println("✓ Queue.offer() operations working correctly");
    }
    
    @Test
    void testQueuePollOperations() {
        System.out.println("\n--- Queue.poll() FIFO Demonstration ---");
        
        String courseCode = "CS101";
        
        // Setup waitlist
        manager.addToWaitlist(1001, courseCode, createTestStudent(1001));
        manager.addToWaitlist(1002, courseCode, createTestStudent(1002));
        manager.addToWaitlist(1003, courseCode, createTestStudent(1003));
        
        System.out.println("Initial waitlist: [1001, 1002, 1003]");
        
        // Process waitlist using Queue.poll() - FIFO order
        Integer firstOut = manager.processNextWaitlistStudent(courseCode);
        Integer secondOut = manager.processNextWaitlistStudent(courseCode);
        
        System.out.println("First student processed (poll): " + firstOut);
        System.out.println("Second student processed (poll): " + secondOut);
        
        assertEquals(1001, firstOut, "First student should be 1001 (FIFO)");
        assertEquals(1002, secondOut, "Second student should be 1002 (FIFO)");
        
        // Check remaining waitlist
        int remainingSize = manager.getWaitlistSize(courseCode);
        System.out.println("Remaining waitlist size: " + remainingSize);
        assertEquals(1, remainingSize);
        
        System.out.println("✓ Queue.poll() FIFO processing working correctly");
    }
    
    @Test
    void testQueuePeekOperations() {
        System.out.println("\n--- Queue.peek() Non-Destructive Lookup ---");
        
        String courseCode = "CS101";
        
        // Setup waitlist
        manager.addToWaitlist(1001, courseCode, createTestStudent(1001));
        manager.addToWaitlist(1002, courseCode, createTestStudent(1002));
        
        System.out.println("Waitlist setup: [1001, 1002]");
        
        // Use Queue.peek() to see next without removing
        Integer nextInLine = manager.peekNextWaitlistStudent(courseCode);
        System.out.println("Next in line (peek): " + nextInLine);
        assertEquals(1001, nextInLine, "Next should be 1001");
        
        // Verify peek didn't remove the student
        int sizeAfterPeek = manager.getWaitlistSize(courseCode);
        System.out.println("Size after peek: " + sizeAfterPeek);
        assertEquals(2, sizeAfterPeek, "Peek should not remove students");
        
        // Another peek should return the same student
        Integer nextAgain = manager.peekNextWaitlistStudent(courseCode);
        System.out.println("Next in line (peek again): " + nextAgain);
        assertEquals(1001, nextAgain, "Should still be 1001");
        
        System.out.println("✓ Queue.peek() non-destructive operations working correctly");
    }
    
    @Test
    void testWaitlistPositionTracking() {
        System.out.println("\n--- Waitlist Position Tracking ---");
        
        String courseCode = "CS101";
        
        // Add students and check positions
        manager.addToWaitlist(1001, courseCode, createTestStudent(1001));
        manager.addToWaitlist(1002, courseCode, createTestStudent(1002));
        manager.addToWaitlist(1003, courseCode, createTestStudent(1003));
        
        System.out.println("Added students: 1001, 1002, 1003");
        
        // Check positions (should be 1-indexed)
        int pos1001 = manager.getWaitlistPosition(1001, courseCode);
        int pos1002 = manager.getWaitlistPosition(1002, courseCode);
        int pos1003 = manager.getWaitlistPosition(1003, courseCode);
        
        System.out.println("Waitlist positions:");
        System.out.println("  Student 1001: Position " + pos1001);
        System.out.println("  Student 1002: Position " + pos1002);
        System.out.println("  Student 1003: Position " + pos1003);
        
        assertEquals(1, pos1001, "First student should be position 1");
        assertEquals(2, pos1002, "Second student should be position 2");
        assertEquals(3, pos1003, "Third student should be position 3");
        
        System.out.println("✓ Queue position tracking working correctly");
    }
    
    @Test
    void testQueueEmptyOperations() {
        System.out.println("\n--- Queue Empty State Handling ---");
        
        String courseCode = "CS101";
        
        // Test operations on empty queue
        Integer nextEmpty = manager.peekNextWaitlistStudent(courseCode);
        Integer processEmpty = manager.processNextWaitlistStudent(courseCode);
        int emptySize = manager.getWaitlistSize(courseCode);
        
        System.out.println("Empty queue operations:");
        System.out.println("  peek() on empty: " + nextEmpty);
        System.out.println("  poll() on empty: " + processEmpty);
        System.out.println("  size() on empty: " + emptySize);
        
        assertNull(nextEmpty, "peek() on empty should return null");
        assertNull(processEmpty, "poll() on empty should return null");
        assertEquals(0, emptySize, "Empty queue size should be 0");
        
        // Test position lookup on empty queue
        int positionEmpty = manager.getWaitlistPosition(1001, courseCode);
        System.out.println("  position lookup on empty: " + positionEmpty);
        assertEquals(-1, positionEmpty, "Position in empty queue should be -1");
        
        System.out.println("✓ Queue empty state handling working correctly");
    }
    
    @Test
    void testMultipleCourseQueues() {
        System.out.println("\n--- Multiple Course Waitlists ---");
        
        // Test multiple independent queues
        manager.addToWaitlist(1001, "CS101", createTestStudent(1001));
        manager.addToWaitlist(1002, "CS101", createTestStudent(1002));
        
        manager.addToWaitlist(1003, "MATH201", createTestStudent(1003));
        manager.addToWaitlist(1004, "MATH201", createTestStudent(1004));
        manager.addToWaitlist(1005, "MATH201", createTestStudent(1005));
        
        System.out.println("Setup multiple course waitlists:");
        System.out.println("  CS101: [1001, 1002]");
        System.out.println("  MATH201: [1003, 1004, 1005]");
        
        // Test independent queue operations
        int cs101Size = manager.getWaitlistSize("CS101");
        int math201Size = manager.getWaitlistSize("MATH201");
        
        Integer cs101Next = manager.peekNextWaitlistStudent("CS101");
        Integer math201Next = manager.peekNextWaitlistStudent("MATH201");
        
        System.out.println("Independent queue states:");
        System.out.println("  CS101 size: " + cs101Size + ", next: " + cs101Next);
        System.out.println("  MATH201 size: " + math201Size + ", next: " + math201Next);
        
        assertEquals(2, cs101Size);
        assertEquals(3, math201Size);
        assertEquals(1001, cs101Next);
        assertEquals(1003, math201Next);
        
        System.out.println("✓ Multiple independent Queue collections working correctly");
    }
}