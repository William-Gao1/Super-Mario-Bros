public class AntiBlock extends block{

   public AntiBlock(int one, int two,int w){
      super(one-10,two-1);
      height = 300;
      width = w+10;
   }
   /*** inBlock ********************************************
      * Purpose: checks if another object is colliding with this object          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object         *
      * Returns: array of integers - 1st index : side 
                                   - 2nd index : how much of the object is colliding with this object (area)                                       *
      ******************************************************/
   public int[] inBlock(int X, int Y, int sizeX, int sizeY){
      return this.inBlock(X,Y,sizeX,sizeY,10.0);
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
   public boolean shouldFall(int X, int Y,int sizeX,int sizeY){
      if((Y+sizeY == y-1||Y+sizeY==y)&&(X>x&&X+sizeX<x+width)){
         return true;
      }
      return false;
   }
   // public boolean shouldTurn(int X, int Y, int sizeX, int sizeY){
//       
//    }
}