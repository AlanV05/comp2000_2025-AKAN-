import java.awt.*;

public class Grid {
    //fields
    Cell [][] cells  = new Cell[20][20];

    //constructors
    public Grid() {

    }


    //methods 
    public void draw (Graphics g){ // the "Graphics" allows the grid to be drawn

  for(int i = 10; i<=710; i+=35){ // creating the cells 35x35
      for(int j = 10; j<=710; j+=35){ // creating the cells 35x35
        g.setColor(Color.white);
        g.drawRect(i, j, 35, 35);
        g.setColor(Color.black);
        g.drawRect(i, j, 35, 35);
            } 
        }

    }       

}