import java.awt.*;
public class BowserFireBall extends block{
   boolean active = false;
   double tempX;
   int dir;
   public BowserFireBall (int one, int two, int d){
      super(one, two);
      image = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//BowserFireBall.png"));
      width = 24;
      height = 10;
      active = true;
      tempX = one;
      dir = d;
      if(dir == -1){
         image = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lBowserFireBall.png"));
      }
   }
   /*** move ********************************************
      * Purpose: moves fireball          *
      * Parameters: none
                             *
      * Returns: none                                       *
      ******************************************************/
   public void move(){
      tempX -=0.5*dir;
      x=(int)Math.round(tempX);
   }
   /*** checkCollision ********************************************
      * Purpose: checks if fireball collides with anything          *
      * Parameters: blocks - array of all the blocks in the level
                    blockLength - how many blocks there are in the level        *
      * Returns: boolean - true if collides
                         - false if not                                       *
      ******************************************************/
   public boolean checkCollision(block[] blocks,int blockLength){
      for (int i = 0; i<blockLength;i++){
         if (blocks[i].inBlock(x,y,width,height,10)[0]!=0){
            return true;
         }
      }
      return false;
   }
   
}