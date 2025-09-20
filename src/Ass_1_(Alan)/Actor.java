import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public abstract class Actor {
  ArrayList<Polygon> shapes = new ArrayList<>();
  Cell loc;

  public void paint(Graphics g) {
    for (Polygon P : shapes){
      g.drawPolygon(P); //outline
      g.fillPolygon(P); //Filling
    }
  }
}
