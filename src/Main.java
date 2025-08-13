import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    class Canvas extends JPanel {
      public Canvas() {
        setPreferredSize(new Dimension(720, 720));
      }

      @Override
      public void paint(Graphics g) { // paint object to "paint" the grid and cells

  int gridStart = 10;
  int recSize = 700;

	g.setColor(java.awt.Color.BLACK);
	g.drawRect(gridStart, gridStart, recSize, recSize);

  for(int i = 10; i<=recSize; i+=25){ // creating the cells 35x35
      // vertical lines:
    g.drawLine(i,gridStart,i,gridStart+recSize);
    // horizontal lines
    g.drawLine(gridStart, i, gridStart+recSize, i);
  } 
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
