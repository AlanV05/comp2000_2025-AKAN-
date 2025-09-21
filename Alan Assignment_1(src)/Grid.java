import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.util.Optional;
import java.util.Random;

public class Grid {
    Cell[][] cells = new Cell[20][20];
    
    public Grid() {
        Random random = new Random();
        
        for(int i=0; i<cells.length; i++) {
            for(int j=0; j<cells[i].length; j++) {
                // Create different terrain types randomly
                int terrainType = random.nextInt(10);
                if(terrainType < 6) {
                    cells[i][j] = new GrassCell(10+Cell.size*i, 10+Cell.size*j);
                } else if(terrainType < 8) {
                    cells[i][j] = new WaterCell(10+Cell.size*i, 10+Cell.size*j);
                } else {
                    cells[i][j] = new RockCell(10+Cell.size*i, 10+Cell.size*j);
                }
                
                // 