package com.humber.registration;

/**
 * Main app for Humber Course Registration System
 * Shows all 4 parts working together:
 * A: Student ID stuff with wrapper classes 
 * B: Duplicate checking with Sets
 * C: Enrollment tracking with Maps  
 * D: Waitlists with Queues
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== Humber Course Registration System ===");
        System.out.println("Initializing system components...");
        
        // setup the main system 
        RegistrationSystem registrationSystem = new RegistrationSystem();
        
        System.out.println("✓ System initialized successfully!");
        System.out.println("✓ StudentIdManager ready (Part A: Wrapper classes)");
        System.out.println("✓ RegistrationValidator ready (Part B: Sets for duplicates)");
        System.out.println("✓ EnrollmentTracker ready (Part C: Maps for enrollments)");
        System.out.println("✓ WaitlistManager ready (Part D: Queues for waitlists)");
        
        // check system status
        System.out.println("\nSystem Status:");
        System.out.println("Registration System Type: " + registrationSystem.getClass().getSimpleName());
        System.out.println("System Components: All four Collections Framework parts initialized");
        
        System.out.println("\nSystem ready for course registration operations!");
        System.out.println("Run unit tests to see Collections Framework demonstrations:");
        System.out.println("  mvn test -Dtest=StudentIdManagerTest    # Part A: Integer wrapper classes");
        System.out.println("  mvn test -Dtest=RegistrationValidatorTest # Part B: Set collections");
        System.out.println("  mvn test -Dtest=EnrollmentTrackerTest    # Part C: Map collections");
        System.out.println("  mvn test -Dtest=WaitlistManagerTest      # Part D: Queue collections");
    }
}
