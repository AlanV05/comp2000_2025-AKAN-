import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.util.Optional;

public class Grid {
  Cell[][] cells = new Cell[20][20];
  
  public Grid() {
    for(int i=0; i<cells.length; i++) {
      for(int j=0; j<cells[i].length; j++) {
        cells[i][j] = new Cell(10+Cell.size*i, 10+Cell.size*j);
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