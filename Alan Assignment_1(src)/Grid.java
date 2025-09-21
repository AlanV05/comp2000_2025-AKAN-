import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.util.Optional;
import java.util.Random;

public class Grid {
    Cell[][] cells = new Cell[20][20];
    
    public Grid() {
        Random random = new Random();
        
        for(int i=0; i<cells.length; i++) {
            for(int j=0; j<cells[i].length; j++) {
                // Create different terrain types randomly
                int terrainType = random.nextInt(10);
                if(terrainType < 6) {
                    cells[i][j] = new GrassCell(10+Cell.size*i, 10+Cell.size*j);
                } else if(terrainType < 8) {
                    cells[i][j] = new WaterCell(10+Cell.size*i, 10+Cell.size*j);
                } else {
                    cells[i][j] = new RockCell(10+Cell.size*i, 10+Cell.size*j);
                }
                
                // Randomly place items (but not on rock)
                if(!(cells[i][j] instanceof RockCell) && random.nextInt(20) == 0) {
                    int itemType = random.nextInt(3);
                    switch(itemType) {
                        case 0:
                            cells[i][j].addItem(new Frisbee());
                            break;
                        case 1:
                            cells[i][j].addItem(new Mouse());
                            break;
                        case 2:
                            cells[i][j].addItem(new Worm());
                            break;
                    }
                }
            }
        }
    }

    public void paint(Graphics g, Point mousePos) {
        for(int i=0; i<cells.length; i++) {
            for(int j=0; j<cells[i].length; j++) {
                cells[i][j].paint(g, mousePos);
            }
        }
        
        // Paint the cell details in the right panel
        paintCellDetails(g, mousePos);
    }

    private void paintCellDetails(Graphics g, Point mousePos) {
        // Clear the details area (right side of screen)
        g.setColor(Color.WHITE);
        g.fillRect(720, 0, 304, 720);
        g.setColor(Color.BLACK);
        g.drawRect(720, 0, 304, 720);
        
        // Get the cell at mouse position using our cellAtPoint method
        Optional<Cell> cellOpt = cellAtPoint(mousePos);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        
        if (cellOpt.isPresent()) {
            Cell cell = cellOpt.get();
            
            // Display cell information
            g.drawString("Cell Details:", 730, 30);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            
            g.drawString("Position: (" + cell.x + ", " + cell.y + ")", 730, 60);
            g.drawString(cell.getTerrainDescription(), 730, 85);
            
            // Show items on this cell
            g.drawString("Items on cell:", 730, 110);
            int yOffset = 130;
            for(Item<?> item : cell.getItems()) {
                g.drawString("- " + item.getClass().getSimpleName(), 730, yOffset);
                yOffset += 20;
            }
            
            // Calculate grid coordinates
            int col = (cell.x - 10) / Cell.size;
            int row = (cell.y - 10) / Cell.size;
            g.drawString("Grid Position: [" + col + ", " + row + "]", 730, yOffset + 20);
            
        } else {
            g.drawString("No cell at mouse position", 730, 30);
        }
    }

    public Optional<Cell> cellAtColRow(int c, int r) {
        // Check bounds before accessing array
        if (c < 0 || c >= cells.length || r < 0 || r >= cells[0].length) {
            return Optional.empty();
        }
        
        return Optional.of(cells[c][r]);
    }

    public Optional<Cell> cellAtPoint(Point p) {
        // Handle null point
        if (p == null) {
            return Optional.empty();
        }
        
        // Calculate which cell this point corresponds to
        int col = (p.x - 10) / Cell.size;
        int row = (p.y - 10) / Cell.size;
        
        // Check if the calculated indices are within bounds
        if (col < 0 || col >= cells.length || row < 0 || row >= cells[0].length) {
            return Optional.empty();
        }
        
        // Return the cell wrapped in Optional
        return Optional.of(cells[col][row]);
    }
}