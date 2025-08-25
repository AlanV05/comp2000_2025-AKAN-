import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

public class Stage {
  Grid grid;
  List<Actor> actors = new ArrayList<>();

  public Stage() {
    grid = new Grid();

    // create actors and add them to the list
    actors.add(new Cat(grid.cellAtColRow(0, 0)));
    actors.add(new Dog(grid.cellAtColRow(0, 15)));
    actors.add(new Bird(grid.cellAtColRow(12, 9)));
  }

  public void paint(Graphics g, Point mouseLoc) {
    // paint the grid first
    grid.paint(g, mouseLoc);

    // now loop through all actors and paint them
    for (Actor actor : actors) {
      actor.paint(g);
    }
  }
}
