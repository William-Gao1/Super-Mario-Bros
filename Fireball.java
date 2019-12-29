public class Fireball extends block{
   int dir =1;
   double time = 1.9;
   boolean inHole = false;
   public Fireball(int one, int two, int d){
      super (one, two);
      dir = d;
      height = 16;
      width = 16;
   }
   /*** move ********************************************
      * Purpose: move the fireball          *
      * Parameters: blocks - array of all the blocks in the level
                    blockLength - how many blocks there are in the level  
                    enemies - array of all the enemies in the level
                    enemyLength - how many enemies are in the level      *
      * Returns: int - which enemy to kill in enemies
                       returns -1 otherwise                                       *
      ******************************************************/
   public int move(block[] blocks,int blockLength,Goomba[] enemies,int enemiesLength){
      if(!active){
         return -1;
      }
      if(active){
         x+=5*dir;
         y+=5*(time-1);
         int side;
         for (int i = 0;i<blockLength;i++){
            side = blocks[i].inBlock(x,y,width,height,10)[0];
            if (side!=0){
               if(blocks[i].getClass() == AntiBlock.class){
                  inHole = true;
               }
               if (side == 1&&!inHole&&blocks[i].getClass()!=Piranha.class){
                  time = 0;
                  return -1;
               
               }
               
               else if(!inHole){
                  active = false;
               }
               else if(inHole){
                  int holeside = blocks[i].inBlock(x,y,width,height,0,1)[0];
               
                  if(holeside !=0){
                     if((holeside == 3&&x>blocks[i].x)||(holeside == 4 && x<blocks[i].x))
                     
                        active = false;
                  }
                 
               }
            }
            
         }
         if(enemiesLength >0){
            for(int i =0;i<enemiesLength;i++){
               if(enemies[i].enabled||enemies[i].shell||enemies[i].shellMove){
                  side = enemies[i].inBlock(x,y,height,width,10.0)[0];
                  if (side != 0){
                     active = false;
                     return i;
                  }
               }
            }
         }
         time+=0.1;
         if (y>485-height&&!inHole){
            y = 485-height;
            time = 0;
            return -1;
         }
         if(y>600){
            active = false;
         }
      
      }
      return -1; 
   }
}