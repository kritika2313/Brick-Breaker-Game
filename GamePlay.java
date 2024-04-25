import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class GamePlay extends JPanel implements KeyListener,ActionListener{
    private boolean play=false;
    private int score=0;
    private int totalBricks=21;
    private Timer timer;
    private int delay=8;
    private int playerx=310;

    private int ballposx=120;
    private int ballposy=350;
    private int ballxdir=-1;
    private int ballydir=-2;

    private MapGenerator map;

    public GamePlay(){
        map=new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 672);

        //drwaing bricks/map.
        map.draw((Graphics2D)g);


        //border
        g.setColor(Color.yellow);
        // Top border
        g.fillRect(0, 0, 692, 5);
        // Left border
        g.fillRect(0, 0, 5, 672);
        // Right border
        g.fillRect(680, 0, 5, 672);

    
        //scores
        g.setColor(Color.ORANGE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("  "+score, 590, 30);

        //slider
        g.setColor(Color.magenta);
        g.fillRect(playerx,650,100,8);


        //ball
        g.setColor(Color.white);
        g.fillOval(ballposx,ballposy,20,20);

        if(ballposy>680 || totalBricks==0){
            play=false;
            ballydir=0;
            ballxdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            if(totalBricks==0){
                g.setColor(Color.green);
                g.drawString(" You Won!!! ", 190,300);
            }else{
                g.drawString(" Game Over!!! Score:  "+score, 190,300);
            }
            

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart ", 230,350);
        }

        g.dispose();
       
    }



    @Override
    public void actionPerformed(ActionEvent e){
        timer.start();

        //ball movement
        if(play){
            //detecting intersection with slider
            //we nedd to create a rectancgle.
            if(new Rectangle(ballposx,ballposy,20,20).
            intersects(new Rectangle(playerx,650,100,8))){
                ballydir=-ballydir;
            }

            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if(map.map[i][j]>0){//then detect intersection
                        int brickx=j*map.brickWidth+80;
                        int bricky=i*map.brickHeight+50; 
                        int brickheight=map.brickHeight; 
                        int brickwidth=map.brickWidth;

                        Rectangle rec=new Rectangle(brickx,bricky,brickwidth,brickheight);
                        Rectangle ballrec=new Rectangle(ballposx,ballposy,20,20);
                        Rectangle brickRec=rec;
                        if(ballrec.intersects(brickRec)){
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score+=5;


                            if(ballposx+19<=brickRec.x || ballposx+1>=brickRec.x+brickRec.width){
                                ballxdir=-ballxdir;
                            }else{
                                ballydir=-ballydir;
                            }
                            break A;
                        }
                    }
                    
                }
                
            }
            ballposx+=ballxdir;
            ballposy+=ballydir;
            //left border
            if(ballposx < 0){
                ballxdir=-ballxdir;
            }
            //top
            if(ballposy < 0){
                ballydir=-ballydir;
            }
            //right
            if(ballposx > 650){
                ballxdir=-ballxdir;
            }

        }
        repaint();

    }



    

    @Override
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {} 

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerx>=600){
                //then keep it to boder of panel
                playerx=600;
            }else{
                moveRight();
            }

        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerx<10){
                //then keep it to boder of panel
                playerx=10;
            }else{
                moveLeft();
            }
            
        }

        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play=true;
                ballposx=120;
                ballposy=350;
                ballxdir=-1;
                ballydir=-2;
                playerx=310;
                score=0;
                totalBricks=21;
                map=new MapGenerator(3, 7);
                repaint();;

            }
            
        }
       
    }

    public void moveRight(){
        play=true;
        playerx+=20;
    }
    public void moveLeft(){
        play=true;
        playerx-=20;
    }
   
}
