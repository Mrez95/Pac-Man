import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

public class Score extends Actor
{
    public Score() 
    {
    }
    public Score(int score)
    {
        makeImage(score);
    }
    private void makeImage(int score)
    {
            GreenfootImage image = new GreenfootImage(730, 700);
            image.setColor(new Color(255,255,255, 0));
            Font font = image.getFont();
            font = font.deriveFont(72.0f);
            image.setFont(font);
            image.setColor(Color.WHITE);
            image.drawString("" + score, 300, 440);
            setImage(image);
    }
}