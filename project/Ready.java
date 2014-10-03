import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Ready extends Actor
{
    private GreenfootSound begin = new GreenfootSound("ready-sound.mp3");
    private int counter=0;
    public void act() 
    {
        if(getWorld().getObjects(Menu.class).isEmpty()) count();
    }
    private void count()
    {
        begin.setVolume(60);
        if(counter==0) begin.play();
        Pacman pw = (Pacman) getWorld();
        if(pw.musicS) begin.setVolume(0);
        else begin.setVolume(60);
        if(counter==600) {
            counter=0; getWorld().removeObject(this);
        }
        else if(getWorld().getObjects(Menu.class).isEmpty()) counter=counter+1;
    }
}