public class pipe extends block{
   boolean enterable = false;
   boolean up = false;
   boolean piranha = false;
   
   public pipe(int one, int two,boolean e,boolean u,boolean p){
      super (one, two);
      width = 70;
      height = 485-two;
      enterable = e;
      up = u;
      piranha = p;
   }
   /*** enter ********************************************
      * Purpose: checks if mario can enter this pipe          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object         *
      * Returns: boolean - true if mario can enter this pipe
                         - false otherwise                                       *
      ******************************************************/
   public boolean enter(int X, int Y, int sizeX,int sizeY){
      if((Y+sizeY == y-1||Y+sizeY==y)&&(X>x&&X+sizeX<x+width)){
         return true;
      }
      return false;
   }
   
      
}