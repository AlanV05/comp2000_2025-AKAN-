interface Movable {
    boolean canMoveTo(Cell destination);
    void moveTo(Cell destination);
    int getMovementSpeed();
}