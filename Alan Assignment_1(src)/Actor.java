import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public abstract class Actor implements Movable {
    ArrayList<Polygon> shapes = new ArrayList<>();
    Cell loc;
    protected int movementSpeed = 1;
    protected ArrayList<Item<?>> inventory = new ArrayList<>();

    public void pain