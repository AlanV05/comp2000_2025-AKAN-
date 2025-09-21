import java.awt.Polygon;

public class Cat extends Actor {
    public Cat(Cell inLoc) {
        this.loc = inLoc;
        this.movementSpeed = 3; // Cats are agile
        updateShapePositions();
    }
    
    @Override
    protected void updateShapePositions() {
        shapes.clear();
        
        Polygon ear1 = new Polygon();
        ear1.addPoint(loc.x + 11, loc.y + 5);
        ear1.addPoint(loc.x + 15, loc.y + 15);
        ear1.addPoint(loc.x + 7, loc.y + 15);
        shapes.add(ear1);

        Polygon ear2 = new Polygon();
        ear2.addPoint(loc.x + 22, loc.y + 5);
        ear2.addPoint(loc.x + 26, loc.y + 15);
        ear2.addPoint(loc.x + 18, loc.y 