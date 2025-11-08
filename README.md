## Course Registration System – Java Collections Framework Demo

## Project Humber College Computer Sciencea
- Author: Harry Josephence
- Created: November 8, 2025
- Platform: Java 11 + Maven
- Package Manager: Maven
- Build Tool: Apache Maven 
- Framework: Java Collections Framework Demonstration

## Overview
The Course Registration System is a comprehensive Java application that demonstrates all four major components of the Java Collections Framework. The project showcases wrapper classes, Sets, Maps, and Queues through a realistic course registration scenario that includes student management, duplicate prevention, enrollment tracking, and waitlist processing.

## 📦 Download

**Download the complete project:**

[![Download Datalab8.zip](https://img.shields.io/badge/Download-Datalab8.zip-blue?style=for-the-badge&logo=download)](https://github.com/hjoseph777/releases/download/v1/Datalab8.zip)


## 📥 Quick Setup

**Get started with the project:**

```bash
git clone [repository-url]
cd course-registration-system
mvn clean compile
mvn exec:java -Dexec.mainClass="com.humber.registration.App"
```

## Important: Where your Collections code lives
- The main wrapper class demo is in [`StudentIdManager.java`](src/main/java/com/humber/registration/StudentIdManager.java) with autoboxing/unboxing demonstrations
- Set-based duplicate prevention is in [`RegistrationValidator.java`](src/main/java/com/humber/registration/RegistrationValidator.java) 
- Map collections for enrollment tracking are in [`EnrollmentTracker.java`](src/main/java/com/humber/registration/EnrollmentTracker.java)
- Queue-based waitlist processing is in [`WaitlistManager.java`](src/main/java/com/humber/registration/WaitlistManager.java)

## Project Explorer
An interactive, collapsible view of the codebase. Click file names to explore the Collections Framework demonstrations.

<details open>
   <summary><strong>src/main/java/com/humber/registration/ – Core Collections Demos</strong></summary>

   - 📁 <strong>com.humber.registration</strong>
      - 🏠 [`App.java`](src/main/java/com/humber/registration/App.java) – **Main application with usage examples**
      - 🎯 [`RegistrationSystem.java`](src/main/java/com/humber/registration/RegistrationSystem.java) – System facade coordinator
      - 📊 **Collections Framework Components:**
         - 🔢 [`StudentIdManager.java`](src/main/java/com/humber/registration/StudentIdManager.java) – **Part A: Wrapper Classes & Autoboxing**
         - ✅ [`RegistrationValidator.java`](src/main/java/com/humber/registration/RegistrationValidator.java) – **Part B: Set Collections**
         - 📈 [`EnrollmentTracker.java`](src/main/java/com/humber/registration/EnrollmentTracker.java) – **Part C: Map Collections**
         - 📋 [`WaitlistManager.java`](src/main/java/com/humber/registration/WaitlistManager.java) – **Part D: Queue Collections**
      - 📝 **Domain Models:**
         - 👤 [`Student.java`](src/main/java/com/humber/registration/Student.java) – Student entity with proper equals/hashCode
         - 📚 [`Course.java`](src/main/java/com/humber/registration/Course.java) – Course entity with capacity management
</details>

<details>
   <summary><strong>src/test/java/ – Unit Tests</strong></summary>

   - 📁 <strong>com.humber.registration</strong>
      - 🧪 [`AppTest.java`](src/test/java/com/humber/registration/AppTest.java) – Basic application tests
      - 🔢 [`StudentIdManagerTest.java`](src/test/java/com/humber/registration/StudentIdManagerTest.java) – Part A: Integer wrapper class tests
      - ✅ [`RegistrationValidatorTest.java`](src/test/java/com/humber/registration/RegistrationValidatorTest.java) – Part B: Set collections tests
      - 📈 [`EnrollmentTrackerTest.java`](src/test/java/com/humber/registration/EnrollmentTrackerTest.java) – Part C: Map collections tests
      - 📋 [`WaitlistManagerTest.java`](src/test/java/com/humber/registration/WaitlistManagerTest.java) – Part D: Queue collections tests
</details>

<details>
   <summary><strong>Configuration & Build</strong></summary>

   - ⚙️ [`pom.xml`](pom.xml) – Maven project configuration and dependencies
   - 🔧 [`.vscode/settings.json`](.vscode/settings.json) – VS Code workspace configuration
   - � [`README.md`](README.md) – Project documentation (this file)
   - 📁 [`target/`](target/) – Maven build output and compiled classes
   - 📊 [`target/surefire-reports/`](target/surefire-reports/) – JUnit test reports and results
</details>

## File Structure

```text
course-registration-system/
├── 📁 src/main/java/com/humber/registration/  # Core Java Collections demos
│   ├── 🏠 App.java                           # Main application entry point
│   ├── 🎯 RegistrationSystem.java            # System facade coordinator
│   │
│   ├── 📊 Collections Framework Components:
│   ├── 🔢 StudentIdManager.java              # Part A: Integer wrapper & autoboxing
│   ├── ✅ RegistrationValidator.java          # Part B: HashSet duplicate prevention  
│   ├── 📈 EnrollmentTracker.java             # Part C: HashMap bidirectional tracking
│   ├── 📋 WaitlistManager.java               # Part D: LinkedList Queue processing
│   │
│   └── 📝 Domain Models:
│       ├── 👤 Student.java                   # Student entity with hashCode/equals
│       └── 📚 Course.java                    # Course entity with capacity logic
│
├── 📁 src/test/java/com/humber/registration/  # Unit tests
│   └── 🧪 AppTest.java                       # Basic application tests
│
├── 📁 target/                                # Maven build output
│   ├── 📁 classes/                           # Compiled .class files
│   └── 📁 test-classes/                      # Compiled test files
│
├── 📁 .vscode/                               # VS Code workspace settings
├── ⚙️ pom.xml                                # Maven project configuration
└── 📋 README.md                             # Documentation (this file)
```

### Collections Framework Components Reference
| Component | Collection Type | File | Purpose |
|-----------|----------------|------|---------|
| 🔢 | Wrapper Classes | [`StudentIdManager.java`](src/main/java/com/humber/registration/StudentIdManager.java) | **Integer autoboxing/unboxing demos** |
| ✅ | Set Collections | [`RegistrationValidator.java`](src/main/java/com/humber/registration/RegistrationValidator.java) | **HashSet duplicate prevention** |
| 📈 | Map Collections | [`EnrollmentTracker.java`](src/main/java/com/humber/registration/EnrollmentTracker.java) | **HashMap bidirectional relationships** |
| 📋 | Queue Collections | [`WaitlistManager.java`](src/main/java/com/humber/registration/WaitlistManager.java) | **LinkedList FIFO processing** |
| 👤 | Domain Model | [`Student.java`](src/main/java/com/humber/registration/Student.java) | Student entity supporting collections |
| 📚 | Domain Model | [`Course.java`](src/main/java/com/humber/registration/Course.java) | Course entity with capacity management |
| 🎯 | System Facade | [`RegistrationSystem.java`](src/main/java/com/humber/registration/RegistrationSystem.java) | Coordinates all components |
| 🏠 | Main App | [`App.java`](src/main/java/com/humber/registration/App.java) | Demonstrates usage scenarios |


## Build & Run

```bash
# Compile the project
mvn clean compile

# Run the main application
mvn exec:java -Dexec.mainClass="com.humber.registration.App"

# Run tests
mvn test

# Run specific component tests
mvn test -Dtest=StudentIdManagerTest        # Part A: Wrapper Classes
mvn test -Dtest=RegistrationValidatorTest   # Part B: Set Collections  
mvn test -Dtest=EnrollmentTrackerTest       # Part C: Map Collections
mvn test -Dtest=WaitlistManagerTest         # Part D: Queue Collections

# Package as JAR
mvn package
```

## Demo: Collections Framework in Action

The following automated test scenarios demonstrate all four Java Collections Framework components working together. Each test provides detailed output showing the specific collection operations and their educational value.

### Part A: Integer Wrapper Classes & Autoboxing/Unboxing

```bash
Owner@DESKTOP-DKC542P MINGW64 ~/DataLab8/course-registration-system
$ mvn test -Dtest=StudentIdManagerTest
--- Getting Used Student IDs (Set<Integer>) ---
All used Student IDs: [1000, 1001, 1002, 1003, 1004]
Total count: 5
✓ Set<Integer> collection working properly

=== PART A: INTEGER WRAPPER CLASS DEMONSTRATIONS ===
Testing autoboxing (int -> Integer) and unboxing (Integer -> int)...
Generated Student IDs:
  ID 1: 1000 (Integer object)
  ID 2: 1003 (Integer object)
✓ Autoboxing successful: int primitives converted to Integer objects

--- String Parsing Demonstration ---
Parsing '12345' -> 12345
✓ Valid parsing successful
Parsing 'ABC123' -> null
✓ Invalid parsing handled correctly (returned null)

--- Unboxing Demonstration ---
Original Integer: 1000
Validation result: true
✓ Unboxing successful: Integer object converted to int for comparison

--- Integer Comparison Methods ---
Comparing 100 vs 200: -1
Comparing 200 vs 100: 1
Comparing 100 vs 100: 0
✓ Integer.compare() method working correctly
```

### Part B: Set Collections & Duplicate Prevention

```bash
Owner@DESKTOP-DKC542P MINGW64 ~/DataLab8/course-registration-system
$ mvn test -Dtest=RegistrationValidatorTest
--- Set Size and Bulk Operations ---
Added 5 active and 3 pending registrations
Random active registration exists: true
Random pending registration exists: true
✓ Set bulk operations and uniqueness maintained

=== PART B: SET COLLECTIONS DEMONSTRATIONS ===
Testing HashSet duplicate prevention...
First check - Already registered? false
Added to active registrations using Set.add()
Second check - Already registered? true
✓ HashSet duplicate prevention working correctly

--- Multiple Set States (Active vs Pending) ---
Added to pending set
Is registered (pending): true
Moved to active set (should auto-remove from pending)
Is registered (active): true
✓ Multiple Set state management working correctly

--- Registration Validation Using Sets ---
Initial validation: true - Registration validation successful
Duplicate validation: false - Student is already registered or pending for this course
✓ Set-based validation logic working correctly

--- Set.contains() Fast Lookup Demo ---
Added registrations:
  Active: Student 1001 in CS101
  Pending: Student 1001 in MATH201
Set.contains() results:
  CS101: true (should be true)
  MATH201: true (should be true)
  BIO301: false (should be false)
✓ Set.contains() operations working efficiently
```

### Part C: Map Collections & Bidirectional Relationships

```bash
Owner@DESKTOP-DKC542P MINGW64 ~/DataLab8/course-registration-system
$ mvn test -Dtest=EnrollmentTrackerTest
=== PART C: MAP COLLECTIONS DEMONSTRATIONS ===
Testing bidirectional Map relationships...
Enrollment result: true
Students in CS101: [1001]
Courses for student 1001: [CS101]
✓ Bidirectional Map relationships working correctly

--- Map Key-Value Relationships ---
Created complex enrollment scenario:
  Student 1001: CS101, MATH201
  Student 1002: CS101
Map lookups:
  Student 1001 courses: [CS101, MATH201]
  Student 1002 courses: [CS101]
  CS101 students: [1001, 1002]
  MATH201 students: [1001]
✓ Map key-value relationships maintained correctly

--- Map.computeIfAbsent() Demonstration ---
Enrolled 2 students in CS101 using computeIfAbsent()
CS101 enrollment: [1001, 1002]
Total in CS101: 2
✓ Map.computeIfAbsent() creating Sets automatically

--- Map-Based Enrollment Lookups ---
Demonstrating Map-based enrollment lookups:
Student 1001 courses: [CS101, MATH201]
CS101 enrolled students: [1001, 1002]
✓ Map-based enrollment lookups working correctly

--- Enrollment Statistics via Map Operations ---
Enrollment setup complete
Statistics via Map operations:
  CS101 enrollment: 2
  MATH201 enrollment: 1
  Student 1001 courses: 2
  Student 1002 courses: 1
✓ Statistics calculated correctly using Map.size()
```


 ### Part D: Queue Collections & FIFO Processing

```bash
Owner@DESKTOP-DKC542P MINGW64 ~/DataLab8/course-registration-system
$ mvn test -Dtest=WaitlistManagerTest
Student 1001: true
Student 1002: true
Student 1003: true
Current waitlist size: 3
✓ Queue.offer() operations working correctly

--- Queue.peek() Non-Destructive Lookup ---
Waitlist setup: [1001, 1002]
Next in line (peek): 1001
Size after peek: 2
Next in line (peek again): 1001
✓ Queue.peek() non-destructive operations working correctly
```
---

*This project demonstrates comprehensive Java Collections Framework usage through a practical, real-world scenario of course registration management.*