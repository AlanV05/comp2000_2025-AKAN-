import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public abstract class Actor implements Movable {
    ArrayList<Polygon> shapes = new ArrayList<>();
    Cell loc;
    protected int movementSpeed = 1;
    protected ArrayList<Item<?>> inventory = new ArrayList<>();
    private Stage stage; // Reference to notify about collections

    public void paint(Graphics g) {
        for (Polygon P : shapes){
            g.drawPolygon(P); //outline
            g.fillPolygon(P); //Filling
        }
    }
    
    @Override
    public boolean canMoveTo(Cell destination) {
        if(destination == null) return false;
        return destination.allowsMovement();
    }
    
    @Override
    public void moveTo(Cell destination) {
        if(canMoveTo(destination)) {
            this.loc = destination;
            updateShapePositions();
            checkForItems();
        }
    }
    
    @Override
    public int getMovementSpeed() {
        return movementSpeed;
    }
    
    protected abstract void updateShapePositions();
    
    // NEW METHOD: Set stage reference
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @SuppressWarnings("unchecked")
    protected void checkForItems() {
        ArrayList<Item<?>> itemsToRemove = new ArrayList<>();
        
        for(Item<?> item : loc.getItems()) {
            try {
                CollectibleItem<Actor> collectible = (CollectibleItem<Actor>) item;
                if(collectible.canBeCollectedBy(this)) {
                    collectible.onCollected(this);
                    inventory.add(item);
                    itemsToRemove.add(item);
                    
                    // NEW: Notify stage about collection
                    if (stage != null) {
                        stage.onItemCollected(item);
                    }
                }
            } catch(ClassCastException e) {
                // Item not meant for this actor type
            }
        }
        
        for(Item<?> item : itemsToRemove) {
            loc.removeItem(item);
        }
    }
    
    public ArrayList<Item<?>> getInventory() {
        return new ArrayList<>(inventory);
    }
}