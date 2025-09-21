import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Optional;

public class Stage {
    Grid grid;
    ArrayList<Actor> actors = new ArrayList<>();
    Actor selectedActor = null;

    public Stage() {
        grid = new Grid();

        // create actors and add them to the list
        Cat cat = new Cat(grid.cellAtColRow(0, 0).get());
        Dog dog = new Dog(grid.cellAtColRow(0, 15).get());
        Bird bird = new Bird(grid.cellAtColRow(12, 9).get());
        
        // Set stage reference for each actor
        cat.setStage(this);
        dog.setStage(this);
        bird.setStage(this);
        
        actors.add(cat);
        actors.add(dog);
        actors.add(bird);
    }

    public void paint(Graphics g, Point mouseLoc) {
        // Update item respawning
        grid.updateRespawning();
        
        // paint the grid first
        grid.paint(g, mouseLoc);

        // now loop through all actors and paint them
        for (Actor actor : actors) {
            actor.paint(g);
        }
        
        // Highlight selected actor
        if (selectedActor != null) {
            g.setColor(java.awt.Color.YELLOW);
            g.drawRect(selectedActor.loc.x - 2, selectedActor.loc.y - 2, 
                      Cell.size + 4, Cell.size + 4);
        }
    }
    
    // NEW METHOD: Called when an actor collects an item
    public void onItemCollected(Item<?> item) {
        grid.onItemCollected(item);
    }
    
    public void handleMouseClick(int mouseX, int mouseY) {
        Optional<Cell> clickedCell = grid.cellAtPoint(new Point(mouseX, mouseY));
        
        if (clickedCell.isPresent()) {
            Cell cell = clickedCell.get();
            
            // Check if we clicked on an actor
            Actor clickedActor = getActorAt(cell);
            
            if (clickedActor != null) {
                // Select this actor
                selectedActor = clickedActor;
                System.out.println("Selected " + clickedActor.getClass().getSimpleName());
            } else if (selectedActor != null) {
                // Try to move selected actor to this cell
                if (selectedActor.canMoveTo(cell)) {
                    selectedActor.moveTo(cell);
                    System.out.println("Moved " + selectedActor.getClass().getSimpleName() + 
                                     " to " + cell.getTerrainDescription());
                } else {
                    System.out.println("Can't move there - " + cell.getTerrainDescription());
                }
            }
        }
    }
    
    private Actor getActorAt(Cell cell) {
        for (Actor actor : actors) {
            if (actor.loc == cell) {
                return actor;
            }
        }
        return null;
    }
}