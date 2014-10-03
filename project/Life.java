import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Life here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Life extends Actor
{
    public void act() 
    {
        int x=getX(); int y=getY();
        if(x==715) setImage(new GreenfootImage("2 pacman2.PNG"));
        if(x==25 && y>=475) setRotation(180);
        if(x==25 && y==270) setImage(new GreenfootImage("P1Sign.png"));
        if(x==715 && y==270) {
            setImage(new GreenfootImage("P2Sign.png"));
        }
    }
}