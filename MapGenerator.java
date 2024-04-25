// import java.awt.BasicStroke;
// import java.awt.Color;
// import java.awt.Graphics2D;


// public class MapGenerator {
//     public int map[][];
//     public int brickwidth,brickheight;
//     public Color[][] brickColors;


//     public MapGenerator(int row,int col){
//          brickColors = new Color[row][col];
        
//         // Assigning colors to bricks
//         for (int i = 0; i < row; i++) {
//             for (int j = 0; j < col; j++) {
//                 brickColors[i][j] = generateColorForRow(i);
//                 map[i][j] = 1; // Indicate that the brick is intact
//             }
//         }
//         brickwidth=540/col;
//         brickheight=150/row;
//     }

//     private Color generateColorForRow(int row) {
//         switch (row) {
//             case 0:
//                 return Color.RED;
//             case 1:
//                 return Color.BLUE;
//             case 2:
//                 return Color.GREEN;
//             // Add more cases for additional rows if needed
//             default:
//                 return Color.BLACK; // Default color
//         }
//     }
//     public void draw(Graphics2D g){
//         for (int i = 0; i < map.length; i++) {
//             for (int j = 0; j < map[0].length; j++) {
//                 if(map[i][j]>0){
//                     Color brickColor = brickColors[i][j];
//                     g.setColor(brickColor);
//                     g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth,brickheight);
//                     //boder.
//                     g.setStroke(new BasicStroke(3));
//                     g.setColor(Color.BLACK);
//                     g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth,brickheight);
//                 }
//             }
//         }
//     }
//     public void setBrickValue(int value,int row,int col){
//         map[row][col]=value;
//     }
// }



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    public int[][] map;
    public int brickWidth, brickHeight;
    public Color[][] brickColors;

    public MapGenerator(int row, int col) {
        map = new int[row][col];
        brickColors = new Color[row][col];
        
        // Ensure map is initialized before accessing it
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = 1; // Indicate that the brick is intact
                brickColors[i][j] = generateColorForRow(i);
            }
        }

        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    // Method to generate color for a row
    private Color generateColorForRow(int row) {
        switch (row) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            // Add more cases for additional rows if needed
            default:
                return Color.BLACK; // Default color
        }
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    Color brickColor = brickColors[i][j];
                    g.setColor(brickColor);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    // Border
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}

