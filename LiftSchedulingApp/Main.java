import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LiftManagementSystem system = null;

        try {
            system = LiftManagementSystem.loadFromFile("lifts.dat");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            system = new LiftManagementSystem(4, 5); // Default 4 lifts, maintenance break after 5 uses
        }
        system.displayLifts();
        while (true) {

            System.out.println("Enter current floor:");
            int currentFloor = scanner.nextInt();
            System.out.println("Enter destination floor:");
            int destinationFloor = scanner.nextInt();

            system.assignLift(currentFloor, destinationFloor);
            system.displayLifts();

            system.endCycle();

            try {
                system.saveToFile("lifts.dat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
