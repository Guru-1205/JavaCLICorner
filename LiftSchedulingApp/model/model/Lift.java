package model;

import java.io.Serializable;

public class Lift implements Serializable {
    private static final long serialVersionUID = 1L;
    private int currentFloor;
    private char symbol;
    private int maintenanceCounter;
    private boolean isUnderMaintenance;
    private boolean justCompletedMaintenance;

    public Lift() {
        this.currentFloor = 0; // Initially at ground floor
        this.symbol = '_';
        this.maintenanceCounter = 0;
        this.isUnderMaintenance = false;
        this.justCompletedMaintenance = false;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getMaintenanceCounter() {
        return maintenanceCounter;
    }

    public void incrementMaintenanceCounter() {
        this.maintenanceCounter++;
    }

    public void setMaintenanceCounter(int maintenanceCounter) {
        this.maintenanceCounter = maintenanceCounter;
    }

    public boolean isUnderMaintenance() {
        return isUnderMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        isUnderMaintenance = underMaintenance;
    }

    public boolean isJustCompletedMaintenance() {
        return justCompletedMaintenance;
    }

    public void setJustCompletedMaintenance(boolean justCompletedMaintenance) {
        this.justCompletedMaintenance = justCompletedMaintenance;
    }
}
