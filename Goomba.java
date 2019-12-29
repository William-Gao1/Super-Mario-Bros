public class Goomba extends block{
   
   boolean fall=false,shell = false;
   double fallTime=1.0;
   boolean enabled = true;
   boolean shellMove = false;
   long deathTime;
   boolean inHole = false;
   boolean killedByKoopa=false;
   double killedByKoopaCount = -5.4;
   boolean comingBack = false;
   long koopaTime = System.currentTimeMillis();
   public Goomba (int one, int two, int d){
      super(one, two);
      dir = d;
      //height = 20;
   }
   public void switchDir(){
      if (enabled)
         dir *= -1;
   }

   public void move(){
      if (enabled)
         x += dir;
   }
/*** shouldTurn ********************************************
      * Purpose: checks if goomba should switch directions           *
      * Parameters: blocks - array of all the blocks in the level
                    length - how many blocks are in the level
                    enemies - array of all the enemies in the level
                    enemyLength - how many enemies are in the level
                    ignore - index that this goomba is so we can ignore it when checking         *
      * Returns: none                                     *
      ******************************************************/
   public void shouldTurn(block[] blocks, int length,Goomba[] enemies, int enemyLength, int ignore)
   {
      if (enabled){
         int side=0;
         for (int i = 0;i<length;i++){
            if(blocks[i].getClass() != AntiBlock.class){
               side = blocks[i].inBlock(x,y,width,height,10.0)[0];
               if (side == 3 || side == 4 && blocks[i].inBlock(x,y,width,height,10.0)[1]>1){
                  switchDir();
                  break;
               }
            }
            
            else{
              
               side = blocks[i].inBlock(x,y,width,height,10.0,1)[0];
               if ((side == 3 || side == 4 )&& blocks[i].inBlock(x,y,width,height,10.0,1)[1]>1){
                  switchDir();
                  break;
               }
               
            }
         }
         if(enemyLength >0){
            for(int i =0;i<enemyLength;i++){
               if(i!=ignore){
                  side = enemies[i].inBlock(x,y,height,width,10.0)[0];
                  if ((side == 3 || side == 4)&&enemies[i].gotten == false&&gotten ==false){
                     if(shellMove == true){
                        enemies[i].deathTime = System.currentTimeMillis();
                        enemies[i].gotten = true;
                        enemies[i].killedByKoopa = true;
                        
                     }
                     else if(enemies[i].gotten == false){
                        switchDir();
                        //enemies[i].dir *=-1;
                        break;
                     }
                  }
               }
            }
         }
      }
   }
   
   /*** fall ********************************************
      * Purpose: if the goomba should fall, fall          *
      * Parameters: blocks - array of all the blocks in the level
                    length - how many blocks there are in the level        *
      * Returns: none                                       *
      ******************************************************/
   public void fall(block[] blocks, int length){
      if(enabled){
      
         if (y>485-height&&!inHole){
            y = 485-height;
            fall = false;
         }
         if (fall){
            fallTime+=0.1;
            if(!inHole){
               y -= (int)(((-4)*fallTime*fallTime));
            }
            else{
               y -= -5;
            }
            for (int i = 0;i<length;i++){
               int side = blocks[i].inBlock(x,y,width,height,10.0,1)[0];
               
               if (side == 3&&dir<0){
                  if(blocks[i].getClass() != AntiBlock.class){
                     x = blocks[i].x-width;
                  }
                  
                  dir=Math.abs(dir);
               }
               else if(side ==1){
                  if(blocks[i].getClass() != AntiBlock.class){
                     fall = false;
                     fallTime = 0;
                     y = blocks[i].y-height;
                     break;
                  }
               }
               else if (side == 4&&dir>0){
                  if(blocks[i].getClass() != AntiBlock.class){
                     x = blocks[i].x+blocks[i].width;
                  }
               
                  dir=-Math.abs(dir);
               }
               
            }
            
         }
      }
   }
   /*** koopaShellDirection ********************************************
      * Purpose: determines which way koopa shell should go when mario jumps on it          *
      * Parameters: X - x-position
                    Y - y-position
                    sizeX - width of object
                    sizeY = height of object         *
      * Returns: int - 1 if koopa shell should go to the right
                       2 if koopa shell should go to the left                                       *
      ******************************************************/
   public int  koopaShellDirection(int X, int Y, int sizeX, int sizeY){//is goomba shoould go right is 1
      if (this.inBlock(X,Y,sizeX,sizeY,10,true)[0] == 3){
         return 1;
      }
      else if (this.inBlock(X,Y,sizeX,sizeY,10,true)[0] ==4){
         return 2;
      }
      return 0;
   }
   public void shouldFall(block[]toCheck,int length,AntiBlock[] holes, int holeLength){
      if(!fall){
         boolean shouldFall = true;
         for (int i = 0; i< length;i++)
         {
            if ((toCheck[i].shouldFall(x,y,width,height) == false||y == 485-height)&&toCheck[i].getClass()!=AntiBlock.class)
            {
            
               shouldFall = false;
               break;
            }
         
         
         }
         int holeWidth=0;
         for (int i = 0; i<holeLength;i++){
            if(holes[i].inBlock(x,y+1,width,height,10)[1]>0){
               holeWidth+=holes[i].inBlock(x,y,width,height,10)[1];
            }
         
            if(holeWidth >= width){
               shouldFall = true;
               inHole = true;
               break;
            }
         }
         if(shouldFall){
            fall = true;
         }
      
      }
   }
   public void move(block[] blocks,int blockLength){
   }
   public void move(int X, int Y, int sizeX, int sizeY){
   }
}