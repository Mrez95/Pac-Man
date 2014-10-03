import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Actor
{
    public Wall() 
    {
    }
    public void setImageRotation(int angle,String imageString)
    {
        GreenfootImage image = new GreenfootImage(imageString);
        image.rotate(angle);
        setImage(image);
    }
    public Wall(int map)
    {
        if(map==2) setImage("Wall1.png");
        if(map==3) setImageRotation(180,"Wall1.png");
        if(map==4) setImage("WallEdge.png");
        if(map==5) setImageRotation(90,"WallEdge.png");
        if(map==6) setImageRotation(90,"Wall1.png");
        if(map==7) setImageRotation(270,"Wall1.png");
        if(map==8) setImage("SWallEdge.png");
        if(map==9) setImageRotation(90,"SWallEdge.png");
        if(map==10) setImageRotation(180,"SWallEdge.png");
        if(map==11) setImageRotation(270,"SWallEdge.png");
        if(map==12) setImage("Corner.png");
        if(map==13) {
            setImage("Corner.png");
            setImageRotation(90,"Corner.png");
        }
        if(map==14) setImageRotation(180,"Corner.png");
        if(map==15) setImageRotation(270,"Corner.png");
        if(map==18) setImage("RCorner.png");
        if(map==19) setImageRotation(180,"RCorner.png");
        if(map==20) setImageRotation(90,"RCorner.png");
        if(map==21) setImageRotation(270,"RCorner.png");
        if(map==22) setImageRotation(0,"Gate.png");
    }
}