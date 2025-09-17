import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Cell extends Rectangle {
  static int size = 35;
  private String type;
  private int elevation;

  public Cell(int x, int y) {
    super(x, y, size, size);
    
    // Assign random properties for demonstration
    String[] types = {"Grass", "Water", "Rock", "Sand", "Forest"};
    this.type = types[(int)(Math.random() * types.length)];
    this.elevation = (int)(Math.random() * 100);
  }

  public void paint(Graphics g, Point mousePos) {
    if(contains(mousePos)) {
      g.setColor(Color.GRAY);
    } else {
      g.setColor(Color.WHITE);
    }
    g.fillRect(x, y, size, size);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, size, size);
  }

  public boolean contains(Point p) {
    if(p != null) {
      return super.contains(p);
    } else {
      return false;
    }
  }

  // Getter methods for the new properties
  public String getType() { 
    return type; 
  }

  public int getElevation() { 
    return elevation; 
  }
}