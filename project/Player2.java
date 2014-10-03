import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;

public class Player2 extends Actor
{
    private boolean left;
    private boolean right = true;
    private boolean down;
    private boolean up;
    private int foodEaten = 0;
    private int setImage=1;
    private int deadImage=1;
    private int imageCounter=0;
    private int totalScore;
    private int ghostScores=0;
    private int p1Life=3;
    public int p2Life=3;
    public boolean playerIsDead;
    private GreenfootSound dieSound = new GreenfootSound("dead-sound.mp3");
    private GreenfootSound waka = new GreenfootSound("Waka-sound.mp3");
    private GreenfootSound fruits = new GreenfootSound("Eating-Fruit-sound.mp3");

    public Player2()
    {
        setImage(new GreenfootImage("2 pacman2.PNG"));
    }
    public void act()
    {
        if(!getWorld().getObjects(Ready.class).isEmpty()) {
                setImage(new GreenfootImage("2 pacman2.PNG"));
                setLocation(380, 440); setRotation(180); deadImage=1; setImage=1; left=false; right=true; down=false; up=false;
        }
        else if (getWorld().getObjects(Menu.class).isEmpty() || !getWorld().getObjects(Score2.class).isEmpty()) {
            Ghost isDead = isPlayerDead(); Player dead = otherDead();
            if(dead==null) {
                if(isDead==null && !playerIsDead) {
                    moveSet(); port(); addScores(); player1Life();
                    if(getWorld().getObjects(Menu.class).isEmpty()) eatFood();
                }
                else if(!getObjectsInRange(25,Ghost.class).isEmpty()) {
                    playerIsDead=true;
                }
                switchImage();
            }
            else {
                setImage(new GreenfootImage("pacman6.png"));
                if(!getWorld().getObjects(Menu.class).isEmpty()) {
                    getWorld().removeObjects(getWorld().getObjects(Score2.class));
                    if(totalScore<100) getWorld().addObject(new Score2(totalScore), 540, 355);
                    else if(totalScore<1000) getWorld().addObject(new Score2(totalScore), 520, 355);
                    else if(totalScore<10000) getWorld().addObject(new Score2(totalScore), 500, 355);
                    else if(totalScore<100000) getWorld().addObject(new Score2(totalScore), 480, 355);
                    else getWorld().addObject(new Score2(totalScore), 460, 355);
                }
            }
        }
    }
    private void eatFood()
    {
        Actor Food1 = getOneObjectAtOffset(0,0, Food1.class); Actor Food2 = getOneObjectAtOffset(0,0, Food2.class); Actor Fruit = getOneObjectAtOffset(0,0, Fruit.class);
        totalScore=foodEaten+ghostScores;
        Pacman pw = (Pacman) getWorld();
        if(Food1!=null) {
            getWorld().removeObject(Food1); foodEaten = foodEaten + 5;
            if(!pw.muteS) waka.play();
        }
        else if(Food2!=null) {
            getWorld().removeObject(Food2); foodEaten = foodEaten + 2;
            if(!pw.muteS) waka.play();
        }
        else if(Fruit!=null) {
            getWorld().removeObject(Fruit); foodEaten = foodEaten + 200;
            if(!pw.muteS) fruits.play();
        }
        displayScore();
    }
    private void moveSet()
    {
        Pacman pWorld=(Pacman) getWorld();
        if(Greenfoot.isKeyDown(pWorld.p2Left) && canMove(getX()-23, getY()) && canMove(getX()-23, getY()-22) && canMove(getX()-23, getY()+22) && canMove(getX()-23, getY()-6) && canMove(getX()-23, getY()+6) && canMove(getX()-23, getY()-12) && canMove(getX()-23, getY()+12)) {
            left = true; right = false; up = false; down = false;
        }
        else if(Greenfoot.isKeyDown(pWorld.p2Right) && canMove(getX()+23, getY()) && canMove(getX()+23, getY()-22) && canMove(getX()+23, getY()+22) && canMove(getX()+23, getY()-6) && canMove(getX()+23, getY()+6) && canMove(getX()+23, getY()-12) && canMove(getX()+23, getY()+12)) {
            left = false; right = true; up = false; down = false;
        }
        else if(Greenfoot.isKeyDown(pWorld.p2Up) && canMove(getX(), getY()-23) && canMove(getX()-22, getY()-23) && canMove(getX()+22, getY()-23) && canMove(getX()-6, getY()-23) && canMove(getX()+6, getY()-23) && canMove(getX()-12, getY()-23) && canMove(getX()+12, getY()-23)) {
            left = false; right = false; up = true; down = false;
        }
        else if(Greenfoot.isKeyDown(pWorld.p2Down) && canMove(getX(), getY()+23) && canMove(getX()-22, getY()+23) && canMove(getX()+22, getY()+23) && canMove(getX()-6, getY()+23) && canMove(getX()+6, getY()+23) && canMove(getX()-12, getY()+23) && canMove(getX()+12, getY()+23)) {
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
                    setImage(new GreenfootImage("2 pacman2.PNG")); setImage=2;
                }
                else if(setImage==2) {
                    setImage(new GreenfootImage("2 pacman3.png")); setImage=3;
                }
                else if(setImage==3) {
                    setImage(new GreenfootImage("2 pacman2.PNG")); setImage=4;
                }
                else if(setImage==4) {
                    setImage(new GreenfootImage("2 pacman1.PNG")); setImage=1;
                }
            }
            else {
                if(deadImage==1){
                    setImage(new GreenfootImage("2 pacman2.PNG")); setRotation(-90);
                }
                Pacman paw = (Pacman) getWorld();
                if(paw.muteS) dieSound.setVolume(0);
                    else dieSound.setVolume(60);
                if(deadImage==5) {
                    setRotation(0); deadImage=6; dieSound.setVolume(60); dieSound.play();
                    if(paw.muteS) dieSound.setVolume(0);
                }
                else if(deadImage==7) {
                    setRotation(90); deadImage=8;
                }
                else if(deadImage==9) {
                    setRotation(180); deadImage=10;
                }
                else if(deadImage==11) {
                    setRotation(-90); deadImage=12;
                }
                else if(deadImage==13) {
                    setImage(new GreenfootImage("pacman dead.png")); deadImage=14;
                }
                else if(deadImage==15) {
                    setImage(new GreenfootImage("pacman6.png")); deadImage=16;
                }
                else if(deadImage==20) {
                    if(p2Life==3) {
                        getWorld().removeObjects(getWorld().getObjects(Life.class)); getWorld().addObject(new Life(), 715, 475); getWorld().addObject(new Life(), 715, 525); p2Life=2; getWorld().addObject(new Life(), 25, 270); getWorld().addObject(new Life(), 715, 270);
                        getWorld().addObject(new Ready(), 365, 530); setLocation(380, 440);
                        left=true; right=false; up=false; down=false; playerIsDead=false; imageCounter=0; deadImage=1;
                        setImage(new GreenfootImage("2 pacman2.PNG")); setRotation(0);
                        if(p1Life==3) {
                            getWorld().addObject(new Life(), 25, 475); getWorld().addObject(new Life(), 25, 525); getWorld().addObject(new Life(), 25, 575);
                        }
                        else if(p1Life==2) {
                            getWorld().addObject(new Life(), 25, 475); getWorld().addObject(new Life(), 25, 525);
                        }
                        else if(p1Life==1) {
                            getWorld().addObject(new Life(), 25, 475);
                        }
                    }
                    else if(p2Life==2) {
                        getWorld().removeObjects(getWorld().getObjects(Life.class)); getWorld().addObject(new Life(), 715, 475); p2Life=1; getWorld().addObject(new Life(), 25, 270); getWorld().addObject(new Life(), 715, 270);
                        getWorld().addObject(new Ready(), 365, 530); setLocation(380, 440);
                        left=true; right=false; up=false; down=false; playerIsDead=false; imageCounter=0; deadImage=1;
                        setImage(new GreenfootImage("2 pacman2.PNG")); setRotation(0);
                        if(p1Life==3) {
                            getWorld().addObject(new Life(), 25, 475); getWorld().addObject(new Life(), 25, 525); getWorld().addObject(new Life(), 25, 575);
                        }
                        else if(p1Life==2) {
                            getWorld().addObject(new Life(), 25, 475); getWorld().addObject(new Life(), 25, 525);
                        }
                        else if(p1Life==1) {
                            getWorld().addObject(new Life(), 25, 475);
                        }
                    }
                    else if(p2Life==1) {
                        getWorld().removeObjects(getWorld().getObjects(Life.class)); p2Life=0; getWorld().addObject(new Life(), 25, 270); getWorld().addObject(new Life(), 715, 270);
                        getWorld().addObject(new Ready(), 365, 530); setLocation(380, 440);
                        left=true; right=false; up=false; down=false; playerIsDead=false; imageCounter=0; deadImage=1;
                        setImage(new GreenfootImage("2 pacman2.PNG")); setRotation(0);
                        if(p1Life==3) {
                            getWorld().addObject(new Life(), 25, 475); getWorld().addObject(new Life(), 25, 525); getWorld().addObject(new Life(), 25, 575);
                        }
                        else if(p1Life==2) {
                            getWorld().addObject(new Life(), 25, 475); getWorld().addObject(new Life(), 25, 525);
                        }
                        else if(p1Life==1) {
                            getWorld().addObject(new Life(), 25, 475);
                        }
                    }
                    else {
                        getWorld().addObject(new Menu(7), 365, 350); getWorld().addObject(new ButtonsOverlay(2), 180, 550); getWorld().addObject(new Buttons(8), 500, 555); deadImage=21; getWorld().removeObjects(getWorld().getObjects(Score2.class));
                        if(getWorld().getObjects(Score2.class).isEmpty()) {
                            if(totalScore<100) getWorld().addObject(new Score2(totalScore), 540, 355);
                            else if(totalScore<1000) getWorld().addObject(new Score2(totalScore), 520, 355);
                            else if(totalScore<10000) getWorld().addObject(new Score2(totalScore), 500, 355);
                            else if(totalScore<100000) getWorld().addObject(new Score2(totalScore), 480, 355);
                            else getWorld().addObject(new Score2(totalScore), 460, 355);
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
        Actor score = new Score2(); getWorld().removeObjects(getWorld().getObjects(Score2.class));
        getWorld().addObject(score, 670, 430);
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
    private void addScores()
    {
        int gSum = 0;
        for (Object obj : getWorld().getObjects(Ghost.class)) {
            Ghost a = (Ghost) obj;
            gSum += a.player2Score;
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
        return getY();
    }
    private void player1Life()
    {
        int pl = 0;
        for (Object obj : getWorld().getObjects(Player.class)) {
            Player a = (Player) obj;
            pl += a.life;
        }
        p1Life=pl;
    }
    private Ghost isPlayerDead()
    {
        for (Object obj : getWorld().getObjects(Ghost.class)) {
            Ghost a = (Ghost) obj;
            if (a.playerDie) return a;
        }
        return null;
    }
    private Player otherDead()
    {
        for (Object obj : getWorld().getObjects(Player.class)) {
            Player i = (Player) obj;
            if (i.playerIsDead) return i;
        }
        return null;
    }
}