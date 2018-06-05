package org.petprojects.java.Arcanoid;

/**
 * Ball class
 */
public class Ball extends BaseObject
{
    private double speed;
    //direction from 0 to 360 degrees
    private double direction;

    //current value of vector of movement (dx, dy)
    private double dx;
    private double dy;

    //can object move
    private boolean isFrozen;

    Ball(double x, double y, double speed, double direction)
    {
        super(x, y, 1);

        this.direction = direction;
        this.speed = speed;

        this.isFrozen = true;
    }

    /**
     * Set a new direction.
     * Calculate new vector of movement
     * This is convenient to use in case of wall bumps
     */
    public void setDirection(double direction)
    {
        this.direction = direction;

        double angel = Math.toRadians(direction);
        dx = Math.cos(angel) * speed;
        dy = -Math.sin(angel) * speed;
    }

    /**
     * Draw ball on canvas.
     */
    @Override
    public void draw(Canvas canvas)
    {
        canvas.setPoint(x, y, 'O');
    }

    /**
     * Move ball.
     */
    public void move()
    {
        if (isFrozen) return;

        x += dx;
        y += dy;

        checkRebound(1, Arcanoid.game.getWidth(), 1, Arcanoid.game.getHeight() + 5);
    }

    /**
     * Check whether ball flew out of borders of the canvas.
     * If yes - reflect it.
     */
    private void checkRebound(int minx, int maxx, int miny, int maxy)
    {
        if (x < minx)
        {
            x = minx + (minx - x);
            dx = -dx;
        }

        if (x > maxx)
        {
            x = maxx - (x - maxx);
            dx = -dx;
        }

        if (y < miny)
        {
            y = miny + (miny - y);
            dy = -dy;
        }

        if (y > maxy)
        {
            y = maxy - (y - maxy);
            dy = -dy;
        }
    }

    /**
     * Launch the ball
     * isFrozen = false
     * Recalculate vector of movement (dx, dy).
     */
    public void start()
    {
        this.setDirection(direction);
        this.isFrozen = false;
    }
}
