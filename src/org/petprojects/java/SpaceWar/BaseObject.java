package org.petprojects.java.SpaceWar;

/**
 * Base class for all game objects
 */
public abstract class BaseObject
{
    protected double x;
    protected double y;
    double radius;
    private boolean isAlive;

    public BaseObject(double x, double y, double radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isAlive = true;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * Method draws object on canvas
     */
    public void draw(Canvas canvas)
    {
        //do nothing
    }

    /**
     * Method moves object by one step
     */
    public void move()
    {
        //do nothing
    }

    /**
     * Check whether object (x,y) is out of borders
     */
    public void checkBorders(double minx, double maxx, double miny, double maxy)
    {
        if (x < minx) x = minx;
        if (x > maxx) x = maxx;
        if (y < miny) y = miny;
        if (y > maxy) y = maxy;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void die()
    {
        isAlive = false;
    }

    /**
     * Check whether transmitted and our (this) object intersects.
     */
    public boolean isIntersect(BaseObject o)
    {
        double dx = x - o.x;
        double dy = y - o.y;
        double destination = Math.sqrt(dx * dx + dy * dy);
        double destination2 = Math.max(radius, o.radius);
        return destination <= destination2;
    }
}
