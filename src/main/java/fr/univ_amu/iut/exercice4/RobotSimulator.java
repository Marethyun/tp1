package fr.univ_amu.iut.exercice4;

import java.util.ArrayList;
import java.util.List;

public class RobotSimulator {
    private Robot robot;
    private String instructions;

    public RobotSimulator(Robot robot, String instructions) {
        this.robot = robot;
        this.instructions = instructions;
    }

    public void simulate() {
        for (Movement movement : this.getMovements()) {
            switch (movement){
                case LEFT:
                    this.robot.turnLeft();
                    break;
                case RIGHT:
                    this.robot.turnRight();
                    break;
                case ADVANCE:
                    this.robot.advance();
                    break;
                case NONE:
                    throw new IllegalArgumentException("Invalid command");
            }
        }
    }

    public List<Movement> getMovements() {

        ArrayList<Movement> movements = new ArrayList<>();

        for (char c : this.instructions.toCharArray()) {
            Movement movement = Movement.getMovement(c);

            if (movement == Movement.NONE) continue;

            movements.add(movement);
        }

        return movements;
    }
}
