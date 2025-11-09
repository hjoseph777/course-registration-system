package com.humber.registration;

import java.util.*;

/**
 * Demo version with sample data
 * No user input required
 */
public class DemoApp {
    public static void main(String[] args) {
        System.out.println("=== Course Registration System ===\n");
        
        runStudentIdDemo();
        runRegistrationDemo();
        runEnrollmentDemo();
        runWaitlistDemo();
        
        System.out.println("\n=== Demo Complete ===");
    }
    
    private static void runStudentIdDemo() {
        System.out.println("--- Student ID Management ---");
        
        List<Integer> studentIds = Arrays.asList(1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010);
        System.out.println("Sample student IDs: " + studentIds);
        
        // Calculate statistics
        int sum = studentIds.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum of all IDs: " + sum);
        System.out.println("Average ID: " + (sum / studentIds.size()));
        
        // Check for duplicate IDs
        long uniqueCount = studentIds.stream().distinct().count();
        if (uniqueCount != studentIds.size()) {
            System.out.println("Warning: Duplicate IDs detected!");
        } else {
            System.out.println("All IDs are unique.");
        }
        System.out.println();
    }
    
    private static void runRegistrationDemo() {
        System.out.println("--- Student Registration ---");
        
        List<String> registrations = Arrays.asList("Alex", "Sarah", "Mike", "Alex", "Emma", "Sarah", "John");
        System.out.println("Original list: " + registrations);
        
        HashSet<String> uniqueStudents = new HashSet<>(registrations);
        System.out.println("HashSet (no duplicates): " + uniqueStudents);
        
        TreeSet<String> sortedRoster = new TreeSet<>(registrations);
        System.out.println("TreeSet (sorted): " + sortedRoster);
        
        System.out.println("Is Alex enrolled? " + sortedRoster.contains("Alex"));
        System.out.println("First student (alphabetically): " + sortedRoster.first());
        System.out.println("Last student (alphabetically): " + sortedRoster.last());
        System.out.println();
    }
    
    private static void runEnrollmentDemo() {
        System.out.println("--- Course Enrollment ---");
        
        HashMap<String, Integer> enrollmentCounts = new HashMap<>();
        enrollmentCounts.put("Java Programming", 25);
        enrollmentCounts.put("Web Dev", 30);
        enrollmentCounts.put("Database Systems", 18);
        enrollmentCounts.put("Mobile Apps", 22);
        enrollmentCounts.put("AI Fundamentals", 35);
        
        System.out.println("Enrollment counts:");
        enrollmentCounts.forEach((course, count) -> 
            System.out.println("  " + course + ": " + count + " students"));
        
        System.out.println("\nTop 3 courses by enrollment:");
        enrollmentCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue()));
        
        System.out.println("\nCourses (alphabetical):");
        TreeMap<String, Integer> sortedCourses = new TreeMap<>(enrollmentCounts);
        sortedCourses.forEach((course, count) -> 
            System.out.println("  " + course + ": " + count + " students"));
        System.out.println();
    }
    
    private static void runWaitlistDemo() {
        System.out.println("--- Waitlist Processing ---");
        
        PriorityQueue<StudentWaitRequest> urgentWaitlist = new PriorityQueue<>(
            Comparator.comparing(StudentWaitRequest::getPriority)
                      .thenComparing(StudentWaitRequest::getTimestamp));
        
        ArrayDeque<StudentWaitRequest> normalWaitlist = new ArrayDeque<>();
        
        urgentWaitlist.offer(new StudentWaitRequest("Alex", "Java Programming", 1));
        normalWaitlist.offer(new StudentWaitRequest("Sarah", "Web Dev", 5));
        urgentWaitlist.offer(new StudentWaitRequest("Mike", "Database Systems", 2));
        normalWaitlist.offer(new StudentWaitRequest("Emma", "Mobile Apps", 5));
        normalWaitlist.offer(new StudentWaitRequest("John", "AI Fundamentals", 5));
        urgentWaitlist.offer(new StudentWaitRequest("Lisa", "Java Programming", 1));
        normalWaitlist.offer(new StudentWaitRequest("Tom", "Web Dev", 5));
        normalWaitlist.offer(new StudentWaitRequest("Anna", "Database Systems", 5));
        
        System.out.println("Processing waitlist requests:");
        int processedCount = 1;
        
        while (!urgentWaitlist.isEmpty() || !normalWaitlist.isEmpty()) {
            if (!urgentWaitlist.isEmpty()) {
                StudentWaitRequest request = urgentWaitlist.poll();
                System.out.println(processedCount++ + ". URGENT: " + request);
            }
            
            if (!normalWaitlist.isEmpty()) {
                StudentWaitRequest request = normalWaitlist.poll();
                System.out.println(processedCount++ + ". NORMAL: " + request);
            }
        }
        System.out.println();
    }
}