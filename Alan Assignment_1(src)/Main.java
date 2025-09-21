import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }

    class Canvas extends JPanel implements MouseMotionListener, MouseListener {
        Stage stage = new Stage();
        
        public Canvas() {
            setPreferredSize(new Dimension(1024, 720));
            addMouseMotionListener(this);
            addMouseListener(this);
        }

        @Override
        public void paint(Graphics g) {
            stage.paint(g, getMousePosition());
        }
        
        @Override
        public void mouseMoved(MouseEvent e) {
            repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            repaint();
        }
        
        // Mouse click methods for animal movement
        @Override
        public void mouseClicked(MouseEvent e) {
            stage.handleMouseClick(e.getX(), e.getY());
            repaint();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            // Not used but required by MouseListener interface
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            // Not used but required by MouseListener interface
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            // Not used but required by MouseListener interface
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            // Not used but required by MouseListener interface
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