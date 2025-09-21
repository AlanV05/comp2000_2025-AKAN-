import java.awt.Color;
import java.awt.Graphics;

public class WaterCell extends Cell {
    public WaterCell(int x, int y) {
        super(x, y);
    }
    
    @Override
    protected void paintTerrain(Graphics g) {
        g.setColor(new Color(30, 144, 255)); // Dodger blue
        g.fillRect(x, y, size, size);
    }
    
    @Override
    public int getMovementModifier() { 
        return 2; // Slower movement
    }
    
    @Override
    public boolean allowsMovement() { 
        return true; 
    }
    
    @Override
    public String getTerrainDescription() { 
        return "Water - Slow movement"; 
    }
}