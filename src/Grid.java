import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;  // <-- Add this import

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
  }

  public Optional<Cell> cellAtColRow(int c, int r) {
    // Check bounds before accessing array
    if (c < 0 || c >= cells.length || r < 0 || r >= cells[0].length) {
        return Optional.empty();
    }
    
    return Optional.of(cells[c][r]);
}

  // Add the new method here:
  public Optional<Cell> cellAtPoint(Point p) {
    // Handle null point
    if (p == null) {
        return Optional.empty();
    }
    
    // Calculate which cell this point corresponds to
    // Need to reverse the calculation from the constructor:
    // cell position = 10 + Cell.size * index
    // So: index = (position - 10) / Cell.size
    
    int col = (p.x - 10) / Cell.size;  // Cell.size = 35
    int row = (p.y - 10) / Cell.size;  // Cell.size = 35
    
    // Check if the calculated indices are within bounds
    if (col < 0 || col >= cells.length || row < 0 || row >= cells[0].length) {
        return Optional.empty();
    }
    
    // Return the cell wrapped in Optional
    return Optional.of(cells[col][row]);  }
}