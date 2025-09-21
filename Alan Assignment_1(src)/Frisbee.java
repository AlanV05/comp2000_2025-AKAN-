import java.awt.Color;
import java.awt.Graphics;

public class Frisbee extends Item<Dog> {
    @Override
    public void paint(Graphics g) {
        if(location != null && !collected) {
            g.setColor(Color.ORANGE);
            // Draw a frisbee as a filled oval with a smaller oval inside
            g.fillOval(location.x + 10, location.y + 15, 15, 15);
            g.setColor(Color.RED);
            g.fillOval(location.x + 13, location.y + 18, 9, 9);
        }
    }
    
    @Override
    public boolean canBeCollectedBy(Dog actor) {
        return true;
    }
    
    @Override
    public void onCollected(Dog actor) {
        collect();
        System.out.println("Dog caught the frisbee!");
    }
    
    @Override
    public String getCollectionMessage(Dog actor) {
        return "Woof! Caught the frisbee!";
    }
}