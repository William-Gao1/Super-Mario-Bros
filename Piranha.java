public class Piranha extends Goomba{  
   long lastPiranha = System.currentTimeMillis();
   long holdUp = System.currentTimeMillis();
   boolean comingUp = false;
   boolean comingDown = false;
   int countComingUp = 0;
   double tempY;
   
   public Piranha (int one, int two){
      super (one+10, two,1);
      tempY = two;
      height = 30;
      width =40;
      
   }
   /*** move ********************************************
      * Purpose: moves the piranha plant          *
      * Parameters: none         *
      * Returns: none                                       *
      ******************************************************/
   public void move(int X,int Y,int sizeX, int sizeY){
      if(Y+sizeY>y-10&&X+sizeX>x-5&&X<x+width+5){
         lastPiranha = System.currentTimeMillis()-2000;
      }
      if(System.currentTimeMillis() - lastPiranha>3000&&comingUp == false&&!comingDown){
         comingUp = true;
      }
      if(comingUp&&countComingUp!= 150){
         tempY -= 0.5;
         y = (int)(Math.round(tempY));
         countComingUp +=1;
         holdUp = System.currentTimeMillis();
      }
      else if(countComingUp == 150||countComingUp !=0){
         if(System.currentTimeMillis()-holdUp>2000){
            comingUp =false;
            comingDown = true;
            tempY+=0.5;
            y = (int)(Math.round(tempY));
            countComingUp --;
            if(countComingUp ==0){
               comingDown = false;
               lastPiranha = System.currentTimeMillis();
            }
         }
      }
   }
         
         
         
         
   
   
}