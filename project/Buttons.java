import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
public class Buttons extends Actor
{
    protected int ID;
    private boolean selected;
    public boolean muteS = false;
    public boolean musicS = false;
    private String imageName="";
    public Buttons()
    {
        
    }
    public Buttons(int state)
    {
        ID = state;
        // if(ID==1) imageName="1 player";
        // else if(ID==2) imageName="2 player";
        // else if(ID==3) imageName="About";
        // else if(ID==4) imageName="Help";
        // else if(ID==5) imageName="music button 1";
        // else if(ID==6) imageName="mute button 1";
        // else if(ID==7) imageName="resume";
        // else imageName="retry";
        switch(ID) {
            case 1: setImage(new GreenfootImage("1 player.png"));
            break;
            case 2: setImage(new GreenfootImage("2 player.png"));
            break;
            case 3: setImage(new GreenfootImage("About.png"));
            break;
            case 4: setImage(new GreenfootImage("Help.png"));
            break;
            case 5: setImage(new GreenfootImage("music button 1.png")); getImage().scale(30,30);
            break;
            case 6: setImage(new GreenfootImage("mute button 1.png")); getImage().scale(30,30);
            break;
            case 7: setImage(new GreenfootImage("resume.png"));
            break;
            case 8: setImage(new GreenfootImage("retry.png"));
            break;
        }
    }
    // private GreenfootImage createImage()
    // {
    //     return imageName+".png";
    // }
    public void act()
    {
        checkMouse();
    }
    private void checkMouse()
    {
        if (Greenfoot.mouseMoved(null)) {
            if (Greenfoot.mouseMoved(this) && !selected) {
                switch(ID) {
                    case 1: setImage(new GreenfootImage("1 player selected.png"));
                    break;
                    case 2: setImage(new GreenfootImage("2 player selected.png"));
                    break;
                    case 3: setImage(new GreenfootImage("About selected.png"));
                    break;
                    case 4: setImage(new GreenfootImage("Help selected.png"));
                    break;
                    case 7: setImage(new GreenfootImage("resume selected.png"));
                    break;
                    case 8: setImage(new GreenfootImage("retry selected.png"));
                    break;
                }
                selected =true;
            }
            if (!Greenfoot.mouseMoved(this) && selected) {
                switch(ID) {
                    case 1: setImage(new GreenfootImage("1 player.png"));
                    break;
                    case 2: setImage(new GreenfootImage("2 player.png"));
                    break;
                    case 3: setImage(new GreenfootImage("About.png"));
                    break;
                    case 4: setImage(new GreenfootImage("Help.png"));
                    break;
                    case 7: setImage(new GreenfootImage("resume.png"));
                    break;
                    case 8: setImage(new GreenfootImage("retry.png"));
                    break;
                }
                selected = false;
            }
        }
        checkClicked();
    }
    public void checkClicked()
    {
        MouseInfo info = Greenfoot.getMouseInfo();
        if(info!=null) {
            int clicked = info.getButton();
            if(Greenfoot.mouseClicked(this) && clicked==1) {
                World world = getWorld(); Pacman pw = (Pacman) getWorld(); List all=getWorld().getObjects(null);
                switch(ID) {
                    case 1: pw.begin.stop(); getWorld().removeObjects(all);
                            pw.singleplayer();
                    break;
                    case 2: pw.begin.stop(); getWorld().removeObjects(all);
                            pw.multiplayer();
                    break;
                    case 3: getWorld().addObject(new Menu(4), 365, 350); getWorld().addObject(new ButtonsOverlay(2), 600, 55);
                    break;
                    case 4: getWorld().addObject(new Menu(1), 365, 350); getWorld().addObject(new ButtonsOverlay(1), 600, 55);
                            getWorld().addObject(new ButtonsOverlay(3), 80, 623); getWorld().addObject(new ButtonsOverlay(4), 245, 623); getWorld().addObject(new ButtonsOverlay(5), 163, 540); getWorld().addObject(new ButtonsOverlay(6), 163, 623);
                            getWorld().addObject(new ButtonsOverlay(7), 458, 623); getWorld().addObject(new ButtonsOverlay(8), 623, 623); getWorld().addObject(new ButtonsOverlay(9), 537, 543); getWorld().addObject(new ButtonsOverlay(10), 540, 623);
                            getWorld().addObject(new ButtonsOverlay(11), 160, 345);
                    break;
                    case 5: if(!pw.musicS) {
                                setImage(new GreenfootImage("music button 2.png")); pw.musicS=true;
                            }
                            else if(pw.musicS) {
                                setImage(new GreenfootImage("music button 1.png")); pw.musicS=false;
                            }
                            getImage().scale(30,30);
                    break;
                    case 6: if(!pw.muteS) {
                                setImage(new GreenfootImage("mute button 2.png")); pw.muteS=true;
                            }
                            else if(pw.muteS) {
                                setImage(new GreenfootImage("mute button 1.png")); pw.muteS=false;
                            }
                            getImage().scale(30,30);
                    break;
                    case 7: getWorld().removeObjects(getWorld().getObjects(Menu.class)); getWorld().removeObjects(getWorld().getObjects(ButtonsOverlay.class));
                            getWorld().removeObject(this);
                    break;
                    case 8: if(pw.type==1) {
                                getWorld().removeObjects(all); pw.singleplayer();
                            }
                            else {
                                getWorld().removeObjects(all); pw.multiplayer();
                            }
                    break;
                }
            }
        }
    }
}
