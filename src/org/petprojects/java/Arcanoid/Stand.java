package org.petprojects.java.Arcanoid;

/**
 *  Stand class
 */
public class Stand extends BaseObject
{
    //Stand representation on canvas
    private static int[][] matrix = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    private double speed = 1;
    //direction (-1 to the left, +1 to the right)
    private double direction = 0;

    Stand(double x, double y)
    {
        super(x,y,3);
    }

    /**
     * Method moves stand according to direction value.
     */
    public void move()
    {
        double dx = speed * direction;
        x = x + dx;

        checkBorders(radius, Arcanoid.game.getWidth() - radius + 1, 1, Arcanoid.game.getHeight() + 1);
    }

    /**
     * direction is set to -1
     */
    public void moveLeft()
    {
        direction = -1;
    }

    /**
     * direction is set to +1
     */
    public void moveRight()
    {
        direction = 1;
    }

    /**
     * Draws a stand on the canvas
     */
    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'M');
    }
}
