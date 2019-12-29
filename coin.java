public class coin extends block{ //Extension off block
   
   public coin(int one, int two){
      super(one, two);
      super.width = 30;
      super.height = 30;
   }
   /*** shouldGetCoin ********************************************
      * Purpose: checks if mario is colliding with this coin          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object         *
      * Returns: boolean - true if should get coin
                         - false otherwise                                       *
      ******************************************************/
   public boolean shouldGetCoin(int X, int Y, int sizeX, int sizeY){
    
      boolean in = false;
      if (X < this.x + this.width &&
      X + sizeX > this.x &&
      Y < this.y + this.height &&
      Y + sizeY > this.y)
         in = true;
      return in;
   
   
   }
   public boolean collected (){
      return gotten;
   }
   public void setGotten(boolean c){
      gotten = c;
   }
}