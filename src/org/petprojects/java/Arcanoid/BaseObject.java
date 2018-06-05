package org.petprojects.java.Arcanoid;

/**
 * Base class for all object classes.
 */
public abstract class BaseObject
{
    protected double x;
    protected double y;
    //радиус объекта
    double radius;

    BaseObject(double x, double y, double radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
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
     * Method draws object on canvas.
     */
    public abstract void draw(Canvas canvas);

    /**
     * Move object for one move
     */
    public abstract void move();

    /**
     * Check whether (x,y) is out of bounds.
     */
    public void checkBorders(double minx, double maxx, double miny, double maxy)
    {
        if (x < minx) x = minx;
        if (x > maxx) x = maxx;
        if (y < miny) y = miny;
        if (y > maxy) y = maxy;
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
