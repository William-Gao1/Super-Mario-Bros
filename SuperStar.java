public class SuperStar extends Mushroom{
   double time = 0;
   double tempY;
   public SuperStar(int one, int two){
      super(one,two);
      tempY = two;
   }
   /*** move ********************************************
      * Purpose: moves the super star          *
      * Parameters: blocks - array of all the blocks in the level
                    blockLength - how many blocks are in the level         *
      * Returns: none                                       *
      ******************************************************/
   public void move(block[] blocks,int blockLength){
      
      
      
      int side;
      for (int i = 0;i<blockLength;i++){
         
         side = blocks[i].inBlock(x,y,width,height,time)[0];
         if (side!=0){
            if (side == 1){
               time = 0;
               y = blocks[i].y-height;
               
            }
            
         }
         else if (y>485-height){
            y = 485-height;
            time = 0;
            
         }
         
      }
      x+=2*dir;
      tempY+=7*(time-1);
      y = (int)(Math.round(tempY));
      time+=0.03;
      
   }
   
}