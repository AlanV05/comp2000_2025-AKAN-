import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Optional;

public class Stage {
    Grid grid;
    ArrayList<Actor> actors = new ArrayList<>();
    Actor selectedActor = null;  // ADD THIS LINE

    public Stage() {
        grid = new Grid();

        // create actors and add them to the list
        actors.add(new Cat(grid.cellAtColRow(0, 0).get()));
        actors.add(new Dog(grid.cellAtColRow(0, 15).get()));
        actors.add(new Bird(grid.cellAtColRow(12, 9).get()));  
    }

    public void paint(Graphics g, Point mouseLoc) {
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
    
    // ADD THIS METHOD:
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
    
    // ADD THIS HELPER METHOD:
    private Actor getActorAt(Cell cell) {
        for (Actor actor : actors) {
            if (actor.loc == cell) {
                return actor;
            }
        }
        return null;
    }
}