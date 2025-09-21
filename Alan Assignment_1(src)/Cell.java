import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Cell extends Rectangle implements TerrainEffect {
    static int size = 35;
    protected ArrayList<Item<?>> items = new ArrayList<>();
    
    public Cell(int x, int y) {
        super(x, y, size, size);
    }

    public void paint(Graphics g, Point mousePos) {
        // Paint terrain-specific background
        paintTerrain(g);
        
        // Highlight if mouse is over
        if(contains(mousePos)) {
            g.setColor(new Color(255, 255, 255, 100)); // Semi-transparent white
            g.fillRect(x, y, size, size);
        }
        
        // Paint border
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
        
        // Paint items on this cell
        for(Item<?> item : items) {
            item.paint(g);
        }
    }
    
    protected abstract void paintTerrain(Graphics g);
    
    public void addItem(Item<?> item) {
        items.add(item);
        item.setLocation(this);
    }
    
    public void removeItem(Item<?> item) {
        items.remove(item);
    }
    
    public ArrayList<Item<?>> getItems() {
        return new ArrayList<>(items);
    }

    public boolean contains(Point p) {
        if(p != null) {
            return super.contains(p);
        } else {
            return false;
        }
    }
}