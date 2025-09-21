import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Custom generic class for managing typed inventories
public class InventoryManager<T extends Actor> {
    private T owner;
    private ArrayList<Item<T>> items;
    private Map<Class<?>, Integer> itemCounts;
    private int maxCapacity;
    
    public InventoryManager(T owner, int maxCapacity) {
        this.owner = owner;
        this.maxCapacity = maxCapacity;
        this.items = new ArrayList<>();
        this.itemCounts = new HashMap<>();
    }
    
    // Generic method with type constraints
    public <I extends Item<T>> boolean addItem(I item) {
        if (items.size() >= maxCapacity) {
            return false; // Inventory full
        }
        
        if (item.canBeCollectedBy(owner)) {
            items.add(item);
            
            // Update count for this item type
            Class<?> itemType = item.getClass();
            itemCounts.put(itemType, itemCounts.getOrDefault(itemType, 0) + 1);
            
            return true;
        }
        return false;
    }
    
    // Generic method to find items of specific type
    @SuppressWarnings("unchecked")
    public <I extends Item<T>> ArrayList<I> getItemsOfType(Class<I> itemType) {
        ArrayList<I> result = new ArrayList<>();
        for (Item<T> item : items) {
            if (itemType.isInstance(item)) {
                result.add((I) item);
            }
        }
        return result;
    }
    
    // Get count of specific item type
    public <I extends Item<T>> int getCountOf(Class<I> itemType) {
        return itemCounts.getOrDefault(itemType, 0);
    }
    
    // Generic method with wildcard
    public boolean hasAnyItemOfType(Class<? extends Item<T>> itemType) {
        return getCountOf(itemType) > 0;
    }
    
    // Calculate inventory value using generic callback
    public <R> R processInventory(InventoryProcessor<T, R> processor) {
        return processor.process(owner, items);
    }
    
    // Nested generic interface
    public interface InventoryProcessor<T extends Actor, R> {
        R process(T actor, ArrayList<Item<T>> items);
    }
    
    // Getters
    public T getOwner() { return owner; }
    public int getSize() { return items.size(); }
    public int getMaxCapacity() { return maxCapacity; }
    public boolean isFull() { return items.size() >= maxCapacity; }
    
    // Get all items (defensive copy)
    public ArrayList<Item<T>> getAllItems() {
        return new ArrayList<>(items);
    }
}