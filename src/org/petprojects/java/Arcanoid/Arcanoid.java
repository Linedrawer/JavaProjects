package org.petprojects.java.Arcanoid;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Main game class
 */
public class Arcanoid
{
    private int width;
    private int height;

    private ArrayList<Brick> bricks = new ArrayList<>();
    private Ball ball;
    private Stand stand;

    private boolean isGameOver = false;

    private Arcanoid(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    private ArrayList<Brick> getBricks()
    {
        return bricks;
    }

    private void setBall(Ball ball)
    {
        this.ball = ball;
    }

    private void setStand(Stand stand)
    {
        this.stand = stand;
    }

    /**
     * Draw borders on canvas and all objects (bricks, ball, stand).
     */
    private void draw(Canvas canvas)
    {
        drawBoders(canvas);
        for (Brick current : bricks)
            current.draw(canvas);
        ball.draw(canvas);
        stand.draw(canvas);
    }

    /**
     * Draw borders method.
     */
    private void drawBoders(Canvas canvas)
    {
        //draw game
        for (int i = 0; i < width + 2; i++)
        {
            for (int j = 0; j < height + 2; j++)
            {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++)
        {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++)
        {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
    }

    /**
     *  Program main cycle.
     *  Key events of the game take place here
     */
    public void run() throws Exception
    {
        Canvas canvas = new Canvas(width, height);

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (!isGameOver)
        {
            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();

                //if 'left arrow' is pressed stand moves to the left
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();
                //if 'right arrow' is pressed stand moves to the right
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();
                // 'space bar' - launch ball
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ball.start();
            }

            //move all objects
            move();

            //check all bumpings
            checkBricksBump();
            checkStandBump();

            //check whether ball flew down through bottom
            checkEndGame();

            //re draw canvas
            canvas.clear();
            draw(canvas);
            canvas.print();

            //pause
            Thread.sleep(300);
        }

        System.out.println("Game Over!");
    }

    /**
     * Move ball and stand
     */
    public void move()
    {
        ball.move();
        stand.move();
    }

    /**
     * Check collision with bricks or borders
     * If collision occurred ball flies off to random direction from 0 to 360 degrees
     */
    private void checkBricksBump()
    {
        for (Brick currentBrick : bricks) {
            if (ball.isIntersect(currentBrick)) {
                double angel = Math.random() * 360;
                ball.setDirection(angel);
                bricks.remove(currentBrick);
                break;
            }
        }
    }

    /**
     * Check collision with stand
     * If collision occurred ball flies off to random direction from 80 to 100 degrees
     */
    private void checkStandBump()
    {
        if (ball.isIntersect(stand)) {
            double angel = 80 + Math.random()*20;
            ball.setDirection(angel);
        }
    }

    /**
     * Check whether ball has flown through bottom
     * If yes - game is over (isGameOver = true)
     */
    private void checkEndGame()
    {
        if (ball.getY() >= height)
            isGameOver = true;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public static Arcanoid game;

    public static void main(String[] args) throws Exception
    {
        game = new Arcanoid(20, 30);

        Ball ball = new Ball(10, 29, 2,  95);
        game.setBall(ball);

        Stand stand = new Stand(10, 30);
        game.setStand(stand);

        game.getBricks().add(new Brick(3, 3));
        game.getBricks().add(new Brick(7, 5));
        game.getBricks().add(new Brick(12, 5));
        game.getBricks().add(new Brick(16, 3));

        game.run();
    }
}



















