import java.awt.Color;
import java.awt.Graphics;

public class Worm extends Item<Bird> {
    @Override
    public void paint(Graphics g) {
        if(location != null && !collected) {
            g.setColor(new Color(139, 69, 19)); // Brown
            // Draw worm as a wavy line
            g.setColor(Color.PINK);
            int startX = location.x + 15;
            int startY = location.y + 20;
            g.drawLine(startX, startY, startX + 3, startY - 2);
            g.drawLine(startX + 3, startY - 2, startX + 6, startY + 1);
            g.drawLine(startX + 6, startY + 1, startX + 9, startY - 1);
            g.drawLine(startX + 9, startY - 1, startX + 12, startY + 2);
        }
    }
    
    @Override
    public boolean canBeCollectedBy(Bird actor) {
        return true;
    }
    
    @Override
    public void onCollected(Bird actor) {
        collect();
        System.out.println("Bird found a juicy worm!");
    }
    
    @Override
    public String getCollectionMessage(Bird actor) {
        return "Tweet! Found a worm!";
    }
}