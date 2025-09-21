import java.awt.Color;
import java.awt.Graphics;

public class Mouse extends Item<Cat> {
    @Override
    public void paint(Graphics g) {
        if(location != null && !collected) {
            g.setColor(Color.GRAY);
            // Draw mouse body
            g.fillOval(location.x + 14, location.y + 16, 8, 6);
            // Draw mouse tail
            g.drawLine(location.x + 22, location.y + 19, location.x + 26, location.y + 22);
            // Draw mouse ears
            g.fillOval(location.x + 13, location.y + 16, 3, 3);
            g.fillOval(location.x + 18, location.y + 16, 3, 3);
        }
    }
    
    @Override
    public boolean canBeCollectedBy(Cat actor) {
        return true;
    }
    
    @Override
    public void onCollected(Cat actor) {
        collect();
        System.out.println("Cat caught a mouse!");
    }
    
    @Override
    public String getCollectionMessage(Cat actor) {
        return "Meow! Caught a mouse!";
    }
}