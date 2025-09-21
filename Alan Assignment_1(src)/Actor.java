import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public abstract class Actor implements Movable {
    ArrayList<Polygon> shapes = new ArrayList<>();
    Cell loc;
    protected int movementSpeed = 1;
    protected InventoryManager<Actor> inventoryManager; // NEW: Using generic InventoryManager
    private Stage stage;

    public Actor() {
        // Initialize with capacity of 10 items
        this.inventoryManager = new InventoryManager<>(this, 10);
    }

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
                    
                    // NEW: Use generic InventoryManager
                    if (inventoryManager.addItem((Item<Actor>) item)) {
                        itemsToRemove.add(item);
                        if (stage != null) {
                            stage.onItemCollected(item);
                        }
                    } else {
                        System.out.println("Inventory full! Can't collect " + item.getClass().getSimpleName());
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
    
    // NEW: Methods using the generic InventoryManager
    public ArrayList<Item<Actor>> getInventory() {
        return inventoryManager.getAllItems();
    }
    
    public <I extends Item<Actor>> int getCountOf(Class<I> itemType) {
        return inventoryManager.getCountOf(itemType);
    }
    
    public boolean hasSpaceInInventory() {
        return !inventoryManager.isFull();
    }
    
    public InventoryManager<Actor> getInventoryManager() {
        return inventoryManager;
    }
}