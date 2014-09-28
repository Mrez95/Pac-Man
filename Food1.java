import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Food1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food1 extends Food
{
    public void act()
    {
    }
    public boolean hasPlayer()
    {
        return (!getObjectsInRange(7,Player.class).isEmpty() || !getObjectsInRange(7,Player2.class).isEmpty());
    }
}
