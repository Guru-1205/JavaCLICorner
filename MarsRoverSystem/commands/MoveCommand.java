package commands;

import rover.Rover;

public class MoveCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.move();
    }
}
