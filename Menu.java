import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

public class Menu extends Actor
{
    public Menu() 
    {
    }
    public Menu(int ID)
    {
        switch(ID) {
            case 1: setImage(new GreenfootImage("help pacman.png"));
            break;
            case 2: setImage(new GreenfootImage("help ghost.png"));
            break;
            case 3: setImage(new GreenfootImage("help food.png"));
            break;
            case 4: setImage(new GreenfootImage("About page.png"));
            break;
            case 6: setImage(new GreenfootImage("pause menu.png"));
            break;
            case 7: makeImage("You Lost!", "   Your score is: ");
            break;
        }
    }
    private void makeImage(String title, String prefix)
    {
        GreenfootImage image = new GreenfootImage(730, 700);
        image.setColor(new Color(0,0,0, 128));
        image.fillRect(0, 0, 730, 700);
        Font font = image.getFont();
        font = font.deriveFont(72.0f);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 200, 200);
        image.drawString(prefix, 100, 300);
        setImage(image);
    }
}