package org.petprojects.java.Sokoban.model;

import java.awt.*;


public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        int x0 = getX() - (getWidth() / 2);
        int y0 = getY() - (getHeight() / 2);
        graphics.setColor(new Color(165, 42, 42));
        graphics.drawRect(x0, y0, getWidth(), getHeight());
        graphics.fillRect(x0, y0, getWidth(), getHeight());
    }
}
