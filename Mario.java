import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Start Screen
public class Mario implements MouseListener, MouseMotionListener
{

   Drawing draw = new Drawing();

   Image backGround=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//MarioStartScreen.png"));
   int x,y,x2,y2;
   JFrame frame = new JFrame("Mario");
   Font font;

 /*** main ********************************************
      * Purpose: instentiates new Mario game          *
      * Parameters: args - arguments from command line        *
      * Returns: none                                       *
      ******************************************************/

   public static void main(String[] args)
   {
      Mario mario = new Mario();
   }
   
   public Mario()
   {
      frame.setVisible(true);
      frame.setSize(807,614);
      frame.add(draw,"Center");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      draw.repaint();
      draw.addMouseListener(this);
      draw.addMouseMotionListener(this);
      try {
      //create the font to use. Specify the size!
         font = Font.createFont(Font.TRUETYPE_FONT, new File("Font//mario.ttf")).deriveFont(24f);
         
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
         //register the font
         ge.registerFont(font);
         
      } catch (IOException f) {
         f.printStackTrace();
      } catch(FontFormatException f) {
         f.printStackTrace();
      }
   }
   class Drawing extends JComponent
   {
       /*** paint ********************************************
      * Purpose: paints all objects to screen          *
      * Parameters: g - graphics object to draw on        *
      * Returns: none                                       *
      ******************************************************/
      public void paint(Graphics g)
      {
         
         
         g.drawImage(backGround,0,0,this);
         g.setFont(font);
         g.setColor(Color.WHITE);
         g.drawString(getHighScore(),411,495);
      
      }
   }
   // public void actionPerformed (ActionEvent e)
   // {
      // System.out.println("Test");
   // }
   public void mouseClicked (MouseEvent e)
   {
      
   }
    /*** mousePressed ********************************************
      * Purpose: determines the coordinates of the click          *
      * Parameters: e - mouse event        *
      * Returns: none                                       *
      ******************************************************/
   public void mousePressed (MouseEvent e)
   {
      x=e.getX();
      y=e.getY();
   
      
   }
   /*** mouseReleased ********************************************
      * Purpose: determines the coordinates of the released and if the user clicked on the one player game button          *
      * Parameters: e - mouse event        *
      * Returns: none                                       *
      ******************************************************/
   public void mouseReleased (MouseEvent e)
   {
      
      x2=e.getX();
      y2=e.getY();
      if (Math.abs(x2-x)<3&&Math.abs(y2-y)<3&&x2>298&&x2<574&&y2>366&&y2<385){
        
         frame.setVisible(false);
         new onePlayerGame("Levels//Level1.txt", 1, 1, 3,0,400,0,0,false);
         
      }
   }
   /*** getHighScore ********************************************
      * Purpose: determines the high score to be displayed          *
      * Parameters: none        *
      * Returns: String - high score to be displayed                                       *
      ******************************************************/
   public String getHighScore(){
      try{
         FileReader fr = new FileReader("HighScore.txt");
         BufferedReader br = new BufferedReader(fr);
         return br.readLine().trim();
      }
      catch(IOException e){
         return "-1";
      }
      
      
   }
   public void mouseExited(MouseEvent e)
   {
   }
   public void mouseEntered(MouseEvent e)
   {
   }
   public void mouseMoved(MouseEvent e)
   {
   }
   public void mouseDragged(MouseEvent e)
   {
   }
   
}

