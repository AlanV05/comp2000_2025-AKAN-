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
        g.draw