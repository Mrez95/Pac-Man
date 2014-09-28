import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;
import java.awt.Font;
public class ButtonsOverlay extends Buttons
{
    protected int ID;
    private boolean selected;
    private int count=1;
    public ButtonsOverlay(int state)
    {
        ID = state; int pState;
        if(ID==1) setImage(new GreenfootImage("next button.png"));
        else if(ID==2) setImage(new GreenfootImage("menu button.png"));
        else if(ID<12) setImage(new GreenfootImage(77,77));
        else makeImage("");
    }
    public void act()
    {
        if(ID>2) checkImage();
        else checkMouse();
    }
    private void checkImage()
    {
        Pacman pw = (Pacman) getWorld();
        if(ID<12) {
            GreenfootImage image = new GreenfootImage(77,77); Font font = image.getFont();
            switch(ID) {
                case 3: if(pw.p1Left=="left") {
                            setImage("key.png"); setRotation(0);
                        }
                        else if(pw.p1Left=="right") {
                            setImage("key.png"); setRotation(180);
                        }
                        else if(pw.p1Left=="up") {
                            setImage("key.png"); setRotation(90);
                        }
                        else if(pw.p1Left=="down") {
                            setImage("key.png"); setRotation(-90);
                        }
                        else {
                            makeKeyImage(pw.p1Left); setRotation(0);
                        }break;
                case 4: if(pw.p1Right=="left") {
                            setImage("key.png"); setRotation(0);
                        }
                        else if(pw.p1Right=="right") {
                            setImage("key.png"); setRotation(180);
                        }
                        else if(pw.p1Right=="up") {
                            setImage("key.png"); setRotation(90);
                        }
                        else if(pw.p1Right=="down") {
                            setImage("key.png"); setRotation(-90);
                        }
                        else {
                            makeKeyImage(pw.p1Right); setRotation(0);
                        }break;
                case 5: if(pw.p1Up=="left") {
                            setImage("key.png"); setRotation(0);
                        }
                        else if(pw.p1Up=="right") {
                            setImage("key.png"); setRotation(180);
                        }
                        else if(pw.p1Up=="up") {
                            setImage("key.png"); setRotation(90);
                        }
                        else if(pw.p1Up=="down") {
                            setImage("key.png"); setRotation(-90);
                        }
                        else {
                            makeKeyImage(pw.p1Up); setRotation(0);
                        }break;
                case 6: if(pw.p1Down=="left") {
                            setImage("key.png"); setRotation(0);
                        }
                        else if(pw.p1Down=="right") {
                            setImage("key.png"); setRotation(180);
                        }
                        else if(pw.p1Down=="up") {
                            setImage("key.png"); setRotation(90);
                        }
                        else if(pw.p1Down=="down") {
                            setImage("key.png"); setRotation(-90);
                        }
                        else {
                            makeKeyImage(pw.p1Down); setRotation(0);
                        }break;
                case 7: if(pw.p2Left=="left") {
                            setImage("key.png"); setRotation(0);
                        }
                        else if(pw.p2Left=="right") {
                            setImage("key.png"); setRotation(180);
                        }
                        else if(pw.p2Left=="up") {
                            setImage("key.png"); setRotation(90);
                        }
                        else if(pw.p2Left=="down") {
                            setImage("key.png"); setRotation(-90);
                        }
                        else {
                            makeKeyImage(pw.p2Left); setRotation(0);
                        }break;
                case 8: if(pw.p2Right=="left") {
                            setImage("key.png"); setRotation(0);
                        }
                        else if(pw.p2Right=="right") {
                            setImage("key.png"); setRotation(180);
                        }
                        else if(pw.p2Right=="up") {
                            setImage("key.png"); setRotation(90);
                        }
                        else if(pw.p2Right=="down") {
                            setImage("key.png"); setRotation(-90);
                        }
                        else {
                            makeKeyImage(pw.p2Right); setRotation(0);
                        }break;
                case 9: if(pw.p2Up=="left") {
                            setImage("key.png"); setRotation(0);
                        }
                        else if(pw.p2Up=="right") {
                            setImage("key.png"); setRotation(180);
                        }
                        else if(pw.p2Up=="up") {
                            setImage("key.png"); setRotation(90);
                        }
                        else if(pw.p2Up=="down") {
                            setImage("key.png"); setRotation(-90);
                        }
                        else {
                            makeKeyImage(pw.p2Up); setRotation(0);
                        }break;
                case 10: if(pw.p2Down=="left") {
                            setImage("key.png"); setRotation(0);
                        }
                        else if(pw.p2Down=="right") {
                            setImage("key.png"); setRotation(180);
                        }
                        else if(pw.p2Down=="up") {
                            setImage("key.png"); setRotation(90);
                        }
                        else if(pw.p2Down=="down") {
                            setImage("key.png"); setRotation(-90);
                        }
                        else {
                            makeKeyImage(pw.p2Down); setRotation(0);
                        }break;
                case 11: if(pw.pause=="left") {
                            setImage("key.png"); setRotation(0);
                        }
                        else if(pw.pause=="right") {
                            setImage("key.png"); setRotation(180);
                        }
                        else if(pw.pause=="up") {
                            setImage("key.png"); setRotation(90);
                        }
                        else if(pw.pause=="down") {
                            setImage("key.png"); setRotation(-90);
                        }
                        else {
                            makeKeyImage(pw.pause); setRotation(0);
                        }break;
            }
        }
        else {
            if(count==1) switch(ID) {
               case 12: makeImage(pw.p1Left); break;
               case 13: makeImage(pw.p1Right); break;
               case 14: makeImage(pw.p1Up); break;
               case 15: makeImage(pw.p1Down); break;
               case 16: makeImage(pw.p2Left); break;
               case 17: makeImage(pw.p2Right); break;
               case 18: makeImage(pw.p2Up); break;
               case 19: makeImage(pw.p2Down); break;
               case 20: makeImage(pw.pause); break;
            }
            count=2;
        }
        checkClicked();
    }
    private void checkMouse()
    {
        if (Greenfoot.mouseMoved(null)) {
            if (Greenfoot.mouseMoved(this) && !selected) {
                if(ID==1) setImage(new GreenfootImage("next button selected.png"));
                else if(ID==2) setImage(new GreenfootImage("menu button selected.png"));
                selected =true;
            }
            if (!Greenfoot.mouseMoved(this) && selected) {
                if(ID==1) setImage(new GreenfootImage("next button.png"));
                else if(ID==2) setImage(new GreenfootImage("menu button.png"));
                selected = false;
            }
        }
        checkClicked();
    }
    public void checkClicked()
    {
        MouseInfo info = Greenfoot.getMouseInfo(); Pacman pw=(Pacman) getWorld();
        if(info!=null) {
            int clicked = info.getButton();
            if(Greenfoot.mouseClicked(this) && clicked==1) {
                while (Greenfoot.getKey() != null);
                World world = getWorld();
                if(ID==1) {
                    if(count==1) {
                        getWorld().addObject(new Menu(2), 365, 350); count=2; List currentButtons = getWorld().getObjects(ButtonsOverlay.class);
                        ButtonsOverlay bO = new ButtonsOverlay(1);
                        bO.count=2; getWorld().addObject(bO, 600, 55);
                        getWorld().removeObjects(currentButtons);
                    }
                    else {
                        getWorld().addObject(new Menu(3), 365, 350); getWorld().addObject(new ButtonsOverlay(2), 600, 55); getWorld().removeObject(this);
                    }
                }
                else if(ID==2) {
                    if(pw.type==0) {
                        getWorld().removeObjects(getWorld().getObjects(Menu.class)); getWorld().removeObject(this);
                    }
                    else {
                        List all=getWorld().getObjects(null); getWorld().removeObjects(all); pw.menu();
                    }
                }
                else if (ID<12) getWorld().addObject(new ButtonsOverlay(ID+9), 365, 350);
                else getWorld().removeObject(this);
            }
        }
        if(ID>11) {
            String key = Greenfoot.getKey();
            if(key!=null && key!="enter" && key!="escape" && key!="control" && key!="shift" && key!="space" && key!="backspace" && (char) 127!=key.charAt(0)) {
                switch(ID) {
                   case 12: pw.p1Left=key; break;
                   case 13: pw.p1Right=key; break;
                   case 14: pw.p1Up=key; break;
                   case 15: pw.p1Down=key; break;
                   case 16: pw.p2Left=key; break;
                   case 17: pw.p2Right=key; break;
                   case 18: pw.p2Up=key; break;
                   case 19: pw.p2Down=key; break;
                   case 20: pw.pause=key; break;
                }
                getWorld().removeObject(this);
            }
        }
    }
    private void makeKeyImage(String type)
    {
        Pacman pw = (Pacman) getWorld(); GreenfootImage image = new GreenfootImage(77,77); Font font = image.getFont();
        image.setColor(new Color(0,0,0, 0)); image.fillRect(0, 0, 77, 77);
        font = font.deriveFont(30.0f); image.setFont(font); image.setColor(Color.BLACK);
        image.drawString(type,30,45);
        setImage(image);
    }
    private void makeImage(String text)
    {
        GreenfootImage image = new GreenfootImage(730, 700);
        image.setColor(new Color(0,0,0, 128));
        image.fillRect(0, 0, 730, 700);
        image.setColor(new Color(255, 255, 255, 200));
        image.fillRect(50, 100, 625, 425);
        image.setColor(new Color(0, 0, 0, 255));
        image.fillRect(75,125, 575, 375);
        Font font = image.getFont();
        font = font.deriveFont(72.0f);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString("Your current key", 100, 200);
        image.drawString(text, 360-(text.length()*15), 345);
        setImage(image);
    }
}