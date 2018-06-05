package org.petprojects.java.Tetris;
/**
 * Class FigureFactory is used to produce figures.
 */
public class FigureFactory
{
    /**
     * Describe all figures to be used in game
     */
    private static final int[][][] BRICKS = {{
            {1, 1, 0},                          //   X X
            {0, 1, 1},                          //     X X
            {0, 0, 0}}, {                       //
            {1, 0, 0},                          //   X
            {1, 1, 0},                          //   X X
            {0, 1, 0}}, {                       //     X
            {0, 1, 0},                          //   X
            {0, 1, 0},                          //   X
            {0, 0, 0}}, {                       //   X
            {1, 1, 0},                          //   X X
            {1, 1, 0},                          //   X X
            {0, 0, 0}}, {                       //
            {1, 1, 1},                          //   X X X
            {0, 1, 0},                          //     X
            {0, 0, 0}}, {                       //
            {1, 1, 1},                          //   X X X
            {1, 1, 1},                          //   X X X
            {0, 0, 0}}                          //
    };
    /**
     * Creates random figure from BRICKS
     */
    public static Figure createRandomFigure(int x,int y)
    {
        int index = (int) (Math.random() * 6);
        return new Figure(x, y, BRICKS[index]);
    }
}