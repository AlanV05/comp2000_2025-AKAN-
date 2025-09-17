import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    class Canvas extends JPanel implements MouseMotionListener {
      Stage stage = new Stage();
      
      public Canvas() {
        setPreferredSize(new Dimension(1024, 720));  // Changed to 1024x720
        addMouseMotionListener(this);  // Add mouse tracking
      }

      @Override
      public void paint(Graphics g) {
        stage.paint(g, getMousePosition());
      }
      
      @Override
      public void mouseMoved(MouseEvent e) {
        repaint();  // Trigger repaint when mouse moves
      }
      
      @Override
      public void mouseDragged(MouseEvent e) {
        repaint();  // Trigger repaint when mouse is dragged
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
        try {
          Thread.sleep(16);  // Add small delay for better performance (~60 FPS)
        } catch (InterruptedException e) {
          break;
        }
        repaint();
      }
    }
}