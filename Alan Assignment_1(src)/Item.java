import java.awt.Graphics;

public abstract class Item<T extends Actor> implements CollectibleItem<T> {
    protected Cell location;
    protected boolean collected = false;
    
    public abstract void paint(Graphics g);
    
    public void setLocation(Cell cell) {
        this.location = cell;
    }
    
    public Cell getLocation() {
        return location;
    }
    
    public boolean isCollected() {
        return collected;
    }
    
    public void collect() {
        collected = true;
        if(location != null) {
            location.removeItem(this);
        }
    }
}