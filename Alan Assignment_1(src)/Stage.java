import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Optional;

public class Stage {
    Grid grid;
    ArrayList<Actor> actors = new ArrayList<>();
    Actor selectedActor = null;
    ArrayList<Cell> validMoves = new ArrayList<>(); // NEW: Store valid move cells
    private final int MAX_MOVEMENT_RANGE = 5; // NEW: Maximum movement distance

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

        // NEW: Highlight valid moves BEFORE painting actors
        paintValidMoves(g);

        // now loop through all actors and paint them
        for (Actor actor : actors) {
            actor.paint(g);
        }
        
        // Highlight selected actor
        if (selectedActor != null) {
            g.setColor(Color.YELLOW);
            g.drawRect(selectedActor.loc.x - 2, selectedActor.loc.y - 2, 
                      Cell.size + 4, Cell.size + 4);
        }
    }
    
    // NEW METHOD: Paint highlighted valid moves
    private void paintValidMoves(Graphics g) {
        g.setColor(new Color(0, 255, 0, 100)); // Semi-transparent green
        for (Cell cell : validMoves) {
            g.fillRect(cell.x + 2, cell.y + 2, Cell.size - 4, Cell.size - 4);
            g.setColor(Color.GREEN);
            g.drawRect(cell.x + 2, cell.y + 2, Cell.size - 4, Cell.size - 4);
            g.setColor(new Color(0, 255, 0, 100)); // Reset for next cell
        }
    }
    
    // Called when an actor collects an item
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
                // Select this actor and calculate valid moves
                selectedActor = clickedActor;
                calculateValidMoves();
                System.out.println("Selected " + clickedActor.getClass().getSimpleName() + 
                                 " - Choose a highlighted cell to move to!");
            } 
            else if (selectedActor != null && validMoves.contains(cell)) {
                // Move to the clicked valid cell
                if (selectedActor.canMoveTo(cell)) {
                    selectedActor.moveTo(cell);
                    System.out.println("Moved " + selectedActor.getClass().getSimpleName() + 
                                     " to " + cell.getTerrainDescription());
                    
                    // Clear selection after moving
                    selectedActor = null;
                    validMoves.clear();
                } else {
                    System.out.println("Can't move there - " + cell.getTerrainDescription());
                }
            }
            else if (selectedActor != null) {
                // Clicked somewhere invalid - show message
                System.out.println("Invalid move! Click on a highlighted green cell or select a different actor.");
            }
        }
    }
    
    // NEW METHOD: Calculate valid moves (up, down, left, right within range)
    private void calculateValidMoves() {
        validMoves.clear();
        
        if (selectedActor == null) return;
        
        // Get current position
        int currentCol = (selectedActor.loc.x - 10) / Cell.size;
        int currentRow = (selectedActor.loc.y - 10) / Cell.size;
        
        // Check all four directions (up, down, left, right)
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // up, down, left, right
        
        for (int[] direction : directions) {
            int deltaCol = direction[0];
            int deltaRow = direction[1];
            
            // Check each step in this direction (1 to MAX_MOVEMENT_RANGE)
            for (int step = 1; step <= MAX_MOVEMENT_RANGE; step++) {
                int newCol = currentCol + (deltaCol * step);
                int newRow = currentRow + (deltaRow * step);
                
                // Check bounds
                Optional<Cell> cellOpt = grid.cellAtColRow(newCol, newRow);
                if (cellOpt.isPresent()) {
                    Cell targetCell = cellOpt.get();
                    
                    // Check if we can move to this cell
                    if (selectedActor.canMoveTo(targetCell) && !isOccupiedByActor(targetCell)) {
                        validMoves.add(targetCell);
                    } else {
                        // If we hit an obstacle, stop checking further in this direction
                        break;
                    }
                } else {
                    // Out of bounds, stop checking this direction
                    break;
                }
            }
        }
        
        System.out.println("Found " + validMoves.size() + " valid moves within " + MAX_MOVEMENT_RANGE + " cells");
    }
    
    // NEW METHOD: Check if a cell is occupied by another actor
    private boolean isOccupiedByActor(Cell cell) {
        for (Actor actor : actors) {
            if (actor != selectedActor && actor.loc == cell) {
                return true;
            }
        }
        return false;
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