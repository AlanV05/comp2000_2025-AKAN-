import java.awt.Color;
import java.awt.Graphics;

public class GrassCell extends Cell {
    public GrassCell(int x, int y) {
        super(x, y);
    }
    
    @Override
    protected void paintTerrain(Graphics g) {
        g.setColor(new Color(34, 139, 34)); // Forest green
        g.fillRect(x, y, size, size);
    }
    
    @Override
    public int getMovementModifier() { 
        return 0; // Normal movement
    }
    
    @Override
    public boolean allowsMovement() { 
        return true; 
    }
    
    @Override
    public String getTerrainDescription() { 
        return "Grass - Easy movement"; 
    }
}