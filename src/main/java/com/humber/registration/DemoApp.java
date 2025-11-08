package com.humber.registration;

import java.util.*;

/**
 * Demo version with sample data for README documentation
 * Shows Collections Framework demos without requiring user input
 */
public class DemoApp {
    public static void main(String[] args) {
        System.out.println("=== Humber Registration System - Collections Demo ===\n");
        
        runPartADemo();  // Student ID handling with sample data
        runPartBDemo();  // Duplicate registration with Sets
        runPartCDemo();  // Course enrollment with Maps
        runPartDDemo();  // Waitlist processing with Queues
        
        System.out.println("\n=== Demo Complete ===");
    }
    
    // Part A: Student ID Handling (Wrapper Classes & Autoboxing) - with sample data
    private static void runPartADemo() {
        System.out.println("--- Part A: Student ID Handling (Wrapper Classes) ---");
        
        List<Integer> studentIds = Arrays.asList(1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010);
        System.out.println("Sample student IDs: " + studentIds);
        
        // compute sum (unboxing: Integer -> int)
        int sum = 0;
        for (Integer id : studentIds) {
            sum += id; // unboxing happens here
        }
        System.out.println("Sum of all IDs: " + sum);
        
        // Test autoboxing comparisons
        Integer a = 128, b = 128;
        Integer c = 42, d = 42;
        
        System.out.println("\nAutoboxing comparison tests:");
        System.out.println("128 == 128: " + (a == b));
        System.out.println("128 .equals 128: " + a.equals(b));
        System.out.println("42 == 42: " + (c == d));
        System.out.println("42 .equals 42: " + c.equals(d));
        System.out.println();
    }
    
    // Part B: Avoiding Duplicate Registration (Sets)
    private static void runPartBDemo() {
        System.out.println("--- Part B: Avoiding Duplicate Registration (Sets) ---");
        
        // list with duplicates
        List<String> registrations = Arrays.asList("Alex", "Sarah", "Mike", "Alex", "Emma", "Sarah", "John");
        System.out.println("Original list: " + registrations);
        
        // remove duplicates with HashSet
        HashSet<String> uniqueStudents = new HashSet<>(registrations);
        System.out.println("HashSet (no duplicates): " + uniqueStudents);
        
        // sorted roster with TreeSet
        TreeSet<String> sortedRoster = new TreeSet<>(registrations);
        System.out.println("TreeSet (sorted): " + sortedRoster);
        
        System.out.println("Is Alex enrolled? " + sortedRoster.contains("Alex"));
        System.out.println("First student (alphabetically): " + sortedRoster.first());
        System.out.println("Last student (alphabetically): " + sortedRoster.last());
        System.out.println();
    }
    
    // Part C: Course Enrollment Records (Map)
    private static void runPartCDemo() {
        System.out.println("--- Part C: Course Enrollment Records (Map) ---");
        
        // sample course enrollments
        HashMap<String, Integer> enrollmentCounts = new HashMap<>();
        enrollmentCounts.put("Java Programming", 25);
        enrollmentCounts.put("Web Dev", 30);
        enrollmentCounts.put("Database Systems", 18);
        enrollmentCounts.put("Mobile Apps", 22);
        enrollmentCounts.put("AI Fundamentals", 35);
        
        System.out.println("Enrollment counts:");
        enrollmentCounts.forEach((course, count) -> 
            System.out.println("  " + course + ": " + count + " students"));
        
        // top 3 courses
        System.out.println("\nTop 3 courses by enrollment:");
        enrollmentCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue()));
        
        // alphabetical course list
        System.out.println("\nCourses (alphabetical):");
        TreeMap<String, Integer> sortedCourses = new TreeMap<>(enrollmentCounts);
        sortedCourses.forEach((course, count) -> 
            System.out.println("  " + course + ": " + count + " students"));
        System.out.println();
    }
    
    // Part D: Waitlist Queue Processing
    private static void runPartDDemo() {
        System.out.println("--- Part D: Waitlist Queue Processing ---");
        
        // Priority queue for urgent requests
        PriorityQueue<StudentWaitRequest> urgentWaitlist = new PriorityQueue<>(
            Comparator.comparing(StudentWaitRequest::getPriority)
                      .thenComparing(StudentWaitRequest::getTimestamp));
        
        // Regular queue for normal requests
        ArrayDeque<StudentWaitRequest> normalWaitlist = new ArrayDeque<>();
        
        // add sample requests
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
        
        // process: one urgent, then one normal, repeat
        while (!urgentWaitlist.isEmpty() || !normalWaitlist.isEmpty()) {
            // handle urgent first
            if (!urgentWaitlist.isEmpty()) {
                StudentWaitRequest request = urgentWaitlist.poll();
                System.out.println(processedCount++ + ". URGENT: " + request);
            }
            
            // then handle normal
            if (!normalWaitlist.isEmpty()) {
                StudentWaitRequest request = normalWaitlist.poll();
                System.out.println(processedCount++ + ". NORMAL: " + request);
            }
        }
        System.out.println();
    }
}