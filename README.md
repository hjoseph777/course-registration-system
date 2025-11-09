# Course Registration System

## Project Information
- Author: Harry Joseph
- Created: November 8, 2025
- Java Version: 21 LTS
- Build Tool: Maven

## Overview
This project demonstrates Java Collections Framework usage through a course registration system. It covers the main collection types: Lists, Sets, Maps, and Queues with practical examples.

## Download

**Download the complete project:**

[![Download DataLab8.zip](https://img.shields.io/badge/Download-DataLab8.zip-blue?style=for-the-badge&logo=download)](https://github.com/hjoseph777/course-registration-system/releases/download/v1/DataLab8.zip)


## Setup

```bash
git clone [repository-url]
cd course-registration-system
mvn clean compile
mvn exec:java -Dexec.mainClass="com.humber.registration.App"
```

## Project Structure

```text
course-registration-system/
├── src/main/java/com/humber/registration/
│   ├── App.java                    # Main application
│   └── StudentWaitRequest.java     # Wait request model
├── src/test/java/com/humber/registration/
│   └── AppTest.java               # Tests
├── pom.xml                        # Maven configuration
└── README.md                      # This file
```

### Console Output

```
=== Course Registration System ===

Part A: Student ID Management with statistics and duplicate validation
--- Student ID Management ---
Sample student IDs: [1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010]
Sum of all IDs: 10055
Average ID: 1005
All IDs are unique.

Part B: Sets with HashSet/TreeSet for registration management
--- Student Registration ---
Original list: [Alex, Sarah, Mike, Alex, Emma, Sarah, John]
HashSet (no duplicates): [Alex, Mike, Sarah, John, Emma]
TreeSet (sorted): [Alex, Emma, John, Mike, Sarah]
Is Alex enrolled? true
First student (alphabetically): Alex
Last student (alphabetically): Sarah

Part C: HashMap/TreeMap for course enrollment tracking
--- Course Enrollment ---
Enrollment counts:
  Database Systems: 18 students
  Web Dev: 30 students
  Java Programming: 25 students
  AI Fundamentals: 35 students
  Mobile Apps: 22 students

Top 3 courses by enrollment:
  AI Fundamentals: 35
  Web Dev: 30
  Java Programming: 25

Courses (alphabetical):
  AI Fundamentals: 35 students
  Database Systems: 18 students
  Java Programming: 25 students
  Mobile Apps: 22 students
  Web Dev: 30 students

Part D: PriorityQueue and ArrayDeque with alternating processing rule
--- Waitlist Processing ---
Processing waitlist requests:
1. URGENT: Alex wants Java Programming (priority: 1)
2. NORMAL: Sarah wants Web Dev (priority: 5)
3. URGENT: Lisa wants Java Programming (priority: 1)
4. NORMAL: Emma wants Mobile Apps (priority: 5)
5. URGENT: Mike wants Database Systems (priority: 2)
6. NORMAL: John wants AI Fundamentals (priority: 5)
7. NORMAL: Tom wants Web Dev (priority: 5)
8. NORMAL: Anna wants Database Systems (priority: 5)

=== Demo Complete ===
```

## Know Your Important Files

Click on any file to view the source code directly on GitHub:

### **Main Application Files**
- **[App.java](https://github.com/hjoseph777/course-registration-system/blob/main/src/main/java/com/humber/registration/App.java)** - Main application with interactive input for Collections Framework demonstrations
- **[DemoApp.java](https://github.com/hjoseph777/course-registration-system/blob/main/src/main/java/com/humber/registration/DemoApp.java)** - Demo version with predefined data (no user input required)
- **[StudentWaitRequest.java](https://github.com/hjoseph777/course-registration-system/blob/main/src/main/java/com/humber/registration/StudentWaitRequest.java)** - Model class for student waitlist requests with priority handling

### **Test Files**
- **[AppTest.java](https://github.com/hjoseph777/course-registration-system/blob/main/src/test/java/com/humber/registration/AppTest.java)** - Unit tests for the application components

### **Configuration & Build**
- **[pom.xml](https://github.com/hjoseph777/course-registration-system/blob/main/pom.xml)** - Maven project configuration with dependencies and build settings
- **[sample_input.txt](https://github.com/hjoseph777/course-registration-system/blob/main/sample_input.txt)** - Sample input data for testing the main application

### **Documentation**
- **[README.md](https://github.com/hjoseph777/course-registration-system/blob/main/README.md)** - This documentation file



## Usage

```bash
# Compile the project
mvn clean compile

# Run the main application
mvn exec:java -Dexec.mainClass="com.humber.registration.App"

# Run tests
mvn test

# Package as JAR
mvn package
```

## Features

The application demonstrates:

1. **Student ID Management** - Calculates statistics (sum, average) and validates for duplicate student IDs
2. **Duplicate Prevention** - Uses HashSet and TreeSet to handle duplicate student registrations with sorting
3. **Course Enrollment** - HashMap for tracking enrollment counts with top courses analysis and alphabetical sorting
4. **Waitlist Processing** - PriorityQueue for urgent requests and ArrayDeque for normal requests with alternating processing rule

## Sample Run

When you run the application, it will prompt for student IDs and then demonstrate each collection type with sample data. You can also run with predefined input:

```bash
mvn exec:java -Dexec.mainClass="com.humber.registration.App" < sample_input.txt
```

## Demo Version

For a quick demo without user input, run the demo version:

```bash
mvn exec:java -Dexec.mainClass="com.humber.registration.DemoApp"
```