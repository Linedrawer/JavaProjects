package org.petprojects.java.SpaceWar;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Space main game class
 */
public class Space
{
    //canvas
    private int width;
    private int height;

    private SpaceShip ship;
    private ArrayList<Ufo> ufos = new ArrayList<Ufo>();
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    private ArrayList<Rocket> rockets = new ArrayList<Rocket>();

    public Space(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    /**
     *  Game main cycle where key game events take place
     */
    public void run()
    {
        Canvas canvas = new Canvas(width, height);

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (ship.isAlive())
        {
            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //If 'left arrow' key is pressed - move spaceship to the left
                System.out.print(event.getKeyCode());
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    ship.moveLeft();
                //If 'right arrow' key is pressed - move spaceship to the right
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    ship.moveRight();
                //If 'space bar' - launch rocket
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ship.fire();
            }

            //move all game objects
            moveAllItems();

            //check collisions
            checkBombs();
            checkRockets();
            //remove dead objects
            removeDead();

            //Create UFO (once in 10 steps)
            createUfo();

            //Refresh canvas
            canvas.clear();
            draw(canvas);
            canvas.print();

            //pause
            Space.sleep(300);
        }

        System.out.println("Game Over!");
    }

    /**
     * Move all game objects
     */
    private void moveAllItems()
    {
        for (BaseObject object : getAllItems())
        {
            object.move();
        }
    }

    /**
     * Method returns list of all game objects
     */
    private List<BaseObject> getAllItems()
    {
        ArrayList<BaseObject> list;
        list = new ArrayList<>(ufos);
        list.add(ship);
        list.addAll(bombs);
        list.addAll(rockets);
        return list;
    }

    /**
     * Create an UFO, once in 10 turns
     */
    private void createUfo()
    {
        if (ufos.size() > 0) return;

        int random10 = (int) (Math.random() * 10);
        if (random10 == 0)
        {
            double x = Math.random() * 20;
            double y = Math.random() * 10;
            ufos.add(new Ufo(x, y));
        }
    }

    /**
     * Check bombs:
     * a) collision with spaceship (bomb and spaceship die)
     * B) bomb falls out of canvas (bomb dies)
     */
    private void checkBombs()
    {
        for (Bomb bomb : bombs)
        {
            if (ship.isIntersect(bomb))
            {
                ship.die();
                bomb.die();
            }

            if (bomb.getY() >= height)
                bomb.die();
        }
    }

    /**
     * Check rockets:
     * a) collision with UFO (rocket and UFO die)
     * b) collision with top border of canvas (rocket dies)
     */
    private void checkRockets()
    {
        for (Rocket rocket : rockets)
        {
            for (Ufo ufo : ufos)
            {
                if (ufo.isIntersect(rocket))
                {
                    ufo.die();
                    rocket.die();
                }
            }

            if (rocket.getY() <= 0)
                rocket.die();
        }
    }

    /**
     * Delete all dead objects (bombs, rockets, UFO) from list
     */
    private void removeDead()
    {
        for (BaseObject object : new ArrayList<BaseObject>(bombs))
        {
            if (!object.isAlive())
                bombs.remove(object);
        }

        for (BaseObject object : new ArrayList<BaseObject>(rockets))
        {
            if (!object.isAlive())
                rockets.remove(object);
        }

        for (BaseObject object : new ArrayList<BaseObject>(ufos))
        {
            if (!object.isAlive())
                ufos.remove(object);
        }
    }

    /**
     * Draw all game objects on canvas:
     * a) fill whole canvas with dots
     * b) draw all game objects
     */
    public void draw(Canvas canvas)
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

        for (BaseObject object : getAllItems())
        {
            object.draw(canvas);
        }
    }


    private void setShip(SpaceShip ship)
    {
        this.ship = ship;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public ArrayList<Bomb> getBombs()
    {
        return bombs;
    }

    public ArrayList<Rocket> getRockets()
    {
        return rockets;
    }

    public static Space game;

    public static void main(String[] args) throws Exception
    {
        game = new Space(20, 20);
        game.setShip(new SpaceShip(10, 16));
        game.run();
    }

    /**
     * Method makes a pause with duration of delay variable
     */
    private static void sleep(int delay)
    {
        try
        {
            Thread.sleep(delay);
        }
        catch (InterruptedException ignored)
        {
        }
    }
}
