package org.petprojects.java.SpaceWar;

/**
 * Rocket class
 */
public class Rocket  extends BaseObject
{

    public Rocket(double x, double y)
    {
        super(x, y, 1);
    }

    /**
     * Draw rocket on canvas
     */
    @Override
    public void draw(Canvas canvas)
    {
        canvas.setPoint(x,y,'R');
    }

    /**
     * Move rocket by one step
     */
    @Override
    public void move()
    {
        y--;
    }
}
