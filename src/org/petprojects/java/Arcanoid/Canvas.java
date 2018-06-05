package org.petprojects.java.Arcanoid;

/**
 * Canvas class.
 */
public class Canvas
{
    private int width;
    private int height;
    //matrix to be drawn on, char is for colour.
    private char[][] matrix;

    public Canvas(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.matrix = new char[height + 2][width + 2];
    }

    /**
     * Clear canvas
     */
    public void clear()
    {
        this.matrix = new char[height + 2][width + 2];
    }

    /**
     * Print transmitted figure to corresponding coordinates with colour c.
     * If transmitted array contains 1s, canvas representation for it will be char c.
     */
    public void drawMatrix(double x, double y, int[][] matrix, char c)
    {
        int height = matrix.length;
        int width = matrix[0].length;

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (matrix[i][j] == 1)
                    setPoint(x + j, y + i, c);
            }
        }
    }

    /**
     * Put a dot on the canvas with coordinates (x,y) and colour - c.
     */
    public void setPoint(double x, double y, char c)
    {
        int x0 = (int) Math.round(x);
        int y0 = (int) Math.round(y);
        if (y0 < 0 || y0 >= matrix.length) return;
        if (x0 < 0 || x0 >= matrix[y0].length) return;

        matrix[y0][x0] = c;
    }

    /**
     * Print canvas content to screen.
     */
    public void print()
    {
        System.out.println();

        for (int i = 0; i < height + 2; i++)
        {
            for (int j = 0; j < width + 2; j++)
            {
                System.out.print(" ");
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }

            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

}
