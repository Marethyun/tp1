package fr.univ_amu.iut.exercice4;


public class Robot {
    private GridPosition gridPosition;
    private Orientation orientation;

    public Robot(GridPosition gridPosition, Orientation orientation) {
        this.gridPosition = gridPosition;
        this.orientation = orientation;
    }

    public GridPosition getGridPosition() {
        return gridPosition;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void turnRight() {
        switch (this.orientation) {

            case EAST:
                this.orientation = Orientation.SOUTH;
                break;
            case SOUTH:
                this.orientation = Orientation.WEST;
                break;
            case WEST:
                this.orientation = Orientation.NORTH;
                break;
            case NORTH:
                this.orientation = Orientation.EAST;
                break;
        }
    }

    public void turnLeft() {
        switch (this.orientation) {

            case EAST:
                this.orientation = Orientation.NORTH;
                break;
            case SOUTH:
                this.orientation = Orientation.EAST;
                break;
            case WEST:
                this.orientation = Orientation.SOUTH;
                break;
            case NORTH:
                this.orientation = Orientation.WEST;
                break;
        }
    }

    public void advance() {
        switch (this.orientation) {

            case EAST:
                this.gridPosition.setX(this.gridPosition.getX() + 1);
                break;
            case SOUTH:
                this.gridPosition.setY(this.gridPosition.getY() - 1);
                break;
            case WEST:
                this.gridPosition.setX(this.gridPosition.getX() - 1);
                break;
            case NORTH:
                this.gridPosition.setY(this.gridPosition.getY() + 1);
                break;
        }
    }
}

