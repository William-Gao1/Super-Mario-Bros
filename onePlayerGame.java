import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;
public class onePlayerGame implements KeyListener
{
/*********************************************************
   *  Names: William Gao, Ricky Khaing, Michael Che         *
   *  Course: ICS 4U Pd.4                                   *
   *  Summative/Culminating                                 *
   *  Game: Super Mario Bros. NES                           *
   *  Due Date: May 23, 2019                                *
   *********************************************************/
   int castleCount=0;
   boolean endingLevel;
   boolean comingUp, crouchCheck = false;
   boolean flipFlagPole = true;
   boolean fall = false;
   long gameoverTime;
   int pipePrevX;
   int l, c, t;
   long s, invMarioTime= 0;
   boolean gameover = false;
   long bridge = 0;
   int CoinCount = 0;
   private static final String BIG_L_DUCKING_MARIO_PNG = "bigLDuckingMario.png";
   int x=100, y=100, rad=50, time = 0, startY,direction, blockPush = -1, countBlockPush =0, blockDirection = 0;
   int width = 35, height = 44;
   int coinY = 0,level = 1;
   int lives = 3;
   boolean endLevel = false;
   int environment = 1;
   int startTime, timesPlayed = 1;
   String currentLevel = "Levels//Level1.txt";
   int marioType;
   boolean onFlagPole = false;
   long actionPreformedTime = System.currentTimeMillis(), koopaTime;
   int maxPos=0,curPos;
   boolean hitSide=false,jumpSound = false;
   int bSide = -1, prevCurMario;
   long score = 0;
   Bowser bowser;
   Axe axe;
   block[] bowserImages = new block[6];   long starMarioTime;
   int secondsLeft = 400;
   InvisBlock invisBlock = new InvisBlock(0,0);
   long timer = System.currentTimeMillis();
   boolean inHole = false,duckCheck = false, smallBig = false;
   StairCase[] stairCases = new StairCase[0];
   Piranha[] piranhas = new Piranha[0];
   Barrier[] barriers = new Barrier[0];
   long tStart, rReleaseTime = 0, lReleaseTime = 0, tAccel;
   //javax.swing.Timer timer = new javax.swing.Timer(15, this);
   boolean right = false, left = false, up = false, down = false, jump = false, leftFacing, rightFacing, isStanding,inBlock, goingLeft = false, stopped = true;
   Drawing draw = new Drawing();
   int heightHolder;
   boolean stopAccel = false; 
   int prevAccel=0;
   boolean shiftKey = false;
   boolean fireFlowerMario = false;
   JFrame oneGame = new JFrame();
   double deathJumpTime=0;
   Boolean endingGame = false;
   double[] intersectResults,checkedIntersect;
   double timeToExit, whatToDo, timeCount=0;
   boolean goingDownPipe = false, invMario = false, starMario = false, bigStarMario = false;
   block[] blocks = {new block(650,350)};
   Goomba[] enemies = new Goomba[0];
   boolean rAccelerate = false, lAccelerate = false,onBlock=false, dead = false, bigMario = false;
   coin[] coins = new coin[]{new coin(650,300)};
   Goomba[] goombas = new Goomba[]{new Goomba(300,452,-1)};
   Koopa[] koopas = new Koopa[]{new Koopa(400, 452, -1)};
   QuestionBlock[] qBlocks = new QuestionBlock[0];
   pipe[] Pipes = new pipe[10];
   AntiBlock[] holes = new AntiBlock[0];
   int onTop, prevX = 100, prevY =100;
   int goombaToBeKilled;
   boolean killJump = false;
   long killTime;
   int scoreInt=100;
   pipe p ;
   Image brokenQBlock=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//BrokenQBlock.png"));
   Mushroom[] powerUps = new Mushroom[10];
   Mushroom[] mushrooms = new Mushroom[10];
   Fireball[] fireballs = new Fireball[3];
   Image mushroom=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Mushroom.png"));
   Image coin=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Coin.gif"));
   Image qBlock=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//QBlock.gif"));
   Image standingMario=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//StandingMario.png"));
   Image jumpingMario=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//JumpingMario.png"));
   Image lStandingMario=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//LStandingMario.png"));
   Image lJumpingMario=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//LJumpingMario.png"));
   Image lRunningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lRunningMario.gif"));
   Image runningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//runningMario.gif"));
   Image brick=Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Brick.png"));
   Image uground = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//uground.png"));
   Image lSlidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lSlidingMario.png"));
   Image slidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//slidingMario.png"));
   Image goomba = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Goomba.gif"));
   Image deadMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//MarioDead.png"));
   Image deadGoomba = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//CrushedGoomba.png"));
   Image bigStandingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigStandingMario.png"));
   Image bigLStandingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigLStandingMario.png"));
   Image bigJumpingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigJumpingMario.png"));
   Image bigLJumpingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigLJumpingMario.png"));
   Image bigRunningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigRunningMario.gif"));
   Image bigLRunningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigLRunningMario.gif"));
   Image bigDuckingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigDuckingMario.png"));
   Image bigSlidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigSlidingMario.png"));
   Image bigLSlidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigLSlidingMario.png"));
   Image ground = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//ground.png"));
   Image cloud = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//cloud.png"));
   Image scloud = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//scloud.png"));
   Image lcloud = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lcloud.png"));
   Image bush = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bush.png"));
   Image hill = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//hill.png"));
   Image Pipe = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//pipe.png"));
   Image croppedMushroom = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//CroppedMushroom.png"));
   Image oneUp = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//1-Up_Mushroom.png"));
   Image croppedOneUp = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Cropped1UpMushroom.png"));
   Image transformingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//TransformingMario.gif"));
   Image fireFlower = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//FireFlower.gif"));
   Image fireRunningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//fireRunningMario.gif"));
   Image lFireRunningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//fireLRunningMario.gif"));
   Image fireJumpingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//fireJumpingMario.png"));
   Image lFireJumpingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lFireJumpingMario.png"));
   Image croppedFireFlower = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//CroppedFireFlower.png"));
   Image fireStandingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//fireStandingMario.png"));
   Image lFireStandingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lFireStandingMario.png"));
   Image fireSlidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//fireSlidingMario.png"));
   Image lFireSlidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lFireSlidingMario.png"));
   Image fireball = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//FireBall.gif"));
   Image koopa = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Koopa.gif"));
   Image leftKoopa = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//leftKoopa.gif"));
   Image koopaShell = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//koopaShell.png"));
   Image reverseStairCase = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//ReverseStairCase.png"));
   Image spinCoin = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//spinCoin.gif"));
   Image font = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Fonts.png"));
   Image stairCase = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//StairCase.png"));
   Image lDuckingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigLDuckingMario.png"));
   Image uKoopa = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//upsideDownKoopa.png"));
   Image uGoomba = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//upsideDownGoomba.gif"));
   Image castle = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Castle.png"));
   Image sStar = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//SuperStar.gif"));
   Image bowserGround = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bowserGround.png"));	
   Image starStandingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starStandingMario.gif"));		
   Image starLStandingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starLStandingMario.gif"));		
   Image starJumpingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starJumpingMario.gif"));		
   Image starLJumpingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starLJumpingMario.gif"));		
   Image starRunningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starRunningMario.gif"));		
   Image starLRunningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starLRunningMario.gif"));		
   Image starDuckingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starDuckingMario.gif"));		
   Image starLDuckingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starLDuckingMario.gif"));		
   Image starSlidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starLSlidingMario.gif"));		
   Image starLSlidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starSlidingMario.gif"));		
   Image starBigStandingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBigStandingMario.gif"));		
   Image starBigLStandingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBigLStandingMario.gif"));		
   Image starBigJumpingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBigJumpingMario.gif"));		
   Image starBigLJumpingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBigLJumpingMario.gif"));		
   Image starBigRunningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBigRunningMario.gif"));		
   Image starBigLRunningMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBigLRunningMario.gif"));		
   Image starBigDuckingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBigDuckingMario.gif"));		
   Image starBigLDuckingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBigLDuckingMario.gif"));		
   Image starBigLSlidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBigSlidingMario.gif"));		
   Image starBigSlidingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//starBiglSlidingMario.gif"));
   Image gameoverScreen = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//gameover.png"));
   Image flagPole = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//FlagPole.png"));
   Image flag = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//flag.png"));
   Image fireFlagMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//fireFlagMario.gif"));
   Image bigFlagMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigFlagMario.gif"));
   Image lBigFlagMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lBigFlagMario.gif"));
   Image lFireFlagMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lFireFlagMario.gif"));
   Image breakingBlock = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//BreakingBlock.gif"));
   Image uBreakingBlock = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//undergroundBreakingBlock.gif"));
   Image croppedStar = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//StarCropped.gif"));
   Image flagMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//FlagMario.gif"));
   Image axeImage = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Axe.png"));
   Image fireDuckingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//fireDuckingMario.png"));
   Image fireLDuckingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//lFireDuckingMario.png"));
   Image koopaComingOut = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//KoopaComingOut.png"));

   long flagPoleTime =0;
   Image instructions = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Instructions.png"));
   Image bowserCeiling = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bowserCeiling.png"));
   Image bowserGroundOnly = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bowserGroundOnly.png"));
   Image bowserCeilingOnly = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bowserCeilingOnly.png"));
   Image duckingMario = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bigDuckingMario.png"));
   Image peach = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Peach.png"));
   
   Image piranha = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//PiranhaPlant.gif"));
   JLabel livesText = new JLabel(Integer.toString(lives));
   Sound overWorld = new Sound("Sounds//overworld.wav");
   Sound underGround = new Sound("Sounds//underground.wav");
   Sound levelClearSound = new Sound("Sounds//levelclear.wav");
   Sound superStarSound = new Sound("Sounds//superstaritem.wav");
   Sound ending = new Sound("Sounds//ending.wav");
   Font customFont,scoreFont;
   Sound hurryOverWorldSound = new Sound("Sounds//hurryoverworld.wav");
   Sound bowserLevelSound =  new Sound("Sounds//bowserscastle.wav");   
   Host host = new Host();
   Image currentMario = standingMario;
   AudioClip bigJumpSound,oneUpSound,bowserDeathSound,bowserFireBallSound,brickBreakSound,fireballSound,flagpoleSound,collisionSound,enemyKilledSound,pipeSound,powerUpSound,powerUpQBlockSound,smallJumpSound,smallBrickSound,endingSound,gameOverSound,loseLifeSound,overWorldSound,underGroundSound,coinSound;
   Camera cam = new Camera(0,0);
   int[] inBlockResults;
   /*** onePlayerGame ************************************
      * Purpose: to construct a new game  
      * Parameters: 
      *  - fileName | gets file name of level text file
      *  - e | the environment type that mario is in
      *  - marioType | the state that mario is in
      *  - l | lives variable
      *  - c | coins variable
      *  - t | time variable
      *  - s | score variable
      *  - prevX |
      *  - goUp |                                    
      * Returns: none                                       *
      ******************************************************/
   public onePlayerGame(String fileName, int e, int marioType, int l, int c, int t, long s,int prevX,boolean goUp)
   {
      startTime = secondsLeft;
      currentLevel = fileName;
      pipePrevX = prevX;
      try{
         bigJumpSound =  Applet.newAudioClip(new File("Sounds//bigmariojump.wav").toURI().toURL());
         oneUpSound =  Applet.newAudioClip(new File("Sounds//1up.wav").toURI().toURL());
         bowserDeathSound =  Applet.newAudioClip(new File("Sounds//bowserfallsintofirepit.wav").toURI().toURL());
         bowserFireBallSound =  Applet.newAudioClip(new File("Sounds//bowserfireball.wav").toURI().toURL());
         brickBreakSound =  Applet.newAudioClip(new File("Sounds//breakbrick.wav").toURI().toURL());
         coinSound =  Applet.newAudioClip(new File("Sounds//coin.wav").toURI().toURL());
         fireballSound = Applet.newAudioClip(new File("Sounds//fireball.wav").toURI().toURL());
         flagpoleSound = Applet.newAudioClip(new File("Sounds//flagpole.wav").toURI().toURL());
         collisionSound = Applet.newAudioClip(new File("Sounds//kickshellANDfireballcollision.wav").toURI().toURL());
         enemyKilledSound = Applet.newAudioClip(new File("Sounds//mariostomp(goomba or koopa).wav").toURI().toURL());
         pipeSound = Applet.newAudioClip(new File("Sounds//pipe.wav").toURI().toURL());
         powerUpSound = Applet.newAudioClip(new File("Sounds//powerup.wav").toURI().toURL());
         smallJumpSound = Applet.newAudioClip(new File("Sounds//smallmariojump.wav").toURI().toURL());
         smallBrickSound = Applet.newAudioClip(new File("Sounds//smallmariotryingtobreakbrick.wav").toURI().toURL());
         powerUpQBlockSound = Applet.newAudioClip(new File("Sounds//powerupQblock.wav").toURI().toURL());
            
         endingSound =  Applet.newAudioClip(new File("Sounds//ending.wav").toURI().toURL());
         gameOverSound = Applet.newAudioClip(new File("Sounds//gameover.wav").toURI().toURL());
         loseLifeSound = Applet.newAudioClip(new File("Sounds//loselife.wav").toURI().toURL());
      }      catch(MalformedURLException f){
      }
      
      try {
      //create the font to use. Specify the size!
         customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Font//mario.ttf")).deriveFont(24f);
         scoreFont = Font.createFont(Font.TRUETYPE_FONT, new File("Font//mario.ttf")).deriveFont(18f);
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
         //register the font
         ge.registerFont(customFont);
         ge.registerFont(scoreFont);
      } catch (IOException f) {
         f.printStackTrace();
      } catch(FontFormatException f) {
         f.printStackTrace();
      }
      if (marioType==2){
         bigMario = true;
         currentMario = bigStandingMario;
         width = 45;
         height = 90;
      }
      else if(marioType ==3){
         fireFlowerMario = true;
         currentMario = fireStandingMario;
         width = 45;
         height = 90;
      }
      
      lives = l;
      CoinCount = c;		
      secondsLeft = t;		
      score = s;
      
      if (currentLevel.equals("Levels//Levels//Level2.txt")){
         instructions = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
      }
      if (e == 1){
         cam.camOn(true);
         ground = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//ground.png")); 
         oneGame.getContentPane().setBackground(new Color(98, 133, 249));
         brick = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//Brick.png"));
         cloud = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//cloud.png"));
         scloud = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//scloud.png"));
         hill = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//hill.png"));
         bush = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bush.png"));
         overWorld.play();
         environment = 1;
      }
      else if (e == 2){
         cam.camOn(false);
         oneGame.getContentPane().setBackground(Color.black);
         ground = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//uground.png"));  
         brick = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//ubrick.png"));
         instructions = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         cloud = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         scloud = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         hill = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         bush = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         breakingBlock = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//undergroundBreakingBlock.gif"));
         underGround.play();
         environment = 2;
      }
      else if (e == 3){
         oneGame.getContentPane().setBackground(Color.black);
         ground = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bowserGround.png"));
         Pipe = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bowserPipe.png"));
         brick = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource("Images//bowserBrick.png"));
         instructions = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         cloud = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         scloud = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         hill = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         bush = Toolkit.getDefaultToolkit().createImage(onePlayerGame.class.getResource(""));
         breakingBlock = uBreakingBlock;
         environment = 3;
         bowserLevelSound.play();
      }   
      getItems(fileName);
      host.start();
      oneGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      oneGame.setVisible(true);
      oneGame.setSize(900,614);
      oneGame.addKeyListener(this);
      oneGame.add(draw,"Center");
      if(goUp){
         draw.paintImmediately(oneGame.getBounds());
         p = getGoingUpPipe(prevX,Pipes,Pipes.length);
         
         if(p!= null){
            x = p.x+10;
            y = 485;
            comingUp = true;
         }
      }
      fall();
      oneGame.setResizable(false);
      
      
   }
   class Drawing extends JComponent
   {
      /*** paint ********************************************
      * Purpose: Prints all the images in the game          *
      * Parameters: g - Graphics object for drawing         *
      * Returns: none                                       *
      ******************************************************/
      public void paint (Graphics g)
      {
         
         if(!gameover&&!endLevel&&!endingLevel){
            if(comingUp&&y>p.y-height+1){
               y-=1;
               
               cam.center(x,y);
               g.drawImage(currentMario,(int)(x-cam.getxOffset()),y,this);
               
            }
            else{
               comingUp = false;
            }
            
            g.setColor(new Color(98, 133, 249));
            if(goingDownPipe||endLevel){
               g.drawImage(currentMario,(int)(x-cam.getxOffset()),y,this);
            }
            g.drawImage(instructions,-250-(int)cam.getxOffset(),100,this);
            g.drawImage(bush,150-(int)cam.getxOffset(),439,this);
            g.drawImage(hill,600-(int)cam.getxOffset(),394,this);
            g.drawImage(cloud,450-(int)cam.getxOffset(),70,this);
            g.drawImage(cloud,900-(int)cam.getxOffset(),150,this);
            g.drawImage(bush,1000-(int)cam.getxOffset(),439,this);
            g.drawImage(scloud,1200-(int)cam.getxOffset(),50,this);
            g.drawImage(cloud,1600-(int)cam.getxOffset(),80,this);
            g.drawImage(bush,1900-(int)cam.getxOffset(),439,this);
            g.drawImage(scloud,2250-(int)cam.getxOffset(),60,this);
            g.drawImage(cloud,2500-(int)cam.getxOffset(),50,this);
            g.drawImage(hill,2650-(int)cam.getxOffset(),400,this);
            g.drawImage(scloud,2850-(int)cam.getxOffset(),50,this);
            g.drawImage(scloud,3500-(int)cam.getxOffset(),60,this);
            g.drawImage(scloud,4100-(int)cam.getxOffset(),75,this);
            g.drawImage(scloud,6000-(int)cam.getxOffset(),50,this);
            g.drawImage(scloud,8100-(int)cam.getxOffset(),50,this);
            g.drawImage(cloud,4900-(int)cam.getxOffset(),60,this);
            g.drawImage(cloud,5500-(int)cam.getxOffset(),40,this);
            g.drawImage(cloud,7000-(int)cam.getxOffset(),70,this);
            g.drawImage(bush,3300-(int)cam.getxOffset(),439,this);
            g.drawImage(bush,4500-(int)cam.getxOffset(),439,this);				
            g.drawImage(bush,7500-(int)cam.getxOffset(),439,this);				
            g.drawImage(bush,8700-(int)cam.getxOffset(),439,this);		
            g.drawImage(hill,3600-(int)cam.getxOffset(),425,this);		
            g.drawImage(hill,3800-(int)cam.getxOffset(),400,this);				
            g.drawImage(hill,5900-(int)cam.getxOffset(),425,this);
            g.drawImage(hill,5500-(int)cam.getxOffset(),400,this);		
            g.drawImage(hill,8000-(int)cam.getxOffset(),394,this);
            
            
            if (environment == 3){
               g.drawImage(bowserCeiling,450-(int)cam.getxOffset(),100,this);
               g.drawImage(bowserCeilingOnly,-300-(int)cam.getxOffset(),100,this);
               g.drawImage(bowserCeilingOnly,1419-(int)cam.getxOffset(),100,this);
               g.drawImage(bowserGroundOnly,-400-(int)cam.getxOffset(),485,this);
               g.drawImage(bowserGroundOnly,1835-(int)cam.getxOffset(),485,this);
               g.drawImage(bowserGroundOnly,2364-(int)cam.getxOffset(),485,this);
            }
            g.setFont(customFont);
            g.setColor(new Color(98, 250, 249));
            
            for (int i = 0; i<stairCases.length;i++){
               if(stairCases[i].reversed == false)
                  g.drawImage(stairCase,(int)(stairCases[i].x-cam.getxOffset()+5),stairCases[i].y,this);
               else 
                  g.drawImage(reverseStairCase,(int)(stairCases[i].x-cam.getxOffset()+5),stairCases[i].y,this);
            
               
            }
            
            if(countBlockPush == 10)
               blockDirection = -1;
            else if(blockPush == -1){
               blockDirection = 0;
            }
            
            	
            g.setColor(Color.white);
            	
            if (System.currentTimeMillis() - timer >= 1000 && !endingGame&&!onFlagPole){		
               secondsLeft -= 1;		
               timer = System.currentTimeMillis();		
               if(currentLevel.equals("Levels//Level1.txt")||currentLevel.equals("Levels//Level2.txt")){
                  if(secondsLeft>100 && secondsLeft == startTime - (180*timesPlayed)){		
                     overWorld.stop();		
                     overWorld.play();	
                     timesPlayed++;	
                  }		
                  if(secondsLeft == 100){		
                     overWorld.stop();		
                     hurryOverWorldSound.play();		
                  } else if (secondsLeft == 0){
                     overWorld.stop();
                     underGround.stop();
                     die();
                  }
               }		
            }		
            if (currentLevel.equals("Levels//Level1.txt") || currentLevel.equals("Levels//underground.txt")){
               g.drawString("1-1", 427,70);
            } else if(currentLevel.equals("Levels//Level2.txt") || currentLevel.equals("Levels//bowser.txt")){
               g.drawString("8-4", 427,70);
            }
            if (CoinCount <= 9){		
               g.drawString(Integer.toString(CoinCount),250,70);
            } else if (CoinCount > 9){
               g.drawString(Integer.toString(CoinCount),240,70);
            }
            if (secondsLeft > 99){		
               g.drawString(Integer.toString(secondsLeft), 610, 70);
            } else if (secondsLeft < 100 && secondsLeft > 9){
               g.drawString(Integer.toString(secondsLeft), 620, 70);
            } else if (secondsLeft < 10){
               g.drawString(Integer.toString(secondsLeft), 635,70);
            }	
            	
            g.drawString(Integer.toString(lives),815,70);
            if (score <= 9){		
               g.drawString(Long.toString(score),52,70);
            } else if (score <= 99 && score >= 10){
               g.drawString(Long.toString(score),42,70);
            } else if (score <= 999 && score >= 100){
               g.drawString(Long.toString(score),30,70);
            } else if (score <= 9999 && score >= 1000){
               g.drawString(Long.toString(score),15,70);
            } else if (score <= 99999 && score >= 10000){
               g.drawString(Long.toString(score),5,70);
            }   
            g.drawString(Integer.toString(lives),815,70); 
            g.setColor(new Color(98, 133, 249));
            for (int i = 0; i<holes.length;i++){
               g.fillRect((int)(holes[i].x-cam.getxOffset()), holes[i].y, holes[i].width, holes[i].height);
            }
            for (int i = 0; i<powerUps.length;i++){
               if (powerUps[i] !=null && powerUps[i].gotten == false){
                  if(powerUps[i].getClass() == Mushroom.class)
                     g.drawImage(mushroom,((int)(powerUps[i].x - cam.getxOffset())),powerUps[i].y,this); //Draws all the bricks
                  else if(powerUps[i].getClass() == OneUp.class)
                     g.drawImage(oneUp,((int)(powerUps[i].x - cam.getxOffset())),powerUps[i].y,this); //Draws all the bricks
                  else if(powerUps[i].getClass() == FireFlower.class)
                     g.drawImage(fireFlower,((int)(powerUps[i].x - cam.getxOffset())),powerUps[i].y,this); //Draws all the bricks
                  else if(powerUps[i].getClass() == SuperStar.class)		
                     g.drawImage(sStar,((int)(powerUps[i].x - cam.getxOffset())),powerUps[i].y,this); //Draws all the bricks
               }
            }
            for(int i = 0; i<coins.length;i++){
               if(coins[i].shouldGetCoin(x,y,width,height)&&coins[i].gotten == false){
                  coins[i].gotten = true;
                  coinSound.play();
                  CoinCount++;		
                  score+=200;
                  if(CoinCount == 100){
                     lives++;
                     CoinCount = 0;
                  }
               }
               if(coins[i].gotten == false) //If coins have not already been gotten
                  g.drawImage(coin,((int)(coins[i].x - cam.getxOffset())),coins[i].y,this); //Draws all the coins
                  
            }
            for (int i = 0; i<piranhas.length;i++){
            
               if(piranhas[i].gotten == true){
                  if(piranhas[i].killScore.showing == false&&piranhas[i].killScore.showed == false){
                     scoreInt = 200;
                     score+= scoreInt;
                  }
                  if(piranhas[i].killScore.showing == false &&piranhas[i].killScore.showed == false){
                     piranhas[i].killScore = new Score(piranhas[i].x,piranhas[i].y+20,scoreInt);
                  }
                     
                  piranhas[i].killScore.updatePosition();
                     
                  if(piranhas[i].killScore.showing == true){
                     g.setColor(Color.WHITE);
                     g.setFont(scoreFont);
                     g.drawString(piranhas[i].killScore.score,(int)(piranhas[i].killScore.x-cam.getxOffset()),(int)piranhas[i].killScore.y);
                  }
               }
               else{
                  g.drawImage(piranha,((int)(piranhas[i].x - cam.getxOffset()))-5,piranhas[i].y,this);
               }
            }
            for (int i = 0; i<Pipes.length;i++){
               
               g.drawImage(Pipe,((int)(Pipes[i].x - cam.getxOffset())),Pipes[i].y,this); //Draws all the bricks
                  
            }
            g.drawImage(ground,(int)(0-cam.getxOffset()),485,this);
            g.drawImage(ground,(int)(4096-cam.getxOffset()),485,this);
            g.drawImage(ground,(int)(-2000-cam.getxOffset()),485,this);
            g.drawImage(ground,(int)(8000-cam.getxOffset()),485,this);
            for (int i = 0; i<holes.length;i++){
               g.fillRect((int)(holes[i].x-cam.getxOffset()), holes[i].y, holes[i].width, holes[i].height);
            }
            for (int i = 0; i<enemies.length;i++){
               if (enemies[i].getClass() == Goomba.class)
               {
                  if (enemies[i].gotten == false) //If goomba have not already been gotten
                     g.drawImage(goomba,(int) (enemies[i].x-cam.getxOffset()), enemies[i].y, this);
                  else{
                     if(checkRecentKill(enemies,enemies.length,goombaToBeKilled)&&enemies[i].killScore.showing == false&&enemies[i].killScore.showed == false){
                        scoreInt*=2;
                        score += scoreInt;
                     }
                     else if(enemies[i].killScore.showing == false&&enemies[i].killScore.showed == false){
                        scoreInt = 100;
                        score += scoreInt;
                     }
                     if(enemies[i].killScore.showing == false &&enemies[i].killScore.showed == false){
                        
                        enemies[i].killScore = new Score(enemies[i].x,enemies[i].y+20,scoreInt);
                     }
                     
                     enemies[i].killScore.updatePosition();
                     
                     if(enemies[i].killScore.showing == true){
                        g.setColor(Color.WHITE);
                        g.setFont(scoreFont);
                        g.drawString(enemies[i].killScore.score,(int)(enemies[i].killScore.x-cam.getxOffset()),(int)enemies[i].killScore.y);
                     }
                     if(System.currentTimeMillis()-enemies[i].deathTime<1000&&enemies[i].killedByKoopa!=true){
                        g.drawImage(deadGoomba, (int)(enemies[i].x-cam.getxOffset()),enemies[i].y+17,this);
                     }
                     else if (enemies[i].killedByKoopa&&enemies[i].y<650){
                        enemies[i].y+=Math.round(0.2*(enemies[i].killedByKoopaCount));
                        g.drawImage(uGoomba, (int)(enemies[i].x-cam.getxOffset()),enemies[i].y,this);
                        enemies[i].killedByKoopaCount += 0.15;
                     }
                  }
               }
               else if (enemies[i].getClass() == Koopa.class)
               {
                  if (enemies[i].shell==true&&!enemies[i].killedByKoopa&&!enemies[i].comingBack){ //If koopa have not already been gotten
                     
                     if(checkRecentKill(enemies,enemies.length,i)&&enemies[i].killScore.showing == false&&enemies[i].killScore.showed == false){
                        scoreInt*=2;
                        score+= scoreInt;
                     }
                     else if(enemies[i].killScore.showing == false&&enemies[i].killScore.showed == false){
                        scoreInt = 100;
                        score+= scoreInt;
                     }
                     if(enemies[i].killScore.showing == false &&enemies[i].killScore.showed == false){
                        enemies[i].killScore = new Score(enemies[i].x,enemies[i].y+20,scoreInt);
                     }
                     
                     enemies[i].killScore.updatePosition();
                     
                     if(enemies[i].killScore.showing == true){
                        g.setColor(Color.WHITE);
                        g.setFont(scoreFont);
                        g.drawString(enemies[i].killScore.score,(int)(enemies[i].killScore.x-cam.getxOffset()),(int)enemies[i].killScore.y);
                     }
                     
                     g.drawImage(koopaShell,(int) (enemies[i].x-cam.getxOffset()), enemies[i].y+14, this);
                  }
                  else if (enemies[i].gotten == false&&enemies[i].dir>0&&enemies[i].killedByKoopa == false&&!enemies[i].comingBack){ //If koopa have not already been gotten
                     g.drawImage(koopa,(int) (enemies[i].x-cam.getxOffset()), enemies[i].y-15, this);
                  }
                  else if (enemies[i].gotten == false&&enemies[i].dir<0&&enemies[i].killedByKoopa == false&&!enemies[i].comingBack){ //If koopa have not already been gotten
                     g.drawImage(leftKoopa,(int) (enemies[i].x-cam.getxOffset()), enemies[i].y-15, this);
                  }
                  else if(enemies[i].killedByKoopa&&enemies[i].y<650){
                    
                     if(checkRecentKill(enemies,enemies.length,goombaToBeKilled)&&enemies[i].killScore.showing == false&&enemies[i].killScore.showed == false){
                        scoreInt*=2;
                     }
                     else if(enemies[i].killScore.showing == false&&enemies[i].killScore.showed == false){
                        scoreInt = 100;
                     }
                     if(enemies[i].killScore.showing == false &&enemies[i].killScore.showed == false){
                        enemies[i].killScore = new Score(enemies[i].x,enemies[i].y+20,scoreInt);
                     }
                     
                     enemies[i].killScore.updatePosition();
                     
                     if(enemies[i].killScore.showing == true){
                        g.setColor(Color.WHITE);
                        g.setFont(scoreFont);
                        g.drawString(enemies[i].killScore.score,(int)(enemies[i].killScore.x-cam.getxOffset()),(int)enemies[i].killScore.y);
                     }
                     
                     enemies[i].y+=Math.round(0.2*(enemies[i].killedByKoopaCount));
                     g.drawImage(uKoopa, (int)(enemies[i].x-cam.getxOffset()),enemies[i].y,this);
                     enemies[i].killedByKoopaCount += 0.15;
                     
                  }
                  else if (enemies[i].comingBack){
                     g.drawImage(koopaComingOut, (int)(enemies[i].x-cam.getxOffset()),enemies[i].y,this);
                  }
               }
               
               
            }
            
            for (int i = 0; i<blocks.length;i++)
            {
               
               if(blocks[i].getClass() == QuestionBlock.class){
                  if(blocks[i].getting == true){
                     if(blocks[i].holds ==1||blocks[i].holds == 5){//change
                        
                        
                        if(blocks[i].getCoin){
                           blocks[i].coinY += -0.41*blocks[i].coinCount;
                           g.drawImage(spinCoin, (int)(blocks[i].x-cam.getxOffset()+7),blocks[i].y-(int)blocks[i].coinY,this);//change
                           blocks[i].coinCount += 0.2;
                        }
                        if(blocks[i].coinCount > 10){
                           blocks[i].coinY = 0;
                           blocks[i].getCoin = false;
                        }
                        
                        //draw coin animation and add one to score
                     }
                     if(blocks[i].holds ==2){//change
                        if(fireFlowerMario == false&&bigMario == false){
                           if(blocks[i].rewardCount>20){
                              g.drawImage(mushroom, (int)(blocks[i].x-cam.getxOffset()),blocks[i].y-Math.round(blocks[i].rewardCount/4),this);//change
                           }
                           else{
                              g.drawImage(croppedMushroom, (int)(blocks[i].x-cam.getxOffset()),blocks[i].y-Math.round(blocks[i].rewardCount/4),this);//change
                           }
                           if (blocks[i].rewardCount == 140){
                              blocks[i].getting = false;
                              for (int j = 0;j<powerUps.length;j++){//change if coin
                                 if(powerUps[j] == null){  
                                    powerUps[j] = new Mushroom(blocks[i].x,blocks[i].y-36);
                                    break;
                                 }
                              }
                           }
                        }
                        else{
                           if(blocks[i].rewardCount>20){
                              g.drawImage(fireFlower, (int)(blocks[i].x-cam.getxOffset()),blocks[i].y-Math.round(blocks[i].rewardCount/4),this);//change
                           }
                           else{
                              g.drawImage(croppedFireFlower, (int)(blocks[i].x-cam.getxOffset()),blocks[i].y-Math.round(blocks[i].rewardCount/4),this);//change
                           }
                           if (blocks[i].rewardCount == 140){
                              blocks[i].getting = false;
                              for (int j = 0;j<powerUps.length;j++){//change if coin
                                 if(powerUps[j] == null){  
                                    powerUps[j] = new FireFlower(blocks[i].x,blocks[i].y-36);
                                    break;
                                 }
                              }
                           }
                        }
                     }
                     if(blocks[i].holds ==3){//change
                        if(blocks[i].rewardCount>20){
                           g.drawImage(sStar, (int)(blocks[i].x-cam.getxOffset()),blocks[i].y-Math.round(blocks[i].rewardCount/4),this);//change
                        }
                        else{
                           g.drawImage(croppedStar, (int)(blocks[i].x-cam.getxOffset()),blocks[i].y-Math.round(blocks[i].rewardCount/4),this);//change
                        }
                        if (blocks[i].rewardCount == 140){
                           blocks[i].getting = false;
                           for (int j = 0;j<powerUps.length;j++){//change if coin
                              if(powerUps[j] == null){  
                                 powerUps[j] = new SuperStar(blocks[i].x,blocks[i].y-36);
                                 break;
                              }
                           }
                        }
                     }
                     if(blocks[i].holds ==4){//change
                        if(blocks[i].rewardCount>20){
                           g.drawImage(oneUp, (int)(blocks[i].x-cam.getxOffset()),blocks[i].y-Math.round(blocks[i].rewardCount/4),this);//change
                        }
                        else{
                           g.drawImage(croppedOneUp, (int)(blocks[i].x-cam.getxOffset()),blocks[i].y-Math.round(blocks[i].rewardCount/4),this);//change
                        }
                        if (blocks[i].rewardCount == 140){
                           blocks[i].getting = false;
                           for (int j = 0;j<powerUps.length;j++){//change if coin
                              if(powerUps[j] == null){  
                                 powerUps[j] = new OneUp(blocks[i].x,blocks[i].y-36);
                                 break;
                              }
                           }
                        }                  }
                     blocks[i].rewardCount++;
                  }
               }
               
               if(blocks[i].getClass() == block.class || blocks[i].getClass()==QuestionBlock.class&&blocks[i].obtained == false){
                  if(blocks[i].gotten ==false){
                     if (blockPush == i){
                        if(blocks[i].getClass() == block.class){
                           g.drawImage(brick,((int)(blocks[i].x - cam.getxOffset())),blocks[i].y-countBlockPush,this);
                        }
                        else if (blocks[i].holds!=4&&blocks[i].holds!=5){
                           g.drawImage(qBlock,((int)(blocks[i].x - cam.getxOffset())),blocks[i].y-countBlockPush,this);
                        }
                        else{
                           g.drawImage(brokenQBlock,((int)(blocks[i].x - cam.getxOffset())),blocks[i].y-countBlockPush,this);
                        }
                        countBlockPush += blockDirection;
                        if (countBlockPush ==0){
                           if(blocks[i].getClass() == QuestionBlock.class)
                              blocks[i].obtained = true;
                           blockPush = -1;
                        }
                     }
                        
                        
                     else if (blocks[i].getClass() == block.class){
                        g.drawImage(brick,((int)(blocks[i].x - cam.getxOffset())),blocks[i].y,this); //Draws all the bricks
                     }
                     else if (blocks[i].getClass() == QuestionBlock.class){
                        if (blocks[i].obtained==false&&blocks[i].holds!=4&&blocks[i].holds!=5)
                           g.drawImage(qBlock,((int)(blocks[i].x - cam.getxOffset())),blocks[i].y,this); //Draws all the bricks
                        else if(blocks[i].holds!=4&&blocks[i].holds!=5)
                           g.drawImage(brokenQBlock,((int)(blocks[i].x - cam.getxOffset())),blocks[i].y,this); //Draws all the bricks
                        
                     }
                  }
                  else {
                     if(blocks[i].updateBreaking()){  
                        g.drawImage(blocks[i].image,((int)(blocks[i].x-35 - cam.getxOffset())),blocks[i].y+35,this); //Draws all the bricks
                     }      
                  }
                  
                  
               }
               else if (blocks[i].getClass() == QuestionBlock.class){
                  if (blocks[i].obtained==false)
                     g.drawImage(qBlock,((int)(blocks[i].x - cam.getxOffset())),blocks[i].y,this); //Draws all the bricks
                  else
                     g.drawImage(brokenQBlock,((int)(blocks[i].x - cam.getxOffset())),blocks[i].y,this); //Draws all the bricks
                  
               }
                 
               
               
               
               else if(blocks[i].getClass() == Castle.class){
                  g.drawImage(castle,(int)(blocks[i].x-142-cam.getxOffset()),blocks[i].y-200,this); //Draws Mario
                  
                  
               }
               
               else if(blocks[i].getClass() == FlagPole.class){
                  g.drawImage(flagPole,(int)(blocks[i].x-cam.getxOffset())-28,blocks[i].y,this);
                  g.drawImage(flag,(int)(blocks[i].flagX-cam.getxOffset())-28,blocks[i].flagY,this);
               }
               
            }
            
            
            for (int i = 0; i<3; i++){
               if(fireballs[i]!= null && fireballs[i].active == true){
                  g.drawImage(fireball, (int)(fireballs[i].x-cam.getxOffset()),fireballs[i].y,this);
               }
            }
            if(axe!=null){
               g.drawImage(axeImage,(int)(axe.x-cam.getxOffset()),axe.y,this); //Draws Mario
            }
         
            g.drawImage(font,0,20,this); 
            if (dead == false&&goingDownPipe == false&&comingUp == false&&!endLevel) //If mario is not dead
               g.drawImage(currentMario,(int)(x-cam.getxOffset()),y,this); //Draws Mario
            
            
         }
            
         else if(endLevel&&!gameover){
            if (currentLevel.equals("Levels//Level1.txt")){
               oneGame.setVisible(false);
               new onePlayerGame("Levels//Level2.txt",1,marioType,lives,CoinCount,400,score,x,false);
               host.timer1.stop();
               oneGame.dispose();
            } else if (currentLevel.equals("Levels//Level2.txt")){
               oneGame.setVisible(false);
               new onePlayerGame("Levels//bowser.txt",3,marioType,lives,CoinCount,secondsLeft,score,prevX,true);
               host.timer1.stop();
               
               oneGame.dispose();
            }
         }
         else if(gameover){
            if(System.currentTimeMillis() - gameoverTime<10000)            
               g.drawImage(gameoverScreen,-175,-100,this);
            else{
               new onePlayerGame("Levels//Level1.txt",1,1,3,0,400,0,0,false);
               host.timer1.stop();
               oneGame.dispose();	
               
            }
         }
         if(onFlagPole){
            if(System.currentTimeMillis()-flagPoleTime>200){
                  
               try{
                  Thread.sleep(15);
               } catch (InterruptedException u){}
               if(y<455-height)
                  y+=2;
               if(blocks[blocks.length-2].flagY<blocks[blocks.length-2].y+251)
                  blocks[blocks.length-2].flagY+=2;
               blocks[blocks.length-2].killScore.updatePosition();
               
               g.setColor(Color.WHITE);
               g.setFont(scoreFont);
               if(blocks[blocks.length-2].killScore.showed == false){
                  g.drawString(blocks[blocks.length-2].killScore.score,(int)(blocks[blocks.length-2].killScore.x-cam.getxOffset()),(int)blocks[blocks.length-2].killScore.y);
               }
            }
            if(y>=455-height&&blocks[blocks.length-2].flagY>=blocks[blocks.length-2].y+251){
               if(flipFlagPole){
                  left = false;
                  right = false;
                  rightFacing = false;
                  leftFacing = false;
                  stopAccel = true;
                     
                  x+=width+5;
                  flipFlagPole = false;
                     
                     
                     
                     
                  try{
                     Thread.sleep(200);
                  }
                  catch (InterruptedException u){}
                  deathJumpTime =0;
                  flagPoleJump();
                     
                     
               }
                  
            }
         }
         else if(endingGame){
                       
               //endingScene
            g.setColor(Color.black);
            if (bigMario){
               currentMario = bigStandingMario;
               y-= 45;
            }
            else if (fireFlowerMario){
               currentMario = fireStandingMario;
               y-=45;
            }
            else
               currentMario = standingMario;
            axeImage = Toolkit.getDefaultToolkit().createImage("");
            brickBreakSound.play();
            g.fillRect(1410-(int)cam.getxOffset(),450,85,100);
            if (System.currentTimeMillis()-bridge > 1000){
               g.fillRect(1325-(int)cam.getxOffset(),450,85,100);
            }
            if (System.currentTimeMillis()-bridge > 2000){
               g.fillRect(1240-(int)cam.getxOffset(),450,85,100);
            }
            if (System.currentTimeMillis()-bridge > 3000){
               g.fillRect(1149-(int)cam.getxOffset(),450,100,100);
               brickBreakSound.stop();
            }
            if (System.currentTimeMillis()-bridge > 4000)
               bowser.y += 5;
            if (bowser.y >= 510 && bowser.y <= 515){
               bowserDeathSound.play();
               bowserLevelSound.stop();
               ending.play();
            }
            if (y != 485-height){
               for(int i = 0; i < 485-height-y; i++){
                  y += i;
               }
            }
            if (System.currentTimeMillis()-bridge > 7000){
               if (x < 2000){
                  if (bigMario)
                     currentMario = bigRunningMario;
                  else if (fireFlowerMario)
                     currentMario = fireRunningMario;
                  else
                     currentMario = runningMario;
                  x += 5;
                  cam.camOn(true);
                  cam.center(x,y);
               }else{
                  g.setColor(Color.white);
                  if (bigMario)
                     currentMario = bigStandingMario;
                  else if (fireFlowerMario)
                     currentMario = fireStandingMario;
                  else
                     currentMario = standingMario;
                  g.drawString("Thank you Mario!",275,250); 
                  g.drawString("HI-SCORE - "+ score, 275,350);
                  setHighScore(score);
               } 
               if(System.currentTimeMillis()-bridge >25000){
               
                  new Mario();
                  ending.stop();
                  oneGame.dispose();
                  host.timer1.stop(); 
               } 
            }
            g.drawImage(currentMario,x-(int)cam.getxOffset(),y,this);   
            score += secondsLeft*100;
            secondsLeft = 0;
            g.drawImage(peach,2050-(int)cam.getxOffset(),417,this);
               
               
         }  
         if(bowser!=null){//start
            if(!endingGame)
               bowserImages = bowser.update(x,y,width,height,maxPos,blocks,blocks.length,1149-cam.getxOffset());
            for (int i = 0; i <6;i++){
               
               if(bowserImages[i]!=null)
                  g.drawImage(bowserImages[i].image, (int)(bowserImages[i].x-cam.getxOffset()),bowserImages[i].y,this);
            }
            for (int i = 0; i<3; i++){
               if(fireballs[i]!= null && fireballs[i].active == true){
                  g.drawImage(fireball, (int)(fireballs[i].x-cam.getxOffset()),fireballs[i].y,this);
               }
            }
         }//end
      
            
      }  		
            
               
            	
            
         
      
   }
   /*** flagPoleJump ********************************************
      * Purpose: Draws the going down flagpole and entering castle scene          *
      * Parameters: none         *
      * Returns: none                                       *
      ******************************************************/
   public void flagPoleJump(){
      flagpoleSound.stop();
      levelClearSound.play();
      if(starMario){
         if(bigMario){
                  
         }
         else{
                  
         }
      }
      else if (fireFlowerMario){
         currentMario = lFireJumpingMario;
      }
      else if(bigMario){
         currentMario = bigLJumpingMario;
      }
      else{
         currentMario = lJumpingMario;
      }
         
      while(y<485-height){
         y+=Math.round((deathJumpTime-5)*2);
         draw.paintImmediately(oneGame.getBounds());
         deathJumpTime+=0.3;
         x+=3;
         try{
            Thread.sleep(15);
         } catch(InterruptedException e){}
         
      }
      if(starMario){
         if(bigMario){
                  
         }
         else{
                  
         }
      }
      else if (fireFlowerMario){
         currentMario = fireRunningMario;
      }
      else if(bigMario){
         currentMario = bigRunningMario;
      }
      else{
         currentMario = runningMario;
      }
      while(!endLevel){
         x+=3;
         draw.paintImmediately(oneGame.getBounds());
         try{
            Thread.sleep(15);
         }
         catch (InterruptedException e){}
         inBlockResults = checkInBlock(blocks,blocks.length,10);
      }
      
      try{
         Thread.sleep(3000);
      }
      catch(InterruptedException e){}
      
   }
   /*** keyPressed ***************************************
      * Purpose: for each button pressed there are certain  *
      * actions that need to be performed                   *
      * Parameters: e - the details on the button pressed   *
      * Returns: none                                       *
      ******************************************************/
   public void keyPressed(KeyEvent e)
   {
      if (e.getKeyCode() == KeyEvent.VK_RIGHT&&!duckCheck)
      {
         if (goingLeft == false && jump == false &&  System.currentTimeMillis() - lReleaseTime <100||left){ //If should slide, then slide
            slide(true);
         }
         if (rAccelerate == false)  //If not accelerating, accelerate     
         {       
            tAccel = System.currentTimeMillis();   
            rAccelerate = true;        
         }
       
         //System.out.println("right key pressed");
       
         right = true; 
         leftFacing = false;
         rightFacing = true;
         updateMario();
         goingLeft = true;
      }
      else if (e.getKeyCode() == KeyEvent.VK_SHIFT){
         if (((fireFlowerMario&&bigStarMario)||fireFlowerMario)&&shiftKey == false){
            shiftKey = true;
            for (int i = 0;i<3;i++){
               if (fireballs[i] == null || fireballs[i].active == false){
                  if(rightFacing||(!left&&right)){
                     fireballs[i] = new Fireball(x+width,y+45,1);
                     //System.out.println("Shift key pressed");
                     break;
                  }
                  else if(!rightFacing||(left)){
                     fireballs[i] = new Fireball(x,y+45,-1);
                     //System.out.println("Shift key pressed");
                     break;
                  }
               }
            }
         }
      }
      else if (e.getKeyCode() == KeyEvent.VK_LEFT)
      {  
       
         if (goingLeft == true && jump == false &&  System.currentTimeMillis() - rReleaseTime <100||right){
            slide(false);
         }
         if (lAccelerate == false)
         {
            tAccel = System.currentTimeMillis();
            lAccelerate = true;
         }
       
         //System.out.println("left key pressed");
         updateMario();
         draw.repaint();
         left = true;
         rightFacing = false;
         leftFacing = true;
         
         goingLeft = false;
      }
      else if (e.getKeyCode() == KeyEvent.VK_DOWN)
      {
         //System.out.println("down key pressed");
         if (!duckCheck&&!jump&&(bigMario||fireFlowerMario||bigStarMario))
         {
            duckCheck = true;
            crouchCheck = true;
            updateMario();
            heightHolder = height;
            y += 42;
            height = 48;
           
            if(rAccelerate){
               prevAccel = acceleration(tAccel);
               slide(false);
               stopAccel = true;
            }
            if (lAccelerate){
               prevAccel = acceleration(tAccel);
               slide(true);
               stopAccel = true;
            }
         }          
         for (int i = 0; i<Pipes.length;i++){
            if (Pipes[i].enterable == true && Pipes[i].enter(x,y,width,height)){
               goingDownPipe = true;
               pipeSound.play();
               if(rightFacing){
                  if(fireFlowerMario){
                     currentMario = fireStandingMario;
                  }
                  else if (bigMario){
                     currentMario = bigStandingMario;
                  }
                  else{
                     currentMario = standingMario;
                  }
               }
               
               else{
                  if(fireFlowerMario&&!duckCheck){
                     currentMario = lFireStandingMario;
                  }
                  else if (bigMario&&!duckCheck){
                     currentMario = bigLStandingMario;
                  }
                  else if(!duckCheck){
                     currentMario = lStandingMario;
                  }
               }
               for (int j = 0; j<height;j++){
                  y +=1;
                  draw.paintImmediately(oneGame.getBounds());
                  try{
                     Thread.sleep(10);
                  }
                  catch (InterruptedException o){
                  
                  }
               }
               goingDownPipe = false;
               
               
            		               
               if (fireFlowerMario == true){
                  marioType = 3;
               }
               else if (bigMario == true){
                  marioType = 2;
               }
               else{ 
                  marioType = 1;
               }  
               if(currentLevel.equals("Levels//Level1.txt")){
                  overWorld.stop();
                  new onePlayerGame("Levels//underground.txt",2,marioType,lives,CoinCount,secondsLeft,score,x,false);
               }
               else if (currentLevel.equals("Levels//underground.txt")){
                  underGround.stop();
                  new onePlayerGame("Levels//Level1.txt",1,marioType,lives,CoinCount,secondsLeft,score,pipePrevX,true);
               } else if (currentLevel.equals("Levels//Level2.txt")){
                  overWorld.stop();
                  underGround.stop();
                  if (x < 4000){
                     new onePlayerGame("Levels//secret.txt",2,marioType,lives,CoinCount,secondsLeft,score,x,false);
                     underGround.stop();
                     overWorld.stop();
                  } else{
                     new onePlayerGame("Levels//bowser.txt",3,marioType,lives,CoinCount,secondsLeft,score,prevX,true);
                     underGround.stop();
                     overWorld.stop();
                  }
               }
               else if (currentLevel.equals("Levels//secret.txt")){
                  new onePlayerGame("Levels//Level2.txt",1,marioType,lives,CoinCount,secondsLeft,score,prevX,true);
                  underGround.stop();
               }
               host.timer1.stop();
               oneGame.dispose();
            }
         }
         draw.repaint();
         updateMario();
      }
      
      else if (e.getKeyCode() == KeyEvent.VK_UP && ((System.currentTimeMillis() - tStart)>1))
      {
      
         if (jump == false&&killJump ==false&&!hitSide){ //If not jumping, jump
            startY = 440-this.y;
            onBlock = false;
            jump = true;
         }
      
      
      }
   }
//----------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------
   /*** keyReleased *************************************
      * Purpose: for each button released there are certain *
      * actions that need to be performed                   *
      * Parameters: e - the details on the button released  *
      * Returns: none                                       *
      ******************************************************/
   public void keyReleased(KeyEvent e)
   {
      if (e.getKeyCode() == KeyEvent.VK_RIGHT)
      {
         leftFacing = true;
         rAccelerate = false;
         rReleaseTime = System.currentTimeMillis();
         //System.out.println("right key released");
         right = false;
         if(jump == false){
            updateMario();
            draw.paintImmediately(oneGame.getBounds());
         }
      }
      else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
         shiftKey = false;
         //System.out.println("Shift key released");
      }
      else if (e.getKeyCode() == KeyEvent.VK_LEFT)
      {
         leftFacing = false;
         lAccelerate = false;
         lReleaseTime = System.currentTimeMillis();
         //System.out.println("left key released");
         left = false;
         if(jump == false){
            updateMario();
            draw.paintImmediately(oneGame.getBounds());
         }
      }
      else if (e.getKeyCode() == KeyEvent.VK_DOWN&&(bigMario||fireFlowerMario||bigStarMario)&&!jump&&crouchCheck == true)
      {
         crouchCheck = false;
         duckCheck = false;
         height = heightHolder;
         y -= 42;
         //System.out.println("down key released");		         //System.out.println("down key released");
         updateMario();
      }
      else if (e.getKeyCode() == KeyEvent.VK_UP)
      {
         //System.out.println("up key released");
      
         tStart = System.currentTimeMillis();
      }
   }
   public void keyTyped(KeyEvent e)
   {
   }
   /*** gravity ***************************************
      * Purpose: mario goes down when in air, also moving in air  *
      * Parameters: time - how far he is into hit jump   *
      * Returns: none                                       *
      ******************************************************/
   public void gravity(double time)
   {
      if(y>485-height){
         y = 485-height;
      }
      else{
         if(!dead&&!onFlagPole&&!endingGame){
            // try{
               // Thread.sleep(200);
            // }
            // catch (InterruptedException e){
            // }
            y =(int)(48.48*(time-1.9)*(time-1.9)+265-startY); //Y changes like a parabola
         //System.out.println("gravity = " + y);
            if (right)  //Moves while jumping
            {    
               x+= 0;
            }
            else if(left)
            {
               x-=0;
            }
            if(invisBlock.inBlock(x,y,width,height,10)[0]==4){		
               x = invisBlock.x+invisBlock.width;		
            }
            inBlockResults = checkInBlock(blocks, blocks.length,time); //Checks if Mario hits a block
            if (inBlockResults[0]!=-1&&blocks[inBlockResults[0]].getClass() != AntiBlock.class){
               jump = false;
            
            
               if(left == true){
                  updateMario();
               }
               else if (leftFacing == false && !right){
                  updateMario();
               }
               else{
                  updateMario();
               }
               if (inBlockResults[1]==1&&!hitSide){ //If he landed on top of the block
                  jumpSound = false;
                  onBlock = true;
                  timeCount = 0;
                  this.y = blocks[inBlockResults[0]].y-height-1;
                  if(left == true){
                     updateMario();
                  }
                  else if (leftFacing == false && !right){
                     updateMario();
                  }
                  else{
                     updateMario();
                  }
                  cam.center(x,y);
                  draw.paintImmediately(oneGame.getBounds());
               
               
               }
               else if(inBlockResults[1] == 2){ //If he is under the block
                  jumpSound = false;
                  //y = blocks[inBlockResults[0]].y+blocks[inBlockResults[0]].height;
                  blocks[inBlockResults[0]].active = true;
               //coins[0].gotten = false;
                  if((bigMario||fireFlowerMario||bigStarMario)&&blocks[inBlockResults[0]].getClass()!=QuestionBlock.class&&blocks[inBlockResults[0]].getClass()!=pipe.class){
                     blocks[inBlockResults[0]].gotten = true;
                     blocks[inBlockResults[0]].gottenTime = System.currentTimeMillis();
                     brickBreakSound.play();
                  }
               //onBlock = true;
                  timeCount = 0;
                  if(blocks[inBlockResults[0]].getClass() == QuestionBlock.class&&blocks[inBlockResults[0]].obtained == false){
                     if(blocks[inBlockResults[0]].holds !=1){
                        powerUpQBlockSound.play();
                     }
                     else{
                        coinSound.play();
                        CoinCount++;		
                        score+=200;
                        if(CoinCount == 100){
                           lives++;
                           CoinCount = 0;
                        }
                        blocks[inBlockResults[0]].getCoin = true;
                     }
                  //blocks[inBlockResults[0]].obtained = true;
                     blocks[inBlockResults[0]].getting = true;
                     blocks[inBlockResults[0]].rewardCount = 0;
                     blockPush = inBlockResults[0];
                     blockDirection = 1;
                  }
                  else if(blocks[inBlockResults[0]].getClass() == block.class){
                     if(bigMario == false &&fireFlowerMario == false)
                        smallBrickSound.play();
                     blockPush = inBlockResults[0];
                     blockDirection = 1;
                  }
               
                  fall();
               }
               else if(inBlockResults[1] == 3){ //Left side
                  onBlock = false;
                  x = blocks[inBlockResults[0]].x-width;
                  cam.center(x,y);
               //System.out.println("Side =" + y);
                  if (timeCount < 1.9&&!hitSide)
                  {
                     hitSide = true;
                     bSide = inBlockResults[0];
                  }
               
               }
               else if(inBlockResults[1] == 4){ //Right side
                  onBlock = false;
                  x = blocks[inBlockResults[0]].x+blocks[inBlockResults[0]].width;
                  cam.center(x,y);
               //System.out.println("Side =" + y);   
                  if (timeCount < 1.9&&!hitSide)
                  {
                     hitSide = true;
                     bSide = inBlockResults[0];
                  }
               }
               else{
                  fall();
                  onBlock = true;
               }
            }            }
      }
   }
   /*** fall *********************************************
      * Purpose: for each button pressed there are certain  *
      * when mario jumps (kills enemies, fall down holes    *
      * etc.)                                               *
      * Parameters: none                                    *
      * Returns: none                                       *
      ******************************************************/
   public void fall()
   {
      if(!fall){
         fall = true;
      }
      hitSide = false;
      jump = false;
      if(y>650&&!dead){
         //dead = true;
         starMario = false;
         bigStarMario = false;
         invMario=false;
         die();
      }
      if (onBlock == false&&dead==false&&!comingUp&&!onFlagPole&&!endingGame&&fall){ //Accelerate down
         for (double i = 1;i<95;i=i+0.1)
         {
            if(dead){
               break;
            }
            if(killJump&&!starMario&&!bigStarMario){
               timeCount = 0;
               break;
            }
            goombaTurn(10.0);
            powerUpTurn(10.0);
            cam.center(x,y);
            if(invisBlock.inBlock(x,y,width,height,10)[0]==4){		
               x = invisBlock.x+invisBlock.width;		
            }
            inBlockResults = checkInBlock(blocks,blocks.length,10.0);
            if (y>485-height&&(inBlockResults[0]!=-1&&blocks[inBlockResults[0]].getClass() != AntiBlock.class||inBlockResults[0] ==-1)&&!inHole)
            {
               
               if(y>650&&!dead){
                  //dead = true;
                  die();
                  break;
               }
               if(!inHole){
                  y = 485-height;
                  timeCount = 0;
                  jump = false;
                  break;
               }
               cam.center(x,y);
               draw.paintImmediately(oneGame.getBounds());
               if (leftFacing == false)
               {
                  updateMario();
               }
               else 
               {
                  updateMario();
               }
               break;
            }
            else if(inBlockResults[0]!=-1 && inHole){
               if (inBlockResults[1] == 3){
                  x = blocks[inBlockResults[0]].x;
               }
               else if(inBlockResults[1] == 4){
                  x = blocks[inBlockResults[0]].x + blocks[inBlockResults[0]].width - width;
               }
            }
            try {
               Thread.sleep(15);
            } catch (InterruptedException f) {
               f.printStackTrace();
            }
            y -= (int)(((-3)*i*i));
            if (left&&!duckCheck)
            {
               x+= acceleration(tAccel);
            }
            else if (right&&!duckCheck)
            {
               x+= acceleration(tAccel);
            }  
            cam.center(x,y);           
            draw.paintImmediately(oneGame.getBounds());   
            if(invisBlock.inBlock(x,y,width,height,10)[0]==4){		            
               x = invisBlock.x+invisBlock.width;		
            }	
            inBlockResults = checkInBlock(blocks, blocks.length,10); //Check if he hits an object
            if (inBlockResults[0]!=-1&&blocks[inBlockResults[0]].getClass() != AntiBlock.class){
               timeCount = 0;
               
               onBlock = true;
               if (inBlockResults[1]==1){
                  this.y = blocks[inBlockResults[0]].y-height;
                  if(left == true){
                     updateMario();
                  }
                  else if (leftFacing == false && !right){
                     updateMario();
                  }
                  else{
                     updateMario();
                  }
                  cam.center(x,y);
                  draw.paintImmediately(oneGame.getBounds());
                  break;
               }
               else if(inBlockResults[1] == 2){
                  //coins[0].gotten = false;
                  //fall();
               }
               else if(inBlockResults[1] == 3){
                  x = blocks[inBlockResults[0]].x-width;
               }
               else if(inBlockResults[1] == 4){
                  x = blocks[inBlockResults[0]].x+blocks[inBlockResults[0]].width;
               }
            }
               
            jump = false;
            jumpSound =false;
         }
         fall = false;
            
      }
         
   }
   /*** slide ********************************************
      * Purpose: when mario is running and he changes       *
      * directions                                          *
      * Parameters: goingLeft | which way to slide          *
      * Returns: none                                       *
      ******************************************************/
   public void slide(boolean goingLeft)
   {
      if(jump == false&&inHole == false&&!dead&&!stopAccel){
         double accel = Math.sqrt(Math.abs(2*prevAccel)); //Finding the sliding distance
         //System.out.println(accel);
         if (goingLeft){
            if (bigMario&&!duckCheck) {
               currentMario = bigLSlidingMario;
            }
            else if(fireFlowerMario&&!duckCheck) {
               currentMario = lFireSlidingMario;
            }
            else if(starMario&&!duckCheck){
               currentMario = starLSlidingMario;
            }
            else if(bigStarMario&&!duckCheck){
               currentMario = starBigLSlidingMario;
            }
            else if(!duckCheck){
               currentMario = lSlidingMario;
            }
         }
         else{
            if (bigMario&&!duckCheck) {
               currentMario = bigSlidingMario;
            }
            else if(fireFlowerMario&&!duckCheck) {
               currentMario = fireSlidingMario;
            }
            else if(starMario&&!duckCheck){
               currentMario = starSlidingMario;
            }
            else if(bigStarMario&&!duckCheck){
               currentMario = starBigSlidingMario;
            }
            else if(!duckCheck){
               currentMario = slidingMario;
            }
         }            cam.center(x,y);
         draw.paintImmediately(oneGame.getBounds());
         for (double i = accel; i>=1;i = i-0.1){
            curPos = x;
            goombaTurn(timeCount);
            powerUpTurn(timeCount);
            if(bowser!=null&&!endingGame){//start
               bowser.update(x,y,width,height,maxPos,blocks,blocks.length,1149-cam.getxOffset());
               bowser.update(x,y,width,height,maxPos,blocks,blocks.length,1149-cam.getxOffset());
               bowser.update(x,y,width,height,maxPos,blocks,blocks.length,1149-cam.getxOffset());
               bowser.update(x,y,width,height,maxPos,blocks,blocks.length,1149-cam.getxOffset());
               bowser.update(x,y,width,height,maxPos,blocks,blocks.length,1149-cam.getxOffset());
               bowser.update(x,y,width,height,maxPos,blocks,blocks.length,1149-cam.getxOffset());
               if(bowser.shouldDie(x,y,width,height,10))
                  die();
            }//end
            if (maxPos <= curPos)
            {
               maxPos = curPos;
               cam.camOn(true);
            }
            else
            {
               cam.camOn(false);
            }
            if(invisBlock.inBlock(x,y,width,height,10)[0]==4){		
               x = invisBlock.x+invisBlock.width;		
            }
            inBlockResults = checkInBlock(blocks,blocks.length,10); //If he hits a block while sliding
            stopAccel = false;
            if (inBlockResults[0] !=-1&&blocks[inBlockResults[0]].getClass() != AntiBlock.class){
               if (inBlockResults[1] == 4){
                  x = blocks[inBlockResults[0]].x +blocks[inBlockResults[0]].width;
                  stopAccel = true;
               }
               else if(inBlockResults[1] == 3){
                  x = blocks[inBlockResults[0]].x -width -1;
                  stopAccel = true;
               }
               break;
            }
         
            try {
               Thread.sleep(10);
            } catch (InterruptedException f) {
               f.printStackTrace();
            }
            if (goingLeft){
               x-= Math.round(i); //Makes him slide
            }
            else{
               x+=Math.round(i);
            }
            cam.center(x,y);
            if (checkShouldFall(blocks,blocks.length)){ //If he slides off a block
               if (goingLeft){
                  x-= 5;
               }
               else{
                  x+= 5;
               }
               cam.center(x,y);
               
               draw.paintImmediately(oneGame.getBounds());
               fall();
               break;
            }
            cam.center(x,y);
            draw.paintImmediately(oneGame.getBounds());
            goombaTurn(10);
            powerUpTurn(10);
         }
      }
   
      prevAccel = 0;
   }
   /*** checkShouldFall **********************************
      * Purpose: checks if mario should fall (holes, blocks *
      * etc.)                                               *
      * Parameters:                                         *
      * - length | the length of the array                  *
      * - toCheck | array of the blocks he should fall off  *
      * Returns:                                            *
      * - true | if mario should fall                       *
      * - false | if mario shouldnt fall                    *
      ******************************************************/
   public boolean checkShouldFall(block[] toCheck, int length)
   {
      boolean shouldFall = true;
      if(y<=485-height){
         for (int i = 0; i< length;i++)
         {
            if (toCheck[i].shouldFall(x,y,width,height) == false&&toCheck[i].getClass()!=AntiBlock.class)
            {
            
               shouldFall = false;
               break;
            }
         
         
         }
      }
      int holeWidth=0;
      for (int i = 0; i<holes.length;i++){
         if(holes[i].inBlock(x,y,width,height,10)[1]>0){
         
            holeWidth+=holes[i].inBlock(x,y,width,height,10,1)[1];
            
         }
         
         if(holeWidth >= width){
            inHole = true;
            return true;
         }
      }
      if(comingUp)
         return false;
      return shouldFall;
   }   
      /*** checkInBlock *************************************
      * Purpose: checks if mario is in block and find the   *
      * side it came from                                   *
      * Parameters:                                         *
      * - toCheck | checks which blocks mario can be in     *
      * - length | length of the toCheck array              *
      * - time | checks how long hes jumping for            *
      * Returns: block and the side that mario hits/goes in *
      ******************************************************/
   public int[] checkInBlock(block[] toCheck, int length, double time)
   {
      int side = 0;
      int area =0;
      int block =-1;
      int alreadySide = 0;
      int holeArea = 0;
      int alreadyBlock=-1;
      boolean alreadyInBlock = false;
      for (int i = 0; i< length;i++)
      {
         if(!comingUp){
            if(!inHole){
               if (blocks[i].getClass() == AntiBlock.class&&blocks[i].inBlock(x,y,width,height,time)[1]>0&&blocks[i].inBlock(x,y,width,height,time)[0]==1){
                  holeArea+=blocks[i].inBlock(x,y,width,height,time)[1];
                  if(holeArea>=width){
                     block = i;
                     side = blocks[i].inBlock(x,y,width,height,time)[0];
                     
                     break;
                  }
               }
               
               else if (blocks[i].inBlock(x,y,width,height,time)[1]>area&&blocks[i].getClass() != AntiBlock.class&&blocks[i].getClass() !=Piranha.class){
                  area = blocks[i].inBlock(x,y,width,height,time)[1];
                  side = blocks[i].inBlock(x,y,width,height,time)[0];
                  block =i;
                  if(side == 1&&blocks[i].getClass() == StairCase.class){
                     y = blocks[i].y-height-1;
                     if(y>=485-height){
                        y = 485-height;
                        draw.paintImmediately(oneGame.getBounds());
                        break;
                     }
                     
                     //jump = false;
                     
                  }
                  else if (side == 3&&blocks[i].getClass() == StairCase.class){
                     x = blocks[i].x-width;
                     if(y>=485-height){
                        y = 485-height;
                        draw.paintImmediately(oneGame.getBounds());
                        break;
                     }
                     //jump = false;
                    
                  }
                  else if (side == 4&&blocks[i].getClass() == StairCase.class){
                     x = blocks[i].x+blocks[i].width;
                     if(y>=485-height){
                        y = 485-height;
                        break;
                     }
                     //jump = false;
                    
                  }
               
               }
            
            }
            else if (blocks[i].getClass() == AntiBlock.class){
               if(!inHole&&blocks[i].inBlock(x,y,width,height,10,1)[1]>5){
                  area = blocks[i].inBlock(x,y,width,height,10,1)[1];
                  side = blocks[i].inBlock(x,y,width,height,10,1)[0];
                  block=i;
                  break;
               }
               else if(blocks[i].inBlock(x,y,width,height,10,2)[1]>5){
                  area = blocks[i].inBlock(x,y,width,height,10,2)[1];
                  side = blocks[i].inBlock(x,y,width,height,10,2)[0];
                  block=i;
                  break;
               }
            
               if(y>650&&!dead){
                  //dead = true;
                  starMario=false;
                  bigStarMario=false;
                  invMario=false;
                  die();
                  break;
               }
            }
         }
         
                  
               
      }
      if(block!=-1&&blocks[block].getClass() == Castle.class){
         
         currentMario = null;
         draw.paintImmediately(oneGame.getBounds());
         try{
            Thread.sleep(1000);
         }
         catch (InterruptedException u){
         }
         endLevel = true;
         
      
      }
      else if (block!=-1&&blocks[block].getClass() == FlagPole.class&&onFlagPole==false){
         if(onFlagPole ==false){
            if(y<blocks[block].y+3)
               blocks[block].killScore = new Score(blocks[block].x-70,y-2,5000);
            else if(y<blocks[block].y+82)
               blocks[block].killScore = new Score(blocks[block].x-70,y-2,4000);
            else if(y<blocks[block].y+161)
               blocks[block].killScore = new Score(blocks[block].x-70,y-2,2000);
            else if(y<blocks[block].y+240)
               blocks[block].killScore = new Score(blocks[block].x-70,y-2,500);
            else
               blocks[block].killScore = new Score(blocks[block].x-70,y-2,100);
            score+=Integer.parseInt(blocks[block].killScore.score);
         }
         
         oneGame.removeKeyListener(this);
         //endingLevel = true;
         onFlagPole = true;
         cam.camOn(false);
         score += secondsLeft*100;
         secondsLeft = 0;
         overWorld.stop();
         underGround.stop();
         flagpoleSound.play();
         updateMario();         
         
      }
      else if (block!=-1&&blocks[block].getClass() == Axe.class){
         oneGame.removeKeyListener(this);
         endingGame = true;
         bridge = System.currentTimeMillis();
         
      }
      if(block!=-1&&blocks[block].getClass() == FlagPole.class&&(currentMario == lBigFlagMario||currentMario == lFireFlagMario)){
         
         return new int[]{-1,0};
      }
      return new int[]{block,side};
   }
   
   /*** acceleration *************************************
      * Purpose: gains speed longer he runs to a max        *
      * Parameters: startTime | start time he starts runs   *
      * Returns: the start time he runs at and the accel    *
      ******************************************************/
   public int acceleration(long startTime)
   {
      if(!dead&&!comingUp){
         long t = (System.currentTimeMillis() - startTime);
         int temp;
      
         if (leftFacing)
         {
            temp = -1;
         }
         else
         {
            temp = 1;
         }
      //Vf = Vi + at
         if (starMario||bigStarMario)
         {
            if (t > 3000)
               return (int)(6)*(temp);
            else
            {
               return (int)((4500 + 0.5*t)/1000)*(temp);
            }
         }
         else
         {
            if (t > 3000)
               return (int)(5)*(temp);
            else
            {
               return (int)((3500 + 0.5*t)/1000)*(temp);
            }
         }
      }
      return 0;
   }
   /*** getItems *****************************************
      * Purpose: loads all items into the level             *
      * Parameters: file | the file reader from level text  *
      * file                                                *
      * Returns: none                                       *
      ******************************************************/
   public void getItems(String file){ //File reader from level text file
      String prevToken = " ";
      int holeCount = 0;
      int holeXCount = 0;
      String itemLine,nextToken;
      block[] tempBlocks = new block[10];
      coin[] tempCoins = new coin[10];
      QuestionBlock[] tempqBlocks = new QuestionBlock[10];
      Goomba[] tempGoombas = new Goomba[10];
      Koopa[] tempKoopas = new Koopa[10];
      Mushroom[] tempMushrooms = new Mushroom[10]; 
      pipe[] tempPipe = new pipe[10];
      AntiBlock[] tempAntiBlocks = new AntiBlock[10];
      StairCase[] tempStairCases = new StairCase[10];
      Castle tempCastle=null;
      FlagPole tempFlagPole = null;
      Piranha[] tempPiranhas = new Piranha[10];
      Barrier[] tempBarriers = new Barrier[10];
   
      int itemX = 0, itemY = -5,listSize = 0, coinsSize = 0, goombaSize = 0, koopaSize = 0, mushroomSize = 0,qBlockSize =0, PipeSize=0,stairCaseSize = 0;
      int antiBlockSize=0,piranhaSize = 0,barrierSize = 0;
      try{
         FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr);
         itemLine = br.readLine();
         while(itemLine!=null){
         
            StringTokenizer st = new StringTokenizer(itemLine, "\tbcgpC12345UPihsSBfza ", true);
            itemLine = br.readLine();
            itemX=0;
            while(st.hasMoreTokens()){
            
               nextToken = st.nextToken();
               if (nextToken.equals(" ")){
                  itemX+=35;
               }
               else if(nextToken.equals("\t")){
                  itemX+=105;
               }
               else if(nextToken.equals("B")){   
                  bowser = new Bowser(itemX, 395);
                  itemX+=35;
               }
               else if(nextToken.equals("b")){
                  if (listSize == tempBlocks.length){
                  //array too small - double its size
                     block[] temp = new block [2*tempBlocks.length];
                     for (int i = 0; i < tempBlocks.length; i++)
                        temp[i] = tempBlocks[i];
                     tempBlocks = temp;
                  }
                  if (environment == 1)
                  {
                     tempBlocks [listSize] = new block(itemX,itemY);
                  
                  }
                  else
                     tempBlocks [listSize] = new block(itemX,itemY,true);
                  listSize++;
                  itemX+=35;
               
               }
               else if(nextToken.equals("z")){
                  if (piranhaSize == tempPiranhas.length){
                  //array too small - double its size
                     Piranha[] temp = new Piranha [2*tempPiranhas.length];
                     for (int i = 0; i < tempPiranhas.length; i++)
                        temp[i] = tempPiranhas[i];
                     tempPiranhas = temp;
                  }
               
                  tempPiranhas [piranhaSize] = new Piranha(itemX+5,itemY);
                  piranhaSize++;
                  
               
               }
               else if(nextToken.equals("C")){
                  tempCastle = new Castle(itemX,itemY);
                  itemX+=35;
               
               }
               else if (nextToken.equals("f")){
                  tempFlagPole = new FlagPole(itemX,itemY);
                  itemX+=35;
               }
               else if(nextToken.equals("s")||nextToken.equals("S")){
                  if (listSize == tempStairCases.length){
                  //array too small - double its size
                     StairCase[] temp = new StairCase [2*tempStairCases.length];
                     for (int i = 0; i < tempStairCases.length; i++)
                        temp[i] = tempStairCases[i];
                     tempStairCases = temp;
                  }
                  if(nextToken.equals("s"))
                     tempStairCases [stairCaseSize] = new StairCase(itemX,itemY,false);
                  else
                     tempStairCases [stairCaseSize] = new StairCase(itemX,itemY,true);
                  stairCaseSize++;
                  itemX+=35;
               
               }
               else if(nextToken.equals("i")){
                  if (barrierSize == tempBarriers.length){
                  //array too small - double its size
                     Barrier[] temp = new Barrier [2*tempBarriers.length];
                     for (int i = 0; i < tempBarriers.length; i++)
                        temp[i] = tempBarriers[i];
                     tempBarriers = temp;
                  }
               
                  tempBarriers [barrierSize] = new Barrier(itemX,itemY);
                  barrierSize++;
                  itemX+=35;
               
               }
               else if(nextToken.equals("1")||nextToken.equals("2")||nextToken.equals("3")||nextToken.equals("4")||nextToken.equals("5")){
                  if (qBlockSize == tempqBlocks.length){
                  //array too small - double its size
                     QuestionBlock[] temp = new QuestionBlock [2*tempqBlocks.length];
                     for (int i = 0; i < tempqBlocks.length; i++)
                        temp[i] = tempqBlocks[i];
                     tempqBlocks = temp;
                  }
                  if (nextToken.equals("1") == false && nextToken.equals("5") == false){
                     mushroomSize ++;
                     
                  }
                  
                  tempqBlocks [qBlockSize] = new QuestionBlock(itemX,itemY,Integer.parseInt(nextToken));
                  if(nextToken.equals("4")||nextToken.equals("5"))
                     tempqBlocks[qBlockSize].active = false;
                  qBlockSize++;
                  itemX+=35;
               
               }
               if(nextToken.equals("p")||nextToken.equals("P")||nextToken.equals("U")||nextToken.equals("z")){
                  if (PipeSize == tempPipe.length){
                  //array too small - double its size
                     pipe[] temp = new pipe [2*tempPipe.length];
                     for (int i = 0; i < tempPipe.length; i++)
                        temp[i] = tempPipe[i];
                     tempPipe = temp;
                  }
                  if(nextToken.equals("P"))
                     tempPipe [PipeSize] = new pipe(itemX,itemY,true,false,false);
                  else if(nextToken.equals("p"))
                     tempPipe [PipeSize] = new pipe(itemX,itemY,false,false,false);
                  else if(nextToken.equals("z"))
                     tempPipe [PipeSize] = new pipe(itemX,itemY,false,false,true);
                  else
                     tempPipe[PipeSize] = new pipe(itemX,itemY,false,true,false);
               
                  PipeSize++;
                  itemX+=35;
               
               }
               else if (nextToken.equals("c")){
                  if (coinsSize == tempCoins.length){
                  //array too small - double its size
                     coin[] temp = new coin [2*tempCoins.length];
                     for (int i = 0; i < tempCoins.length; i++)
                        temp[i] = tempCoins[i];
                     tempCoins = temp;
                  }
               
                  tempCoins [coinsSize] = new coin(itemX,itemY);
                  coinsSize++;
                  itemX+=35;
               }
               else if (nextToken.equals("h")){
                  if (holeCount == tempAntiBlocks.length){
                  //array too small - double its size
                     AntiBlock[] temp = new AntiBlock [2*tempAntiBlocks.length];
                     for (int i = 0; i < tempAntiBlocks.length; i++){
                     
                        
                        temp[i] = tempAntiBlocks[i];
                     
                        
                     }
                     tempAntiBlocks = temp;
                  }
                  if(prevToken.equals("h")){
                     holeXCount +=35;
                     tempAntiBlocks [holeCount] = new AntiBlock(itemX,itemY,holeXCount);
                  }
                  else{
                     tempAntiBlocks [holeCount] = new AntiBlock(itemX,itemY,35);
                     holeXCount += 35;
                     itemX+=35;
                     
                  }
                  itemX+=35;
               }
               else if (nextToken.equals("g")){
                  if (goombaSize == tempGoombas.length){
                    //array too small - double its size
                     Goomba[] temp = new Goomba [2*tempGoombas.length];
                     for (int i = 0; i < tempGoombas.length; i++)
                        temp[i] = tempGoombas[i];
                     tempGoombas = temp;
                  }
                 
                  tempGoombas [goombaSize] = new Goomba(itemX,itemY, -1);
                  goombaSize++;
                  itemX+=35;
               }
               else if (nextToken.equals("k")){
                  if (koopaSize == tempKoopas.length){
                    //array too small - double its size
                     Koopa[] temp = new Koopa [2*tempKoopas.length];
                     for (int i = 0; i < tempKoopas.length; i++)
                        temp[i] = tempKoopas[i];
                     tempKoopas = temp;
                  }
                 
                  tempKoopas [koopaSize] = new Koopa(itemX,itemY, -1);
                  koopaSize++;
                  itemX+=35;
               }
               else if (nextToken.equals("m")){
                  if (mushroomSize == tempMushrooms.length){
                    //array too small - double its size
                     Mushroom[] temp = new Mushroom [2*tempMushrooms.length];
                     for (int i = 0; i < tempMushrooms.length; i++)
                        temp[i] = tempMushrooms[i];
                     tempMushrooms = temp;
                  }
                 
                  tempMushrooms [mushroomSize] = new Mushroom(itemX,itemY);
                  mushroomSize++;
                  itemX+=35;
               }
               else if(nextToken.equals("a")){   
                  axe = new Axe(itemX, itemY);
                  itemX+=35;
               }
               if(prevToken.equals("h") == false){
                  holeXCount = 0;
                  
               }
               if(prevToken.equals("h") && nextToken.equals("h") == false){
                  holeCount++;
               }
               prevToken = nextToken;
               
            }
            itemY+=35;
         }
         
         br.close();
         if(axe!=null){
            if((tempCastle!=null^tempFlagPole!=null)){
               if(tempFlagPole!=null)
                  blocks = new block[listSize+qBlockSize+PipeSize+holeCount+3+(stairCaseSize*8)+piranhaSize+barrierSize];
               else
                  blocks = new block[listSize+qBlockSize+PipeSize+holeCount+2+(stairCaseSize*8)+piranhaSize+barrierSize];
            }
            else if (tempCastle != null &&tempFlagPole!=null)
               blocks = new block[listSize+qBlockSize+PipeSize+holeCount+4+(stairCaseSize*8)+piranhaSize+barrierSize];         
            else
               blocks = new block[listSize+qBlockSize+PipeSize+holeCount+1+(stairCaseSize*8)+piranhaSize+barrierSize];
         }
         else{
            if((tempCastle!=null^tempFlagPole!=null)){
               if(tempFlagPole!=null)
                  blocks = new block[listSize+qBlockSize+PipeSize+holeCount+2+(stairCaseSize*8)+piranhaSize+barrierSize];
               else
                  blocks = new block[listSize+qBlockSize+PipeSize+holeCount+1+(stairCaseSize*8)+piranhaSize+barrierSize];
            }
            else if (tempCastle != null &&tempFlagPole!=null)
               blocks = new block[listSize+qBlockSize+PipeSize+holeCount+3+(stairCaseSize*8)+piranhaSize+barrierSize];         
            else
               blocks = new block[listSize+qBlockSize+PipeSize+holeCount+(stairCaseSize*8)+piranhaSize+barrierSize];
         }
         coins = new coin[coinsSize];
         goombas = new Goomba[goombaSize];
         koopas = new Koopa[koopaSize];
         mushrooms = new Mushroom[mushroomSize];
         qBlocks = new QuestionBlock[qBlockSize];
         powerUps = new Mushroom[mushroomSize];
         Pipes = new pipe[PipeSize];
         enemies = new Goomba[goombaSize+koopaSize+piranhaSize];
         holes = new AntiBlock[holeCount];
         stairCases = new StairCase[stairCaseSize];
         piranhas = new Piranha[piranhaSize];
         barriers = new Barrier[barrierSize];
         
         for (int i = 0; i<listSize;i++){
            blocks[i] = tempBlocks[i];
         
         }
         
            
         for (int i = 0; i<qBlockSize;i++){
            blocks[i+listSize] = tempqBlocks[i];
            qBlocks[i] = tempqBlocks[i];
         }
         for (int i=0;i<coinsSize;i++){
            coins[i] = tempCoins[i];
         }
         
         for (int i = 0; i< koopaSize;i++){
            koopas[i] = tempKoopas[i];
            enemies[i] = tempKoopas[i];
         }
         for (int i = 0; i< goombaSize;i++){
            goombas[i] = tempGoombas[i];
            enemies[i+koopaSize] = tempGoombas[i];
         }
         for (int i = 0; i<  mushroomSize;i++){
            powerUps[i] = tempMushrooms[i];
            mushrooms[i] = tempMushrooms[i];
         }
         
         for (int i = 0;i<holeCount;i++){
            holes[i] = tempAntiBlocks[i];
            blocks[i+listSize+qBlockSize] = tempAntiBlocks[i];
         }
         for (int i = 0; i<stairCaseSize; i++){
            stairCases[i] = tempStairCases[i];
            for (int j = 0 ; j<8;j++){
               if(stairCases[i].reversed == false){
                  blocks[((i*8)+j)+listSize+qBlockSize+holeCount] = new StairCase(tempStairCases[i].x+38*j,tempStairCases[i].y+(8-(j+1))*36,false);
               }
               else{
                  blocks[((i*8)+j)+listSize+qBlockSize+holeCount] = new StairCase(tempStairCases[i].x+38*j,tempStairCases[i].y+((j))*36,true);
               }
            }
         }
         for (int i = 0; i<piranhaSize;i++){
            blocks[i+((stairCaseSize*8))+listSize+qBlockSize+holeCount] = tempPiranhas[i];
            piranhas[i] = tempPiranhas[i];
            enemies[i+koopaSize+goombaSize] = tempPiranhas[i];
         }
         for(int i =0; i< PipeSize; i++){
            Pipes[i] = tempPipe[i];
            blocks[i+listSize+qBlockSize+holeCount+piranhaSize+((stairCaseSize*8))] = tempPipe[i];
         }
         for(int i =0; i< barrierSize; i++){
            barriers[i] = tempBarriers[i];
            blocks[i+listSize+qBlockSize+holeCount+piranhaSize+((stairCaseSize*8))+PipeSize] = tempBarriers[i];
         }
         if(axe!=null){
            blocks[barrierSize+listSize+qBlockSize+holeCount+piranhaSize+((stairCaseSize*8))+PipeSize] = axe;
         }
         if(axe==null){
            if(tempCastle!=null)
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+piranhaSize+barrierSize] = tempCastle;
            if(tempFlagPole!=null&&tempCastle!=null){
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+1+piranhaSize+barrierSize] = tempFlagPole;
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+2+piranhaSize+barrierSize] = new FlagBlock(tempFlagPole.x-5,tempFlagPole.y+286);
            }
            else if(tempFlagPole!=null){
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+PipeSize] = tempFlagPole;
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+1+PipeSize] = new FlagBlock(tempFlagPole.x-5,tempFlagPole.y+286);
            }
         }
         else{
            if(tempCastle!=null)
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+piranhaSize+barrierSize+1] = tempCastle;
            if(tempFlagPole!=null&&tempCastle!=null){
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+1+piranhaSize+barrierSize+1] = tempFlagPole;
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+2+piranhaSize+barrierSize+1] = new FlagBlock(tempFlagPole.x-5,tempFlagPole.y+286);
            }
            else if(tempFlagPole!=null){
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+PipeSize+1] = tempFlagPole;
               blocks[((stairCaseSize*8))+listSize+qBlockSize+PipeSize+holeCount+1+PipeSize+1] = new FlagBlock(tempFlagPole.x-5,tempFlagPole.y+286);
            }
         }
      }
      catch(IOException e){
         System.out.println("Levels//Level Not Found");
      }
   }      
   /*** powerUpTurn **************************************
      * Purpose: moves powerups and collisions with mario   *
      * Parameters: time | (does not do anything atm)       *
      * Returns: none                                       *
      ******************************************************/
   public void powerUpTurn(double time){
      if (dead == false){
         int mushroomHit;
         for (int i = 0; i < powerUps.length; i++){
            if (powerUps[i]!=null &&powerUps[i].gotten == false){
               if(powerUps[i].getClass()!=SuperStar.class){//start
                  powerUps[i].shouldTurn(blocks,blocks.length,powerUps,0,i);
                  powerUps[i].shouldFall(blocks,blocks.length,holes,holes.length);
                  powerUps[i].fall(blocks,blocks.length);
                  powerUps[i].move();
               }
               else{
                  powerUps[i].move(blocks,blocks.length);
                  
                  powerUps[i].shouldTurn(blocks,blocks.length,powerUps,0,i);
               }//end
               mushroomHit = powerUps[i].inBlock(this.x, this.y, width, height,time)[0];
               if (mushroomHit !=0){
                  powerUps[i].gotten = true;
                  
                     
                  if (powerUps[i].getClass() == OneUp.class){
                     oneUpSound.play();
                     lives++;
                  }
                  else if (powerUps[i].getClass() == FireFlower.class||powerUps[i].getClass()==Mushroom.class){
                     if(powerUps[i].getClass()==FireFlower.class&&(bigMario||bigStarMario)){
                        powerUpSound.play();
                        fireFlowerMario = true;
                        bigMario = false;
                        stopAccel = true;
                        marioType = 3;
                        score += 1000;
                     }
                     
                     else if(!bigStarMario&&!bigMario&&!fireFlowerMario){  
                        powerUpSound.play();
                        bigMario = true;
                        stopAccel = true;
                        marioType = 2;
                        score += 1000;
                        for (double h = 0; h<=(2000/300);h++){
                           if (currentMario == bigStandingMario&&rightFacing&&!onFlagPole&&!endingGame){
                              currentMario = standingMario;
                              y+=45;
                           }
                           else if(currentMario == bigLStandingMario&&!rightFacing&&!onFlagPole&&!endingGame){
                              currentMario = lStandingMario;
                              y+=45;
                           }
                           else if (currentMario != bigStandingMario && rightFacing&&!onFlagPole&&!endingGame){           
                              currentMario = bigStandingMario;
                              y -= 45;
                           }
                           else if(currentMario != bigLStandingMario && !rightFacing&&!onFlagPole&&!endingGame){
                              currentMario = bigLStandingMario;
                              y -=45;
                           }
                           draw.paintImmediately(oneGame.getBounds());
                           try{
                              Thread.sleep(300);
                           }
                           catch(InterruptedException e){
                           }
                        }
                        if(!onFlagPole&&!endingGame)
                           currentMario = bigStandingMario;
                        width = 45;
                        height = 90;
                     }
                     else if(powerUps[i].getClass() == Mushroom.class){
                        score+=1000;
                     }
                  }
                  
                  else if (powerUps[i].getClass() == SuperStar.class){		
                     //Play star sound effect	
                     overWorld.stop();
                     superStarSound.play();	
                     if (bigMario||fireFlowerMario)
                     {		
                        if (bigMario)
                           prevCurMario = 1;
                        else if (fireFlowerMario)
                           prevCurMario = 2;
                        bigStarMario = true;		
                     }
                     else
                     {
                        starMario=true;
                     }
                     starMarioTime = System.currentTimeMillis();
                     //invMario = true;
                     bigMario = false;		
                     //fireFlowerMario = false;
                     stopAccel = true;		
                     score += 1000;
                  }
               }
            }
         }
         draw.paintImmediately(oneGame.getBounds());
      }
   }
   /*** die **********************************************
      * Purpose: when mario dies                            *
      * Parameters: none                                    *
      * Returns: none                                       *
      ******************************************************/
   public void die(){ //Death Animation
      if (bigMario == false && fireFlowerMario == false&&!dead&&!invMario&&starMario==false&&bigStarMario==false)		      
      {
         currentMario = deadMario;
         lives--;
      // try {
        // draw.paintImmediately(oneGame.getBounds());
        // Thread.sleep(500);
      // } catch (InterruptedException f) {
        // f.printStackTrace();
      // }
         overWorld.stop();
         underGround.stop();
         bowserLevelSound.stop();
         if(lives!=0){
            loseLifeSound.play();
         }
         else{
            loseLifeSound.play();
             
         }
         int counti=0;
         int velocity = 5;
         for (double i = 0 ; i < 5; i=i+0.2){
            try {
               Thread.sleep(25);
            } catch (InterruptedException f) {
               f.printStackTrace();
            }
            y -= Math.round(velocity - i);
            cam.center(x,y);
            draw.paintImmediately(oneGame.getBounds());
         }
         while(y< 600){
            try {
               Thread.sleep(30);
            } catch (InterruptedException f) {
               f.printStackTrace();
            }
            y -= (int)(((-0.15)*counti*counti));
            counti++;
            draw.paintImmediately(oneGame.getBounds());
         }
         dead = true;
         
         score -= 1000;
         
         if (score <= 0){
            score = 0;
         }
         if (lives != 0){
            
            try{
               Thread.sleep(3500);
            } catch(InterruptedException e){
            }
            if (currentLevel.equals("Levels//Level1.txt") || currentLevel.equals("Underground.txt")){
               new onePlayerGame("Levels//Level1.txt", 1, 1, lives,CoinCount,400,score,x,false);
               host.timer1.stop();
               oneGame.dispose();
               
            } else if (currentLevel.equals("Levels//Level2.txt") || currentLevel.equals("Levels//bowser.txt")){
               new onePlayerGame("Levels//Level2.txt",1,1,lives,CoinCount,400,score,x,false);
               host.timer1.stop();
               oneGame.dispose();
            }   
         }
         else {
            if (lives == 0){		           
               gameover = true;
               try{
                  Thread.sleep(3500);
               } catch(InterruptedException e){
               }		
               gameOverSound.play();
               gameoverTime = System.currentTimeMillis();	               
                         
               draw.paintImmediately(oneGame.getBounds());	
               
            
            }
         //DRAW END GAME HERE
            		            
         }		
      }		
      else if (bigMario == true && fireFlowerMario == false&&!invMario)		
      {		
         pipeSound.play();
         if (currentMario != bigDuckingMario&&currentMario != lDuckingMario)
         {
            y+=45;
         }
         dePowerUp(2);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {		
         }		
         y-=45;
         dePowerUp(5);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {		
         }		
         y+=45;
         dePowerUp(2);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {		
         }	
         y-=45;	
         dePowerUp(5);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {		
         }		
         y+=45;
         dePowerUp(2);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {		
         }		
      		
      		
      }		
      else if (fireFlowerMario == true&&!invMario)		
      {		
         pipeSound.play();
         dePowerUp(3);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {		
         }		
         dePowerUp(6);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {		
         }		
         dePowerUp(3);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {		
         }		
         dePowerUp(6);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {		
         }		
         dePowerUp(3);		
         draw.paintImmediately(oneGame.getBounds());		
         try{		
            Thread.sleep(250);		
         }		
         catch (InterruptedException e)		
         {            }
      }
   }
   /*** dePowerUp ****************************************
      * Purpose: when mario loses his power up              *
      * Parameters: curMario | the state of mario           *
      * Returns: none                                       *
      ******************************************************/     
   public void dePowerUp(int curMario){		
      invMario = true;		
      if (System.currentTimeMillis() - invMarioTime > 2000)		
      {		
         invMarioTime = System.currentTimeMillis();		
      }		
      if (curMario == 3) //fire flower to big
      {	         	
         fireFlowerMario = false;		
         bigMario = true;		
         width = 35;		
         height = 90;		
         if (rightFacing && currentMario == fireStandingMario)		
         {		
            currentMario = bigStandingMario;		
         } 		
         else if (!rightFacing && currentMario == lFireStandingMario)		
         {		
            currentMario = bigLStandingMario;		
         }		
         else if (rightFacing && currentMario == fireJumpingMario)		
         {		
            currentMario = bigJumpingMario;		
         }		
         else if (!rightFacing && currentMario == lFireJumpingMario)		
         {		
            currentMario = bigLJumpingMario;		
         }		
         else if (rightFacing && currentMario == fireRunningMario)		
         {		
            currentMario = bigRunningMario;		
         }		
         else if (!rightFacing && currentMario == lFireRunningMario)		
         {		
            currentMario = bigLRunningMario;		
         }	
         else if (rightFacing && currentMario == fireDuckingMario)
         {
            currentMario = duckingMario;
         }	
         else if (!rightFacing && currentMario == fireLDuckingMario)
         {
            currentMario = lDuckingMario;
         }	
         else if (!leftFacing && currentMario == fireSlidingMario)
         {
            currentMario = bigSlidingMario;
         }	
         else if (leftFacing && currentMario == lFireSlidingMario)
         {
            currentMario = bigLSlidingMario;
         }	
      	
      }		
      else if (curMario == 2)		//big to small
      {			
         if (currentMario == lDuckingMario||currentMario == bigDuckingMario)
         {
            width = 45;
            
            if (currentMario == lDuckingMario)
            {
               currentMario = lStandingMario;
            }
            else
            {
               currentMario = standingMario;  
            }
            duckCheck = false;
         }
         fireFlowerMario = false;		
         bigMario = false;	
         width = 45;		
         height = 44;		
         if (rightFacing && currentMario == bigStandingMario)		
         {		
            currentMario = standingMario;		
         } 		
         else if (!rightFacing && currentMario == bigLStandingMario)		
         {		
            currentMario = lStandingMario;		
         }		
         else if (rightFacing && currentMario == bigJumpingMario)		
         {		
            currentMario = jumpingMario;		
         }		
         else if (!rightFacing && currentMario == bigLJumpingMario)		
         {		
            currentMario = lJumpingMario;		
         }		
         else if (rightFacing && currentMario == bigRunningMario)		
         {		
            currentMario = runningMario;		
         }		
         else if (!rightFacing && currentMario == bigLRunningMario)		
         {		
            currentMario = lRunningMario;		
         }		
         else if (!leftFacing && currentMario == bigSlidingMario)
         {
            currentMario = slidingMario;
         }	
         else if (leftFacing && currentMario == bigLSlidingMario)
         {
            currentMario = lSlidingMario;
         }			
      }		
      else if (curMario == 6) //big to fire		
      {		
         fireFlowerMario = true;		
         bigMario = false;		
         width = 45;		
         height = 90;		
         if (rightFacing && currentMario == bigStandingMario)		
         {		
            currentMario = fireStandingMario;		
         } 		
         else if (!rightFacing && currentMario == bigLStandingMario)		
         {		
            currentMario = lFireStandingMario;		
         }		
         else if (rightFacing && currentMario == bigJumpingMario)		
         {		
            currentMario = lFireJumpingMario;		
         }		
         else if (!rightFacing && currentMario == bigLJumpingMario)		
         {		
            currentMario = lFireJumpingMario;		
         }		
         else if (rightFacing && currentMario == bigRunningMario)		
         {		
            currentMario = fireRunningMario;		
         }		
         else if (!rightFacing && currentMario == bigLRunningMario)		
         {		
            currentMario = lFireRunningMario;		
         }		
         else if (rightFacing && currentMario == duckingMario)
         {
            currentMario = fireDuckingMario;
         }	
         else if (!rightFacing && currentMario == lDuckingMario)
         {
            currentMario = fireLDuckingMario;
         }	
         else if (!leftFacing && currentMario == bigSlidingMario)
         {
            currentMario = fireSlidingMario;
         }	
         else if (leftFacing && currentMario == bigLSlidingMario)
         {
            currentMario = lFireSlidingMario;
         }	
      
      }		
      else if (curMario == 5) //small to big
      {		
         fireFlowerMario = false;		
         bigMario = true;		
         width = 45;		
         height = 90;		
         if (rightFacing && currentMario == standingMario)		
         {		
            currentMario = bigStandingMario;		
         } 		
         else if (!rightFacing && currentMario == lStandingMario)		
         {		
            currentMario = bigLStandingMario;		
         }		
         else if (rightFacing && currentMario == jumpingMario)		
         {		
            currentMario = bigJumpingMario;		
         }		
         else if (!rightFacing && currentMario == lJumpingMario)		
         {		
            currentMario = bigLJumpingMario;		
         }		
         else if (rightFacing && currentMario == runningMario)		
         {		
            currentMario = bigRunningMario;		
         }		
         else if (!rightFacing && currentMario == lRunningMario)		
         {		
            currentMario = bigLRunningMario;		
         }	
         else if (!leftFacing && currentMario == slidingMario)
         {
            currentMario = bigSlidingMario;
         }	
         else if (leftFacing && currentMario == lSlidingMario)
         {
            currentMario = bigLSlidingMario;
         }
      }	
   }
   /*** marioDeathJump ***********************************
      * Purpose: when he kills an enemy by jumping          *
      * Parameters: none                                    *
      * Returns: none                                       *
      ******************************************************/    
   public void marioDeathJump(){
      inBlockResults = checkInBlock(blocks,blocks.length,timeCount);
      if(y<485-height){
          //If he hits a block while sliding
         if (inBlockResults[0] !=-1&&(inBlockResults[0]!=-1&&blocks[inBlockResults[0]].getClass() != AntiBlock.class)){
            if (inBlockResults[1] == 1){
               y = blocks[inBlockResults[0]].y -height;
            }
            else {
               fall();
            }
            deathJumpTime = 0;
            killJump = false;
         }
      
         deathJumpTime+=0.5;
         y += (Math.round((deathJumpTime-10)*0.5));
         cam.center(x,y);
         draw.paintImmediately(oneGame.getBounds());
       
              
      }
      else{
         killJump =false;
         deathJumpTime =0;
       
         y = 485-height;
         timeCount = 0;
         if(!onFlagPole&&!endingGame){
            if(left == true){
               updateMario();    
            }
            else if (leftFacing == false && !right){
               updateMario();    
            }
            else{
               updateMario();    
            }
         }
         else{
            updateMario();    
         }
         
         draw.paintImmediately(oneGame.getBounds());
      }
   }
   /*** goombaTurn ***************************************
      * Purpose: enemies colliding with mario and blocks and*
      * movement                                            *
      * Parameters: none (time does not do anything atm)    *
      * Returns: none                                       *
      ******************************************************/
   public void goombaTurn(double time){ //Moves all the goomba and checks if a goomba hits Mario
      if (dead == false){
         int enemyHit;
         for (int i = 0; i < enemies.length; i++){
            if (enemies[i].gotten == false||enemies[i].shellMove){
               if(enemies[i].getClass()!= Piranha.class){
                  enemies[i].shouldTurn(blocks,blocks.length,enemies,enemies.length,i);
                  enemies[i].shouldFall(blocks,blocks.length,holes,holes.length);
                  enemies[i].fall(blocks,blocks.length);
               }
               if((enemies[i].shell == false||enemies[i].shellMove == true)&&enemies[i].getClass() !=Piranha.class)
                  enemies[i].move();
               if(enemies[i].getClass() == Piranha.class){
                  enemies[i].move(x,y,width,height);
               }
               enemyHit = enemies[i].inBlock(this.x, this.y, width, height,time)[0];
               if (enemies[i].getClass() == Goomba.class)
               {
                  if (enemyHit !=0){
                     if (starMario||bigStarMario)
                     {
                        enemies[i].killedByKoopa = true;
                        enemies[i].deathTime = System.currentTimeMillis();
                        enemies[i].gotten= true;
                     }
                  
                     if (enemyHit != 1 ){
                        
                     
                     //dead = true;
                        die(); //If a goomba hits mario on the not top, Mario dies
                     }
                     else if (!invMario&&!starMario&&!bigStarMario) {
                        enemyKilledSound.play();
                        enemies[i].gotten = true;
                        
                        
                        enemies[i].obtained = true;
                        enemies[i].deathTime = System.currentTimeMillis();
                        killJump = true;
                        deathJumpTime =0;
                        goombaToBeKilled = i;
                        jump=false;
                     //timeCount =0;
                     }
                  }
               }
               else if (enemies[i].getClass() == Koopa.class)
               {
                  if(enemyHit !=0){
                     if (starMario||bigStarMario)
                     {
                        enemies[i].killedByKoopa = true;
                        enemies[i].deathTime = System.currentTimeMillis();
                        enemies[i].gotten= true;
                     }
                     if((enemies[i].shell == false||enemies[i].shellMove) && enemyHit !=1&&killJump == false&&System.currentTimeMillis()-enemies[i].deathTime>600){
                        die();
                     //subract life
                     //play death music
                     }
                     else if((enemies[i].shell == false||enemies[i].shellMove) && enemyHit == 1 && killJump == false&&!invMario&&!starMario&&!bigStarMario){
                        enemyKilledSound.play();
                        enemies[i].shell = true;
                        enemies[i].koopaTime = System.currentTimeMillis();
                        enemies[i].shellMove = false;
                        enemies[i].deathTime = System.currentTimeMillis();
                        goombaToBeKilled = i;
                        killJump = true;
                        deathJumpTime = 0;
                        jump = false;
                        
                     }
                     else if (enemies[i].shell == true && enemies[i].shellMove == false&&System.currentTimeMillis()-enemies[i].deathTime>100&&!invMario){
                        if(enemies[i].koopaShellDirection(x,y,width,height) == 1){
                           enemies[i].dir = 5;
                           enemies[i].shellMove = true;
                           collisionSound.play();
                        }
                        else if(enemies[i].koopaShellDirection(x,y,width,height) == 2){
                           enemies[i].dir = -5;
                           enemies[i].shellMove = true;
                           collisionSound.play();
                        }
                     }
                     
                    
                  }
                  if(System.currentTimeMillis()-enemies[i].koopaTime>4000&&enemies[i].shell == true&&enemies[i].shellMove == false){
                     enemies[i].comingBack = true;
                     
                  }
                  if (System.currentTimeMillis() - enemies[i].koopaTime > 5000&&enemies[i].shell == true && enemies[i].shellMove == false)
                  {
                     enemies[i].comingBack = false;
                     enemies[i].koopaTime = System.currentTimeMillis();
                     if(enemies[i].dir>0){
                        enemies[i].dir = 1;
                     }
                     else {
                        enemies[i].dir = -1;
                     }
                     enemies[i].shellMove = false;
                     enemies[i].shell=false;
                     enemies[i].gotten = false;
                  }   
               }
               else if (enemies[i].getClass() == Piranha.class)
               {
                  if(enemyHit !=0){
                     if (starMario||bigStarMario)
                     {
                        //enemies[i].killedByKoopa = true;
                        //enemies[i].deathTime = System.currentTimeMillis();
                        enemies[i].gotten= true;
                     }
                     else{
                        die();
                     //subract life
                     //play death music
                     }
                                             
                  }
               }
            }
         }
         
         cam.center(x,y);
         draw.paintImmediately(oneGame.getBounds());
      }
   }   /*** fireballTurn *************************************
      * Purpose: the use of a fireball and collisions       *
      * Parameters: none                                    *
      * Returns: none                                       *
      ******************************************************/
   public void fireballTurn(){
      for(int i = 0; i<3;i++){
         if(fireballs[i] != null&&fireballs[i].active == true){
            int fireballSide = fireballs[i].move(blocks,blocks.length,enemies,enemies.length);
            if(fireballSide!=-1){
               enemyKilledSound.play();
               enemies[fireballSide].enabled = false;
               enemies[fireballSide].gotten = true;
               enemies[fireballSide].deathTime = System.currentTimeMillis();
               enemies[fireballSide].killedByKoopa = true;
            }
         }
      }
   }
   /*** checkRecentKill **********************************
      * Purpose: checks the most recent kill                *
      * Parameters:                                         *
      * - enemies | array of enemies                        *
      * - enemyLength | the length of array                 *
      * - neglect | neglects enemy checking itself          *
      * Returns:                                            *
      * - true | if there is a recent kill                  *             
      * - false | if there is no recent kill                *
      ******************************************************/
   public boolean checkRecentKill(Goomba[] enemies,int enemyLength,int neglect){
      for (int i = 0; i<enemyLength;i++){
         if (System.currentTimeMillis() - enemies[i].deathTime<1000&&i!=neglect){
            return true;
         }
      }
      
      return false;
   }
   /*** getGoingUpPipe ***********************************
      * Purpose: finds the nearest up pipe from the x pos   *
      * Parameters:                                         *
      * - pX | x coord of pipe                              *                        
      * - p | array of avaliable pipes                      *                 
      * - pipeLength | the length of pipe array             *
      * Returns: toReturn | the pipe to be returned         *
      ******************************************************/
   public pipe getGoingUpPipe(int pX, pipe[] p,int pipeLength){
      int shortestDistance=99999;
      pipe toReturn=null;
      for (int i = 0; i<pipeLength;i++){
         if(p[i].x-pX>0&&p[i].x-pX<shortestDistance&&p[i].up==true){
            toReturn = p[i];
            shortestDistance = p[i].x-pX;
         }
      }
      return toReturn;
   }
     /*** updateMario *************************************
        Purpose: set current Mario image to correct one            *
        * Parameters: none                                    *                              
        * Returns: none                                       *
        ******************************************************/
   public void updateMario()
   {
      if(onFlagPole){
         if (fireFlowerMario){
                        //fireFlower mario
            currentMario = fireFlagMario;
                        
         }
         else if (bigMario){
                        //bigMario
            currentMario = bigFlagMario;
                        
         }
         else {
            currentMario = flagMario;
         }
      }
      if(!endingGame&&!onFlagPole){
         if (bigMario && !fireFlowerMario&&!bigStarMario) //bigMario
         {
            marioType = 2;
            if (jump&&(rightFacing||right)) //right jump
            {
               currentMario = bigJumpingMario;
            }
            else if (jump&&(!rightFacing||left)) //left jump
            {
               currentMario = bigLJumpingMario;
            }
            else if (duckCheck&&rightFacing) //right ducking
            {
               currentMario = bigDuckingMario;
            }
            else if (duckCheck&&!rightFacing)//left ducking
            {
               currentMario = lDuckingMario;
            }
            else if (!jump&&right&&!duckCheck) //Right running
            {
               currentMario = bigRunningMario;
            }
            else if (!jump&&left&&!duckCheck) //Left running
            {
               currentMario = bigLRunningMario;
            }
            else
            {
               if (rightFacing) //right standing
               {
                  currentMario = bigStandingMario;
               }
               else if (!rightFacing) //left standing
               {
                  currentMario = bigLStandingMario;
               }
            }
         }
         else if (fireFlowerMario&&!bigStarMario) //FireFlowerMario
         {
            marioType = 3;
            if (jump&&(rightFacing||right)) //right jump
            {
               currentMario = fireJumpingMario;
            }
            else if (jump&&(!rightFacing||left)) //left jump
            {
               currentMario = lFireJumpingMario;
            }
            else if (duckCheck&&rightFacing) //right ducking
            {
               currentMario = fireDuckingMario;
            }
            else if (duckCheck&&!rightFacing)//left ducking
            {
               currentMario = fireLDuckingMario;
            }
            else if (!jump&&right&&!duckCheck) //Right running
            {
               currentMario = fireRunningMario;
            }
            else if (!jump&&left&&!duckCheck) //Left running
            {
               currentMario = lFireRunningMario;
            }
            else
            {
               if (rightFacing) //right standing
               {
                  currentMario = fireStandingMario;
               }
               else if (!rightFacing) //left standing
               {
                  currentMario = lFireStandingMario;
               }
            }
         }
         else if (bigStarMario) //Big Star Mario
         {
            if (jump&&(rightFacing||right)) //right jump
            {
               currentMario = starBigJumpingMario;
            }
            else if (jump&&(!rightFacing||left)) //left jump
            {
               currentMario = starBigLJumpingMario;
            }
            else if (duckCheck&&rightFacing) //right ducking
            {
               currentMario = starBigDuckingMario;
            }
            else if (duckCheck&&!rightFacing)//left ducking
            {
               currentMario = starBigLDuckingMario;
            }
            else if (!jump&&right&&!duckCheck) //Right running
            {
               currentMario = starBigRunningMario;
            }
            else if (!jump&&left&&!duckCheck) //Left running
            {
               currentMario = starBigLRunningMario;
            }
            else
            {
               if (rightFacing) //right standing
               {
                  currentMario = starBigStandingMario;
               }
               else if (!rightFacing) //left standing
               {
                  currentMario = starBigLStandingMario;
               }
            }
         }
         else if (starMario) //small Star Mario
         {
            if (jump&&(rightFacing||right)) //right jump
            {
               currentMario = starJumpingMario;
            }
            else if (jump&&(!rightFacing||left)) //left jump
            {
               currentMario = starLJumpingMario;
            }
            else if (!jump&&right&&!duckCheck) //Right running
            {
               currentMario = starRunningMario;
            }
            else if (!jump&&left&&!duckCheck) //Left running
            {
               currentMario = starLRunningMario;
            }
            else
            {
               if (rightFacing) //right standing
               {
                  currentMario = starStandingMario;
               }
               else if (!rightFacing) //left standing
               {
                  currentMario = starLStandingMario;
               }
            }
         }
         else //small mario
         {
            marioType = 1;
            if (jump&&(rightFacing||right)) //right jump
            {
               currentMario = jumpingMario;
            }
            else if (jump&&(!rightFacing||left)) //left jump
            {
               currentMario = lJumpingMario;
            }
            else if (!jump&&right&&!duckCheck) //Right running
            {
               currentMario = runningMario;
            }
            else if (!jump&&left&&!duckCheck) //Left running
            {
               currentMario = lRunningMario;
            }
            else
            {
               if (rightFacing) //right standing
               {
                  currentMario = standingMario;
               }
               else if (!rightFacing) //left standing
               {
                  currentMario = lStandingMario;
               }
            }
         }
      }
   }
  
      /*** setHighScore *************************************
      * Purpose: Have score for the next game               *
      * Parameters: highScore | saves highscore             *
      * Returns: none                                       *
      ******************************************************/

   public void setHighScore(long highScore){
      try{
         FileWriter fw = new FileWriter("HighScore.txt");
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write(String.valueOf(highScore));
         bw.close();
      }
      catch (IOException e){
      }
   }
         
   /*** main *********************************************
      * Purpose: starts the onePlayerGame constructor       *
      * Parameters: args | string of arguments              *
      * Returns: none                                       *
      ******************************************************/
   public static void main(String[] args)
   {
      new onePlayerGame("Levels//Level1.txt",1,1,3,0,400,0,0,false);	
   }
   /*** Host *********************************************
      * Purpose: creates a new thread                       *
      * Parameters: none                                    *
      * Returns: none                                       *
      ******************************************************/
   public class Host extends Thread implements ActionListener{
      javax.swing.Timer timer1 = new javax.swing.Timer(1, this);
       /*** run *********************************************
         * Purpose: starts the thread                          *
         * Parameters:                                         *                               
         * Returns: none                                       *
         ******************************************************/ 
      public void run(){
         timer1.start();
         
      
      }
      
           
      /*** actionPerformed **********************************
         * Purpose: an action performed when user inputs feedback*
         * Parameters: e | the action that is performed        *
         * Returns: none                                       *
         ******************************************************/
      public void actionPerformed(ActionEvent e){
         //System.out.println(System.currentTimeMillis()-actionPreformedTime);
         if (currentLevel.equals("Levels//bowser.txt") && axe.x - 400 < x){
            //cam.camOn(false);
            maxPos = axe.x;
         }   
         updateMario();
         
         if (System.currentTimeMillis() - invMarioTime > 2000)		
         {		
            invMario = false;		
         } 
         if (System.currentTimeMillis() - starMarioTime > 7000)
         {
            if (starMario)
            {
               starMario = false;
               superStarSound.stop();
               overWorld.play();
            }
            else if (bigStarMario)
            {
               overWorld.play();
               bigStarMario = false;
               if (prevCurMario == 1)
                  bigMario = true;
               else if (prevCurMario == 2)
                  fireFlowerMario = true;
            }
         }
         if(dead){
            timer1.stop();
         }
         if(!dead&&!endingGame){
            if(bowser!=null&&!endingGame){//start
               bowser.update(x,y,width,height,maxPos,blocks,blocks.length,1149-cam.getxOffset());
               if(bowser.shouldDie(x,y,width,height,timeCount)){
                  die();
               }
            }//end
            if(System.currentTimeMillis()-actionPreformedTime>=10){
               actionPreformedTime = System.currentTimeMillis();
               curPos = x;
               
               if (maxPos <= curPos)
               {
                  maxPos = curPos;
                  invisBlock.x = maxPos - 435;
                  cam.camOn(true);
               }
               else
               {
                  cam.camOn(false);
               }
               if(y>650&&!dead){
                  //dead = true;
                  invMario = false;
                  die();
               }
               else{
                  fireballTurn();
                  goombaTurn(timeCount); //Moves all the goombas
                  powerUpTurn(timeCount);
                  prevAccel = acceleration(tAccel);//Stores previous accel (for slide)
                  if(killJump&&!starMario&&!bigStarMario){
                     marioDeathJump();
                  
                  }
                  if (left&&!right)
                  {
                     if (stopAccel)
                     {
                        tAccel = System.currentTimeMillis();
                     }
                     if(!duckCheck)
                        x+= acceleration(tAccel);
                  
                     if(jump==false){
                        updateMario();    
                        if(invisBlock.inBlock(x,y,width,height,10)[0]==4){		
                           x = invisBlock.x+invisBlock.width;		
                        }
                        inBlockResults = checkInBlock(blocks,blocks.length,10.0); //Makes sure mario doesnt go inside a block
                        if(!duckCheck)
                           stopAccel = false;
                        if (inBlockResults[0] !=-1&&blocks[inBlockResults[0]].getClass() != AntiBlock.class&&!comingUp){
                           if (inBlockResults[1] == 4){
                              x = blocks[inBlockResults[0]].x +blocks[inBlockResults[0]].width;
                              stopAccel = true;
                           }
                        }
                     
                        timer1.restart();
                     }
                     
                     else{
                        if(!duckCheck)
                           x-=2;
                     }
                     cam.center(x,y);
                     draw.paintImmediately(oneGame.getBounds());
                  }
                  if (right&&!left)
                  {
                     if (stopAccel)
                     {
                        tAccel = System.currentTimeMillis();
                     }
                     if(!duckCheck)
                        x+= acceleration(tAccel);
                  
                     if(jump ==false){
                        updateMario();    
                        if(invisBlock.inBlock(x,y,width,height,10)[0]==4){		
                           x = invisBlock.x+invisBlock.width;		
                        }
                        inBlockResults = checkInBlock(blocks,blocks.length,10.0);
                        if(!duckCheck)
                           stopAccel = false;
                        if (inBlockResults[0] !=-1&&blocks[inBlockResults[0]].getClass() != AntiBlock.class&&!comingUp){
                           if (inBlockResults[1] == 3){
                              x = blocks[inBlockResults[0]].x -width;
                              stopAccel = true;
                           }
                        }
                        timer1.restart();
                     }
                     else{
                        if(!duckCheck)
                           x+=2;
                     }
                  
                     cam.center(x,y);
                     draw.paintImmediately(oneGame.getBounds());
                  
                  }
                  if (jump||hitSide){
                     if(left == true || (leftFacing == false && !right)){
                        if (bigMario) {
                           if(!jumpSound){
                              bigJumpSound.play();
                              jumpSound = true;
                           }
                           if(!onFlagPole&&!endingGame)
                              updateMario();       
                        }
                        else if(fireFlowerMario) {
                           if(!jumpSound){
                              jumpSound = true;
                              bigJumpSound.play();
                           }
                           if(!onFlagPole&&!endingGame)
                              updateMario();    
                        }
                        else{
                           if(!jumpSound){
                              smallJumpSound.play();
                              jumpSound = true;
                           }
                           if(!onFlagPole&&!endingGame)
                              updateMario();    
                        }
                     }
                     else{
                        if (bigMario) {
                           if(!jumpSound){
                              jumpSound = true;
                              bigJumpSound.play();
                           }
                           if(!onFlagPole&&!endingGame)
                              updateMario();       
                        }
                        else if(fireFlowerMario) {
                           if(!jumpSound){
                              jumpSound = true;
                              bigJumpSound.play();
                           }
                           if(!onFlagPole&&!endingGame)
                              updateMario();    
                        }
                        else{
                           if(!jumpSound){
                              smallJumpSound.play();
                              jumpSound = true;
                           }
                           if(!onFlagPole&&!endingGame)
                              updateMario();    
                        }
                     }
                     timeCount+=0.07; //Counts the amount of time since start of jump
                  
                  
                     gravity(timeCount);
                     cam.center(x,y);
                     draw.paintImmediately(oneGame.getBounds());
                  
                  
                     if (hitSide&&((y+height<blocks[bSide].y)||(y>blocks[bSide].y+blocks[bSide].height)))
                     {
                     
                        hitSide = false;
                        gravity(timeCount);
                        jump = true;
                        //timeCount = 0;
                     }
                  }
                  if(invisBlock.inBlock(x,y,width,height,10)[0]==4){		
                     x = invisBlock.x+invisBlock.width;		
                  }
                  inBlockResults = checkInBlock(blocks,blocks.length,timeCount);
                  if(41.55*((timeCount+0.1)-1.9)*((timeCount+0.1)-1.9)+290-startY>485-height&&jump&&(inBlockResults[0]==-1||inBlockResults[0]!=-1&&blocks[inBlockResults[0]].getClass() != AntiBlock.class)){ //Checks if Mario will land in the floor, if yes, draw Mario on floor
                     y = 485-height;
                     jump = false;
                     jumpSound = false;
                     updateMario();    
                     cam.center(x,y);
                     draw.paintImmediately(oneGame.getBounds());
                     onBlock = false;
                     fall();
                     timeCount = 0;
                  }
               
                  if(!comingUp&&checkShouldFall(blocks,blocks.length)==true&&jump == false&&killJump ==false&&!hitSide) //If Mario should fall, fall
                  {
                     onBlock = false;
                     fall();
                  }
               
               
                  cam.center(x,y);
                  draw.paintImmediately(oneGame.getBounds());
               }
            }
         }
      }
   }
}




















