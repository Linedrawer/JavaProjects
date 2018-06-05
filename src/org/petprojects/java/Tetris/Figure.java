package org.petprojects.java.Tetris;


/**
 * Figure class describe a figure of a tetris game
 */
public class Figure
{
    //Matrix which defines form of figure: 1 - filled cell, 0 - empty cell
    private int[][] matrix;
    //Coordinates
    private int x;
    private int y;

    Figure(int x, int y, int[][] matrix)
    {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int[][] getMatrix()
    {
        return matrix;
    }

    /**
     * Rotate figure around its main diagonal
     */
    public void rotate()
    {
        int[][] matrix2 = new int[3][3];

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                matrix2[i][j] = matrix[j][i];
            }
        }

        matrix = matrix2;
    }

    /**
     * Move figure to the left.
     * Check whether it goes out of field bounds and/or goes over taken cells
     */
    public void left()
    {
        x--;
        if (!isCurrentPositionAvailable())
            x++;
    }

    /**
     * Move figure to the right.
     * Check whether it goes out of field bounds and/or goes over taken cells
     */
    public void right()
    {
        x++;
        if (!isCurrentPositionAvailable())
            x--;
    }

    /**
     * Move figure up.
     * Is used when part of figure goes over taken cell.
     */
    public void up()
    {
        y--;
    }

    /**
     * Move figure down.
     */
    public void down()
    {
        y++;
    }

    /**
     * Move figure down until it goes over a taken cell
     */
    public void downMaximum()
    {
        while (isCurrentPositionAvailable())
        {
            y++;
        }

        y--;
    }

    /**
     * Check whether figure can be on a current position:
     * a) does not go out of field bounds
     * b) does not go over taken cells
     */
    public boolean isCurrentPositionAvailable()
    {
        Field field = Tetris.game.getField();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (matrix[i][j] == 1)
                {
                    if (y + i >= field.getHeight())
                        return false;

                    Integer value = field.getValue(x + j, y + i);
                    if (value == null || value == 1)
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * Land figure - add all it filled cells to field cells
     */
    public void landed()
    {
        Field field = Tetris.game.getField();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (matrix[i][j] == 1)
                    field.setValue(x + j, y + i, 1);
            }
        }
    }
}
