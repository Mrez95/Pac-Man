import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;

public class Player extends Actor
{
    private boolean foodRefilled=true;
    private boolean left = true;
    private boolean right;
    private boolean down;
    private boolean up;
    private boolean pdown;
    private int foodEaten = 0;
    private int setImage=1;
    private int imageCounter=0;
    private int totalScore;
    private int ghostScores=0;
    private int p2Life=0;
    private int deadImage=1;
    public int life=3;
    public boolean playerIsDead;
    public boolean paused;
    private GreenfootSound loop = new GreenfootSound("playing-loop-sound.mp3");
    private GreenfootSound dieSound = new GreenfootSound("dead-sound.mp3");
    private GreenfootSound waka = new GreenfootSound("Waka-sound.mp3");
    private GreenfootSound fruits = new GreenfootSound("Eating-Fruit-sound.mp3");
    private void foodRefill()
    {
        setLocation(365, 440); setRotation(0); setImage(new GreenfootImage("pacman2.png"));
        int[][] map = {
        {13,9,9,9,9,9,9,9,9,9,9,9,9,9,9,0,9,9,9,9,9,9,9,9,9,9,9,0,9,9,9,9,9,9,9,9,9,9,9,9,9,9,14},
        {8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10},
        {8,0,17,17,17,17,17,17,17,17,17,17,17,17,0,5,0,17,17,17,17,16,17,17,17,17,0,5,0,17,17,17,17,17,17,17,17,17,17,17,17,0,10},
        {8,0,17,0,0,0,0,17,0,0,0,0,0,17,0,5,0,17,0,0,0,0,0,0,0,17,0,5,0,17,0,0,0,0,0,17,0,0,0,0,17,0,10},
        {8,0,16,0,2,3,0,17,0,2,4,3,0,17,0,7,0,17,0,18,11,11,11,20,0,17,0,7,0,17,0,2,4,3,0,17,0,2,3,0,16,0,10},
        {8,0,17,0,0,0,0,17,0,0,0,0,0,17,0,0,0,17,0,10,0,0,0,8,0,17,0,0,0,17,0,0,0,0,0,17,0,0,0,0,17,0,10},
        {8,0,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,0,21,9,9,9,19,0,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,0,10},
        {8,0,17,0,0,0,0,17,0,0,0,17,0,0,0,0,0,17,0,0,0,0,0,0,0,17,0,0,0,0,0,17,0,0,0,17,0,0,0,0,17,0,10},
        {8,0,17,0,2,3,0,17,0,6,0,17,0,2,4,3,0,17,17,17,17,17,17,17,17,17,0,2,4,3,0,17,0,6,0,17,0,2,3,0,17,0,10},
        {8,0,17,0,0,0,0,17,0,5,0,17,0,0,0,0,0,0,0,0,0,17,0,0,0,0,0,0,0,0,0,17,0,5,0,17,0,0,0,0,17,0,10},
        {8,0,17,17,17,17,17,17,0,5,0,17,17,17,17,0,18,11,11,20,0,17,0,18,11,11,20,0,17,17,17,17,0,5,0,17,17,17,17,17,17,0,10},
        {8,0,0,0,0,0,0,17,0,5,0,0,0,0,17,0,10,0,0,8,0,17,0,10,0,0,8,0,17,0,0,0,0,5,0,17,0,0,0,0,0,0,10},
        {0,11,11,11,11,20,0,17,0,10,4,4,3,0,17,0,21,9,9,19,0,17,0,21,9,9,19,0,17,0,2,4,4,8,0,17,0,18,11,11,11,11,0},
        {0,0,0,0,0,8,0,17,0,5,0,0,0,0,17,0,0,0,0,0,0,17,0,0,0,0,0,0,17,0,0,0,0,5,0,17,0,10,0,0,0,0,0},
        {0,0,0,0,0,8,0,17,0,7,0,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,0,7,0,17,0,10,0,0,0,0,0},
        {0,0,0,0,0,8,0,17,0,0,0,17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,17,0,0,0,17,0,10,0,0,0,0,0},
        {0,0,0,0,0,8,0,17,17,17,17,17,0,6,0,0,0,6,22,22,22,22,22,22,22,6,0,0,0,6,0,17,17,17,17,17,0,10,0,0,0,0,0},
        {0,0,0,0,0,8,0,17,0,0,0,0,0,5,0,0,0,5,0,0,0,0,0,0,0,5,0,0,0,5,0,0,0,0,0,17,0,10,0,0,0,0,0},
        {9,9,9,9,9,19,0,17,0,2,4,4,4,19,0,0,0,5,0,23,0,0,0,24,0,5,0,0,0,21,4,4,4,3,0,17,0,21,9,9,9,9,9},
        {0,0,0,0,0,0,0,17,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,17,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,17,0,0,0,0,0,0,0,0,0,5,0,0,0,27,0,0,0,5,0,0,0,0,0,0,0,0,0,17,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,17,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,17,0,0,0,0,0,0,0},
        {11,11,11,11,11,20,0,17,0,2,4,4,4,20,0,0,0,5,0,25,0,0,0,26,0,5,0,0,0,18,4,4,4,3,0,17,0,18,11,11,11,11,11},
        {0,0,0,0,0,8,0,17,0,0,0,0,0,5,0,0,0,5,0,0,0,0,0,0,0,5,0,0,0,5,0,0,0,0,0,17,0,10,0,0,0,0,0},
        {0,0,0,0,0,8,0,17,17,17,17,17,0,7,0,0,0,21,4,4,4,4,4,4,4,19,0,0,0,7,0,17,17,17,17,17,0,10,0,0,0,0,0},
        {0,0,0,0,0,8,0,17,0,0,0,17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,17,0,0,0,17,0,10,0,0,0,0,0},
        {0,0,0,0,0,8,0,17,0,6,0,17,17,17,17,17,17,17,17,17,17,1,17,17,17,17,17,17,17,17,17,17,0,6,0,17,0,10,0,0,0,0,0},
        {0,0,0,0,0,8,0,17,0,5,0,0,0,0,17,0,0,0,0,0,0,17,0,0,0,0,0,0,17,0,0,0,0,5,0,17,0,10,0,0,0,0,0},
        {0,9,9,9,9,19,0,17,0,10,4,4,3,0,17,0,18,11,11,20,0,17,0,18,11,11,20,0,17,0,2,4,4,8,0,17,0,21,9,9,9,9,0},
        {8,0,0,0,0,0,0,17,0,5,0,0,0,0,17,0,10,0,0,8,0,17,0,10,0,0,8,0,17,0,0,0,0,5,0,17,0,0,0,0,0,0,10},
        {8,0,17,17,17,17,17,17,0,5,0,17,17,17,17,0,21,9,9,19,0,17,0,21,9,9,19,0,17,17,17,17,0,5,0,17,17,17,17,17,17,0,10},
        {8,0,17,0,0,0,0,17,0,5,0,17,0,0,0,0,0,0,0,0,0,17,0,0,0,0,0,0,0,0,0,17,0,5,0,17,0,0,0,0,17,0,10},
        {8,0,17,0,2,3,0,17,0,7,0,17,0,2,4,3,0,17,17,17,17,17,17,17,17,17,0,2,4,3,0,17,0,7,0,17,0,2,3,0,17,0,10},
        {8,0,17,0,0,0,0,17,0,0,0,17,0,0,0,0,0,17,0,0,0,0,0,0,0,17,0,0,0,0,0,17,0,0,0,17,0,0,0,0,17,0,10},
        {8,0,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,0,18,11,11,11,20,0,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,0,10},
        {8,0,17,0,0,0,0,17,0,0,0,0,0,17,0,0,0,17,0,10,0,0,0,8,0,17,0,0,0,17,0,0,0,0,0,17,0,0,0,0,17,0,10},
        {8,0,16,0,2,3,0,17,0,2,4,3,0,17,0,6,0,17,0,21,9,9,9,19,0,17,0,6,0,17,0,2,4,3,0,17,0,2,3,0,16,0,10},
        {8,0,17,0,0,0,0,17,0,0,0,0,0,17,0,5,0,17,0,0,0,0,0,0,0,17,0,5,0,17,0,0,0,0,0,17,0,0,0,0,17,0,10},
        {8,0,17,17,17,17,17,17,17,17,17,17,17,17,0,5,0,17,17,17,17,16,17,17,17,17,0,5,0,17,17,17,17,17,17,17,17,17,17,17,17,0,10},
        {8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10},
        {12,11,11,11,11,11,11,11,11,11,11,11,11,11,11,0,11,11,11,11,11,11,11,11,11,11,11,0,11,11,11,11,11,11,11,11,11,11,11,11,11,11,15},
        };
        for(int x=0; x<map.length; x++) {
            for(int y=0; y<map[x].length; y++) {
                if(map[x][y]==16) {
                    getWorld().addObject(new Food1(), y*15+50, x*15+50);
                }
                if(map[x][y]==17) {
                    getWorld().addObject(new Food2(), y*15+50, x*15+50);
                }
            }
        }
        getWorld().addObject(new Ready(), 365, 530); foodRefilled=true;
    }
    public Player()
    {
        setImage(new GreenfootImage("pacman2.png"));
    }
    public void removeFood()
    {
        getWorld().removeObjects(getWorld().getObjects(Food.class));
    }
    public void act()
    {
        Pacman pw = (Pacman) getWorld();
        if(pw.type==0) {
            motionSet();
        }
        else {
            if(pw.type==2 && !getWorld().getObjects(Ready.class).isEmpty()) {
                setImage(new GreenfootImage("pacman2.png"));
                setLocation(350, 440); setRotation(0); deadImage=1; setImage=1; left=true; right=false; down=false; up=false;
            }
            else if(!getWorld().getObjects(Ready.class).isEmpty()) {
                setImage(new GreenfootImage("pacman2.png"));
                setLocation(365, 440); setRotation(0); deadImage=1; setImage=1; left=true; right=false; down=false; up=false;
            }
            if(getWorld().getObjects(Ready.class).isEmpty()) {
                if(!paused)
                {
                    Ghost isDead = isPlayerDead(); Player2 dead = otherDead();
                    if(dead==null) {
                        if(isDead==null && !playerIsDead) {
                            moveSet(); port(); addScores();
                            if(getWorld().getObjects(Menu.class).isEmpty() && !pw.muteS) loop.play();
                            else loop.stop();
                            if(pw.type==2) player2Life();
                            if(getWorld().getObjects(Food.class).isEmpty()) {
                                foodRefilled=false; foodRefill();
                            }
                            else if(getWorld().getObjects(Menu.class).isEmpty()) {
                                eatFood(); createFruit();
                            }
                        }
                        else if(!getObjectsInRange(25,Ghost.class).isEmpty()) {
                            playerIsDead=true;
                            loop.stop();
                        }
                        switchImage();
                    }
                    else {
                        setImage(new GreenfootImage("pacman6.png"));
                        if(!getWorld().getObjects(Menu.class).isEmpty()) {
                            getWorld().removeObjects(getWorld().getObjects(Score.class));
                            if(totalScore<100) getWorld().addObject(new Score(totalScore), 240, 355);
                            else if(totalScore<1000) getWorld().addObject(new Score(totalScore), 220, 355);
                            else if(totalScore<10000) getWorld().addObject(new Score(totalScore), 200, 355);
                            else if(totalScore<100000) getWorld().addObject(new Score(totalScore), 180, 355);
                            else getWorld().addObject(new Score(totalScore), 160, 355);
                        }
                        loop.stop();
                    }
                }
            }
            if (!playerIsDead) isPaused();
        }
    }
    private void motionSet()
    {
        int x=getX(); int y=getY();
        if(y==345) {
            left=true; right=false;
        }
        if(y==475) {
            left=false; right=true;
        }
        if(x==0 && y==345) setLocation(464,475);
        if(x==730 && y==475) setLocation(0,475);
        if(x==330 && y==475) setLocation(375, 345);
        motionMove(); motionImage();
    }
    private void motionMove()
    {
        if(left) {
            setLocation(getX()-2,getY()); setRotation(0);
        }
        else if(right) {
            setLocation(getX()+2,getY()); setRotation(180);
        }
    }
    private void motionImage()
    {
        if(imageCounter==10) {
            if(setImage==1) {
                setImage(new GreenfootImage("pacman2.png")); setImage=2;
            }
            else if(setImage==2) {
                setImage(new GreenfootImage("pacman3.png")); setImage=1;
            }
            imageCounter=0;
        }
        else imageCounter=imageCounter+1;
    }
    private void eatFood()
    {
        Actor Food1 = getOneObjectAtOffset(0,0, Food1.class); Actor Food2 = getOneObjectAtOffset(0,0, Food2.class); Actor Fruit = getOneObjectAtOffset(0,0, Fruit.class);
        totalScore=foodEaten+ghostScores;
        Pacman paw=(Pacman) getWorld();
        if(Food1!=null) {
            getWorld().removeObject(Food1); foodEaten = foodEaten + 5;
            if(!paw.muteS) waka.play();
        }
        else if(Food2!=null) {
            getWorld().removeObject(Food2); foodEaten = foodEaten + 2;
            if(!paw.muteS) waka.play();
        }
        else if(Fruit!=null) {
            getWorld().removeObject(Fruit); foodEaten = foodEaten + 200;
            if(!paw.muteS) fruits.play();
        }
        displayScore();
    }
    private void createFruit()
    {
        if(getWorld().getObjects(Fruit.class).isEmpty() && Greenfoot.getRandomNumber(10000)>9997) getWorld().addObject(new Fruit(), 365, 260);
    }
    private void moveSet()
    {
        Pacman pWorld = (Pacman) getWorld();
        if(Greenfoot.isKeyDown(pWorld.p1Left) && canMove(getX()-23, getY()) && canMove(getX()-23, getY()-22) && canMove(getX()-23, getY()+22) && canMove(getX()-23, getY()-6) && canMove(getX()-23, getY()+6) && canMove(getX()-23, getY()-12) && canMove(getX()-23, getY()+12)) {
            left = true; right = false; up = false; down = false;
        }
        else if(Greenfoot.isKeyDown(pWorld.p1Right) && canMove(getX()+23, getY()) && canMove(getX()+23, getY()-22) && canMove(getX()+23, getY()+22) && canMove(getX()+23, getY()-6) && canMove(getX()+23, getY()+6) && canMove(getX()+23, getY()-12) && canMove(getX()+23, getY()+12)) {
            left = false; right = true; up = false; down = false;
        }
        else if(Greenfoot.isKeyDown(pWorld.p1Up) && canMove(getX(), getY()-23) && canMove(getX()-22, getY()-23) && canMove(getX()+22, getY()-23) && canMove(getX()-6, getY()-23) && canMove(getX()+6, getY()-23) && canMove(getX()-12, getY()-23) && canMove(getX()+12, getY()-23)) {
            left = false; right = false; up = true; down = false;
        }
        else if(Greenfoot.isKeyDown(pWorld.p1Down) && canMove(getX(), getY()+23) && canMove(getX()-22, getY()+23) && canMove(getX()+22, getY()+23) && canMove(getX()-6, getY()+23) && canMove(getX()+6, getY()+23) && canMove(getX()-12, getY()+23) && canMove(getX()+12, getY()+23)) {
            left = false; right = false; up = false; down = true;
        }
        move();
    }
    private void move()
    {
        if(left && canMove(getX()-23, getY()) && canMove(getX()-23, getY()-12) && canMove(getX()-23, getY()+12)) {
            setLocation(getX()-1,getY()); setRotation(0);
        }
        else if(right && canMove(getX()+23, getY()) && canMove(getX()+23, getY()-12) && canMove(getX()+23, getY()+12)) {
            setLocation(getX()+1,getY()); setRotation(180);
        }
        else if(up && canMove(getX(), getY()-23) && canMove(getX()-12, getY()-23) && canMove(getX()+12, getY()-23)) {
            setLocation(getX(),getY()-1); setRotation(90);
        }
        else if(down && canMove(getX(), getY()+23) && canMove(getX()-12, getY()+23) && canMove(getX()+12, getY()+23))
        {
            setLocation(getX(),getY()+1); setRotation(270);
        }
    }
    private void switchImage()
    {
        if(imageCounter==15) {
            if(!playerIsDead) {
                if(setImage==1) {
                    setImage(new GreenfootImage("pacman2.png")); setImage=2;
                }
                else if(setImage==2) {
                    setImage(new GreenfootImage("pacman3.png")); setImage=3;
                }
                else if(setImage==3) {
                    setImage(new GreenfootImage("pacman2.png")); setImage=4;
                }
                else if(setImage==4) {
                    setImage(new GreenfootImage("pacman1.png")); setImage=1;
                }
            }
            else {
                if(deadImage==1) setImage(new GreenfootImage("pacman3.png"));
                setRotation(90); Pacman pacw=(Pacman) getWorld();
                if(pacw.muteS) dieSound.setVolume(0);
                else dieSound.setVolume(60);
                if(deadImage==5) {
                    setImage(new GreenfootImage("pacman4.png")); deadImage=6; dieSound.setVolume(60); dieSound.play();
                    if(pacw.muteS) dieSound.setVolume(0);
                }
                else if(deadImage==7) {
                    setImage(new GreenfootImage("pacman1.png")); deadImage=8;
                }
                else if(deadImage==9) {
                    setImage(new GreenfootImage("pacman5.png")); deadImage=10;
                }
                else if(deadImage==11) {
                    setImage(new GreenfootImage("pacman6.png")); deadImage=12;
                }
                else if(deadImage==13) {
                    setImage(new GreenfootImage("pacman dead.png")); deadImage=14;
                }
                else if(deadImage==15) {
                    setImage(new GreenfootImage("pacman6.png")); deadImage=16;
                }
                else if(deadImage==20) {
                    if(life==3) {
                        getWorld().removeObjects(getWorld().getObjects(Life.class)); getWorld().addObject(new Life(), 25, 475); getWorld().addObject(new Life(), 25, 525); life=2; getWorld().addObject(new Life(), 25, 270);
                        getWorld().addObject(new Ready(), 365, 530); setLocation(365, 440);
                        left=true; right=false; up=false; down=false; playerIsDead=false; imageCounter=0; deadImage=1;
                        setImage(new GreenfootImage("pacman2.png")); setRotation(0);
                        if(p2Life==3) {
                            getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 525); getWorld().addObject(new Life(), 715, 575); getWorld().addObject(new Life(), 715, 270);
                        }
                        else if(p2Life==2) {
                            getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 525); getWorld().addObject(new Life(), 715, 270);
                        }
                        else if(p2Life==1) {
                            getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 270);
                        }
                    }
                    else if(life==2) {
                        getWorld().removeObjects(getWorld().getObjects(Life.class)); getWorld().addObject(new Life(), 25, 475); life=1;  getWorld().addObject(new Life(), 25, 270);
                        getWorld().addObject(new Ready(), 365, 530); setLocation(365, 440);
                        left=true; right=false; up=false; down=false; playerIsDead=false; imageCounter=0; deadImage=1;
                        setImage(new GreenfootImage("pacman2.png")); setRotation(0);
                        if(p2Life==3) {
                            getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 525); getWorld().addObject(new Life(), 715, 575); getWorld().addObject(new Life(), 715, 270);
                        }
                        else if(p2Life==2) {
                            getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 525); getWorld().addObject(new Life(), 715, 270);
                        }
                        else if(p2Life==1) {
                            getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 270);
                        }
                    }
                    else if(life==1) {
                        getWorld().removeObjects(getWorld().getObjects(Life.class)); life=0;  getWorld().addObject(new Life(), 25, 270);
                        getWorld().addObject(new Ready(), 365, 530); setLocation(365, 440);
                        left=true; right=false; up=false; down=false; playerIsDead=false; imageCounter=0; deadImage=1;
                        setImage(new GreenfootImage("pacman2.png")); setRotation(0);
                        if(p2Life==3) {
                            getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 525); getWorld().addObject(new Life(), 715, 575); getWorld().addObject(new Life(), 715, 270);
                        }
                        else if(p2Life==2) {
                            getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 525); getWorld().addObject(new Life(), 715, 270);
                        }
                        else if(p2Life==1) {
                            getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 270);
                        }
                    }
                    else {
                        getWorld().addObject(new Menu(7), 365, 350); getWorld().addObject(new ButtonsOverlay(2), 180, 550); getWorld().addObject(new Buttons(8), 500, 555); deadImage=21;
                        getWorld().removeObjects(getWorld().getObjects(Score.class));
                        if(getWorld().getObjects(Score.class).isEmpty() && pacw.type==1) {
                            if(totalScore<100) getWorld().addObject(new Score(totalScore), 390, 355);
                                    else if(totalScore<1000) getWorld().addObject(new Score(totalScore), 370, 355);
                                    else if(totalScore<10000) getWorld().addObject(new Score(totalScore), 350, 355);
                                    else if(totalScore<100000) getWorld().addObject(new Score(totalScore), 320, 355);
                                    else getWorld().addObject(new Score(totalScore), 300, 355);
                        }
                        else if(getWorld().getObjects(Score.class).isEmpty() && pacw.type==2) {
                            if(totalScore<100) getWorld().addObject(new Score(totalScore), 240, 355);
                            else if(totalScore<1000) getWorld().addObject(new Score(totalScore), 220, 355);
                            else if(totalScore<10000) getWorld().addObject(new Score(totalScore), 200, 355);
                            else if(totalScore<100000) getWorld().addObject(new Score(totalScore), 180, 355);
                            else getWorld().addObject(new Score(totalScore), 160, 355);
                        }
                    }
                }
                else if (deadImage!=21) deadImage=deadImage+1;
            }
            imageCounter=0;
        }
        else imageCounter=imageCounter+1;
    }
    private void displayScore()
    {
        Actor score = new Score(); getWorld().removeObjects(getWorld().getObjects(Score.class));
        getWorld().addObject(score, 65, 430);
        score.setImage(new GreenfootImage("Score: " + totalScore, 30, java.awt.Color.YELLOW, java.awt.Color.BLACK));
    }
    private void port()
    {
        int x=getX(); int y=getY();
        if(x==680 && y==350) {
            setLocation(51,350); left=false; right=true;
        }
        if(x==50 && y==350) {
            setLocation(679,350); left=true; right=false;
        }
    }
    private void isPaused()
    {
        Pacman pw = (Pacman) getWorld();
        if(Greenfoot.isKeyDown(pw.pause) && !paused && !pdown && getWorld().getObjects(Menu.class).isEmpty()) {
            getWorld().addObject(new Menu(6), 365, 350);
            getWorld().addObject(new Buttons(7), 330, 400); getWorld().addObject(new ButtonsOverlay(2), 330, 480); getWorld().removeObjects(getWorld().getObjects(Score.class)); getWorld().removeObjects(getWorld().getObjects(Score2.class));
            paused=true; loop.stop();
        }
        else if(Greenfoot.isKeyDown(pw.pause) && paused && !pdown) {
            getWorld().removeObjects(getWorld().getObjects(Menu.class)); getWorld().removeObjects(getWorld().getObjects(Buttons.class));
            Pacman pwo = (Pacman) getWorld();
            if(!pwo.muteS) getWorld().addObject(new Buttons(6), 345, 670);
            else {
                Buttons mute = new Buttons(6); mute.setImage(new GreenfootImage("mute button 2.png")); mute.getImage().scale(30,30);
                getWorld().addObject(mute, 345, 670);
            }
            if(!pwo.musicS) getWorld().addObject(new Buttons(5), 390, 670);
            else {
                Buttons music = new Buttons(5); music.setImage(new GreenfootImage("music button 2.png")); music.getImage().scale(30,30);
                getWorld().addObject(music, 390, 670);
            }
            paused=false;
        }
        if(Greenfoot.isKeyDown("p")) pdown=true;
        else pdown=false;
        if(getWorld().getObjects(Menu.class).isEmpty()) paused=false;
    }
    private void player2Life()
    {
        int pl = 0;
        for (Object obj : getWorld().getObjects(Player2.class)) {
            Player2 a = (Player2) obj;
            pl += a.p2Life;
        }
        p2Life=pl;
    }
    private void addScores()
    {
        int gSum = 0;
        for (Object obj : getWorld().getObjects(Ghost.class)) {
            Ghost a = (Ghost) obj;
            gSum += a.playerScore;
        }
        ghostScores = gSum;
    }
    private boolean canMove(int x, int y)
    {
        if(getWorld().getObjectsAt(x, y, Wall.class).isEmpty()) return true;
        return false;
    }
    protected boolean PlayerDead()
    {
        return !getObjectsInRange(25,Ghost.class).isEmpty();
    }
    protected int getPosition(String axis)
    {
        if(axis=="x") return getX();
        else return getY();
    }
    private Ghost isPlayerDead()
    {
        for (Object obj : getWorld().getObjects(Ghost.class)) {
            Ghost a = (Ghost) obj;
            if (a.playerDie) return a;
        }
        return null;
    }
    private Player2 otherDead()
    {
        for (Object obj : getWorld().getObjects(Player2.class)) {
            Player2 i = (Player2) obj;
            if (i.playerIsDead) return i;
        }
        return null;
    }
}