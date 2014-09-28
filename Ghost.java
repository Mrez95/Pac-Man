import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Ghost extends Actor
{
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private boolean out;
    private boolean eatable;
    private boolean dead;
    public boolean playerDie;
    private int exitCounter=0;
    private int moveCounter=0;
    private int dangerCounter=0;
    private int setImage=1;
    public int playerScore=0;
    public int player2Score=0;
    protected int ID;
    protected GreenfootSound eaten = new GreenfootSound("Ghost-eaten-sound.mp3");
    public Ghost(int ghostID)
    {
        ID = ghostID;
        switch(ID) {
            case 1: setImage(new GreenfootImage("Ghost1.png"));
            break;
            case 2: setImage(new GreenfootImage("Ghost2.png"));
            break;
            case 3: setImage(new GreenfootImage("Ghost3.png"));
            break;
            case 4: setImage(new GreenfootImage("Ghost4.png"));
            break;
            case 5: setImage(new GreenfootImage("Ghost5.png"));
            break;
        }
    }
    public GreenfootImage getGhostSubimage(String subimage)
    {
        return new GreenfootImage("Ghost"+ID+subimage+".png");
    }
    public void act()
    {
        Pacman pw=(Pacman) getWorld();
        if(pw.type==0) {
            motionSet();
        }
        else {
            if(!getWorld().getObjects(Ready.class).isEmpty()) {
                switch(ID) {
                    case 1: setLocation(335,320); setImage(new GreenfootImage("Ghost1.png"));
                    break;
                    case 2: setLocation(395,320); setImage(new GreenfootImage("Ghost2.png"));
                    break;
                    case 3: setLocation(365,350); setImage(new GreenfootImage("Ghost3.png"));
                    break;
                    case 4: setLocation(335,380); setImage(new GreenfootImage("Ghost4.png"));
                    break;
                    case 5: setLocation(395,380); setImage(new GreenfootImage("Ghost5.png"));
                    break;
                }
                playerDie=false; setRotation(0); out=false; dead=false; eatable=false; dangerCounter=0; exitCounter=0; moveCounter=0; setImage=1;
            }
            if(getWorld().getObjects(Menu.class).isEmpty() && getWorld().getObjects(Ready.class).isEmpty()) {
                if(!playerDie) {
                    eatenPill(); dangerCount();
                    if(!exitCounter()) {
                        if(!eatable) {
                            begin();
                        }
                        else {
                            dangerSettings();
                        }
                    }
                    else if(!out) {
                        moveOut();
                    }
                    else if(!dead) {
                        moveSet();
                    }
                    else {
                        deadMoveAI(); move();
                    }
                    port(); playerDied();
                }
            }
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
        if(x==0 && y==345) setLocation(3,475);
        if(x==331 && y==475) setLocation(644, 345);
        motionMove(); motionImage();
    }
    private void motionMove()
    {
        if(left) setLocation(getX()-2,getY());
        else if(right) setLocation(getX()+2,getY());
    }
    private void motionImage()
    {
        int y=getY();
        if(moveCounter==10) {
            if(y==345) {
                if(setImage==1) {
                    setImage(getGhostSubimage("L2")); setImage=2;
                }
                else {
                    setImage(getGhostSubimage("L")); setImage=1;
                }
            }
            if(y==475) {
                if(setImage==1) {
                    setImage(new GreenfootImage("GhostEatable.png")); setImage=2;
                }
                else {
                    setImage(new GreenfootImage("GhostEatable3.png")); setImage=1;
                }
            }
            moveCounter=0;
        }
        else moveCounter=moveCounter+1;
    }
    private void playerDied()
    {
        Player death = getPlayerDeath();
        Player2 death2 = getPlayerDeath2();
        if(death!=null || death2!=null) {
            playerDie=true; setImage(new GreenfootImage("pacman6.png"));
        }
    }
    private void dangerCount()
    {
        if(dangerCounter>0 && dangerCounter<1800) {
            dangerCounter=dangerCounter+2;
        }
    }
    private void eatenPill() {
        Food1 eating = getCurrentFood();
        if (eating != null) {
            eatable=true; dangerCounter=2;
        }
    }
    private Food1 getCurrentFood()
    {
        for (Object obj : getWorld().getObjects(Food1.class)) {
            Food1 food = (Food1) obj;
            if (food.hasPlayer()) return food;
        }
        return null;
    }
    private Player getPlayerDeath()
    {
        for (Object obj : getWorld().getObjects(Player.class)) {
            Player p = (Player) obj;
            if (p.playerIsDead) {
                return p;
            }
        }
        return null;
    }
    private Player2 getPlayerDeath2()
    {
        for (Object obj : getWorld().getObjects(Player2.class)) {
            Player2 i = (Player2) obj;
            if (i.playerIsDead) {
                return i;
            }
        }
        return null;
    }
    private Player paused()
    {
        for (Object obj : getWorld().getObjects(Player.class)) {
            Player p = (Player) obj;
            if (p.paused) return p;
        }
        return null;
    }
    private void begin()
    {
        if(Greenfoot.getRandomNumber(1000)>995) {
            setImage(getGhostSubimage("L"));
        }
        else if(Greenfoot.getRandomNumber(1000)>995) {
            setImage(getGhostSubimage("R"));
        }
        else if(Greenfoot.getRandomNumber(1000)>995) {
            setImage(getGhostSubimage("U"));
        }
        else if(Greenfoot.getRandomNumber(1000)>995) {
            setImage(getGhostSubimage("D"));
        }
        else if(Greenfoot.getRandomNumber(1000)>995) {
            setImage(getGhostSubimage(""));
        }
    }
    private void moveOut()
    {
        int y = getY();
        if(y==260) {
            out=true;
        }
        else {
            left=false; right=false; up=true; down=false;
            if(!eatable) {
                move();
            }
            else {
                eatableMove();
            }
        }
    }
    private void moveSet()
    {
        if(moveCounter==0) {
            if(getObjectsInRange(99, Player.class).isEmpty() && getObjectsInRange(99, Player2.class).isEmpty()) {
                if(left) {
                    if(canMove(getX(), getY()-23) && canMove(getX()-22, getY()-23) && canMove(getX()+22, getY()-23) && canMove(getX()-6, getY()-23) && canMove(getX()+6, getY()-23) && canMove(getX()-12, getY()-23) && canMove(getX()+12, getY()-23) && Greenfoot.getRandomNumber(1000)>800) {
                        up=true; left=false;
                    }
                    else if(canMove(getX(), getY()+23) && canMove(getX()-22, getY()+23) && canMove(getX()+22, getY()+23) && canMove(getX()-6, getY()+23) && canMove(getX()+6, getY()+23) && canMove(getX()-12, getY()+23) && canMove(getX()+12, getY()+23) && Greenfoot.getRandomNumber(1000)>750) {
                        down=true; left=false;
                    }
                    else if(!canMove(getX()-23,getY()) && Greenfoot.getRandomNumber(1000)>990) {
                        right=true; left=false;
                    }
                }
                else if(right) {
                    if(canMove(getX(), getY()-23) && canMove(getX()-22, getY()-23) && canMove(getX()+22, getY()-23) && canMove(getX()-6, getY()-23) && canMove(getX()+6, getY()-23) && canMove(getX()-12, getY()-23) && canMove(getX()+12, getY()-23) && Greenfoot.getRandomNumber(1000)>800) {
                        up=true; right=false;
                    }
                    else if(canMove(getX(), getY()+23) && canMove(getX()-22, getY()+23) && canMove(getX()+22, getY()+23) && canMove(getX()-6, getY()+23) && canMove(getX()+6, getY()+23) && canMove(getX()-12, getY()+23) && canMove(getX()+12, getY()+23) && Greenfoot.getRandomNumber(1000)>750) {
                        down=true; right=false;
                    }
                    else if(!canMove(getX()+23,getY()) && Greenfoot.getRandomNumber(1000)>990) {
                        left=true; right=false;
                    }
                }
                else if(up) {
                    if(canMove(getX()-23, getY()) && canMove(getX()-23, getY()-22) && canMove(getX()-23, getY()+22) && canMove(getX()-23, getY()-6) && canMove(getX()-23, getY()+6) && canMove(getX()-23, getY()-12) && canMove(getX()-23, getY()+12) && Greenfoot.getRandomNumber(1000)>800) {
                        left=true; up=false;
                    }
                    else if(canMove(getX()+23, getY()) && canMove(getX()+23, getY()-22) && canMove(getX()+23, getY()+22) && canMove(getX()+23, getY()-6) && canMove(getX()+23, getY()+6) && canMove(getX()+23, getY()-12) && canMove(getX()+23, getY()+12) && Greenfoot.getRandomNumber(1000)>750) {
                        right=true; up=false;
                    }
                    else if(!canMove(getX(),getY()-23) && Greenfoot.getRandomNumber(1000)>990) {
                        down=true; up=false;
                    }
                }
                else if(down) {
                    if(canMove(getX()-23, getY()) && canMove(getX()-23, getY()-22) && canMove(getX()-23, getY()+22) && canMove(getX()-23, getY()-6) && canMove(getX()-23, getY()+6) && canMove(getX()-23, getY()-12) && canMove(getX()-23, getY()+12) && Greenfoot.getRandomNumber(1000)>800) {
                        left=true; down=false;
                    }
                    else if(canMove(getX()+23, getY()) && canMove(getX()+23, getY()-22) && canMove(getX()+23, getY()+22) && canMove(getX()+23, getY()-6) && canMove(getX()+23, getY()+6) && canMove(getX()+23, getY()-12) && canMove(getX()+23, getY()+12) && Greenfoot.getRandomNumber(1000)>750) {
                        right=true; down=false;
                    }
                    else if(!canMove(getX(),getY()+23) && Greenfoot.getRandomNumber(1000)>990) {
                        up=true; down=false;
                    }
                }
            }
            else followPlayerAI();
            if(!eatable) {
                move();
            }
            else {
                eatableMove();
            }
        }
        else {
            eatableMove();
        }
        eatPlayer();
    }
    private void followPlayerAI()
    {
        int player1X = playerGetPosition(1, "x"); int player1Y = playerGetPosition(1, "y");
        int player2X = playerGetPosition(2, "x"); int player2Y = playerGetPosition(2, "y");
        int posX = getX(); int posY = getY();
        if(left) {
            if((player1X<posX || player2X<posX) && (player1Y==posY || player2Y==posY) && (canMove(posX-23, posY) && canMove(posX-23, posY-22) && canMove(posX-23, posY+22) && canMove(posX-23, posY-6) && canMove(posX-23, posY+6) && canMove(posX-23, posY-12) && canMove(posX-23, posY+12))) left=true;
            else if((player1Y<posY || player2Y<posY) && (canMove(posX, posY-23) && canMove(posX-22, posY-23) && canMove(posX+22, posY-23) && canMove(posX-6, posY-23) && canMove(posX+6, posY-23) && canMove(posX-12, posY-23) && canMove(posX+12, posY-23))) {
                left=false;
                if(!eatable) up=true;
                else down=true;
            }
            else if((player1Y>posY || player2Y>posY) && (canMove(posX, posY+23) && canMove(posX-22, posY+23) && canMove(posX+22, posY+23) && canMove(posX-6, posY+23) && canMove(posX+6, posY+23) && canMove(posX-12, posY+23) && canMove(posX+12, posY+23))) {
                left=false;
                if(!eatable) down=true;
                else up=true;
            }
            else if(!canMove(posX-23, posY) && !canMove(posX, posY-23)) {
                left=false;
                if(!eatable) down=true;
                else up=true;
            }
            else if(!canMove(posX-23, posY) && !canMove(posX, posY+23)) {
                left=false;
                if(!eatable) up=true;
                else down=true;
            }
        }
        else if(right) {
            if((player1X>posX || player2X>posX) && (player1Y==posY || player2Y==posY) && (canMove(posX+23, posY) && canMove(posX+23, posY-22) && canMove(posX+23, posY+22) && canMove(posX+23, posY-6) && canMove(posX+23, posY+6) && canMove(posX+23, posY-12) && canMove(posX+23, posY+12))) right=true;
            else if((player1Y<posY || player2Y<posY) && (canMove(posX, posY-23) && canMove(posX-22, posY-23) && canMove(posX+22, posY-23) && canMove(posX-6, posY-23) && canMove(posX+6, posY-23) && canMove(posX-12, posY-23) && canMove(posX+12, posY-23))) {
                right=false;
                if(!eatable) up=true;
                else down=true;
            }
            else if((player1Y>posY || player2Y>posY) && (canMove(posX, posY+23) && canMove(posX-22, posY+23) && canMove(posX+22, posY+23) && canMove(posX-6, posY+23) && canMove(posX+6, posY+23) && canMove(posX-12, posY+23) && canMove(posX+12, posY+23))) {
                right=false;
                if(!eatable) down=true;
                else up=true;
            }
            else if(!canMove(posX+23, posY) && !canMove(posX, posY-23)) {
                right=false;
                if(!eatable) down=true;
                else up=true;
            }
            else if(!canMove(posX+23, posY) && !canMove(posX, posY+23)) {
                left=false;
                if(!eatable) up=true;
                else down=true;
            }
        }
        else if(up) {
            if((player1Y<posY || player2Y<posY) && (player1X==posX || player2X==posX) && (canMove(posX, posY-23) && canMove(posX-22, posY-23) && canMove(posX+22, posY-23) && canMove(posX-6, posY-23) && canMove(posX+6, posY-23) && canMove(posX-12, posY-23) && canMove(posX+12, posY-23))) up=true;
            else if((player1X<posX || player2X<posX) && (canMove(posX-23, posY) && canMove(posX-23, posY-22) && canMove(posX-23, posY+22) && canMove(posX-23, posY-6) && canMove(posX-23, posY+6) && canMove(posX-23, posY-12) && canMove(posX-23, posY+12))) {
                up=false;
                if(!eatable) left=true;
                else right=true;
            }
            else if((player1X>posX || player2X>posX) && (canMove(posX+23, posY) && canMove(posX+23, posY-22) && canMove(posX+23, posY+22) && canMove(posX+23, posY-6) && canMove(posX+23, posY+6) && canMove(posX+23, posY-12) && canMove(posX+23, posY+12))) {
                up=false;
                if(!eatable) right=true;
                else left=true;
            }
            else if(!canMove(posX, posY-23) && !canMove(posX-23, posY)) {
                up=false;
                if(!eatable) right=true;
                else left=true;
            }
            else if(!canMove(posX, posY-23) && !canMove(posX+23, posY)) {
                up=false;
                if(!eatable) left=true;
                else right=true;
            }
        }
        else if(down) {
            if((player1Y>posY || player2Y>posY) && (player1X==posX || player2X==posX) && (canMove(getX(), getY()+23) && canMove(getX()-22, getY()+23) && canMove(getX()+22, getY()+23) && canMove(getX()-6, getY()+23) && canMove(getX()+6, getY()+23) && canMove(getX()-12, getY()+23) && canMove(getX()+12, getY()+23))) down=true;
            else if((player1X<posX || player2X<posX) && (canMove(posX-23, posY) && canMove(posX-23, posY-22) && canMove(posX-23, posY+22) && canMove(posX-23, posY-6) && canMove(posX-23, posY+6) && canMove(posX-23, posY-12) && canMove(posX-23, posY+12))) {
                down=false;
                if(!eatable) left=true;
                else right=true;
            }
            else if((player1X>posX || player2X>posX) && (canMove(posX+23, posY) && canMove(posX+23, posY-22) && canMove(posX+23, posY+22) && canMove(posX+23, posY-6) && canMove(posX+23, posY+6) && canMove(posX+23, posY-12) && canMove(posX+23, posY+12))) {
                down=false;
                if(!eatable) right=true;
                else left=true;
            }
            else if(!canMove(posX, posY+23) && !canMove(posX-23, posY)) {
                up=false;
                if(!eatable) right=true;
                else left=true;
            }
            else if(!canMove(posX, posY+23) && !canMove(posX+23, posY)) {
                up=false;
                if(!eatable) left=true;
                else right=true;
            }
        }
    }
    private void move()
    {
        if(up && !out) {
            setLocation(getX(),getY()-1); setImage(getGhostSubimage("U")); setRotation(0);
        }
        else if(left && canMove(getX()-23, getY()) && canMove(getX()-23, getY()-12) && canMove(getX()-23, getY()+12)) {
            setLocation(getX()-1,getY());
            if(!dead) {
                setImage(new GreenfootImage(getGhostSubimage("L")));
            }
            else {
                setImage(new GreenfootImage("GhostDead1.png")); setRotation(180);
            }
        }
        else if (right && canMove(getX()+23, getY()) && canMove(getX()+23, getY()-12) && canMove(getX()+23, getY()+12)) {
            setLocation(getX()+1,getY());
            if(!dead) {
                setImage(new GreenfootImage(getGhostSubimage("R")));
            }
            else {
                setImage(new GreenfootImage("GhostDead1.png")); setRotation(0);
            }
        }
        else if(up && canMove(getX(), getY()-23) && canMove(getX()-12, getY()-23) && canMove(getX()+12, getY()-23)) {
            setLocation(getX(),getY()-1);
            if(!dead) {
                setImage(new GreenfootImage(getGhostSubimage("U")));
            }
            else {
                setImage(new GreenfootImage("GhostDead2.png")); setRotation(0);
            }
        }
        else if(down && canMove(getX(), getY()+23) && canMove(getX()-12, getY()+23) && canMove(getX()+12, getY()+23)) {
            setLocation(getX(),getY()+1);
            if(!dead) {
                setImage(new GreenfootImage(getGhostSubimage("D")));
            }
            else {
                setImage(new GreenfootImage("GhostDead2.png")); setRotation(180);
            }
        }
        else if(down && dead && !canMove(getX(),getY()+23)) {
            setLocation(getX(),getY()+1); setImage(new GreenfootImage("GhostDead2.png")); setRotation(180);
        }
    }
    private void eatableMove()
    {
        if(moveCounter==1) {
            if(up && !out) {
                setLocation(getX(),getY()-1);
            }
            else if(left && canMove(getX()-23, getY()) && canMove(getX()-23, getY()-12) && canMove(getX()-23, getY()+12)) {
                setLocation(getX()-1,getY());
            }
            else if (right && canMove(getX()+23, getY()) && canMove(getX()+23, getY()-12) && canMove(getX()+23, getY()+12)) {
                setLocation(getX()+1,getY());
            }
            else if(up && canMove(getX(), getY()-23) && canMove(getX()-12, getY()-23) && canMove(getX()+12, getY()-23)) {
                setLocation(getX(),getY()-1);
            }
            else if(down && canMove(getX(), getY()+23) && canMove(getX()-12, getY()+23) && canMove(getX()+12, getY()+23)) {
                setLocation(getX(),getY()+1);
            }
            moveCounter=0;
        }
        else {
            moveCounter=1;
        }
        dangerSettings();
    }
    private void dangerSettings()
    {
        if(dangerCounter<1300) {
            setImage(new GreenfootImage("GhostEatable.png"));
        }
        if(dangerCounter==1300 || dangerCounter==1500 || dangerCounter==1700) {
            setImage(new GreenfootImage("GhostEatable2.png"));
        }
        else if(dangerCounter==1400 || dangerCounter==1600 || dangerCounter==1800) {
            setImage(new GreenfootImage("GhostEatable.png"));
        }
        if(dangerCounter==1800) {
            dangerCounter=0; eatable=false;
        }
        if(dead) {
            dangerCounter=0;
        }
    }
    private void deadMoveAI()
    {
        int x=getX(); int y=getY();
        if((x==650 && (y==200 || y==500)) || (x==575 && (y==290 || y==410)) || (x==515 && (y==200 || y==260 || y==440 || y==500)) || (x==470 && (y==260 || y==440)) || (x==455 && y==260) || (x==425 && (y==170 || y==530)) || (x==245 && (y==140 || y==560)) || (y==260 && (x<=515 && x>365))) {
            left=true; right=false; up=false; down=false;
        }
        else if((x==80 && (y==200 || y==500)) || (x==155 && (y==290 || y==410)) || (x==215 && (y==200 || y==260 || y==440 || y==500)) || (x==260 && (y==260 || y==440)) || (y==260 && x==275) || (x==305 && (y==170 || y==530)) || (x==365 && y==440) || (x==485 && (y==140 || y==560)) || (y==260 && (x>=215 && x<365))) {
            left=false; right=true; up=false; down=false;
        }
        else if ((y==620 && (x==80 || x==155 || x==245 || x==305 || x==425 || x==485 || x==575 || x==650)) || (y==560 && (x==80 || x==155 || x==215 || x==305 || x==425 || x==515 || x==575 || x==650)) || (y==530 && x==365) || (y==500 && (x==155 || x==260 || x==470 || x==575)) || (y==440 && (x==275 || x==455)) || (y==350 && (x==455 || x==275)) || (y==290 && (x==215 || x==515))) {
            left=false; right=false; up=true; down=false;
        }
        else if((y==80 && (x==80 || x==155 || x==245 || x==305 || x==425 || x==485 || x==575 || x== 650)) || (y==140 && (x==80 || x==155 || x==215 || x==305 || x==425 || x==515 || x==575 || x==650)) || (y==170 && x==365) || (y==200 && (x==155 || x==260 || x==470 || x==575)) || (y==260 && x==365) || (y==410 && (x==215 || x==515)) || (y==425 && x==80)) {
            left=false; right=false; up=false; down=true;
        }
        if(x==365 && y==350) {
            up=true; down=false; out=false; eatable=false; dead=false; dangerCounter=0; moveCounter=0; setImage=1; setImage(new GreenfootImage("Ghost1.png"));
        }
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
    private void eatPlayer()
    {
        Pacman pw = (Pacman) getWorld();
        if((!getObjectsInRange(25,Player.class).isEmpty() || !getObjectsInRange(25,Player2.class).isEmpty()) && dangerCounter==0 && dead==false) playerDie=true;
        else if(!getObjectsInRange(25,Player.class).isEmpty() && dangerCounter!=0 && !dead) {
            dead=true; playerScore=playerScore+200;
            if(!pw.muteS) eaten.play();
        }
        else if(!getObjectsInRange(25,Player2.class).isEmpty() && dangerCounter!=0 && !dead) {
            dead=true; player2Score=player2Score+200;
            if(!pw.muteS) eaten.play();
        }
    }
    private boolean exitCounter()
    {
        if(exitCounter==(ID-1)*250) return true;
        exitCounter=exitCounter+1; return false;
    }
    private boolean canMove(int X, int Y)
    {
        if(getWorld().getObjectsAt(X,Y, Wall.class).isEmpty()) return true;
        return false;
    }
    private int playerGetPosition(int player, String axis)
    {
        int position=0;
        if(player==1) {
            for (Object obj : getWorld().getObjects(Player.class)) {
                Player a = (Player) obj;
                position = a.getPosition(axis);
            }
        }
        else {
            for (Object obj : getWorld().getObjects(Player2.class)) {
                Player2 a = (Player2) obj;
                position = a.getPosition(axis);
            }
        }
        return position;
    }
}