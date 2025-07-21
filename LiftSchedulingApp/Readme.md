
# Lift Management System 🏢🛗

The Lift Management System is a Java-based application designed to efficiently manage multiple lifts within a building. This system enables dynamic assignment of lifts based on passenger requests, considers maintenance cycles, and handles various operational scenarios.

## Features ✨

- **Multiple Lift Management**: Efficiently manage and track multiple lifts operating within a building environment.
  
- **Dynamic Lift Assignment**: Assign the closest available lift to passengers based on their current floor and destination.
  
- **Maintenance Handling**: Automatically manage lift maintenance cycles after a specified number of uses to ensure operational reliability.
  
- **Directional Priority**: Prioritize lifts moving in the same direction as the passenger's destination for faster service.
  
- **Odd/Even Floor Assignment**: Optimize lift assignments by assigning odd-numbered lifts to odd floors and even-numbered lifts to even floors when no lifts are moving towards the destination.
  
- **Persistence**: Save and load system state using object serialization to maintain lift configurations and maintenance schedules across sessions.

## Usage 🚀

### Requirements 🛠️

- Java Development Kit (JDK) installed
- Integrated Development Environment (IDE) or command-line Java compiler

### Installation 📦

1. Clone or download the repository from [GitHub](https://github.com/your/repository).
   
2. Open the project in your preferred Java IDE.

### Getting Started 🏁

1. Compile and run the `Main` class.
   
2. Follow the on-screen prompts to interact with the application:
   - Enter the current floor and destination floor to assign a lift.
   - View the current status of all lifts after each assignment.
   - Optionally end the cycle to reset maintenance states of lifts.

### Example Code Snippet

```java
// Example usage in Java code
public class Main {
    public static void main(String[] args) {
        LiftManagementSystem system = new LiftManagementSystem(4, 5); // Initialize with 4 lifts and maintenance after 5 uses

        // Use system.assignLift(currentFloor, destinationFloor) to assign a lift
        // Use system.displayLifts() to display current lift statuses
        // Use system.endCycle() to end the maintenance cycle

        // Save and load system state using system.saveToFile(filename) and LiftManagementSystem.loadFromFile(filename)
    }
}
```

### Notes 📝

- Adjust the number of lifts and maintenance thresholds in the `LiftManagementSystem` constructor as per your building's requirements.
  
- Ensure proper exception handling and file management practices when saving and loading system state using object serialization.

---

