import java.awt.Color;
import java.awt.Graphics;

public class WaterCell extends Cell {
    public WaterCell(int x, int y) {
        super(x, y);
    }
    
    protected void paintTerrain(Graphics g) {
        g.setColor(new Color(30, 144, 255)); // Dodger blue
        g.fillRect(x, y, size, size);
    }
    
    
    public int getMovementModifier() { 
        return 2; // Slower movement
    }
    
    public boolean allowsMovement() { 
        return true; 
    }
    
    public String getTerrainDescription() { 
        return "Water - Slow movement"; 
    }
}