import java.awt.*;
public class Bowser extends block{
   
   BowserFireBall[] fireBall = new BowserFireBall[2];
   Hammer[] hammers = new Hammer[3];
   long lastFireBall = System.currentTimeMillis();
   long lastHammer = System.currentTimeMillis();
   long prevHammerThrown = 0;
   long lastJump = System.currentTimeMillis();
   double jumpTime = 0;
   boolean jump = false;
   int hammerCount = -1;
   block[] images = new block[6];
   double tempY,tempX;
   int minX, maxX;
   Image lBowser = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lBowser.gif"));
   Image bowser = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Bowser.gif"));
   Image closedBowser = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//ClosedBowser.png"));
   Image lClosedBowser = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lClosedBowser.png"));
   public Bowser (int one, int two){
      super (one,two);
      width = 90;
      height = 90;
      tempY = two;
      tempX = one;
      image = bowser;
   
   }
   
   /*** update ********************************************
      * Purpose: updates bowsers attack, and position          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object
                    mX - maximum position that mario has traveled
                    blocks - an array of blocks that are in the level
                    blockLength - how many blocks are in the level        *
                    confineLeft - left side of bridege coordinate
      * Returns: array of integers - 1st index : side 
                                   - 2nd index : how much of the object is colliding with this object (area)                                       *
      ******************************************************/
   public block[] update(int X, int Y, int sizeX, int sizeY,int mX,block[]blocks,int blockLength,float confineLeft){
   
      minX = mX-435;
      if(x>X){
         if(image != bowser){
            image = bowser;
         }
         if(System.currentTimeMillis() - lastFireBall>3000&&image != closedBowser){
            image = closedBowser;
         }
         if(x-X>175&&X-x<700&&x>1149){
           
            tempX-=0.05;
         }
         else if(x-X<175&&X-x<700){
            tempX+=0.05;
         }
      }
      else if(x<X){
      //change image to reverse bowser
         if(image != lBowser){
            image = lBowser;
         }
         
         if(System.currentTimeMillis() - lastFireBall>3000&&image != lClosedBowser){
            image = lClosedBowser;
         }
         if(X-x>175){
            tempX+=0.05;
         }
         else if(X-x<175){
            tempX-=0.05;
         }
      }
      
      x = (int)Math.round(tempX);
      if (System.currentTimeMillis()-lastJump>8000){
         jump = true;
      }
      if(System.currentTimeMillis()-lastHammer>6000&&hammerCount == -1){
         hammerCount = 0;
         
      }
      if(System.currentTimeMillis() - lastFireBall>4000){
         spawnFireBall(X);
         lastFireBall = System.currentTimeMillis();
      }
      jump();
      moveFireBalls(blocks,blockLength);
      moveHammer(X);
      images[0] = this;
      images[1] = fireBall[0];
      images[2] = fireBall[1];
      images[3] = hammers[0];
      images[4] = hammers[1];
      images[5] = hammers[2];
      //jump();
      return images;
      
   }
   /*** moveHammer ********************************************
      * Purpose: moves bowsers hammers          *
      * Parameters: X - x-position of bowser
                             *
      * Returns: none                                       *
      ******************************************************/
   public void moveHammer(int X){
      if(hammerCount!=-1){
         if(hammerCount == 0){
            if(X<x){
               hammers[hammerCount] = new Hammer(x,y,1);
            }
            else if(X>x){
               hammers[hammerCount] = new Hammer(x+width,y,-1);
            }
            hammerCount++;
            prevHammerThrown= System.currentTimeMillis();
         }
         else{
            if (System.currentTimeMillis()-prevHammerThrown>200){
               if(X<x){
                  hammers[hammerCount] = new Hammer(x,y,1);
               }
               else if(X>x){
                  hammers[hammerCount] = new Hammer(x+width,y,-1);
               }
               hammerCount++;
               prevHammerThrown = System.currentTimeMillis();
            }
         }
         if(hammerCount == 3){
            hammerCount = -1;
            lastHammer = System.currentTimeMillis();
         }
      }
      
      
      for (int i = 0; i<hammers.length;i++){
      
         if (hammers[i]!=null ){
            hammers[i].move();
            
            if(hammers[i].y>=600){
               hammers[i].active = false;
               hammers[i] = null;
            }
            
            
         
         }  
         
      }
      if(hammers[2]!=null&&hammers[2].y>600){
         for (int i = 0;i<3;i++){
            hammers[i] = null;
         }
         
      }
   }
   /*** moveFireBalls ********************************************
      * Purpose: moves bowsers fireBalls          *
      * Parameters: blocks - array of all the blocks in the level
                    blockLength - how many blocks there are in the level        *
      * Returns: none                                       *
      ******************************************************/
   public void moveFireBalls(block[] blocks,int blockLength){
      for (int i = 0; i<fireBall.length;i++){
         if(fireBall[i]!=null&&fireBall[i].x<minX){
            fireBall[i] = null;
         }
         if(fireBall[i]!=null ){
            fireBall[i].move();
            
            if(fireBall[i].checkCollision(blocks,blockLength)){
               fireBall[i] = null;
            }
         }
      }
   }
   /*** jump ********************************************
      * Purpose: makes bowser jump          *
      * Parameters: 
                             *
      * Returns: none                                       *
      ******************************************************/
   public void jump(){
      if(y>485-height){
         jump = false;
         jumpTime = 0;
         lastJump = System.currentTimeMillis();
         tempY = 485-height;
         y = 485-height;
      }
      else if(jump){
         
        
         tempY += (jumpTime-0.5);
         y= (int)Math.round((tempY));
         jumpTime+=0.0008;
      }         
   }
   /*** shouldDie ********************************************
      * Purpose: checks if another mario should die due to bowser          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of mario
                    sizeY = height of mario       
                    time - how long mario has jumped for  *
      * Returns: boolean - true if mario should die
                         - false otherwise                                      *
      ******************************************************/
   public boolean shouldDie(int X, int Y, int sizeX, int sizeY,double time){
      for (int i = 0; i<hammers.length;i++){
         if (hammers[i]!=null&&hammers[i].inBlock(X,Y,sizeX,sizeY,time)[0]!= 0){
            System.out.println(hammers[i].x);
            return true;
         }
      }
      if (inBlock(X,Y,sizeX,sizeY,time)[0]!=0){
         return true;
      }
      for (int i = 0; i<fireBall.length;i++){
         if (fireBall[i]!=null&&fireBall[i].inBlock(X,Y,sizeX,sizeY,time)[0] != 0){
            return true;
         }
      }
      return false;
   }
   /*** spawnFireBall ********************************************
      * Purpose: spawns a bowser fireball          *
      * Parameters: X - x-position of bowser
                             *
      * Returns: none                                       *
      ******************************************************/
   public void spawnFireBall(int X){
      if(fireBall[0]==null){
         if (X>x)
            fireBall[0] = new BowserFireBall(x+width,y+50,-1);
         else if (X<x)
            fireBall[0] = new BowserFireBall(x,y+50,1);
       
      }
      else if(fireBall[1]==null){
         if (X>x)
            fireBall[1] = new BowserFireBall(x+width,y+50,-1);
         else if (X<x)
            fireBall[1] = new BowserFireBall(x,y+50,1);
      }
   }
}