public class Mushroom extends Goomba{
   public Mushroom(int one, int two){
      super(one, two,1);
      height = 35;
      width = 35;
   }
   /*** getsMushroom ********************************************
      * Purpose: checks if another mario should get this powerup          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object         *
      * Returns: boolean - true if mario should get it
                         - false otherwise                                       *
      ******************************************************/
   public boolean getsMushroom(int X, int Y, int sizeX, int sizeY){
      if (this.inBlock(X,Y,sizeX,sizeY,10.0)[0] !=0){
         return true;
      }
      return false;
   }
   
}