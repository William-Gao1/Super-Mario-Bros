public class Score{
   String score;
   boolean showing = false, showed = false;
   int turns;
   double x, y;
   public Score(int X, int Y, int s){
      score = String.valueOf(s);
      x = X;
      y = Y-50;
   }
   /*** updatePosition ********************************************
      * Purpose: updates position of the floating score (e.g when killing goomba)          *
      * Parameters: none          *
      * Returns: none                                       *
      ******************************************************/
   public void updatePosition(){
      if(!showed){
         if(showing == false && showed == false){
            showing = true;
         }
      
         y-=0.3;
         if(turns == 100){
            showing = false;
            showed = true;
         }
         turns++;
      }
   }
      
}
