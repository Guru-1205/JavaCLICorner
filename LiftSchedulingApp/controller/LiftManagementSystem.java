
import java.io.*;
import java.util.*;
import model.Lift;

public class LiftManagementSystem implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Integer, Lift> lifts;
    private int maintenanceThreshold;

    public LiftManagementSystem(int numberOfLifts, int maintenanceThreshold) {
        this.lifts = new HashMap<>();
        this.maintenanceThreshold = maintenanceThreshold;

        for (int i = 1; i <= numberOfLifts; i++) {
            lifts.put(i, new Lift());
        }
    }

    public void displayLifts() {
        for (Map.Entry<Integer, Lift> entry : lifts.entrySet()) {
            System.out.println("Lift " + entry.getKey() + ": Floor " + entry.getValue().getCurrentFloor() + " "
                    + entry.getValue().getSymbol() + " " + entry.getValue().getMaintenanceCounter());
        }
    }

    public void assignLift(int currentFloor, int destinationFloor) {
        List<Integer> closestLifts = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Lift> entry : lifts.entrySet()) {
            Lift lift = entry.getValue();

            if (lift.isUnderMaintenance() || lift.isJustCompletedMaintenance())
                continue;

            int distance = Math.abs(lift.getCurrentFloor() - currentFloor);
            if (distance < minDistance) {
                closestLifts.clear();
                closestLifts.add(entry.getKey());
                minDistance = distance;
            } else if (distance == minDistance) {
                closestLifts.add(entry.getKey());
            }
        }

        if (!closestLifts.isEmpty()) {
            int assignedLiftId = selectLiftBasedOnConstraints(closestLifts, currentFloor, destinationFloor);
            moveLift(assignedLiftId, currentFloor, destinationFloor);
        } else {
            System.out.println("No lift available.");
        }

        for (Lift lift : lifts.values()) {
            if (lift.isJustCompletedMaintenance()) {
                lift.setJustCompletedMaintenance(false);
            }
        }
    }

    private int selectLiftBasedOnConstraints(List<Integer> closestLifts, int currentFloor, int destinationFloor) {
        List<Integer> movingInSameDirectionLifts = new ArrayList<>();
        List<Integer> oddEvenMatchedLifts = new ArrayList<>();

        boolean isGoingUp = destinationFloor > currentFloor;

        for (int liftId : closestLifts) {
            Lift lift = lifts.get(liftId);

            if ((isGoingUp && lift.getSymbol() == '+') || (!isGoingUp && lift.getSymbol() == '-')) {
                movingInSameDirectionLifts.add(liftId);
            } else if ((currentFloor % 2 == 0 && liftId % 2 == 0) || (currentFloor % 2 != 0 && liftId % 2 != 0)) {
                oddEvenMatchedLifts.add(liftId);
            }
        }

        if (!movingInSameDirectionLifts.isEmpty()) {
            return movingInSameDirectionLifts.get(0); // Select the first lift moving in the same direction
        } else if (!oddEvenMatchedLifts.isEmpty()) {
            return oddEvenMatchedLifts.get(0); // Select the first lift that matches the odd/even constraint
        } else {
            Random random = new Random();
            return closestLifts.get(random.nextInt(closestLifts.size())); // Select a random lift from the closest lifts
        }
    }

    private void moveLift(int liftId, int currentFloor, int destinationFloor) {
        Lift lift = lifts.get(liftId);

        if (lift.isUnderMaintenance()) {
            return;
        }

        if (lift.getCurrentFloor() < currentFloor) {
            lift.setSymbol('+');
        } else if (lift.getCurrentFloor() > currentFloor) {
            lift.setSymbol('-');
        } else {
            lift.setSymbol('_');
        }

        lift.setCurrentFloor(destinationFloor);
        lift.incrementMaintenanceCounter();

        if (lift.getMaintenanceCounter() >= maintenanceThreshold) {
            lift.setUnderMaintenance(true);
            lift.setSymbol('M');
        }
    }

    public void endCycle() {
        for (Lift lift : lifts.values()) {
            if (lift.isUnderMaintenance()) {
                lift.setUnderMaintenance(false);
                lift.setSymbol('_');
                lift.setMaintenanceCounter(0);
                lift.setJustCompletedMaintenance(true);
            }
        }
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    public static LiftManagementSystem loadFromFile(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            return new LiftManagementSystem(4, 5); // Default 4 lifts, maintenance break after 5 uses
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (LiftManagementSystem) ois.readObject();
        }
    }
}
