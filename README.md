## Course Registration System – Java Collections Framework Demo

## Project Humber College Computer Science
- Author: Harry Josephence
- Created: November 8, 2025
- Platform: Java 11 + Maven
- Package Manager: Maven
- Build Tool: Apache Maven 
- Framework: Java Collections Framework Demonstration

## Overview
The Humber Registration System demonstrates the four major components of the Java Collections Framework through simple, focused examples. This educational project covers wrapper classes, Sets, Maps, and Queues using console-based demonstrations that show fundamental Collections operations.

## 📦 Download

**Download the complete project:**

[![Download Datalab8.zip](https://img.shields.io/badge/Download-Datalab8.zip-blue?style=for-the-badge&logo=download)](https://github.com/hjoseph777/releases/download/v1/Datalab8.zip)

Or get it directly from the releases page:
- **Release**: [v1 - Datalab8.zip](https://github.com/hjoseph777/releases/tag/v1)
- **Direct Download**: [Datalab8.zip](https://github.com/hjoseph777/releases/download/v1/Datalab8.zip)

## 📥 Quick Setup

**Get started with the project:**

```bash
git clone [repository-url]
cd course-registration-system
mvn clean compile
mvn exec:java -Dexec.mainClass="com.humber.registration.App"
```

urgent and normal queues

## Project Explorer
An interactive, collapsible view of the simplified codebase focused on Collections Framework demonstrations.

<details open>
   <summary><strong>src/main/java/com/humber/registration/ – Core Collections Demos</strong></summary>

   - 📁 <strong>com.humber.registration</strong>
      - 🏠 [`App.java`](src/main/java/com/humber/registration/App.java) – **Main application with all four Collections demos**
      - � [`StudentWaitRequest.java`](src/main/java/com/humber/registration/StudentWaitRequest.java) – **Support class for Part D Queue demonstrations**
</details>

<details>
   <summary><strong>src/test/java/ – Unit Tests</strong></summary>

   - 📁 <strong>com.humber.registration</strong>
      - 🧪 [`AppTest.java`](src/test/java/com/humber/registration/AppTest.java) – Basic application and StudentWaitRequest tests
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
│   ├── 🏠 App.java                           # Main application with all Collections demonstrations
│   └── � StudentWaitRequest.java            # Support class for Queue demonstrations
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



## Build & Run

```bash
# Compile the project
mvn clean compile

# Run the main application (requires user input)
mvn exec:java -Dexec.mainClass="com.humber.registration.App"

# Run tests
mvn test

# Package as JAR
mvn package
```

## Demo: Collections Framework in Action

The main application demonstrates all four Java Collections Framework components. Run the application to see each part in action:

### Interactive Demonstration

When you run `mvn exec:java -Dexec.mainClass="com.humber.registration.App"`, you'll see:

1. **Part A: Student ID Handling** - Prompts you to enter 10 student IDs, demonstrates autoboxing/unboxing, and shows Integer comparison behavior
2. **Part B: Duplicate Registration Prevention** - Shows how HashSet and TreeSet handle duplicate student names automatically
3. **Part C: Course Enrollment Tracking** - Displays HashMap operations for course enrollment counting and TreeMap for alphabetical sorting
4. **Part D: Waitlist Processing** - Demonstrates PriorityQueue and ArrayDeque working together to process urgent and normal waitlist requests

### Sample Output

Using the provided sample data file:

```bash
mvn exec:java -Dexec.mainClass="com.humber.registration.App" < sample_input.txt
```

Console output:

```
=== Humber Registration System - Collections Demo ===

--- Part A: Student ID Handling (Wrapper Classes) ---
Enter 10 student IDs:
ID 1: ID 2: ID 3: ID 4: ID 5: ID 6: ID 7: ID 8: ID 9: ID 10: Sum of all IDs: 10055

Autoboxing comparison tests:
128 == 128: false
128 .equals 128: true
42 == 42: true
42 .equals 42: true

--- Part B: Avoiding Duplicate Registration (Sets) ---
Original list: [Alex, Sarah, Mike, Alex, Emma, Sarah, John]
HashSet (no duplicates): [Alex, Mike, Sarah, John, Emma]
TreeSet (sorted): [Alex, Emma, John, Mike, Sarah]
Is Alex enrolled? true
First student (alphabetically): Alex
Last student (alphabetically): Sarah

--- Part C: Course Enrollment Records (Map) ---
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

--- Part D: Waitlist Queue Processing ---
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

### Demo Version (no user input required)

For demonstration purposes without user input:

```bash
mvn exec:java -Dexec.mainClass="com.humber.registration.DemoApp"
```

This version displays the same functionality with predefined sample data, perfect for viewing the Collections Framework behavior without entering 10 student IDs manually.

---

*This simplified project demonstrates fundamental Java Collections Framework concepts through focused, educational examples.*