package com.humber.registration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic test for Collections demo app
 */
public class AppTest {
    
    @Test
    public void testAppRuns() {
        // just make sure the main method doesn't crash
        assertDoesNotThrow(() -> {
            // App.main would require user input, so we just test instantiation
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
}
