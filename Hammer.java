import java.awt.*;
public class Hammer extends block{
   double throwTime = 0;
   boolean active = false;
   double tempY,tempX;
   int dir;
   public Hammer(int one, int two, int d){
      super(one,two);
      width = 16;
      height = 16;
      image =  Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Hammer.gif"));
      tempY = two;
      tempX = one;
      dir = d;
   }
    /*** move ********************************************
      * Purpose: moves hammer in a parabolic shape          *
      * Parameters: none        *
      * Returns: none                                       *
      ******************************************************/
   public void move(){
     
      tempY += (throwTime-1.4);
      y = (int)Math.round(tempY);
      tempX-=0.3*dir;
      x = (int)Math.round(tempX);
      throwTime+=0.005;
   }
}