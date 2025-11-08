package com.humber.registration;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Part D: Waitlist Manager - queue-based waitlists 
 * Manages course waitlists using FIFO Queue processing
 */
public class WaitlistManager {
    
    // course code -> waitlist Queue 
    private Map<String, Queue<Integer>> courseWaitlists;
    
    // track positions for students
    private Map<String, Map<Integer, Integer>> waitlistPositions;
    
    // waitlist entries with extra data
    private Map<String, List<WaitlistEntry>> waitlistEntries;
    
    private int maxWaitlistSize = 50;  // max per course
    
    public WaitlistManager() {
        // init everything - using LinkedList as Queue since it implements Queue interface
        this.courseWaitlists = new HashMap<>();
        this.waitlistPositions = new HashMap<>();
        this.waitlistEntries = new HashMap<>();
        
        // note: could use PriorityQueue later if we need priority ordering
    }
    
    /**
     * Adds student to waitlist for course
     * Shows Queue.offer() and FIFO behavior
     */
    public boolean addToWaitlist(Integer studentId, String courseCode, Student student) {
        // basic validation
        if (studentId == null || courseCode == null || courseCode.trim().isEmpty()) {
            return false;
        }
        
        // setup waitlist if first time
        initializeWaitlistForCourse(courseCode);
        
        Queue<Integer> waitlist = courseWaitlists.get(courseCode);
        Map<Integer, Integer> positions = waitlistPositions.get(courseCode);
        
        // check if already on waitlist
        if (positions.containsKey(studentId)) {
            return false;  // duplicate 
        }
        
        // check if waitlist full
        if (waitlist.size() >= maxWaitlistSize) {
            return false;  // no space
        }
        
        // add to queue using offer() - FIFO 
        boolean added = waitlist.offer(studentId);
        
        if (added) {
            // update position tracking 
            int position = waitlist.size();  // position = current size
            positions.put(studentId, position);
            
            // create entry with timestamp for tracking
            WaitlistEntry entry = new WaitlistEntry(studentId, courseCode, java.time.LocalDateTime.now());
            List<WaitlistEntry> entries = waitlistEntries.get(courseCode);
            entries.add(entry);
            
            return true;
        }
        
        return false;  // somthing went wrong
    }
    
    /**
     * Removes the next student from waitlist and enrolls them
     * Demonstrates Queue.poll() operation for FIFO processing
     */
    public Integer processNextWaitlistStudent(String courseCode) {
        Queue<Integer> waitlist = courseWaitlists.get(courseCode);
        
        if (waitlist == null || waitlist.isEmpty()) {
            return null;  // No waitlist or no students waiting
        }
        
        // 1. Use poll() to remove and return next student (FIFO)
        Integer nextStudentId = waitlist.poll();
        
        if (nextStudentId != null) {
            // 2. Update position tracking for remaining students
            updateWaitlistPositions(courseCode);
            
            // 3. Update waitlist entries
            List<WaitlistEntry> entries = waitlistEntries.get(courseCode);
            if (entries != null) {
                // Find and update the entry for this student
                for (WaitlistEntry entry : entries) {
                    if (entry.getStudentId().equals(nextStudentId)) {
                        entry.setStatus("PROCESSED");
                        break;
                    }
                }
            }
        }
        
        // Return student ID that was removed, null if Queue empty
        return nextStudentId;
    }
    
    /**
     * Shows who's next in line without removing them
     * Demonstrates Queue.peek() operation
     */
    public Integer peekNextWaitlistStudent(String courseCode) {
        Queue<Integer> waitlist = courseWaitlists.get(courseCode);
        
        if (waitlist == null) {
            return null;  // No waitlist for this course
        }
        
        // Use Queue.peek() to see next student without removing
        return waitlist.peek();  // Returns null if waitlist empty
    }
    
    /**
     * Removes a specific student from the waitlist
     * Demonstrates Queue.remove() with specific element
     */
    public boolean removeFromWaitlist(Integer studentId, String courseCode) {
        Queue<Integer> waitlist = courseWaitlists.get(courseCode);
        Map<Integer, Integer> positions = waitlistPositions.get(courseCode);
        
        if (waitlist == null || positions == null) {
            return false;  // No waitlist for this course
        }
        
        // 1. Check if student is on waitlist
        if (!positions.containsKey(studentId)) {
            return false;  // Student not on waitlist
        }
        
        // 2. Use Queue.remove(studentId) to remove specific student
        boolean removed = waitlist.remove(studentId);
        
        if (removed) {
            // 3. Update position tracking for all students after removed position
            positions.remove(studentId);
            updateWaitlistPositions(courseCode);
            
            // 4. Update waitlist entries
            List<WaitlistEntry> entries = waitlistEntries.get(courseCode);
            if (entries != null) {
                for (WaitlistEntry entry : entries) {
                    if (entry.getStudentId().equals(studentId)) {
                        entry.setStatus("REMOVED");
                        break;
                    }
                }
            }
        }
        
        return removed;
    }
    
    /**
     * Gets current position of student in waitlist
     * Shows how to work with Queue positioning
     */
    public int getWaitlistPosition(Integer studentId, String courseCode) {
        Map<Integer, Integer> positions = waitlistPositions.get(courseCode);
        
        if (positions == null) {
            return -1;  // No waitlist for this course
        }
        
        // Check waitlistPositions Map for quick lookup
        Integer position = positions.get(studentId);
        
        // Return position (1-based), or -1 if not on waitlist
        return position != null ? position : -1;
    }
    
    /**
     * Gets current size of waitlist for a course
     * Simple Queue.size() operation
     */
    public int getWaitlistSize(String courseCode) {
        Queue<Integer> waitlist = courseWaitlists.get(courseCode);
        
        // Return 0 if course has no waitlist
        if (waitlist == null) {
            return 0;
        }
        
        return waitlist.size();
    }
    
    /**
     * Gets all students currently on waitlist in order
     * Demonstrates Queue iteration while preserving order
     */
    public List<Integer> getWaitlistStudents(String courseCode) {
        Queue<Integer> waitlist = courseWaitlists.get(courseCode);
        
        if (waitlist == null) {
            return new ArrayList<>();  // No waitlist for this course
        }
        
        // Convert to List preserving FIFO order
        // Return defensive copy to prevent external modification
        return new ArrayList<>(waitlist);
    }
    
    /**
     * Moves student to different position in waitlist (if allowed)
     * Advanced Queue manipulation - might need to reconstruct Queue
     */
    public boolean moveStudentInWaitlist(Integer studentId, String courseCode, int newPosition) {
        // TODO: Implement position change logic
        // This is tricky with standard Queue - might need to:
        // 1. Convert Queue to List
        // 2. Remove student from current position  
        // 3. Insert at new position
        // 4. Reconstruct Queue from List
        // 5. Update all position tracking
        return false;
    }
    
    /**
     * Processes multiple waitlist spots when multiple students drop
     * Batch Queue processing
     */
    public List<Integer> processMultipleWaitlistStudents(String courseCode, int numberOfSpots) {
        List<Integer> processedStudents = new ArrayList<>();
        
        // Use multiple poll() operations
        for (int i = 0; i < numberOfSpots; i++) {
            Integer studentId = processNextWaitlistStudent(courseCode);
            
            if (studentId == null) {
                break;  // No more students in waitlist
            }
            
            processedStudents.add(studentId);
        }
        
        // Return List of student IDs that were removed
        return processedStudents;
    }
    
    /**
     * Transfers student from one course waitlist to another
     * Demonstrates cross-Queue operations
     */
    public boolean transferWaitlistStudent(Integer studentId, String fromCourseCode, String toCourseCode) {
        // TODO: Implement waitlist transfer
        // 1. Remove from source Queue
        // 2. Add to destination Queue  
        // 3. Update position tracking for both courses
        // 4. Update waitlist entries
        // Should be atomic - either both operations succeed or both fail
        return false;
    }
    
    /**
     * Gets waitlist statistics across all courses
     * Demonstrates aggregating data from multiple Queues
     */
    public WaitlistStatistics getWaitlistStatistics() {
        // TODO: Implement statistics calculation
        // Iterate through all course waitlists
        // Calculate total waitlisted students, average wait times, etc.
        // Find courses with longest/shortest waitlists
        return null;
    }
    
    /**
     * Clears waitlist for a course (when course is cancelled)
     * Demonstrates Queue.clear() operation
     */
    public List<Integer> clearWaitlist(String courseCode) {
        // TODO: Implement waitlist clearing
        // Get current students before clearing
        // Clear the Queue
        // Update all tracking Maps
        // Return list of students who were removed
        return null;
    }
    
    /**
     * Estimates wait time for a student based on their position
     * Business logic using Queue position data
     */
    public String estimateWaitTime(Integer studentId, String courseCode) {
        // TODO: Implement wait time estimation
        // Get student's position in Queue
        // Calculate estimated wait based on historical data
        // Return human-readable estimate like "2-3 weeks"
        return null;
    }
    
    /**
     * Gets detailed waitlist entry information
     */
    public WaitlistEntry getWaitlistEntry(Integer studentId, String courseCode) {
        // TODO: Implement entry retrieval
        // Look up in waitlistEntries Map
        // Return entry with full metadata
        return null;
    }
    
    // Helper method to initialize waitlist for new course
    private void initializeWaitlistForCourse(String courseCode) {
        courseWaitlists.putIfAbsent(courseCode, new LinkedList<>());
        waitlistPositions.putIfAbsent(courseCode, new HashMap<>());
        waitlistEntries.putIfAbsent(courseCode, new ArrayList<>());
    }
    
    // Helper method to update positions after Queue modification
    private void updateWaitlistPositions(String courseCode) {
        Queue<Integer> waitlist = courseWaitlists.get(courseCode);
        Map<Integer, Integer> positions = waitlistPositions.get(courseCode);
        
        if (waitlist == null || positions == null) {
            return;
        }
        
        // Clear existing positions
        positions.clear();
        
        // Recalculate positions for all students in Queue
        int position = 1;
        for (Integer studentId : waitlist) {
            positions.put(studentId, position++);
        }
    }
    
    // Getters and configuration
    public int getMaxWaitlistSize() {
        return maxWaitlistSize;
    }
    
    public void setMaxWaitlistSize(int maxWaitlistSize) {
        this.maxWaitlistSize = maxWaitlistSize;
    }
    
    public Map<String, Queue<Integer>> getCourseWaitlists() {
        // Return copy to protect internal state
        Map<String, Queue<Integer>> copy = new HashMap<>();
        for (Map.Entry<String, Queue<Integer>> entry : courseWaitlists.entrySet()) {
            copy.put(entry.getKey(), new LinkedList<>(entry.getValue()));
        }
        return copy;
    }
    
    /**
     * Inner class for waitlist entry details
     */
    public static class WaitlistEntry {
        private Integer studentId;
        private String courseCode;
        private java.time.LocalDateTime addedDate;
        private int priority;
        private String reason;
        private String status;
        
        public WaitlistEntry(Integer studentId, String courseCode, java.time.LocalDateTime addedDate) {
            this.studentId = studentId;
            this.courseCode = courseCode;
            this.addedDate = addedDate;
            this.priority = 1; // Default priority
            this.status = "WAITING";
        }
        
        // Getters and setters
        public Integer getStudentId() { return studentId; }
        public String getCourseCode() { return courseCode; }
        public java.time.LocalDateTime getAddedDate() { return addedDate; }
        public int getPriority() { return priority; }
        public void setPriority(int priority) { this.priority = priority; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
    
    /**
     * Inner class for waitlist statistics
     */
    public static class WaitlistStatistics {
        private int totalWaitlistedStudents;
        private int totalCoursesWithWaitlists;
        private double averageWaitlistLength;
        private String longestWaitlistCourse;
        private String shortestWaitlistCourse;
        
        // TODO: Add constructor and getters
        public WaitlistStatistics() {}
        
        public int getTotalWaitlistedStudents() { return totalWaitlistedStudents; }
        public int getTotalCoursesWithWaitlists() { return totalCoursesWithWaitlists; }
        public double getAverageWaitlistLength() { return averageWaitlistLength; }
        public String getLongestWaitlistCourse() { return longestWaitlistCourse; }
        public String getShortestWaitlistCourse() { return shortestWaitlistCourse; }
    }
}