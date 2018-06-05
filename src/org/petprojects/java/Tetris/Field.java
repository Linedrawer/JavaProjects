package org.petprojects.java.Tetris;

import java.util.ArrayList;

/**
 *  Field class describes "cell field" of Tetris game
 */
public class Field
{
    private int width;
    private int height;

    //cell matrix: 1 - cell is taken, 0 - cell is free
    private int[][] matrix;

    Field(int width, int height)
    {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    /**
     * Method returns cell value represent by its coordinates (x,y)
     * If coordinates are out of matrix bound it returns null
     */
    public Integer getValue(int x, int y)
    {
        if (x >= 0 && x < width && y >= 0 && y < height)
            return matrix[y][x];

        return null;
    }

    /**
     *  Method sets transmitted value to cell of matrix by coordinates (x,y)
     */
    public void setValue(int x, int y, int value)
    {
        if (x >= 0 && x < width && y >= 0 && y < height)
            matrix[y][x] = value;
    }

    /**
     * Method prints current matrix content
     */
    public void print()
    {
        //Create array where current state of the game is held
        int[][] canvas = new int[height][width];

        //Copy field matrix to array
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                canvas[i][j] = matrix[i][j];
            }
        }

        //Copy figure to array, only free cells can be used
        int left = Tetris.game.getFigure().getX();
        int top = Tetris.game.getFigure().getY();
        int[][] brickMatrix = Tetris.game.getFigure().getMatrix();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (top + i >= height || left + j >= width) continue;
                if (brickMatrix[i][j] == 1)
                    canvas[top + i][left + j] = 2;
            }
        }


        //Print canvas starting from frame border
        System.out.println("---------------------------------------------------------------------------\n");

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                int index = canvas[i][j];
                if (index == 0)
                    System.out.print(" . ");
                else if (index == 1)
                    System.out.print(" X ");
                else if (index == 2)
                    System.out.print(" X ");
                else
                    System.out.print("???");
            }
            System.out.println();
        }


        System.out.println();
        System.out.println();
    }

    /**
     * Delete full lines
     */
    public void removeFullLines()
    {
        //List which contains lines
        ArrayList<int[]> lines = new ArrayList<>();

        //Copy all not-full lines to list
        for (int i = 0; i <height; i++)
        {
            //Calculate amount of 1s in a line - sum them up
            int count = 0;
            for (int j = 0; j < width; j++)
            {
                count += matrix[i][j];
            }

            //If sum is not equal to line width - add to the list
            if (count != width)
                lines.add(matrix[i]);
        }

        //Add missing lines to the start of the list
        while (lines.size()<height)
        {
            lines.add(0,new int[width]);
        }

        //Transform list back to matrix
        matrix = lines.toArray(new int[height][width]);
    }
}
