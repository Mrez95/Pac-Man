import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Pacman extends World
{
    GreenfootSound begin = new GreenfootSound("begin-sound.mp3");
    public boolean muteS;
    public boolean musicS;
    public int type;
    public String p1Left="left";
    public String p1Right="right";
    public String p1Up="up";
    public String p1Down="down";
    public String p2Left="a";
    public String p2Right="d";
    public String p2Up="w";
    public String p2Down="s";
    public String pause="p";
    private int[][] map = {
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
    public Pacman()
    {
        super(731, 701, 1);
        setPaintOrder(ButtonsOverlay.class, Menu.class, Buttons.class, Cover.class,Wall.class,Ghost.class,Player.class,Food.class);
        setActOrder(Player.class,Food.class,Ghost.class);
        menu();
        Greenfoot.setSpeed(50); Greenfoot.start();
    }
    public void act()
    {
        if(type==0 && musicS) begin.setVolume(0);
        else if(type==0) begin.setVolume(60);
    }
    public void menu()
    {
        addObject(new Cover(), 365, 350); addObject(new Buttons(1), 560, 290); addObject(new Buttons(2), 560, 377); addObject(new Buttons(3), 528, 455); addObject(new Buttons(4), 558, 543);
        for (int i=0; i<25; i++) addObject(new Wall(),i*15 , 410);
        addObject(new Player(), 375, 345); addObject(new Ghost(1), 420, 345); addObject(new Ghost(2), 465, 345); addObject(new Ghost(3), 510, 345); addObject(new Ghost(4), 555, 345); addObject(new Ghost(5), 600, 345);
        Buttons mute = new Buttons(6); if(muteS) mute.setImage("mute button 2.png"); else mute.setImage("mute button 1.png"); mute.getImage().scale(30,30);
        Buttons music = new Buttons(5); if(musicS) music.setImage("music button 2.png"); else music.setImage("music button 1.png"); music.getImage().scale(30,30);
        addObject(mute, 465, 620); addObject(music, 510, 620); begin.setVolume(60);
        if(!musicS) begin.play();
        type=0; Greenfoot.setSpeed(50);
        setPaintOrder(ButtonsOverlay.class, Menu.class, Buttons.class, Cover.class,Wall.class,Ghost.class,Player.class,Food.class);
        setActOrder(Player.class,Food.class,Ghost.class);
    }
    public void singleplayer()
    {
        for(int x = 0; x<map.length; x++) {
            for(int y = 0; y<map[x].length; y++) {
                mapReader(map[x][y],x,y); //Greg v code
                if(map[x][y]==1) addObject(new Player(), y*15+50, x*15+50);
            }
        }
        addObject(new Ready(), 365, 530);
        for(int i=0; i<3; i++) {
            addObject(new Life(), 25, 475+i*50);
        }
        addObject(new Life(), 25, 270);
        Buttons mute = new Buttons(6); if(muteS) mute.setImage("mute button 2.png"); else mute.setImage("mute button 1.png"); mute.getImage().scale(30,30);
        Buttons music = new Buttons(5); if(musicS) music.setImage("music button 2.png"); else music.setImage("music button 1.png"); music.getImage().scale(30,30);
        addObject(mute, 345, 670); addObject(music, 390, 670);
        setPaintOrder(Buttons.class, Score.class, Menu.class, Wall.class, Ghost.class, Player.class, Fruit.class, Food.class);
        setActOrder(Food.class,Ghost.class,Player.class);
        Greenfoot.setSpeed(56); type=1;
    }
    public void multiplayer()
    {
        for(int x = 0; x<map.length; x++) {
            for(int y = 0; y<map[x].length; y++) {
                if(map[x][y]==1) {
                    addObject(new Player(), y*15+35, x*15+50); addObject(new Player2(), y*15+65, x*15+50);
                }
                else mapReader(map[x][y],x,y);
            }
        }
        addObject(new Ready(), 365, 530);
        for(int i=0; i<3; i++) {
            addObject(new Life(), 25, 475+i*50);
        }
        for(int i=0; i<3; i++) {
            addObject(new Life(), 715, 475+i*50);
        }
        addObject(new Life(), 25, 270); addObject(new Life(), 715, 270);
        Buttons mute = new Buttons(6); if(muteS) mute.setImage("mute button 2.png"); else mute.setImage("mute button 1.png"); mute.getImage().scale(30,30);
        Buttons music = new Buttons(5); if(musicS) music.setImage("music button 2.png"); else music.setImage("music button 1.png"); music.getImage().scale(30,30);
        addObject(mute, 345, 670); addObject(music, 390, 670);
        setPaintOrder(Buttons.class, Score.class, Score2.class, Menu.class, Wall.class, Ghost.class,Player.class, Player2.class, Fruit.class, Food.class);
        setActOrder(Food.class,Ghost.class,Player.class);
        Greenfoot.setSpeed(56); type=2;
    }
    public void mapReader(int map,int x,int y)
    {
        if(map<1) {
            return;
        }
        if(map<2) {
            return;
        }
        if(map<23) {
            if(map == 16) {
                addObject(new Food1(), y*15+50, x*15+50);
                return;
            }
            if(map == 17) {
                addObject(new Food2(), y*15+50, x*15+50);
                return;
            }
            addObject(new Wall(map), y*15+50, x*15+50);
            return;
        }
        switch(map) {
            case 23:addObject(new Ghost(1), y*15+50, x*15+50);
            break;
            case 24:addObject(new Ghost(2), y*15+50, x*15+50);
            break;
            case 25:addObject(new Ghost(3), y*15+50, x*15+50);
            break;
            case 26:addObject(new Ghost(4), y*15+50, x*15+50);
            break;
            case 27:addObject(new Ghost(5), y*15+50, x*15+50);
            break;
        }
    }
}