package org.petprojects.java.SpaceWar;

/**
 * Bomb class
 */
public class Bomb extends BaseObject
{
    public Bomb(double x, double y)
    {
        super(x, y, 1);
    }

    /**
     * Draw bomb on canvas
     */
    @Override
    public void draw(Canvas canvas)
    {
        canvas.setPoint(x,y,'B');
    }

    /**
     * Move bomb by one step
     */
    @Override
    public void move()
    {
        y++;
    }
}