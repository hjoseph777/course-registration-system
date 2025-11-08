package com.humber.registration;

import java.time.LocalDateTime;

/**
 * StudentWaitRequest - represents a student's waitlist request
 * Used in Part D for queue processing demos
 */
public class StudentWaitRequest {
    private String studentName;
    private String courseName;
    private int priority;      // 1 = urgent, higher numbers = normal
    private LocalDateTime timestamp;
    
    public StudentWaitRequest(String studentName, String courseName, int priority) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.priority = priority;
        this.timestamp = LocalDateTime.now();
    }
    
    // getters
    public String getStudentName() { return studentName; }
    public String getCourseName() { return courseName; }
    public int getPriority() { return priority; }
    public LocalDateTime getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return studentName + " wants " + courseName + " (priority: " + priority + ")";
    }
}