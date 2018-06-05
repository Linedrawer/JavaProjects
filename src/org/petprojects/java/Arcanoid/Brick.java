package org.petprojects.java.Arcanoid;

/**
 * Brick object class.
 */
public class Brick extends BaseObject
{
    //brick presentation on canvas
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Brick(double x, double y)
    {
        super(x,y,3);
    }

    /**
     * Draw brick on canvas
     */
    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'H');
    }

    /**
     * Do nothing - brick does not move
     */
    @Override
    public void move()
    {
        //do nothing
    }
}
