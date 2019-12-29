//THis is a test
import java.awt.*;
import java.awt.geom.Area;
public class block 
{	
   int x,y;
   int width, height;
   boolean gotten = false;
   int dir = -1;
   boolean obtained = false;
   boolean getting=false;
   int rewardCount=0;
   int holds=0;
   boolean active = true;
   double coinCount = -10,coinY = 0;
   boolean getCoin = false;
   int flagX ,flagY;
   Score killScore = new Score(0,0,100);
   Image image = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//BreakingBlock.gif"));
   long gottenTime = 0;
   boolean reversed = false;

 
   public block(int one, int two)
   {
      x = one;
      y = two;
      width = 35;
      height = 35;
   }
   public block(int one, int two, boolean underground)
   {
      x = one;
      y = two;
      width = 35;
      height = 35;
      image = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//undergroundBreakingBlock.gif"));
   }
   /*** shouldFall ********************************************
      * Purpose: checks if another object is not on this object          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object         *
      * Returns: boolean - true if object is not on this object
                         - false if object is on this object                                       *
      ******************************************************/
   public boolean shouldFall(int X, int Y, int sizeX, int sizeY)
   {
      
      boolean fall = true;
      if((Y == 485-sizeY || ((X+sizeX)-this.x >0&&X-this.x <width&&Y+sizeY==this.y-1))&&!gotten)
         fall = false;
      
      return fall;
      
   }
   
   
   /*** inBlock ********************************************
      * Purpose: checks if another object is colliding with this object          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object
                    time - amount of time that pobject is in the air for         *
      * Returns: array of integers - 1st index : side 
                                   - 2nd index : how much of the object is colliding with this object (area)                                       *
      ******************************************************/
   public int[] inBlock (int X, int Y, int sizeX, int sizeY,double time)       //         1
   {     
      if(x>X-100&&x<X+100){                                                                            //      _______   
         Polygon mario = new Polygon(new int[]{X,X+sizeX,X+sizeX,X},new int[]{Y,Y,Y+sizeY,Y+sizeY},4);
         Polygon sideT = new Polygon(new int[]{x,x+width,x+width,x}, new int[]{y,y,y-2,y-2},4);
         Polygon sideB = new Polygon(new int[]{x,x+width,x+width,x}, new int[]{y+height,y+height,y+2,y+2},4);
         Polygon sideL = new Polygon(new int[]{x,x,x-1,x-1}, new int[]{y,y+height,y+height,y},4);
         Polygon sideR = new Polygon(new int[]{x+width,x+width,x+1,x+1}, new int[]{y,y+height,y+height,y},4);
         Area leftSide = new Area (sideL);
         Area rightSide = new Area (sideR);
         Area topSide = new Area(sideT);
         Area bottomSide = new Area(sideB);
         int top = 0, bottom = 0, left = 0, right = 0;
         int area =0, side =0;
      
         if (X < this.x + this.width &&
         X + sizeX > this.x &&
         Y <= this.y + this.height &&
         Y + sizeY >= this.y)//collision
         {
            if (Y<this.y){
               top = this.y;
            }
            else{
               top = Y;
            }
            if (Y+sizeY>y+height){
               bottom = y+height;
            }
            else{
               bottom = Y+sizeY;
            }
            if(X > x){
               left = X;
            }
            else{
               left = x;
            }
            if(X+sizeX>x+width){
               right = x+width;
            }
            else{
               right = X+sizeX;
            }
         
            if (topSide.intersects(mario.getBounds())&&time>1.9&&Y<y-5){
               if ((right-left )>area){
                  area = right-left;
                  side =1;
               }
            
            }
            if (bottomSide.intersects(mario.getBounds())&&time<1.9&&Y+sizeY>y+height-5){
               if ((right-left )>area){
                  area = right-left;
                  side =2;
               }
            
            }
            if (leftSide.intersects(mario.getBounds())&&X+sizeX>=x){
               if ((bottom-top)>area+10&&side!=1){
                  area = bottom-top;
                  side =3;
               }
               else if ((bottom-top)>area&&side==1){
                  area = bottom-top;
                  side =3;
               }
                  
            }
            if (rightSide.intersects(mario.getBounds())&&X<=x+width){
               if ((bottom-top)>area+10&&side!=1){
                  area = bottom-top;
                  side =4;
               }
               else if((bottom-top)>area&&side ==1){
                  area = bottom-top;
                  side =4;
               }
            
            }
            if(gotten||(active == false&&side != 2)){
               return new int[]{0,-1};
            }   
            return new int[] {side, area};
         }
      }
      return new int []{0,-1};
   }
   /*** inBlock ********************************************
      * Purpose: checks if another object is colliding with this object          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object
                    time - amount of time that pobject is in the air for
                    antiHole - if this objject is a hole         *
      * Returns: array of integers - 1st index : side 
                                   - 2nd index : how much of the object is colliding with this object (area)                                       *
      ******************************************************/
   public int[] inBlock (int X, int Y, int sizeX, int sizeY,double time,int antiHole)       //         1
   {     
                                                                                 //      _______   
      if(x>X-100&&x<X+100){                                                                            //      _______   
         Polygon mario = new Polygon(new int[]{X,X+sizeX,X+sizeX,X},new int[]{Y,Y,Y+sizeY,Y+sizeY},4);
         Polygon sideT = new Polygon(new int[]{x,x+width,x+width,x}, new int[]{y,y,y-2,y-2},4);
         Polygon sideB = new Polygon(new int[]{x,x+width,x+width,x}, new int[]{y+height,y+height,y+2,y+2},4);
         Polygon sideL = new Polygon(new int[]{x,x,x-1,x-1}, new int[]{y,y+height,y+height,y},4);
         Polygon sideR = new Polygon(new int[]{x+width,x+width,x+1,x+1}, new int[]{y,y+height,y+height,y},4);
         Area leftSide = new Area (sideL);
         Area rightSide = new Area (sideR);
         Area topSide = new Area(sideT);
         Area bottomSide = new Area(sideB);
         int top = 0, bottom = 0, left = 0, right = 0;
         int area =0, side =0;
      
         
         if (Y<this.y){
            top = this.y;
         }
         else{
            top = Y;
         }
         if (Y+sizeY>y+height){
            bottom = y+height;
         }
         else{
            bottom = Y+sizeY;
         }
         if(X > x){
            left = X;
         }
         else{
            left = x;
         }
         if(X+sizeX>x+width){
            right = x+width;
         }
         else{
            right = X+sizeX;
         }
         
         if (topSide.intersects(mario.getBounds())&&time>1.9&&antiHole!=2){
            if ((right-left )>area){
               area = right-left;
               side =1;
            }
            
         }
         if (bottomSide.intersects(mario.getBounds())&&time<1.9&&antiHole!=2){
            if ((right-left )>area){
               area = right-left;
               side =2;
            }
            
         }
         if (leftSide.intersects(mario.getBounds())&&X<=x){
            if ((bottom-top)>area){
               area = bottom-top;
               side =3;
            }
            
         }
         if (rightSide.intersects(mario.getBounds())&&X+sizeX>=x+width){
            if ((bottom-top)>area){
               area = bottom-top;
               side =4;
            }
            
         }
         if(gotten||(active == false&&side != 2)){
            return new int[]{-1,0};
         }   
         return new int[] {side, area};
         
      }
      return new int []{0,-1};      }
      /*** inBlock ********************************************
      * Purpose: checks if another object is colliding with this object          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object
                    time - amount of time that pobject is in the air for
                    second - if should return the second greatest side         *
      * Returns: array of integers - 1st index : side 
                                   - 2nd index : how much of the object is colliding with this object (area)                                       *
      ******************************************************/
   public int[] inBlock (int X, int Y, int sizeX, int sizeY,double time,boolean second){
      if(x>X-100&&x<X+100){                                                                            //      _______   
         Polygon mario = new Polygon(new int[]{X,X+sizeX,X+sizeX,X},new int[]{Y,Y,Y+sizeY,Y+sizeY},4);
         Polygon sideT = new Polygon(new int[]{x,x+width,x+width,x}, new int[]{y,y,y-2,y-2},4);
         Polygon sideB = new Polygon(new int[]{x,x+width,x+width,x}, new int[]{y+height,y+height,y+2,y+2},4);
         Polygon sideL = new Polygon(new int[]{x,x,x-1,x-1}, new int[]{y,y+height,y+height,y},4);
         Polygon sideR = new Polygon(new int[]{x+width,x+width,x+1,x+1}, new int[]{y,y+height,y+height,y},4);
         Area leftSide = new Area (sideL);
         Area rightSide = new Area (sideR);
         Area topSide = new Area(sideT);
         Area bottomSide = new Area(sideB);
         int top = 0, bottom = 0, left = 0, right = 0;
         int area =0, side =0;
      
         if (X < this.x + this.width &&
         X + sizeX > this.x &&
         Y <= this.y + this.height &&
         Y + sizeY >= this.y)//collision
         {
            if (Y<this.y){
               top = this.y;
            }
            else{
               top = Y;
            }
            if (Y+sizeY>y+height){
               bottom = y+height;
            }
            else{
               bottom = Y+sizeY;
            }
            if(X > x){
               left = X;
            }
            else{
               left = x;
            }
            if(X+sizeX>x+width){
               right = x+width;
            }
            else{
               right = X+sizeX;
            }
         
            
            if (leftSide.intersects(mario.getBounds())){
               if ((bottom-top)>area){
                  area = bottom-top;
                  side =3;
               }
            
            }
            if (rightSide.intersects(mario.getBounds())){
               if ((bottom-top)>area){
                  area = bottom-top;
                  side =4;
               }
            
            }
            if(gotten||(active == false&&side != 2)){
               return new int[]{0,-1};
            }   
            return new int[] {side, area};
         }
      }
      return new int []{0,-1};
   }
   /*** updateBreaking ********************************************
      * Purpose: checks if should continue to draw breaking block animation          *
      * Parameters: none
      * Returns: boolean - true if should continue to draw
                         - false if not                                       *
      ******************************************************/
   public boolean updateBreaking(){
      if(gotten&&System.currentTimeMillis()-gottenTime<1300){
         return true;
      
      }
      return false;
   }
         
   
}


