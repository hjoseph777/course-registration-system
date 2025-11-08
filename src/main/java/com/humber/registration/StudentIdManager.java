package com.humber.registration;

/**
 * Part A: Student ID Manager - wrapper class demos
 * Shows autoboxing/unboxing with Integer 
 */
public class StudentIdManager {
    
    // using Integer collections 
    private java.util.Set<Integer> usedStudentIds;
    private java.util.List<Integer> recentlyAssigned;
    
    private Integer nextAvailableId;
    private Integer maxIdValue;
    
    public StudentIdManager() {
        // setup collections
        this.usedStudentIds = new java.util.HashSet<>();
        this.recentlyAssigned = new java.util.ArrayList<>();
        
        this.nextAvailableId = Integer.valueOf(1000);  // could just use 1000 but showing explicit boxing
        this.maxIdValue = Integer.valueOf(999999);
        
        // add some test IDs
        reserveStudentId(Integer.valueOf(1001));
        reserveStudentId(Integer.valueOf(1002)); 
    }
    
    /**
     * Gets a new unique student ID
     * Shows autoboxing when converting int to Integer
     */
    public Integer generateNewStudentId() {
        Integer candidateId = this.nextAvailableId;
        
        // find an unused ID 
        while (candidateId.compareTo(maxIdValue) <= 0) {
            
            // Check if this ID is already used
            if (!usedStudentIds.contains(candidateId)) {
                // found one! 
                reserveStudentId(candidateId);
                
                // update next - autoboxing happens here
                this.nextAvailableId = candidateId + 1;  // int + Integer -> Integer
                
                // add to recent list for tracking
                recentlyAssigned.add(candidateId);
                if (recentlyAssigned.size() > 10) {
                    recentlyAssigned.remove(0);  // keep list small
                }
                
                return candidateId;
            }
            
            // try next one
            candidateId = candidateId + 1;
        }
        
        throw new RuntimeException("No available student IDs! Max reached: " + maxIdValue);
    }
    
    /**
     * Validates student ID format
     * Shows unboxing when we extract int value from Integer
     */
    public boolean validateStudentId(Integer studentId) {
        // null check - wrappers can be null unlike primitives 
        if (studentId == null) {
            return false;
        }
        
        // range check - shows implicit unboxing 
        if (studentId < 1000 || studentId > maxIdValue) {
            return false;
        }
        
        // check format using wrapper methods
        String idString = studentId.toString();
        if (idString.endsWith("0000")) {  // probably not a good ID
            return false;  // probably invalid
        }
        
        return true;
    }
    
    /**
     * Converts between different ID formats - demonstrates wrapper class methods
     */
    public String formatStudentId(Integer studentId) {
        // Handle null case - unique to wrapper classes
        if (studentId == null) {
            return "INVALID_ID";
        }
        
        // Use Integer.toString() method - wrapper class functionality
        String baseId = studentId.toString();
        
        // Format with leading zeros using Integer operations
        // First, let's ensure it's in our valid range
        if (!validateStudentId(studentId)) {
            return "INVALID_ID_" + baseId;
        }
        
        // Create formatted string with prefix
        // Using String.format with wrapper class value
        return String.format("STU%06d", studentId);  // Auto-unboxing in format
    }
    
    /**
     * Parses student ID from string - shows wrapper class parsing capabilities
     */
    public Integer parseStudentId(String idString) {
        // Handle null or empty strings
        if (idString == null || idString.trim().isEmpty()) {
            return null;  // Return null Integer (wrapper class can be null)
        }
        
        // Clean up the input string
        String cleanId = idString.trim().toUpperCase();
        
        try {
            // Handle formatted IDs like "STU001234"
            if (cleanId.startsWith("STU")) {
                // Extract numeric part
                String numericPart = cleanId.substring(3);
                
                // Use Integer.valueOf() - returns Integer wrapper
                Integer parsedId = Integer.valueOf(numericPart);
                
                // Validate the parsed ID
                if (validateStudentId(parsedId)) {
                    return parsedId;
                } else {
                    return null;  // Invalid ID
                }
            }
            // Handle raw numeric strings
            else {
                // Demonstrate parseInt vs valueOf difference:
                // parseInt returns primitive int, valueOf returns Integer wrapper
                int primitiveResult = Integer.parseInt(cleanId);  // Returns int
                Integer wrapperResult = Integer.valueOf(primitiveResult);  // Autoboxing
                
                // Could also use Integer.valueOf(cleanId) directly
                // but showing the difference for educational purposes
                
                if (validateStudentId(wrapperResult)) {
                    return wrapperResult;
                } else {
                    return null;
                }
            }
            
        } catch (NumberFormatException e) {
            // This is why we need to handle exceptions with parsing
            // Return null to indicate parsing failure
            return null;
        }
    }
    
    /**
     * Compares student IDs - demonstrates wrapper class comparison methods
     */
    public int compareStudentIds(Integer id1, Integer id2) {
        // Handle null cases - unique to wrapper classes
        if (id1 == null && id2 == null) {
            return 0;  // Both null, considered equal
        }
        if (id1 == null) {
            return -1; // null is "less than" any valid ID
        }
        if (id2 == null) {
            return 1;  // any valid ID is "greater than" null
        }
        
        // Use Integer.compareTo() method - wrapper class feature
        // This is more reliable than == for wrapper classes!
        int result = id1.compareTo(id2);
        
        // Educational note: showing why == can be dangerous with wrapper classes
        // Integer a = 127; Integer b = 127; // a == b is true (cached)
        // Integer c = 128; Integer d = 128; // c == d is false (not cached)
        // Always use .equals() or .compareTo() for wrapper classes!
        
        return result;  // -1 if id1 < id2, 0 if equal, 1 if id1 > id2
    }
    
    /**
     * Gets statistics about student IDs - demonstrates wrapper class utility methods
     */
    public void displayIdStatistics() {
        System.out.println("\n=== Student ID Statistics ===");
        
        if (usedStudentIds.isEmpty()) {
            System.out.println("No student IDs have been assigned yet.");
            return;
        }
        
        // Convert Set to List for easier processing - demonstrates collection conversion
        java.util.List<Integer> idList = new java.util.ArrayList<>(usedStudentIds);
        
        // Sort using wrapper class natural ordering
        java.util.Collections.sort(idList);  // Uses Integer.compareTo() internally
        
        // Find min and max using wrapper class methods
        Integer minId = java.util.Collections.min(idList);  // Returns Integer wrapper
        Integer maxId = java.util.Collections.max(idList);  // Returns Integer wrapper
        
        // Calculate range using wrapper class arithmetic (demonstrates autoboxing)
        Integer range = maxId - minId;  // Auto-unboxing to int, then autoboxing back to Integer
        
        // Calculate average - shows wrapper class in mathematical operations
        long sum = 0;
        for (Integer id : usedStudentIds) {
            sum += id;  // Auto-unboxing Integer to int for addition
        }
        double average = (double) sum / usedStudentIds.size();
        
        // Display statistics
        System.out.println("Total IDs assigned: " + usedStudentIds.size());
        System.out.println("Lowest ID: " + formatStudentId(minId));
        System.out.println("Highest ID: " + formatStudentId(maxId));
        System.out.println("ID Range: " + range);
        System.out.println("Average ID: " + String.format("%.2f", average));
        System.out.println("Next available: " + formatStudentId(nextAvailableId));
        
        // Show recent assignments
        System.out.println("Recently assigned IDs:");
        for (int i = recentlyAssigned.size() - 1; i >= 0; i--) {
            Integer recentId = recentlyAssigned.get(i);
            System.out.println("  " + formatStudentId(recentId));
        }
    }
    
    /**
     * Reserves a specific student ID
     */
    public boolean reserveStudentId(Integer studentId) {
        // Validate the ID first
        if (!validateStudentId(studentId)) {
            return false;  // Invalid ID cannot be reserved
        }
        
        // Check if already reserved - Set.contains() with wrapper class
        if (usedStudentIds.contains(studentId)) {
            return false;  // Already in use
        }
        
        // Reserve the ID - Set.add() with Integer wrapper
        boolean added = usedStudentIds.add(studentId);
        
        if (added) {
            // Update recently assigned list
            recentlyAssigned.add(studentId);
            
            // Keep recent list manageable
            if (recentlyAssigned.size() > 10) {
                recentlyAssigned.remove(0);
            }
            
            // Update next available ID if this affects our sequence
            if (studentId.equals(nextAvailableId)) {
                // We just reserved the next available ID, increment it
                nextAvailableId = nextAvailableId + 1;  // Autoboxing in action
            }
        }
        
        return added;
    }
    
    /**
     * Releases a student ID back to available pool
     */
    public boolean releaseStudentId(Integer studentId) {
        // Validate the ID first
        if (!validateStudentId(studentId)) {
            return false;  // Invalid ID cannot be released
        }
        
        // Remove from used set - Set.remove() with wrapper class
        boolean removed = usedStudentIds.remove(studentId);
        
        if (removed) {
            // Update next available if this creates a lower available ID
            if (studentId.compareTo(nextAvailableId) < 0) {
                nextAvailableId = studentId;  // This ID is now available again
            }
            
            // Note: We don't remove from recentlyAssigned list
            // That maintains historical record of assignments
        }
        
        return removed;
    }
    
    // Getters for testing and validation
    public java.util.Set<Integer> getUsedStudentIds() {
        return usedStudentIds;
    }
    
    public Integer getNextAvailableId() {
        return nextAvailableId;
    }
    
    public Integer getMaxIdValue() {
        return maxIdValue;
    }
}