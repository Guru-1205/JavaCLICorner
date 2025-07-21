import commands.*;
import grid.*;
import rover.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input Grid Size
        System.out.print("Enter grid width: ");
        int width = scanner.nextInt();
        System.out.print("Enter grid height: ");
        int height = scanner.nextInt();

        // Initialize Grid
        Grid grid = new Grid(width, height);

        // Input Obstacles
        System.out.print("Enter number of obstacles: ");
        int numObstacles = scanner.nextInt();
        for (int i = 0; i < numObstacles; i++) {
            System.out.print("Enter obstacle " + (i + 1) + " x-coordinate: ");
            int ox = scanner.nextInt();
            System.out.print("Enter obstacle " + (i + 1) + " y-coordinate: ");
            int oy = scanner.nextInt();
            grid.addObstacle(new Obstacle(ox, oy));
        }

        // Input Starting Position
        System.out.print("Enter starting x-coordinate: ");
        int startX = scanner.nextInt();
        System.out.print("Enter starting y-coordinate: ");
        int startY = scanner.nextInt();
        System.out.print("Enter starting direction (N/E/S/W): ");
        Direction startDirection = Direction.valueOf(scanner.next().toUpperCase());

        // Initialize Rover
        Rover rover = new Rover(startX, startY, startDirection, grid);

        // Input Commands
        System.out.print("Enter the number of commands: ");
        int numCommands = scanner.nextInt();
        List<Command> commands = new ArrayList<>();
        System.out.println("Enter commands (M for move, L for turn left, R for turn right):");
        for (int i = 0; i < numCommands; i++) {
            char cmd = scanner.next().charAt(0);
            switch (cmd) {
                case 'M' -> commands.add(new MoveCommand());
                case 'L' -> commands.add(new TurnLeftCommand());
                case 'R' -> commands.add(new TurnRightCommand());
                default -> System.out.println("Invalid command: " + cmd);
            }
        }

        // Execute Commands
        for (Command command : commands) {
            command.execute(rover);
        }

        // Get Status Report
        StatusReport statusReport = rover.getStatusReport();
        System.out.println(statusReport);

        // Print Encountered Obstacles
        Set<int[]> encounteredObstacles = rover.getEncounteredObstacles();
        if (!encounteredObstacles.isEmpty()) {
            System.out.println("Rover encountered obstacles at:");
            for (int[] obstacle : encounteredObstacles) {
                System.out.printf("(%d, %d)%n", obstacle[0], obstacle[1]);
            }
        } else {
            System.out.println("Rover did not encounter any obstacles.");
        }

        scanner.close();
    }
}
