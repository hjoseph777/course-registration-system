package com.humber.registration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    
    @Test
    public void testAppInstantiation() {
        assertDoesNotThrow(() -> {
            App app = new App();
            assertNotNull(app);
        });
    }
    
    @Test
    public void testStudentWaitRequest() {
        StudentWaitRequest request = new StudentWaitRequest("Alex", "Java Programming", 1);
        assertEquals("Alex", request.getStudentName());
        assertEquals("Java Programming", request.getCourseName());
        assertEquals(1, request.getPriority());
        assertNotNull(request.getTimestamp());
    }
    
    @Test
    public void testStudentWaitRequestComparison() {
        StudentWaitRequest urgent = new StudentWaitRequest("John", "Web Dev", 1);
        StudentWaitRequest normal = new StudentWaitRequest("Jane", "Database", 5);
        
        assertTrue(urgent.getPriority() < normal.getPriority());
    }
}
