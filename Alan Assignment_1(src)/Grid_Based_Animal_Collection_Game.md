# Grid-Based Animal Collecting Iteams Game

## How to Compile and Run

### Step 1: Compile
Open terminal/command prompt in the folder with all your `.java` files and run:

- javac *.java


### Step 2: Run
- java Main



## How to Play
- Click on an animal (**Dog, Cat, or Bird**) to select it  
- Click on **green highlighted squares** to move the animal there  
- Animals collect items automatically:
  -  Dogs collect **Frisbees**
  -  Cats collect **Mice**
  -  Birds collect **Insects**

**Terrain effects:**
-  **Green = Grass** → normal speed  
-  **Blue = Water** → slower  
-  **Gray = Rock** → impassable  


## Good Design Examples

### 1. Inheritance – Why it's useful
**Before inheritance (bad way):**
```java
public class GrassCell {
    // 50 lines of code for basic cell stuff
    // + grass-specific code
}
public class WaterCell {
    // Same 50 lines copied and pasted!
    // + water-specific code  
}
```

**With inheritance (good way):**
```java
public abstract class Cell {
    // 50 lines of common code written ONCE
}
public class GrassCell extends Cell {
    // Only grass-specific code (5 lines)
}
public class WaterCell extends Cell {
    // Only water-specific code (5 lines)
}
```

**Why this is good design:**
- Less code duplication → common stuff written once  
- Easy to add new terrain → just extend `Cell`  
- Easy to fix bugs → fix in `Cell` class, all terrain types benefit  



### 2. Interfaces – Why they make code flexible
**Problem:** How do we let different animals collect different items?

**Bad solution:**
```java
// In Actor class - messy if/else chains
if (this instanceof Dog && item instanceof Frisbee) {
    // collect frisbee
} else if (this instanceof Cat && item instanceof Mouse) {
    // collect mouse
}
// This gets messy fast!
```

**Good solution with interfaces:**
```java
// CollectibleItem interface
public interface CollectibleItem<T extends Actor> {
    boolean canBeCollectedBy(T actor);
    void onCollected(T actor);
}

// In Actor class - clean and simple
if (collectible.canBeCollectedBy(this)) {
    collectible.onCollected(this);
}
```

**Why this is good design:**
- Same code works for all items → no messy `if/else`  
- Easy to add new items → just implement the interface  
- Items control their own rules → e.g., Frisbee knows only Dogs can collect it  



### 3. Generics – Preventing bugs before they happen
**Problem:** Stop cats from collecting dog items (and crashing the game)

**Without generics (dangerous):**
```java
public class Item {
    // Any animal can collect any item - could cause crashes!
}
```

**With generics (safe):**
```java
public class Frisbee extends Item<Dog> {
    // Compiler makes sure only Dogs can collect this
}
public class Mouse extends Item<Cat> {
    // Compiler makes sure only Cats can collect this  
}
```

**Why this is good design:**
- Catches mistakes **while coding**, not during gameplay  
- Clear code → `Item<Dog>` tells you it's for dogs  
- No crashes → impossible to give mouse to dog  



## Key Programming Concepts Demonstrated
- **Inheritance:** `Cell → GrassCell / WaterCell / RockCell` (shared code, different behaviors)  
- **Interfaces:** `CollectibleItem`, `Movable`, `TerrainEffect` (flexible contracts)  
- **Generics:** `Item<T>`, `InventoryManager<T>` (type-safe collections)  
- **Polymorphism:** Same method calls work on different object types  
- **Encapsulation:** Each class handles its own responsibilities  


## Files Overview
- `Main.java` → Starts the game, handles mouse clicks  
- `Stage.java` → Game logic, movement system  
- `Grid.java` → Creates the game board  
- `Cell.java` + terrain classes → Different ground types  
- `Actor.java` + animal classes → Different animals  
- `Item.java` + item classes → Different collectible items  
- **Interface files** → Define behaviour contracts  
