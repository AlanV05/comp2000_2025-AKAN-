import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    class Cell {
      int x;
      int y;
      int cellHeight;
      int cellWidth;

      public Cell(int h, int w, int x, int y){
        this.cellHeight = h;
        this.cellWidth = w;
      }

      public void makeCell (Graphics g){
        g.setColor(java.awt.Color.BLACK);
        g.drawRect(x, y, cellWidth, cellHeight);
      }    
      }

    }
    class Grid { // to modify the grid specs
      int posX;
      int posY;
      int heigth;
      int width; 
      int cellSize;

      public Grid (int posX, int posY, int heigth, int width, int cellSize){
        this.posX = posX;
        this.posY = posY; 
        this.heigth = heigth;
        this.width = width;
        this.cellSize = cellSize; 
      }
      public void makeGrid (Graphics g){
        for(int i = posX; i<=width; i+=cellSize){
          for(int j = posY; j<=heigth; j+=cellSize){
            g.drawRect(i, j, cellSize, cellSize);
          }
        }
      }
    
    }


    class Canvas extends JPanel {
      public Canvas() {
        setPreferredSize(new Dimension(720, 720));
      }

      @Override
      public void paint(Graphics g) {
          super.paint(g);
Grid grid = new Grid(10, 10,700, 700, 25);
        }
  }

    private Main() {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Canvas canvas = new Canvas();
      this.setContentPane(canvas);
      this.pack();
      this.setVisible(true);
    }

    public void run() {
      while(true) {
        repaint();
      }
    }
}
