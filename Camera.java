public class Camera
{

   private float xOffset, yOffset;
   boolean turnOn;
/*** camOn ********************************************
      * Purpose: toggles if camera should move with mario          *
      * Parameters: none
                             *
      * Returns: none                                       *
      ******************************************************/
   public void camOn(boolean On)
   {
      if (On)
      {
         turnOn = true;
      }
      else
      {
         turnOn = false;
      }
   }

   public Camera(float xOffset, float yOffset)
   {
      this.xOffset = xOffset;
      this.yOffset = yOffset;
   }
/*** center ********************************************
      * Purpose: centers camera with mario          *
      * Parameters: xpos - x position of mario
                    ypos - y position of mario
                             *
      * Returns: none                                       *
      ******************************************************/
   public void center(int xpos, int ypos)
   {
      
      if (turnOn)
      {
         xOffset = (int)(xpos - 403.5);
         yOffset = ypos - 307;
      }
      xOffset = (int)(xOffset);
   }
   
/*** move ********************************************
      * Purpose: moves camera a set amount*
      * Parameters: xamt - amount to move in the x direction
                    yamt - amount to move in the y direction         *
      * Returns: none                                       *
      ******************************************************/
   public void move(float xAmt, float yAmt)
   {
      
      xOffset += xAmt;
      yOffset += yAmt;
      
   }
   public float getxOffset()
   {
      return xOffset;
   }
   public float getyOffset()
   {
      return yOffset;
   }
   public void setxOffset(float xOffset) 
   {
      this.xOffset = xOffset;
   }
   public void setyOffset(float yOffset) 
   {
      this.yOffset = yOffset;
   }

}