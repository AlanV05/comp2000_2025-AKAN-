import java.awt.Color;
import java.awt.Graphics;

public class RockCell extends Cell {
    public RockCell(int x, int y) {
        super(x, y);
    }
    
    @Override
    protected void paintTerrain(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, size, size);
    }
    
    @Override
    public int getMovementModifier() { 
        return 0; 
    }
    
    @Override
    public boolean allowsMovement() { 
        return false; // Can't move through rocks
    }
    
    @Override
    public String getTerrainDescription() { 
        return "Rock - Impassable"; 
    }
}