package org.petprojects.java.Snake;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Main class.
 */
public class Room
{
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

    public Room(int width, int height, Snake snake)
    {
        this.width = width;
        this.height = height;
        this.snake = snake;
    }

    public Mouse getMouse()
    {
        return mouse;
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
     *  Main loop.
     *  Key events of the game take place here
     */
    public void run()
    {
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (snake.isAlive())
        {
            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //If 'q' is pressed - quit game
                if (event.getKeyChar() == 'q') return;

                //If 'left arrow' is pressed - move left
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);
                //If 'right arrow' is pressed - move right
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(SnakeDirection.RIGHT);
                //If 'up arrow' is pressed - move up
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(SnakeDirection.UP);
                //If 'down arrow' is pressed - move down
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(SnakeDirection.DOWN);
            }

            snake.move();   //move snake
            print();        //print game current status
            sleep();        //pause between steps
        }

        System.out.println("Game Over!");
    }

    /**
     * Выводим на экран текущее состояние игры
     */
    private void print()
    {
        //array to put current state of game inta
        int[][] matrix = new int[height][width];

        //draw snake
        ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>(snake.getSections());
        for (SnakeSection snakeSection : sections)
        {
            matrix[snakeSection.getY()][snakeSection.getX()] = 1;
        }

        //draw snake head (4 - if snake is dead)
        matrix[snake.getY()][snake.getX()] = snake.isAlive() ? 2 : 4;

        //draw mouse
        matrix[mouse.getY()][mouse.getX()] = 3;

        //print field, snake and mouse
        String[] symbols = {" . ", " x ", " X ", "^_^", "RIP"};
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                System.out.print(symbols[matrix[y][x]]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Method is called when mouse is eaten
     */
    public void eatMouse()
    {
        createMouse();
    }

    /**
     * Create a mouse in bounds of field
     */
    private void createMouse()
    {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);

        mouse = new Mouse(x, y);
    }


    public static Room game;

    public static void main(String[] args)
    {
        game = new Room(20, 20, new Snake(10, 10));
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
    }

    //Массив "пауз" в зависимости от уровня.
    private static int[] levelDelay = {1000, 600, 550, 500, 480, 460, 440, 420, 400, 380, 360, 340, 320, 300, 285, 270};

    /**
     * Program makes pause, its duration depends on snake length (level)
     */
    private void sleep()
    {
        try
        {
            int level = snake.getSections().size();
            int delay = level < 15 ? levelDelay[level] : 250;
            Thread.sleep(delay);
        }
        catch (InterruptedException ignored)
        {
        }
    }
}
