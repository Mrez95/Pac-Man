import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fruit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fruit extends Actor
{
    private int time=0;
    public Fruit() 
    {
        setImage(new GreenfootImage("fruit " + Greenfoot.getRandomNumber(5) + ".png"));
    }
    public void act()
    {
        if(time==1300) getWorld().removeObject(this);
        else time=time+1;
    }
}