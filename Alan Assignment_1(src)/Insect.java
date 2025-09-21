import java.awt.Color;
import java.awt.Graphics;

public class Insect extends Item<Bird> {
    @Override
    public void paint(Graphics g) {
        if(location != null && !collected) {
            // Draw insect body (dark brown oval)
            g.setColor(new Color(101, 67, 33)); // Dark brown
            g.fillOval(location.x + 15, location.y + 18, 8, 4);
            
            // Draw insect head (smaller dark oval)
            g.setColor(new Color(80, 50, 20)); // Darker brown
            g.fillOval(location.x + 14, location.y + 18, 3, 3);
            
            // Draw antennae (thin lines)
            g.setColor(Color.BLACK);
            g.drawLine(location.x + 15, location.y + 18, location.x + 13, location.y + 16);
            g.drawLine(location.x + 16, location.y + 18, location.x + 14, location.y + 16);
            
            // Draw legs (small lines)
            g.drawLine(location.x + 16, location.y + 21, location.x + 15, location.y + 23);
            g.drawLine(location.x + 19, location.y + 21, location.x + 20, location.y + 23);
            g.drawLine(location.x + 21, location.y + 21, location.x + 22, location.y + 23);
        }
    }
    
    @Override
    public boolean canBeCollectedBy(Bird actor) {
        return true;
    }
    
    @Override
    public void onCollected(Bird actor) {
        collect();
        System.out.println("Bird caught a tasty insect!");
    }
    
    @Override
    public String getCollectionMessage(Bird actor) {
        return "Tweet! Caught an insect!";
    }
}